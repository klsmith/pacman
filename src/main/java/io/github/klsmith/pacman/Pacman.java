package io.github.klsmith.pacman;

import java.awt.Color;

import io.github.klsmith.game.Canvas;
import io.github.klsmith.game.Entity;

public class Pacman implements Entity {

	@Override
	public void update(double deltaMs) {

	}

	@Override
	public void draw(Canvas canvas) {
		canvas.arc()
				.atPoint(200, 200)
				.withBoundsInDegrees(335, 25)
				.withRadius(16)
				.withColor(Color.WHITE)
				.setFill()
				.render();
	}

}
