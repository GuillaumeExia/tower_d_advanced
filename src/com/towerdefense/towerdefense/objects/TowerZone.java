package com.towerdefense.towerdefense.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.towerdefense.display.TowerShop;
import com.towerdefense.events.MouseHandler;
import com.towerdefense.towerdefense.GlobalVariables;

public class TowerZone extends Ground {
	public final static int GROUND_TYPE = 5;
	public final static Rectangle SPRITE_RECTANGLE = new Rectangle(96, 0, 32, 32);
	public final static boolean WALKABLE = false;
	public boolean busy = false;
	public TowerZone self;

	public TowerZone(int x, int y) {
		super(x, y, GROUND_TYPE);
		this.self = this;
		this.setWalkable(WALKABLE);
		this.setImage(GlobalVariables.getSprite().getSubimage(SPRITE_RECTANGLE.x, SPRITE_RECTANGLE.y, SPRITE_RECTANGLE.width, SPRITE_RECTANGLE.height));

		MouseHandler.addEventObserver(new MouseHandler() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (TowerZone.this.getBounds().contains(e.getPoint())) {
					TowerShop.getTowerShop().setXY(TowerZone.this.getCenterPoint().x, TowerZone.this.getCenterPoint().y);
					TowerShop.getTowerShop().show(TowerShop.TOWER, TowerZone.this.self);
				}
			}
		});
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

}