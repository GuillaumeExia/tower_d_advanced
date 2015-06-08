package com.towerdefense.towerdefense.entities.towers;

import com.towerdefense.towerdefense.GlobalVariables;

public class VPNTower extends Tower {
    public final static int WIDTH = 23;
    public final static int HEIGHT = 32;

    public static String TOWER_TYPE = "error";
    public static final int TOWER_IDENTIFIER = 3;

    public VPNTower(int x, int y) {
        super(x, y);
        setWidth(WIDTH);
        setHeight(HEIGHT);

        this.setImage(GlobalVariables.getSprite().getSubimage(417, 0, WIDTH, HEIGHT));
    }
}