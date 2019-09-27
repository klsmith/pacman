package io.github.klsmith.game;

import java.awt.Color;

public interface Canvas {

	int getWidth();

	int getHeight();

	void background(Color color);

	default ArcSpec arc() {
		return new ArcSpec(this);
	}

	void render(ArcSpec arc);

}
