package com.towerdefense.towerdefense.entities.towers;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.entities.EntityType;

public class VPNTower extends Tower {
	public final static int WIDTH = 23;
	public final static int HEIGHT = 32;
	public final static int RANGE = 32;
	public final static int DAMAGE = 50;
	public final static int COOLDOWN = 20;
	public final static int MAXHEALTH = 500;
	public final static int COST = 500;

	public static String TOWER_TYPE = "error";
	public static final int TOWER_IDENTIFIER = 3;

	public VPNTower(int x, int y) {
		super(x, y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setRangeValue(RANGE);
		setType(EntityType.Hack);
		setDamageValue(DAMAGE);
		setCooldown(COOLDOWN);
		setHealth(MAXHEALTH);
		setCost(COST);

		setImage(GlobalVariables.getSprite().getSubimage(417, 0, WIDTH, HEIGHT));
	}
}