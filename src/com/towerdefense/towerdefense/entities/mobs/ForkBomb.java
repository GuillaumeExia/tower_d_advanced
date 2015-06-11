package com.towerdefense.towerdefense.entities.mobs;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.entities.EntityType;

public class ForkBomb extends Mob {
	public final static int SPEED = 2;
	public final static int WIDTH = 32;
	public final static int HEIGHT = 32;
	public final static int DMG = 2;
	public final static int RANGE = 32;
	public final static int DAMAGE = 10;
	public final static int COOLDOWN = 20;
	public final static int HEALTH = 500;
	public final static int REWARD = 500;
	public final static double PROTECTION = 0.9;

	public static String MOB_TYPE = "error";
	public static final int MOB_IDENTIFIER = 2;

	public ForkBomb(int x, int y) {
		super(x, y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setMovementSpeed(SPEED);
		setRangeValue(RANGE);
		setType(EntityType.Heat);
		setDamageValue(DAMAGE);
		setCooldown(COOLDOWN);
		setHealth(HEALTH);
		setReward(REWARD);
		setProtection(PROTECTION);

		setImage(GlobalVariables.getSprites()
				.getSubimage(32 * (MOB_IDENTIFIER - 1), Mob.MOB_SPRITE_HEIGHT,
						WIDTH, HEIGHT));
	}

	public void suicide() {

	}

}
