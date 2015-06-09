package com.towerdefense.towerdefense.entities.towers;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.entities.EntityType;

public class FireWallTower extends Tower {
	public final static int WIDTH = 24;
	public final static int HEIGHT = 32;
	public final static int RANGE = 32;
	public final static int DAMAGE = 10;
	public final static int COOLDOWN = 20;
	public final static int COST = 500;
	public final static int MAXHEALTH = 500;

	public static String TOWER_TYPE = "error";
	public static final int TOWER_IDENTIFIER = 1;

	public FireWallTower(int x, int y) {
		super(x, y);
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		this.setRangeValue(RANGE);
		this.setType(EntityType.Viral);
		this.setDamageValue(DAMAGE);
		this.setCooldown(COOLDOWN);
		this.setHealth(MAXHEALTH);
		this.setCost(COST);

		this.setImage(GlobalVariables.getSprite().getSubimage(362, 0, WIDTH, HEIGHT));
	}
}