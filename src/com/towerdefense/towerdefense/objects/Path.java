package com.towerdefense.towerdefense.objects;

import java.awt.Rectangle;

import com.towerdefense.towerdefense.GlobalVariables;

public class Path extends Ground {
	public final static int GROUND_TYPE = 2;
	public final static Rectangle SPRITE_RECTANGLE = new Rectangle(
			32 * (GROUND_TYPE - 1), Ground.GROUND_SPRITE_HEIGHT, 32, 32);
	public final static boolean WALKABLE = true;

	public Path(int x, int y) {
		super(x, y, GROUND_TYPE);
		setWalkable(WALKABLE);

		setImage(GlobalVariables.getSprites().getSubimage(SPRITE_RECTANGLE.x,
				SPRITE_RECTANGLE.y, SPRITE_RECTANGLE.width,
				SPRITE_RECTANGLE.height));
	}
}
