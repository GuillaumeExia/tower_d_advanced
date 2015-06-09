package com.towerdefense.towerdefense.entities.mobs;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.towerdefense.towerdefense.entities.CanMove;
import com.towerdefense.towerdefense.entities.Entity;
import com.towerdefense.towerdefense.entities.towers.Tower;

public abstract class Mob extends Entity implements CanMove {

	public static int previousMobSpawnTime = 0;
	private static int mobCount = 0;
	private int movementSpeed;
	private int reward;
	private int ultimDamage;
	private int protection;
	private int spawnTime = 0;
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

	@Override
	public void attack(final ArrayList<Tower> towers) {
		getNearestTower(towerCollision(towers));

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(width, height, x, y);
	}

	public Rectangle getBoundsRange() {
		return new Rectangle(width + (getRangeValue() * 2), height
				+ (getRangeValue() * 2), x, y);
	}

	public Point getCenterPoint() {
		return new Point((width / 2) + x, (height / 2) + y);
	}

	public int getHeight() {
		return height;
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

	public Tower getNearestTower(final ArrayList<Tower> towers) {
		int ghostX = 0, ghostY = 0, minimum = -1;
		HashMap<Integer, Tower> distance = new HashMap<Integer, Tower>();
		for (Tower tower : towers) {
			ghostX = tower.getX();
			ghostY = tower.getY();
			if (ghostX < 0) {
				ghostX = -ghostX;
			}
			if (ghostY < 0) {
				ghostY = -ghostY;
			}
			distance.put(ghostX + ghostY, tower);
		}
		for (Entry<Integer, Tower> entry : distance.entrySet()) {
			if ((minimum == -1) || (minimum > entry.getKey())) {
				minimum = entry.getKey();
			}
		}
		return distance.get(minimum);
	}

	public int getProtection() {
		return protection;
	}

	public int getReward() {
		return reward;
	}

	public int getSpawnTime() {
		return spawnTime;
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

	@Override
	public void move(int dx, int dy) {
		lastX = x;
		lastY = y;

		x += movementSpeed * dx;
		y += movementSpeed * dy;
	}

	public void setHeight(int height) {
		this.height = height;
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

	public void setReward(int reward) {
		this.reward = reward;
	}

	public void setSpawnTime(int spawnTime) {
		this.spawnTime = previousMobSpawnTime + spawnTime;
		previousMobSpawnTime = this.spawnTime;
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

	public ArrayList<Tower> towerCollision(final ArrayList<Tower> towers) {
		ArrayList<Tower> result = new ArrayList<Tower>();
		for (Tower tower : towers) {
			if (tower.getBounds().intersects(getBoundsRange())) {
				result.add(tower);
			}
		}
		return result;
	}

}
