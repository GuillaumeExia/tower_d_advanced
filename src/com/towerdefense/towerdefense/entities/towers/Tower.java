package com.towerdefense.towerdefense.entities.towers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.entities.Entity;
import com.towerdefense.towerdefense.entities.mobs.Mob;

public abstract class Tower extends Entity {

	public final static int MAXHEALTH = 500;
	public static final int TOWER_SPRITE_HEIGHT = 64;
	public static final double DAMAGE_UPGRADE_RATIO = 1.3;
	public static final double COOLDOWN_UPGRADE_RATIO = 0.8;
	public static final double RANGE_UPGRADE_RATIO = 1.1;

	private int width;

	private int height;

	private Rectangle actionZone;

	private Image image;

	private int x;
	private int y;
	private int cost;
	private int upgradeLimit;
	private int upgrade = 0;

	public Tower(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void attack(ArrayList<?> mobs) {
		if (isMobCollision((ArrayList<Mob>) mobs)) {
			if (getCooldownCounter() >= getCooldown()) {
				upgrade();
				getNearestMob(mobCollision((ArrayList<Mob>) mobs)).dropHealth(
						mobs, getDamageValue());
				setCooldownCounter(0);
			}
		}
		setCooldownCounter(getCooldownCounter() + 1);
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

	public Rectangle getBoundsRange() {
		return new Rectangle(x - (width / 2), y - (height / 2), width
				+ (getRangeValue() * 2), height + (getRangeValue() * 2));
	}

	public Point getCenterPoint() {
		return new Point((width / 2) + x, (height / 2) + y);
	}

	public int getCost() {
		return cost;
	}

	public int getHeight() {
		return height;
	}

	public Mob getNearestMob(final ArrayList<Mob> mobs) {
		int ghostX = 0, ghostY = 0, minimum = -1;
		HashMap<Integer, Mob> distance = new HashMap<Integer, Mob>();
		for (Mob mob : mobs) {
			ghostX = mob.getX();
			ghostY = mob.getY();
			if (ghostX < 0) {
				ghostX = -ghostX;
			}
			if (ghostY < 0) {
				ghostY = -ghostY;
			}
			distance.put(ghostX + ghostY, mob);
		}
		for (Entry<Integer, Mob> entry : distance.entrySet()) {
			if ((minimum == -1) || (minimum > entry.getKey())) {
				minimum = entry.getKey();
			}
		}
		return distance.get(minimum);
	}

	public int getUpgrade() {
		return upgrade;
	}

	public int getUpgradeLimit() {
		return upgradeLimit;
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

	public boolean isMobCollision(final ArrayList<Mob> mobs) {
		boolean detection = false;
		for (Mob mob : mobs) {
			if (getBoundsRange().intersects(mob.getBounds())) {
				detection = true;
			}
		}
		return detection;
	}

	public ArrayList<Mob> mobCollision(final ArrayList<Mob> mobs) {
		boolean empty = true;
		ArrayList<Mob> result = new ArrayList<Mob>();
		for (Mob mob : mobs) {
			if (mob.getBounds().intersects(getBoundsRange())) {
				result.add(mob);
				empty = false;
			}
		}
		if (!empty) {
			return result;
		} else {
			return null;
		}
	}

	public boolean payForTower() {
		if (GlobalVariables.money >= cost) {
			GlobalVariables.money -= cost;
			return true;
		}
		return false;
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

	public void setUpgrade(int upgrade) {
		this.upgrade = upgrade;
	}

	public void setUpgradeLimit(int upgradeLimit) {
		this.upgradeLimit = upgradeLimit;
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

	public void upgrade() {
		if (getUpgrade() < getUpgradeLimit()) {
			setDamageValue((int) (getDamageValue() * DAMAGE_UPGRADE_RATIO));
			setCooldown((int) (getCooldown() * COOLDOWN_UPGRADE_RATIO));
			setRangeValue((int) (getRangeValue() * RANGE_UPGRADE_RATIO));
			setImage(GlobalVariables.getSprites().getSubimage(
					32 * (getIdentifier() - 1),
					Tower.TOWER_SPRITE_HEIGHT + (32 * (getUpgrade() + 1)),
					getWidth(), getHeight()));
			setUpgrade(getUpgrade() + 1);
		}

	}

}
