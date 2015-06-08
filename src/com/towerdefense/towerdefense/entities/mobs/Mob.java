package com.towerdefense.towerdefense.entities.mobs;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import com.towerdefense.towerdefense.entities.EntityType;

public abstract class Mob {

	public static int previousMobSpawnTime = 0;
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
		g.drawImage(this.image, this.x, this.y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(this.width, this.height, this.x, this.y);
	}

	public Point getCenterPoint() {
		return new Point((this.width / 2) + this.x, (this.height / 2) + this.y);
	}

	public int getDamageValue() {
		return this.damageValue;
	}

	public int getHealthPoints() {
		return this.healthPoints;
	}

	public int getHeight() {
		return this.height;
	}

	public Image getImage() {
		return this.image;
	}

	public int getLastX() {
		return this.lastX;
	}

	public int getLastY() {
		return this.lastY;
	}

	public int getMovementSpeed() {
		return this.movementSpeed;
	}

	public int getProtection() {
		return this.protection;
	}

	public int getRangeValue() {
		return this.rangeValue;
	}

	public int getReward() {
		return this.reward;
	}

	public int getSpawnTime() {
		return this.spawnTime;
	}

	public EntityType getType() {
		return this.type;
	}

	public int getUltimDamage() {
		return this.ultimDamage;
	}

	public int getWidth() {
		return this.width;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	// @Override
	public void move(int dx, int dy) {
		this.lastX = this.x;
		this.lastY = this.y;

		this.x += this.movementSpeed * dx;
		this.y += this.movementSpeed * dy;
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
