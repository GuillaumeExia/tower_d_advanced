package com.towerdefense.towerdefense.entities.towers;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.entities.EntityType;

public class PatcherTower extends Tower {
	public final static int WIDTH = 32;
	public final static int HEIGHT = 32;
	public final static int RANGE = 32;
	public final static int DAMAGE = 50;
	public final static int COOLDOWN = 20;
	public final static int MAXHEALTH = 500;
	public final static int COST = 500;
	public final static int UPGRADE_LIMIT = 3;

	public static String TOWER_TYPE = "error";
	public static final int TOWER_IDENTIFIER = 2;

	public PatcherTower(int x, int y) {
		super(x, y);
		init();
	}

	public PatcherTower(int x, int y, int health, int upgrade) {
		super(x, y);
		init();
		setUpgrade(upgrade - 1);
		upgrade();
		setHealth(health);
	}

	public void init() {
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setRangeValue(RANGE);
		setType(EntityType.Error);
		setDamageValue(DAMAGE);
		setCooldown(COOLDOWN);
		setHealth(MAXHEALTH);
		setCost(COST);
		setUpgradeLimit(UPGRADE_LIMIT);
		setIdentifier(TOWER_IDENTIFIER);
		setImage(GlobalVariables.getSprites().getSubimage(
				32 * (TOWER_IDENTIFIER - 1), Tower.TOWER_SPRITE_HEIGHT, WIDTH,
				HEIGHT));
	}
}