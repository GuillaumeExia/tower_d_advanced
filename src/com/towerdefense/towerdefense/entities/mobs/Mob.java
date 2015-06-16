package com.towerdefense.towerdefense.entities.mobs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.entities.CanMove;
import com.towerdefense.towerdefense.entities.Entity;
import com.towerdefense.towerdefense.entities.towers.Tower;

public abstract class Mob extends Entity implements CanMove {

	private static final int TYPE_MULTIPLIER = 2;
	public static final int MOB_SPRITE_HEIGHT = 32;

	public static int previousMobSpawnTime = 0;
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
	public static int killedMobs = 0;

	public Mob(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void attack(ArrayList<?> towers) {
		int finalDamageValue = this.getDamageValue();
		Tower damagedTower = null;
		if (this.isTowerCollision((ArrayList<Tower>) towers)) {
			if (this.getCooldownCounter() >= this.getCooldown()) {
				damagedTower = this.getNearestTower(this.towerCollision((ArrayList<Tower>) towers));
				if (damagedTower.getType().equals(this.getType())) {
					finalDamageValue *= TYPE_MULTIPLIER;
				}
				damagedTower.dropHealth(towers, finalDamageValue);
				this.setCooldownCounter(0);
			}
		}
		this.setCooldownCounter(this.getCooldownCounter() + 1);
	}

	@Override
	public void die(ArrayList<?> list) {
		GlobalVariables.money += this.reward;
		list.remove(this);
		killedMobs++;
	}

	public void draw(Graphics g) {
		g.drawImage(this.getImage(), this.x, this.y, null);
		g.setColor(Color.black);
		g.fillRect(this.x, this.y + 27, 27, 5);
		g.setColor(new Color(220, 28, 182));
		g.fillRect(this.x + 1, this.y + 28, (int) (this.getHealth() * 0.05), 3);
		g.setColor(Color.black);
	}

	@Override
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

	@Override
	public int getX() {
		return this.x;
	}

	@Override
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

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
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