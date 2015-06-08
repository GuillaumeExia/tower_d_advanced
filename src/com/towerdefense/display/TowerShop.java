package com.towerdefense.display;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.towerdefense.events.MouseHandler;
import com.towerdefense.events.TowerShopListener;
import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.objects.Ground;
import com.towerdefense.towerdefense.objects.TowerZone;

public class TowerShop {

	public static final int TOWER = 1;
	public static final int UPGRADE = 2;
	public static final int REPAIR = 3;

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
	private Object objectCaller;
	private ArrayList<TowerShopListener> towerShopListeners = new ArrayList<TowerShopListener>();
	private ArrayList<Rectangle> items = new ArrayList<Rectangle>();

	private BufferedImage[] towerImages = { GlobalVariables.getSprite().getSubimage(362, 0, 24, 32), GlobalVariables.getSprite().getSubimage(390, 0, 24, 32),
			GlobalVariables.getSprite().getSubimage(417, 0, 23, 32), GlobalVariables.getSprite().getSubimage(417, 0, 23, 32) };

	private BufferedImage[] towerTools = { GlobalVariables.getSprite().getSubimage(305, 3, 27, 27), GlobalVariables.getSprite().getSubimage(333, 3, 26, 26) };

	private BufferedImage[] workstationTools = { GlobalVariables.getSprite().getSubimage(275, 3, 27, 27) };

	public TowerShop() {
		towerShop = this;
		this.x = 0;
		this.y = 0;
		this.rayon = this.WIDTH / 2;

		MouseHandler.addEventObserver(new MouseHandler() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < TowerShop.this.items.size(); i++) {
					if (TowerShop.this.items.get(i).contains(e.getPoint())) {
						if (TowerShop.this.mode == TOWER) {
							Ground towerZone = (TowerZone) TowerShop.this.objectCaller; // Revoir
							TowerShop.this.fireTowerAdd(i + 1, towerZone.getX(), towerZone.getY());
						}
						TowerShop.this.items.clear();
					}

				}
			}
		});
	}

	public void addTowerShopListener(TowerShopListener towerShopListener) {
		this.towerShopListeners.add(towerShopListener);
	}

	public void draw(Graphics g) {
		if (this.display) {
			this.items.clear();
			g.drawOval(this.x, this.y, this.WIDTH, this.HEIGHT);

			if (this.mode == TOWER) {
				int angle = 360 / this.towerImages.length;
				for (int i = 0, j = 0; j < this.towerImages.length; i += angle, j++) {
					int xc = (int) (this.centreX + (this.rayon * Math.cos((Math.PI * i) / 180)));
					int yc = (int) (this.centreY + (this.rayon * Math.sin((Math.PI * i) / 180)));
					this.items
							.add(new Rectangle(xc - (this.towerImages[j].getWidth() / 2), yc - (this.towerImages[j].getHeight() / 2), this.towerImages[j].getWidth(), this.towerImages[j].getHeight()));
					g.drawImage(this.towerImages[j], xc - (this.towerImages[j].getWidth() / 2), yc - (this.towerImages[j].getHeight() / 2), null);
				}
			}
			else if (this.mode == UPGRADE) {
				int angle = 360 / this.towerTools.length;
				for (int i = 0, j = 0; j < this.towerTools.length; i += angle, j++) {
					int xc = (int) (this.centreX + (this.rayon * Math.cos((Math.PI * i) / 180)));
					int yc = (int) (this.centreY + (this.rayon * Math.sin((Math.PI * i) / 180)));
					this.items.add(new Rectangle(xc - (this.towerTools[j].getWidth() / 2), yc - (this.towerTools[j].getHeight() / 2), this.towerTools[j].getWidth(), this.towerTools[j].getHeight()));
					g.drawImage(this.towerTools[j], xc - (this.towerTools[j].getWidth() / 2), yc - (this.towerTools[j].getHeight() / 2), null);
				}
			}
			else if (this.mode == REPAIR) {
				g.drawImage(this.workstationTools[0], this.x - (this.workstationTools[0].getWidth() / 2), (this.y + (this.WIDTH / 2)) - (this.workstationTools[0].getHeight() / 2), null);
				this.items.add(new Rectangle(this.x - (this.towerTools[0].getWidth() / 2), (this.y + (this.WIDTH / 2)) - (this.workstationTools[0].getHeight() / 2), this.towerTools[0].getWidth(),
						this.towerTools[0].getHeight()));
			}
		}
	}

	public void fireTowerAdd(int idTower, int x, int y) {
		for (TowerShopListener shopListener : this.towerShopListeners) {
			shopListener.onTowerAdd(idTower, x, y);
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void hide() {
		this.display = false;
	}

	public boolean isDisplay() {
		return this.display;
	}

	public void setXY(int x, int y) {
		this.x = x - (this.WIDTH / 2);
		this.y = y - (this.HEIGHT / 2);
		this.centreX = x;
		this.centreY = y;
	}

	public void show(int mode, Object caller) {
		this.objectCaller = caller;
		this.mode = mode;
		this.display = true;
	}
}
