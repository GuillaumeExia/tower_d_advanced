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

	public static final int TOWER_SPRITE_HEIGHT = 64;
	public static final double DAMAGE_UPGRADE_RATIO = 1.3;
	public static final double COOLDOWN_UPGRADE_RATIO = 0.8;
	public static final double RANGE_UPGRADE_RATIO = 1.1;

	private int width;
	private int height;
	private int maxHealth;
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
				if (Tower.this.getBounds().contains(e.getPoint()) && alive) {
					TowerShop.getTowerShop().setXY(
							Tower.this.getCenterPoint().x,
							Tower.this.getCenterPoint().y
                    );
					TowerShop.getTowerShop().show(TowerShop.UPGRADE, Tower.this);
				}
			}
		});
	}

	@Override
	public void attack(ArrayList<?> mobs) {
		if (isMobCollision((ArrayList<Mob>) mobs)) {
			if (getCooldownCounter() >= getCooldown()) {
				getNearestMob(mobCollision((ArrayList<Mob>) mobs)).dropHealth(mobs, getDamageValue());
				setCooldownCounter(0);
			}
		}
		setCooldownCounter(getCooldownCounter() + 1);
	}

	@Override
	public void die(ArrayList<?> list) {
		super.die(list);
		if(linkedTowerZone != null) {
			linkedTowerZone.setBusy(false);
		}
		alive = false;
	}

	public void draw(Graphics g) {
		double ratio = 25 / maxHealth;
		g.drawImage(image, x, y, null);
		g.setColor(Color.black);
		g.fillRect(x, y + 26, 27, 5);
		g.setColor(new Color(126, 212, 249));
		g.fillRect(x + 3, y + 27, (int) (((float) getHealth() / (float) maxHealth) * 25), 3);
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

	public TowerZone getLinkedTowerZone() {
		return linkedTowerZone;
	}

	public int getMaxHealth() {
		return maxHealth;
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

	@Override
	public boolean isAlive() {
		return alive;
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
		actionZone = new Rectangle(getCenterPoint().x - (zone[0] / 2), getCenterPoint().y - (zone[1] / 2), zone[0], zone[1]);
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

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
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

	public void totalRepair() {
		setHealth(maxHealth);
	}

	public void upgrade() {
		int upgradePrice = (upgrade + 1) * cost;
		if ((getUpgrade() < getUpgradeLimit()) && (upgradePrice <= GlobalVariables.money)) {
			setDamageValue((int) (getDamageValue() * DAMAGE_UPGRADE_RATIO));
			setCooldown((int) (getCooldown() * COOLDOWN_UPGRADE_RATIO));
			setRangeValue((int) (getRangeValue() * RANGE_UPGRADE_RATIO));
			setImage(GlobalVariables.getSprites().getSubimage(
					32 * (getIdentifier() - 1),
					Tower.TOWER_SPRITE_HEIGHT + (32 * (getUpgrade() + 1)),
					getWidth(), getHeight())
			);
			GlobalVariables.dropMoney(upgradePrice);
			totalRepair();
			upgrade++;
		}
	}

}
