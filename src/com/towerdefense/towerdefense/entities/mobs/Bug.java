package com.towerdefense.towerdefense.entities.mobs;

import com.towerdefense.towerdefense.GlobalVariables;

public class Bug extends Mob {
    public final static int SPEED = 4;
    public final static int WIDTH = 31;
    public final static int HEIGHT = 32;

    public static String MOB_TYPE = "error";
    public static final int MOB_IDENTIFIER = 1;

    public Bug(int x, int y) {
        super(x, y);
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setMovementSpeed(SPEED);

        this.setImage(GlobalVariables.getSprite().getSubimage(147, 0, WIDTH, HEIGHT));
    }
}
