package com.towerdefense.towerdefense;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.towerdefense.display.TowerShop;
import com.towerdefense.events.TowerShopListener;
import com.towerdefense.towerdefense.database.DBLink;
import com.towerdefense.towerdefense.entities.Workstation;
import com.towerdefense.towerdefense.entities.mobs.Mob;
import com.towerdefense.towerdefense.entities.mobs.MobFactory;
import com.towerdefense.towerdefense.entities.towers.Tower;
import com.towerdefense.towerdefense.entities.towers.TowerFactory;
import com.towerdefense.towerdefense.objects.Ground;

public class Map {
	private static Map selectedMap;

	public static Map getSelectedMap() {
		return selectedMap;
	}

	public static void setSelectedMap(Map selectedMap) {
		Map.selectedMap = selectedMap;
	}

	private int width;
	private int height;
	private String name;
	private int id;
	private ArrayList<Ground> grounds;
	private ArrayList<Tower> towers;
	private Tower workstation;
	private ArrayList<Mob> mobs;

	private int wave = 30;

	private int waveTime = 0;

	public Map(String name, int width, int height, int id) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.id = id;
	}

	public boolean detectCollision(Mob mob, int newPosX, int newPosY) {
		for (Ground currentGround : this.grounds) {
			Rectangle newPosMob = new Rectangle(newPosX, newPosY, mob.getWidth(), mob.getHeight());
			Rectangle ground = currentGround.getBounds();
			if (ground.intersects(newPosMob)) {
				// currentGround.debugColor = Color.green;
				// currentGround.debug = true;
				if (!currentGround.isWalkable()) {
					currentGround.debugColor = Color.red;
					return false;
				}
			}
		}
		return true;
	}

	public boolean detectWorkstationCollision(int i) {
		if (this.mobs.get(i).getBounds().intersects(this.workstation.getActionZone())) {
			this.mobs.remove(i);
			return true;
		}
		return false;
	}

	public void draw(Graphics g) {
		this.waveTime += 30;
		this.drawTerrain(g);
		this.drawWorkstation(g);
		this.drawMobs(g);
		this.drawTowers(g);
		this.testWaveEnding();
		TowerShop.getTowerShop().draw(g);
		g.drawString("Wave : " + this.wave, 730, 15);
	}

	public void drawMobs(Graphics g) {
		for (int i = 0; i < this.mobs.size(); i++) {
			if (!this.detectWorkstationCollision(i) && (this.waveTime > this.mobs.get(i).getSpawnTime())) {
				this.mobMove(this.mobs.get(i));
				this.mobs.get(i).draw(g);
			}
		}
	}

	public void drawTerrain(Graphics g) {
		for (Ground ground : this.grounds) {
			ground.draw(g);
		}
	}

	public void drawTowers(Graphics g) {
		for (Tower tower : this.towers) {
			tower.draw(g);
		}
	}

	public void drawWorkstation(Graphics g) {
		this.workstation = Workstation.getWorkstation();
		if (this.workstation != null) {
			this.workstation.draw(g);
		}
	}

	public void fetchTerrain() {
		if (this.grounds == null) {
			DBLink dbLink = new DBLink();
			this.grounds = dbLink.mapSelection(this.id);
		}
	}

	public int getHeight() {
		return this.height;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Mob getRandomMob() {
		Random random = new Random();
		return MobFactory.createMob(random.nextInt(MobFactory.MOB_TYPE_AMOUNT) + 1);
	}

	public int getRandomTime() {
		Random rand = new Random();

		return rand.nextInt(2500) + 500;
	}

	public int getWidth() {
		return this.width;
	}

	public void init() {
		this.fetchTerrain();
		this.mobs = new ArrayList();
		this.spawnMobs();

		this.towers = new ArrayList();
		TowerShop towerShop = new TowerShop();
		towerShop.addTowerShopListener(new TowerShopListener() {
			@Override
			public void onTowerAdd(int idTower, int x, int y) {
				System.out.println(idTower);
				Map.this.towers.add(TowerFactory.createTower(idTower, x, y));
			}
		});
	}

	public void mobMove(Mob mob) {
		int xMob = mob.getX();
		int yMob = mob.getY();
		int speed = mob.getMovementSpeed();

		if (this.detectCollision(mob, xMob + speed, yMob) && (mob.getLastX() != (xMob + speed))) {
			mob.move(1, 0);
		}
		else if (this.detectCollision(mob, xMob, yMob + speed) && (mob.getLastY() != (yMob + speed))) {
			mob.move(0, 1);
		}
		else if (this.detectCollision(mob, xMob - speed, yMob) && (mob.getLastX() != (xMob - speed))) {
			mob.move(-1, 0);
		}
		else if (this.detectCollision(mob, xMob, yMob - speed) && (mob.getLastY() != (yMob - speed))) {
			mob.move(0, -1);
		}
	}

	public void nextWave() {
		this.waveTime = 0;
		Mob.previousMobSpawnTime = 0;
		this.wave++;
		this.spawnMobs();
	}

	public boolean spawnMob(final int choice) {
		if (choice == -1) {
			Mob mob = this.getRandomMob();
			mob.setSpawnTime(this.getRandomTime());
			this.mobs.add(mob);
			return true;
		}
		else if ((choice <= MobFactory.MOB_TYPE_AMOUNT) && (choice >= 0)) {
			this.mobs.add(MobFactory.createMob(choice));
			return true;
		}
		else {
			return false;
		}
	}

	public void spawnMobs() {
		int mobAmount = 0;
		int amountAtStart = 3;
		double multiplier = 1.3;
		mobAmount = (int) (amountAtStart * Math.pow(multiplier, this.wave));
		for (int i = 0; i < mobAmount; i++) {
			this.spawnMob(-1);
		}
	}

	public void testWaveEnding() {
		if (this.mobs.size() == 0) {
			this.nextWave();
		}
	}

	/*
	 * public boolean spawnTower(final int choice) { if ((choice <=
	 * TowerFactory.TOWER_TYPE_AMOUNT) && (choice >= 0)) {
	 * towers.add(TowerFactory.createTower(choice)); return true; } else {
	 * return false; } }
	 */
}
