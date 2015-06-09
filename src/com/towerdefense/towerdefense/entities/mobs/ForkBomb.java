package com.towerdefense.towerdefense.entities.mobs;

import com.towerdefense.towerdefense.GlobalVariables;

public class ForkBomb extends Mob {
	public final static int SPEED = 2;
	public final static int WIDTH = 32;
	public final static int HEIGHT = 32;
	public final static int DMG = 2;
	public final static int RANGE = 32;

	public static String MOB_TYPE = "error";
	public static final int MOB_IDENTIFIER = 2;

	public ForkBomb(int x, int y) {
		super(x, y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setMovementSpeed(SPEED);
		setUltimDamage(DMG);
		setRangeValue(RANGE);

		setImage(GlobalVariables.getSprite().getSubimage(244, 0, WIDTH, HEIGHT));
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	public void suicide() {

	}

	/*
	 * @Override public void move() { // TODO Auto-generated method stub
	 * 
	 * }
	 */

}
