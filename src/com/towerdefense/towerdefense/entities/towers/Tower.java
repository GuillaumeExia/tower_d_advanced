package com.towerdefense.towerdefense.entities.towers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.towerdefense.towerdefense.entities.Entity;

public abstract class Tower extends Entity {

	public final static int MAXHEALTH = 500;
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
	public void attack(ArrayList<?> mobs) {

	}

	@Override
	public void die(ArrayList<?> list) {
		list.remove(this);
	}

	public void draw(Graphics g) {
		double ratio = 25 / MAXHEALTH;
		g.drawImage(image, x, y, null);
		g.setColor(Color.black);
		g.fillRect(x, y + 26, 27, 5);
		g.setColor(Color.green);
		g.fillRect(x + 1, y + 26, (int) (getHealth() * 0.05), 3);
		g.setColor(Color.black);
	}

	public Rectangle getActionZone() {
		return actionZone;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public Point getCenterPoint() {
		return new Point((width / 2) + x, (height / 2) + y);
	}

	public int getCost() {
		return cost;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
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

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

}
