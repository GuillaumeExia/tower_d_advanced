package com.towerdefense.towerdefense.entities.mobs;

import com.towerdefense.towerdefense.GlobalVariables;

public class Virus extends Mob {
	public final static int SPEED = 1;
	public final static int WIDTH = 31;
	public final static int HEIGHT = 32;

	public static String MOB_TYPE = "error";
	public static final int MOB_IDENTIFIER = 4;

	public Virus(int x, int y) {
		super(x, y);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setMovementSpeed(SPEED);

		this.setImage(GlobalVariables.getSprite().getSubimage(178, 0, WIDTH, HEIGHT));
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	/*@Override
	public void move() {
		// TODO Auto-generated method stub

	}*/

}
