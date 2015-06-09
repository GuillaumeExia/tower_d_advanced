package com.towerdefense.towerdefense.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Ground {
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
		g.drawImage(this.image, this.x, this.y, null);

		/*
		 * if (this.debug) { g.setColor(this.debugColor); g.fillRect(this.x,
		 * this.y, WIDTH, HEIGHT); g.setColor(Color.DARK_GRAY); }
		 */
	}

	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, WIDTH, HEIGHT);
	}

	public Point getCenterPoint() {
		return new Point((WIDTH / 2) + this.x, (HEIGHT / 2) + this.y);
	}

	public Image getImage() {
		return this.image;
	}

	public int getType() {
		return this.type;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean isWalkable() {
		return this.walkable;
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
