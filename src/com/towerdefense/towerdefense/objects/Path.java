package com.towerdefense.towerdefense.objects;

import com.towerdefense.towerdefense.GlobalVariables;
import java.awt.*;

public class Path extends Ground{
    public final static int GROUND_TYPE = 2;
    public final static Rectangle SPRITE_RECTANGLE = new Rectangle(0, 0, 32, 32);
    public final static boolean WALKABLE = true;

    public Path(int x, int y) {
        super(x, y, GROUND_TYPE);
        setWalkable(WALKABLE);

        this.setImage(GlobalVariables.getSprite().getSubimage(SPRITE_RECTANGLE.x, SPRITE_RECTANGLE.y, SPRITE_RECTANGLE.width, SPRITE_RECTANGLE.height));
    }
}
