package com.towerdefense.towerdefense.entities;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.towerdefense.display.TowerShop;
import com.towerdefense.events.MouseHandler;
import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.entities.towers.Tower;

public class Workstation extends Tower implements CanBeRepaired {
	public static Rectangle SPRITE_RECTANGLE = new Rectangle(64, 0, 32, 32);
	public static int[] ACTION_ZONE = { 34, 34 };
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	private static Workstation workstation = null;

	public static Tower getWorkstation() {
		return workstation;
	}

	public Workstation(int x, int y) {
		super(x, y);
		workstation = this;
		setImage(GlobalVariables.getSprite().getSubimage(SPRITE_RECTANGLE.x,
				SPRITE_RECTANGLE.y, SPRITE_RECTANGLE.width * 2,
				SPRITE_RECTANGLE.height));
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setActionZone(ACTION_ZONE);

		MouseHandler.addEventObserver(new MouseHandler() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (getBounds().contains(e.getPoint())) {
					System.out.println(getBounds() + " " + e.getPoint());
					TowerShop.getTowerShop().setXY(getCenterPoint().x,
							getCenterPoint().y);
					TowerShop.getTowerShop().show(TowerShop.REPAIR, this);
				}
			}
		});
	}

	/*
	 * private int healthPoints;
	 * 
	 * public int getHealthPoints() { return healthPoints; }
	 */

	/*
	 * @see canBeRepaired#repair()
	 */
	@Override
	public void repair() {
	}

	/*
	 * public void setHealthPoints(int healthPoints) { this.healthPoints =
	 * healthPoints; }
	 */

}
