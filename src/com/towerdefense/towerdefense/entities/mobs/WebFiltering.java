package com.towerdefense.towerdefense.entities.mobs;

import com.towerdefense.towerdefense.GlobalVariables;

public class WebFiltering extends Mob {
	public final static int SPEED = 1;
	public final static int WIDTH = 32;
	public final static int HEIGHT = 32;
	public final static int RANGE = 32;

	public static String MOB_TYPE = "error";
	public static final int MOB_IDENTIFIER = 5;

	public WebFiltering(int x, int y) {
		super(x, y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setMovementSpeed(SPEED);
		setRangeValue(RANGE);

		setImage(GlobalVariables.getSprite().getSubimage(177, 0, WIDTH, HEIGHT));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public void move() { // TODO Auto-generated method stub
	 * 
	 * }
	 */

}
