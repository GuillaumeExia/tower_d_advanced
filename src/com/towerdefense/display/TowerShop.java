package com.towerdefense.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.towerdefense.events.MouseHandler;
import com.towerdefense.events.TowerShopListener;
import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.Map;
import com.towerdefense.towerdefense.entities.Workstation;
import com.towerdefense.towerdefense.entities.towers.FireWallTower;
import com.towerdefense.towerdefense.entities.towers.PatcherTower;
import com.towerdefense.towerdefense.entities.towers.Tower;
import com.towerdefense.towerdefense.entities.towers.VPNTower;
import com.towerdefense.towerdefense.entities.towers.WaterCoolerTower;
import com.towerdefense.towerdefense.objects.TowerZone;

public class TowerShop {

	public static final int TOWER = 1;
	public static final int UPGRADE = 2;
	public static final int REPAIR = 3;
	public static boolean OPEN = false;

	private static TowerShop towerShop;

	public static TowerShop getTowerShop() {
		return towerShop;
	}

	private boolean display = false;
	private final int WIDTH = 100;
	private final int HEIGHT = 100;
	private int x;
	private int y;
	private int centreX;
	private int centreY;
	private int rayon;
	private int mode;
	private int busy = 0;
	private Object objectCaller;
	private ArrayList<TowerShopListener> towerShopListeners = new ArrayList<TowerShopListener>();
	private ArrayList<Rectangle> items = new ArrayList<Rectangle>();
	private int[] towerPrice = {FireWallTower.COST, PatcherTower.COST, VPNTower.COST, WaterCoolerTower.COST};

	private BufferedImage[] towerImages = {
			GlobalVariables.getSprites().getSubimage(0, 343, 46, 40),
            GlobalVariables.getSprites().getSubimage(49, 343, 46, 40),
            GlobalVariables.getSprites().getSubimage(100, 343, 46, 40),
            GlobalVariables.getSprites().getSubimage(149, 343, 46, 40)
    };

	private BufferedImage[] towerTools = {
			GlobalVariables.getSprites().getSubimage(300, 343, 37, 32),
			GlobalVariables.getSprites().getSubimage(250, 343, 37, 32)
	};

	private BufferedImage[] workstationTools = {
            GlobalVariables.getSprites().getSubimage(200, 343, 46, 40)
    };

	public TowerShop() {
		towerShop = this;
		x = 0;
		y = 0;
		rayon = WIDTH / 2;

		MouseHandler.addEventObserver(new MouseHandler() {

			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < items.size(); i++) {
					if (items.get(i).contains(e.getPoint())) {
						if ((mode == TOWER)) {
							TowerZone towerZone = (TowerZone) objectCaller;
							TowerShop.this.fireTowerAdd(i + 1, towerZone);

						} else if (mode == UPGRADE) {
							if(i == 0){
								Tower tower = (Tower) objectCaller;
								tower.upgrade();
							} else if(i == 1){
								Tower tower = (Tower) objectCaller;
								Map.getSelectedMap().removeTower(tower);
							}
						} else if ((mode == REPAIR) && (i == 0)) {
							Workstation workstation = (Workstation) objectCaller;
							workstation.repair();
						}
						items.clear();
					}
				}
			}
		});
	}

	public void addTowerShopListener(TowerShopListener towerShopListener) {
		towerShopListeners.add(towerShopListener);
	}

	public void draw(Graphics g) {
        if (display) {
			items.clear();
			g.setColor(new Color(126, 212, 249));
			g.drawOval(x, y, WIDTH, HEIGHT);

			if (mode == TOWER) {
				int angle = 360 / towerImages.length;
				for (int i = 0, j = 0; j < towerImages.length; i += angle, j++) {
					int xc = (int) (centreX + (rayon * Math.cos((Math.PI * i) / 180)));
					int yc = (int) (centreY + (rayon * Math.sin((Math.PI * i) / 180)));
					items.add(new Rectangle(
                            xc - (towerImages[j].getWidth() / 2),
                            yc - (towerImages[j].getHeight() / 2),
                            towerImages[j].getWidth(),
                            towerImages[j].getHeight()
                    ));
					g.drawImage(
                            towerImages[j],
                            xc - (towerImages[j].getWidth() / 2),
                            yc - (towerImages[j].getHeight() / 2),
                            null
                    );

                    Font font = new Font("Arial", Font.PLAIN, 11);
                    int xTextArea = xc - (towerImages[j].getWidth() / 2) + 27 - (g.getFontMetrics(font).stringWidth("" + towerPrice[j]) / 2);
                    g.setFont(font);
                    g.setColor(Color.white);
                    g.drawString(towerPrice[j] + "", xTextArea, yc + 17);
                    g.setColor(Color.black);
				}
			} else if (mode == UPGRADE) {
				int angle = 360 / towerTools.length;
				for (int i = 0, j = 0; j < towerTools.length; i += angle, j++) {
					int xc = (int) (centreX + (rayon * Math.cos((Math.PI * i) / 180)));
					int yc = (int) (centreY + (rayon * Math.sin((Math.PI * i) / 180)));
					items.add(new Rectangle(
                            xc - (towerTools[j].getWidth() / 2),
                            yc - (towerTools[j].getHeight() / 2),
							towerTools[j].getWidth(), towerTools[j].getHeight())
                    );
					g.drawImage(
                            towerTools[j], xc - (towerTools[j].getWidth() / 2),
                            yc - (towerTools[j].getHeight() / 2), null
                    );

                    if(j == 0) {
                        Tower currentTower = (Tower) objectCaller;

                        Font font = new Font("Arial", Font.PLAIN, 10);
                        int xTextArea = xc - (towerTools[j].getWidth() / 2) + 21 - (g.getFontMetrics(font).stringWidth(currentTower.getCost() * (currentTower.getUpgrade() + 1) + "") / 2);
                        g.setFont(font);
                        g.setColor(Color.white);
                        g.drawString(currentTower.getCost() * (currentTower.getUpgrade() + 1) + "", xTextArea, yc + 14);
                        g.setColor(Color.black);
                    }
				}
			} else if (mode == REPAIR) {
                g.drawImage(
                        workstationTools[0],
                        x - (workstationTools[0].getWidth() / 2),
                        (y + (WIDTH / 2)) - (workstationTools[0].getHeight() / 2),
                        null
                );
                items.add(new Rectangle(
                        x - (towerTools[0].getWidth() / 2),
                        (y + (WIDTH / 2)) - (workstationTools[0].getHeight() / 2),
                        towerTools[0].getWidth(),
                        towerTools[0].getHeight())
                );
			}
		}
	}

	public void fireTowerAdd(int idTower, TowerZone towerZone) {
		for (TowerShopListener shopListener : towerShopListeners) {
			shopListener.onTowerAdd(idTower, towerZone);
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void hide() {
		display = false;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setXY(int x, int y) {
		this.x = x - (WIDTH / 2);
		this.y = y - (HEIGHT / 2);
		centreX = x;
		centreY = y;
	}

	public void show(int mode, Object caller) {
		objectCaller = caller;
		this.mode = mode;
		display = true;
	}
}
