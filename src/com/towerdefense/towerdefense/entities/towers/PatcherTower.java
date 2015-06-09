package com.towerdefense.towerdefense.entities.towers;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.entities.EntityType;

public class PatcherTower extends Tower {
	public final static int WIDTH = 24;
	public final static int HEIGHT = 32;
	public final static int RANGE = 32;
	public final static int DAMAGE = 10;
	public final static int COOLDOWN = 20;
	public final static int HEALTH = 500;
	public final static int COST = 500;

	public static String TOWER_TYPE = "error";
	public static final int TOWER_IDENTIFIER = 2;

	public PatcherTower(int x, int y) {
		super(x, y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setRangeValue(RANGE);
		setType(EntityType.Error);
		setDamageValue(DAMAGE);
		setCooldown(COOLDOWN);
		setHealth(HEALTH);
		setCost(COST);

		setImage(GlobalVariables.getSprite().getSubimage(390, 0, WIDTH, HEIGHT));
	}
}