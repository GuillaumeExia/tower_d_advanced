package com.towerdefense.towerdefense.entities.towers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.towerdefense.display.TowerShop;
import com.towerdefense.events.MouseHandler;
import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.entities.Entity;
import com.towerdefense.towerdefense.entities.mobs.Mob;
import com.towerdefense.towerdefense.objects.TowerZone;

public abstract class Tower extends Entity {

	public final static int MAXHEALTH = 500;
	public static final int TOWER_SPRITE_HEIGHT = 64;
	public static final double DAMAGE_UPGRADE_RATIO = 1.3;
	public static final double COOLDOWN_UPGRADE_RATIO = 0.8;
	public static final double RANGE_UPGRADE_RATIO = 1.1;

	private int width;

	private int height;

	private Rectangle actionZone;

	private TowerZone linkedTowerZone;

	private boolean alive = true;

	private Image image;

	protected int x;
	protected int y;
	private int cost;
	private int upgradeLimit;
	private int upgrade = 0;

	public Tower(int x, int y) {
		this.x = x;
		this.y = y;

		MouseHandler.addEventObserver(new MouseHandler() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Tower.this.getBounds().contains(e.getPoint()) && Tower.this.alive) {
					TowerShop.getTowerShop().setXY(Tower.this.getCenterPoint().x, Tower.this.getCenterPoint().y);
					TowerShop.getTowerShop().show(TowerShop.UPGRADE, Tower.this);
				}
			}
		});
	}

	@Override
	public void attack(ArrayList<?> mobs) {
		if (this.isMobCollision((ArrayList<Mob>) mobs)) {
			if (this.getCooldownCounter() >= this.getCooldown()) {
				this.getNearestMob(this.mobCollision((ArrayList<Mob>) mobs)).dropHealth(mobs, this.getDamageValue());
				this.setCooldownCounter(0);
			}
		}
		this.setCooldownCounter(this.getCooldownCounter() + 1);
	}

	@Override
	public void die(ArrayList<?> list) {
		super.die(list);
		this.linkedTowerZone.setBusy(false);
		this.alive = false;
	}

	public void draw(Graphics g) {
		double ratio = 25 / MAXHEALTH;
		g.drawImage(this.image, this.x, this.y, null);
		g.setColor(Color.black);
		g.fillRect(this.x, this.y + 26, 27, 5);
		g.setColor(new Color(126, 212, 249));
		g.fillRect(this.x + 1, this.y + 27, (int) (this.getHealth() * 0.05), 3);

	}

	public Rectangle getActionZone() {
		return this.actionZone;
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

	public int getCost() {
		return this.cost;
	}

	public int getHeight() {
		return this.height;
	}

	public TowerZone getLinkedTowerZone() {
		return this.linkedTowerZone;
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
		return this.upgrade;
	}

	public int getUpgradeLimit() {
		return this.upgradeLimit;
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

	@Override
	public boolean isAlive() {
		return this.alive;
	}

	public boolean isMobCollision(final ArrayList<Mob> mobs) {
		boolean detection = false;
		for (Mob mob : mobs) {
			if (this.getBoundsRange().intersects(mob.getBounds())) {
				detection = true;
			}
		}
		return detection;
	}

	public ArrayList<Mob> mobCollision(final ArrayList<Mob> mobs) {
		boolean empty = true;
		ArrayList<Mob> result = new ArrayList<Mob>();
		for (Mob mob : mobs) {
			if (mob.getBounds().intersects(this.getBoundsRange())) {
				result.add(mob);
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

	public boolean payForTower() {
		if (GlobalVariables.money >= this.cost) {
			GlobalVariables.money -= this.cost;
			return true;
		}
		return false;
	}

	public void setActionZone(int[] zone) {
		this.actionZone = new Rectangle(this.getCenterPoint().x - (zone[0] / 2), this.getCenterPoint().y - (zone[1] / 2), zone[0], zone[1]);
	}

	@Override
	public void setAlive(boolean alive) {
		this.alive = alive;
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

	public void setLinkedTowerZone(TowerZone linkedTowerZone) {
		this.linkedTowerZone = linkedTowerZone;
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
		if (this.getUpgrade() < this.getUpgradeLimit()) {
			this.setDamageValue((int) (this.getDamageValue() * DAMAGE_UPGRADE_RATIO));
			this.setCooldown((int) (this.getCooldown() * COOLDOWN_UPGRADE_RATIO));
			this.setRangeValue((int) (this.getRangeValue() * RANGE_UPGRADE_RATIO));
			this.setImage(GlobalVariables.getSprites().getSubimage(32 * (this.getIdentifier() - 1), Tower.TOWER_SPRITE_HEIGHT + (32 * (this.getUpgrade() + 1)), this.getWidth(), this.getHeight()));
			this.setUpgrade(this.getUpgrade() + 1);
		}

	}

}
