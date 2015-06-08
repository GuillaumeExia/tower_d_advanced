package com.towerdefense.towerdefense.entities.towers;

import com.towerdefense.towerdefense.GlobalVariables;

public class FireWallTower extends Tower {
    public final static int WIDTH = 24;
    public final static int HEIGHT = 32;

    public static String TOWER_TYPE = "error";
    public static final int TOWER_IDENTIFIER = 1;

    public FireWallTower(int x, int y) {
        super(x, y);
        setWidth(WIDTH);
        setHeight(HEIGHT);

        this.setImage(GlobalVariables.getSprite().getSubimage(362, 0, WIDTH, HEIGHT));
    }
}