package com.towerdefense.towerdefense.entities.towers;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.entities.EntityType;

public class FireWallTower extends Tower {
	public final static int WIDTH = 32;
	public final static int HEIGHT = 32;
	public final static int RANGE = 32;
	public final static int DAMAGE = 50;
	public final static int COOLDOWN = 20;
	public final static int COST = 500;
	public final static int MAXHEALTH = 500;
	public final static int UPGRADE_LIMIT = 3;

	public static String TOWER_TYPE = "error";
	public static final int TOWER_IDENTIFIER = 1;

	public FireWallTower(int x, int y) {
		super(x, y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setRangeValue(RANGE);
		setType(EntityType.Viral);
		setDamageValue(DAMAGE);
		setCooldown(COOLDOWN);
		setHealth(MAXHEALTH);
		setCost(COST);
		setUpgradeLimit(UPGRADE_LIMIT);
		setIdentifier(TOWER_IDENTIFIER);

		setImage(GlobalVariables.getSprite().getSubimage(362, 0, WIDTH, HEIGHT));
	}

}