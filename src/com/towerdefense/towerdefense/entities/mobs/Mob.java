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

	public Mob(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void attack(ArrayList<?> towers) {
		int finalDamageValue = getDamageValue();
		Tower damagedTower = null;
		if (isTowerCollision((ArrayList<Tower>) towers)) {
			if (getCooldownCounter() >= getCooldown()) {
				damagedTower = getNearestTower(towerCollision((ArrayList<Tower>) towers));
				if (damagedTower.getType().equals(getType())) {
					finalDamageValue *= TYPE_MULTIPLIER;
				}
				damagedTower.dropHealth(towers, finalDamageValue);
				setCooldownCounter(0);
			}
		}
		setCooldownCounter(getCooldownCounter() + 1);
	}

	@Override
	public void die(ArrayList<?> list) {
		GlobalVariables.money += reward;
		list.remove(this);
	}

	public void draw(Graphics g) {
		g.drawImage(getImage(), x, y, null);
		g.setColor(Color.black);
		g.fillRect(x, y + 27, 27, 5);
		g.setColor(Color.green);
		g.fillRect(x + 1, y + 27, (int) (getHealth() * 0.05), 3);
		g.setColor(Color.black);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public Rectangle getBoundsRange() {
		return new Rectangle(x - (width / 2), y - (height / 2), width
				+ (getRangeValue() * 2), height + (getRangeValue() * 2));
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

	public double getProtection() {
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

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	public boolean isTowerCollision(final ArrayList<Tower> towers) {
		boolean detection = false;
		for (Tower tower : towers) {
			if (getBoundsRange().intersects(tower.getBounds())) {
				detection = true;
			}
		}
		return detection;
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
			if (tower.getBounds().intersects(getBoundsRange())) {
				result.add(tower);
				empty = false;
			}
		}
		if (!empty) {
			return result;
		} else {
			return null;
		}
	}

}