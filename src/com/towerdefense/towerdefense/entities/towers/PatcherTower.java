package com.towerdefense.towerdefense.entities.towers;

import com.towerdefense.towerdefense.GlobalVariables;

public class PatcherTower extends Tower {
    public final static int WIDTH = 24;
    public final static int HEIGHT = 32;

    public static String TOWER_TYPE = "error";
    public static final int TOWER_IDENTIFIER = 2;

    public PatcherTower(int x, int y) {
        super(x, y);
        setWidth(WIDTH);
        setHeight(HEIGHT);

        this.setImage(GlobalVariables.getSprite().getSubimage(390, 0, WIDTH, HEIGHT));
    }
}