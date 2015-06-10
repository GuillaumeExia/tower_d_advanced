package com.towerdefense.towerdefense.entities.mobs;

import java.awt.Color;
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
	private double protection;
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
	public void attack(ArrayList<Tower> towers) {
		if (this.isTowerCollision(towers)) {
			this.getNearestTower(this.towerCollision(towers)).dropHealth(towers, this.getDamageValue());
		}
	}

	@Override
	public void die(ArrayList<?> list) {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics g) {
		g.drawImage(this.getImage(), this.x, this.y, null);
		g.setColor(Color.black);
		g.fillRect(this.x, this.y + 26, 27, 5);
		g.setColor(Color.green);
		g.fillRect(this.x + 1, this.y + 26, (int) (this.getHealth() * 0.05), 3);
		g.setColor(Color.black);
	}

	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	public Rectangle getBoundsRange() {
		return new Rectangle(this.x - (this.width / 2), this.y - (this.height / 2), this.width + (this.getRangeValue() * 2), this.height + (this.getRangeValue() * 2));
	}

	public Point getCenterPoint() {
		return new Point((this.width / 2) + this.x, (this.height / 2) + this.y);
	}

	public int getHeight() {
		return this.height;
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

	public double getProtection() {
		return this.protection;
	}

	public int getReward() {
		return this.reward;
	}

	public int getSpawnTime() {
		return this.spawnTime;
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

	public boolean isTowerCollision(final ArrayList<Tower> towers) {
		boolean detection = false;
		for (Tower tower : towers) {
			if (this.getBoundsRange().intersects(tower.getBounds())) {
				detection = true;
			}
		}
		return detection;
	}

	@Override
	public void move(int dx, int dy) {
		this.lastX = this.x;
		this.lastY = this.y;

		this.x += this.movementSpeed * dx;
		this.y += this.movementSpeed * dy;
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

	public void setProtection(double protection) {
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
		boolean empty = true;
		ArrayList<Tower> result = new ArrayList<Tower>();
		for (Tower tower : towers) {
			if (tower.getBounds().intersects(this.getBoundsRange())) {
				result.add(tower);
				empty = false;
			}
		}
		if (!empty) {
			return result;
		}
		else {
			return null;
		}
	}

}
