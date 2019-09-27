package io.github.klsmith.game;

import java.awt.Color;
import java.awt.Point;

public final class ArcSpec {

	private final Canvas canvas;
	private Point point = null;
	private double radius = 0;
	private double openRadians = 0;
	private double closeRadians = 0;
	private Color color = null;
	private boolean fill = false;

	public ArcSpec(Canvas canvas) {
		this.canvas = canvas;
	}

	public ArcSpec atPoint(int x, int y) {
		point = new Point(x, y);
		return this;
	}

	public Point getPoint() {
		return point;
	}

	public double getRadius() {
		return radius;
	}

	public double getOpenRadians() {
		return openRadians;
	}

	public double getCloseRadians() {
		return closeRadians;
	}

	public Color getColor() {
		return color;
	}

	public boolean isFill() {
		return fill;
	}

	/**
	 * Defines angle boundaries where opening on one angle, continuing clockwise,
	 * then closing on the other angle.
	 */
	public ArcSpec withBoundsInDegrees(double openDegrees, double closeDegrees) {
		openRadians = Math.toRadians(openDegrees);
		closeRadians = Math.toRadians(closeDegrees);
		return this;
	}

	public ArcSpec withRadius(double radius) {
		this.radius = radius;
		return this;
	}

	public ArcSpec withColor(Color color) {
		this.color = color;
		return this;
	}

	public ArcSpec setFill() {
		fill = true;
		return this;
	}

	public ArcSpec setOutlineOnly() {
		fill = false;
		return this;
	}

	public void render() {
		canvas.render(this);
	}

}
