package io.github.klsmith.game;

public interface Game {

	String getTitle();

	boolean isRunning();

	int getWidth();

	int getHeight();

	void update(double deltaMs);

	void render(Canvas canvas);

}
