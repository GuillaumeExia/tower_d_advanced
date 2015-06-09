package com.towerdefense.towerdefense.entities.mobs;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import com.towerdefense.towerdefense.entities.EntityType;

public abstract class Mob {

	public static int previousMobSpawnTime = 0;
	private static int mobCount = 0;
	private int healthPoints;
	private int damageValue;
	private int movementSpeed;
	private int rangeValue;
	private int reward;
	private int ultimDamage;

	private EntityType type;

	private int protection;
	private int spawnTime = 0;

	private Image image;

	private int x;
	private int y;

	private int width;

	private int height;
	private int lastX = 0;
	private int lastY = 0;

	public Mob(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// @Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	// @Override
	public void die() {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(width, height, x, y);
	}

	public Point getCenterPoint() {
		return new Point((width / 2) + x, (height / 2) + y);
	}

	public int getDamageValue() {
		return damageValue;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public int getHeight() {
		return height;
	}

	public Image getImage() {
		return image;
	}

	public int getLastX() {
		return lastX;
	}

	public int getLastY() {
		return lastY;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public int getProtection() {
		return protection;
	}

	public int getRangeValue() {
		return rangeValue;
	}

	public int getReward() {
		return reward;
	}

	public int getSpawnTime() {
		return spawnTime;
	}

	public EntityType getType() {
		return type;
	}

	public int getUltimDamage() {
		return ultimDamage;
	}

	public int getWidth() {
		return width;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// @Override
	public void move(int dx, int dy) {
		lastX = x;
		lastY = y;

		x += movementSpeed * dx;
		y += movementSpeed * dy;
	}

	public void setDamageValue(int damageValue) {
		this.damageValue = damageValue;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setLastX(int lastX) {
		this.lastX = lastX;
	}

	public void setLastY(int lastY) {
		this.lastY = lastY;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

	public void setRangeValue(int rangeValue) {
		this.rangeValue = rangeValue;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public void setSpawnTime(int spawnTime) {
		this.spawnTime = previousMobSpawnTime + spawnTime;
		previousMobSpawnTime = this.spawnTime;
	}

	public void setType(EntityType type) {
		this.type = type;
	}

	public void setUltimDamage(int ultimDamage) {
		this.ultimDamage = ultimDamage;
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
