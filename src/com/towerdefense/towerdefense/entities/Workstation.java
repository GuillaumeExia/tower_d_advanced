package com.towerdefense.towerdefense.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.towerdefense.display.TowerShop;
import com.towerdefense.events.MouseHandler;
import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.entities.towers.Tower;

public class Workstation extends Tower implements CanBeRepaired {
	public static final int SPRITE_Y_LEVEL = 7 * 32;
	public static Rectangle SPRITE_RECTANGLE = new Rectangle(0, SPRITE_Y_LEVEL, 96, 96);
	public static int[] ACTION_ZONE = { 98, 94 };
	public static int WIDTH = 32;
	public static int HEIGHT = 32;
	private final int MAX_LIFE = GlobalVariables.life;
	private static Workstation workstation = null;

	public static Tower getWorkstation() {
		return workstation;
	}

	private int repair = 50;

	public Workstation(int x, int y) {
		super(x, y);
		workstation = this;
		setImage(GlobalVariables.getSprites().getSubimage(
				SPRITE_RECTANGLE.x,
				SPRITE_RECTANGLE.y, 
				SPRITE_RECTANGLE.width * 2,
				SPRITE_RECTANGLE.height)
		);
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setActionZone(ACTION_ZONE);

		MouseHandler.addEventObserver(new MouseHandler() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Workstation.this.getBounds().contains(e.getPoint())) {
					TowerShop.getTowerShop().setXY(
							Workstation.this.getCenterPoint().x,
							Workstation.this.getCenterPoint().y
					);
					TowerShop.getTowerShop().show(TowerShop.REPAIR, Workstation.this);
				}
			}
		});
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(
				GlobalVariables.getSprites().getSubimage(
						SPRITE_RECTANGLE.x,
						SPRITE_RECTANGLE.y, 
						SPRITE_RECTANGLE.width,
						SPRITE_RECTANGLE.height), 
					getX(), 
					getY(), 
					null
		);
		drawLifeBar(g);
	}
	
	public void drawLifeBar(Graphics g){
        int barHeight = 5;
        int barWidth = 55;
        int fakeWidth = 3*getWidth();
        int fakeHeight = 3*getHeight();
        int currentLife = barWidth * GlobalVariables.life / MAX_LIFE - 2;

        g.setColor(Color.black);
        g.fillRect(getX() + (fakeWidth / 2) - (barWidth / 2), getY() + fakeHeight, barWidth, barHeight);
        g.setColor(Color.red);
        g.fillRect(getX() + 1 +  (fakeWidth / 2)  - (barWidth / 2), getY() + fakeHeight + 1, currentLife, barHeight - 2);
    }

	public static void setWorkstation(Workstation workstation) {
		Workstation.workstation = workstation;
	}
	
	@Override
	public void repair() {
		int price = 5000;
		if ((GlobalVariables.life <= 950) && (GlobalVariables.money >= price)) {
			GlobalVariables.dropMoney(price);
			GlobalVariables.life += repair;
		}

	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x - 1, y + 2, ACTION_ZONE[0], ACTION_ZONE[1]);
	}

    @Override
    public Point getCenterPoint() {
        return new Point((WIDTH * 3 / 2) + x, (HEIGHT * 3 / 2) + y);
    }
	
	/*
	 * private int healthPoints;
	 * 
	 * public int getHealthPoints() { return healthPoints; }
	 */

	/*
	 * @see canBeRepaired#repair()
	 */

	/*
	 * public void setHealthPoints(int healthPoints) { this.healthPoints =
	 * healthPoints; }
	 */
}
