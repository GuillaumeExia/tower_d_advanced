package com.towerdefense.towerdefense.entities.towers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Tower {

	private int width;
	private int height;
	private Rectangle actionZone;
	private Image image;
	private int x;
	private int y;
	private int cost;

	public Tower(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}

	public Rectangle getActionZone() {
		return actionZone;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public Point getCenterPoint() {
		return new Point((width / 2) + x, (height / 2) + y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setActionZone(int[] zone) {
		actionZone = new Rectangle(getCenterPoint().x - (zone[0] / 2),
				getCenterPoint().y - (zone[1] / 2), zone[0], zone[1]);
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
