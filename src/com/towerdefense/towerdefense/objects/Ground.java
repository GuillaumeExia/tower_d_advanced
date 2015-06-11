package com.towerdefense.towerdefense.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Ground {
	public static final int GROUND_SPRITE_HEIGHT = 0;
	private static int WIDTH = 32;
	private static int HEIGHT = 32;
	private Image image;
	private int x;
	private int y;
	private int type;
	private boolean walkable;
	public boolean debug = false;
	public Color debugColor = Color.green;

	public Ground(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);

		/*
		 * if (this.debug) { g.setColor(this.debugColor); g.fillRect(this.x,
		 * this.y, WIDTH, HEIGHT); g.setColor(Color.DARK_GRAY); }
		 */
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public Point getCenterPoint() {
		return new Point((WIDTH / 2) + x, (HEIGHT / 2) + y);
	}

	public Image getImage() {
		return image;
	}

	public int getType() {
		return type;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

}
