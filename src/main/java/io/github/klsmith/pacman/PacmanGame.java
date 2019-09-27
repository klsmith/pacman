package io.github.klsmith.pacman;

import java.awt.Color;

import io.github.klsmith.game.Canvas;
import io.github.klsmith.game.Game;
import io.github.klsmith.game.swing.SwingRenderingEngine;

public class PacmanGame implements Game {

	private static final String TITLE = "Pacman";

	private boolean running;
	private int width;
	private int height;

	private Pacman pacman;

	public PacmanGame() {
		this.running = true;
		this.width = 640;
		this.height = 480;
		pacman = new Pacman();
	}

	public static void main(String[] args) {
		SwingRenderingEngine.run(new PacmanGame());
	}

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void update(double deltaMs) {

	}

	@Override
	public void render(Canvas canvas) {
		canvas.background(Color.BLACK);
		pacman.draw(canvas);
	}

}
