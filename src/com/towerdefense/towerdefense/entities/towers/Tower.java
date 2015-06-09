package com.towerdefense.towerdefense.entities.towers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.towerdefense.towerdefense.entities.Entity;

public abstract class Tower extends Entity {

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

	@Override
	public void attack(ArrayList<Tower> towers) {

	}

	@Override
	public void die(ArrayList<?> list) {
		list.remove(this);
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

	public int getCost() {
		return cost;
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

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
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
