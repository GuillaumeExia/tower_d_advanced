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
	public void attack(ArrayList<Tower> towers) {

	}

	@Override
	public void die(ArrayList<?> list) {
		list.remove(this);
	}

	public void draw(Graphics g) {
		g.drawImage(this.image, this.x, this.y, null);
		g.setColor(Color.black);
		g.fillRect(this.x + 1, this.y + 26, 27, 5);
		g.setColor(Color.green);
		g.fillRect(this.x + 2, this.y + 26, (int) ((this.getHealth() / 500) * 25), 3);
		g.setColor(Color.black);
	}

	public Rectangle getActionZone() {
		return this.actionZone;
	}

	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	public Point getCenterPoint() {
		return new Point((this.width / 2) + this.x, (this.height / 2) + this.y);
	}

	public int getCost() {
		return this.cost;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setActionZone(int[] zone) {
		this.actionZone = new Rectangle(this.getCenterPoint().x - (zone[0] / 2), this.getCenterPoint().y - (zone[1] / 2), zone[0], zone[1]);
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
