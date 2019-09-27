package io.github.klsmith.game.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import io.github.klsmith.game.ArcSpec;
import io.github.klsmith.game.Canvas;
import io.github.klsmith.game.Game;
import io.github.klsmith.game.RenderingEngine;

public class SwingRenderingEngine extends JPanel implements RenderingEngine {

	private final Game game;

	public SwingRenderingEngine(Game game) {
		this.game = game;
		setPreferredSize(new Dimension(game.getWidth(), game.getHeight()));
	}

	public static void run(Game game) {
		SwingUtilities.invokeLater(() -> startGameWindow(game));
	}

	private static void startGameWindow(Game game) {
		final JFrame frame = new JFrame(game.getTitle());
		final SwingRenderingEngine engine = new SwingRenderingEngine(game);
		frame.add(engine);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		engine.grabFocus();
		final Thread gameLoop = new Thread() {

			@Override
			public void run() {
				while (game.isRunning()) {
					game.update(10);
					engine.paintImmediately(0, 0, engine.getWidth(), engine.getHeight());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};
		gameLoop.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		final Canvas canvas = new SwingCanvas(this, (Graphics2D) g);
		game.render(canvas);
	}

	private static class SwingCanvas implements Canvas {

		private SwingRenderingEngine engine;
		private Graphics2D graphics;

		private SwingCanvas(SwingRenderingEngine engine, Graphics2D graphics) {
			this.engine = engine;
			this.graphics = graphics;
		}

		@Override
		public int getWidth() {
			return engine.getWidth();
		}

		@Override
		public int getHeight() {
			return engine.getHeight();
		}

		private void tempColor(Color color, Runnable runnable) {
			final Color temp = graphics.getColor();
			graphics.setColor(color);
			runnable.run();
			graphics.setColor(temp);
		}

		@Override
		public void background(Color color) {
			tempColor(color, () -> {
				graphics.fillRect(0, 0, getWidth(), getHeight());
			});
		}

		@Override
		public void render(ArcSpec arc) {
			final int topLeftX = (int) (arc.getPoint().getX() - arc.getRadius());
			final int topLeftY = (int) (arc.getPoint().getY() - arc.getRadius());
			final int width = (int) (arc.getRadius() * 2);
			final int height = width;
			final int startDegrees = (int) Math.toDegrees(arc.getOpenRadians());
			final int diffDegrees = (int) Math.toDegrees(arc.getCloseRadians() - arc.getOpenRadians());
			tempColor(arc.getColor(), () -> {
				if (arc.isFill()) {
					graphics.fillArc(topLeftX, topLeftY,
							width, height,
							startDegrees,
							diffDegrees);
				} else {
					graphics.drawArc(
							topLeftX, topLeftY,
							width, height,
							startDegrees,
							diffDegrees);
				}
			});
		}

	}

}
