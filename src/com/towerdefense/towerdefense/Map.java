package com.towerdefense.towerdefense;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.towerdefense.display.PanelMenu;
import com.towerdefense.display.TowerShop;
import com.towerdefense.events.TowerShopListener;
import com.towerdefense.towerdefense.database.DataBase;
import com.towerdefense.towerdefense.entities.Workstation;
import com.towerdefense.towerdefense.entities.mobs.Mob;
import com.towerdefense.towerdefense.entities.mobs.MobFactory;
import com.towerdefense.towerdefense.entities.towers.Tower;
import com.towerdefense.towerdefense.entities.towers.TowerFactory;
import com.towerdefense.towerdefense.objects.Ground;
import com.towerdefense.towerdefense.objects.TowerZone;

public class Map {
	private static Map selectedMap;

	private static int wave = 1;

	public static Map getSelectedMap() {
		return selectedMap;
	}

	public static int getWave() {
		return wave;
	}

	public static void setSelectedMap(Map selectedMap) {
		Map.selectedMap = selectedMap;
	}

	public static void setWave(int wave) {
		Map.wave = wave;
	}

	private int width;

	private int height;

	private String name;
	private int id;
	private ArrayList<Ground> grounds;
	private ArrayList<Tower> towers;
	private Tower workstation;

	private ArrayList<Mob> mobs;
	private ArrayList<Mob> mobToRemove = new ArrayList<Mob>();

	private ArrayList<Mob> mobToShoot = new ArrayList<Mob>();

	private int waveTime = 0;

	public Map(Save save) {
		DataBase database = new DataBase();
		wave = save.getWave();
		PanelMenu.stopwatch.setTimeIs(save.getTime());
		GlobalVariables.money = save.getMoney();
		this.id = save.getIdMap();
		GlobalVariables.life = save.getLife();
		// this.height = database.getMap(save.getIdMap()).height;
		// this.width = database.getMap(save.getIdMap()).width;
		this.height = 800;
		this.width = 600;
		Map.setSelectedMap(this);
		this.init();
	}

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
				// currentGround.debug = true
				if (!currentGround.isWalkable()) {
					currentGround.debugColor = Color.red;
					return false;
				}
			}
		}
		return true;
	}

	public boolean detectWorkstationCollision(Mob m) {
		if (m.getBounds().intersects(this.workstation.getActionZone())) {
			this.mobToRemove.add(m);
			GlobalVariables.life -= this.mobs.get(m.getIdentifier()).getDamageValue();

			if (GlobalVariables.life == 0) {

				// afficher game over
				System.out.println("Game over");

				// remplir la table score
				// System.out.println(GlobalVariables)

			}
		}
		return false;
	}

	public void draw(Graphics g) {
		this.waveTime += 30;
		this.drawTerrain(g);
		this.drawWorkstation(g);
		this.drawMobs(g);
		this.drawTowers(g);
		this.drawBullets(g);
		this.testWaveEnding();
		TowerShop.getTowerShop().draw(g);
	}

	public void drawBullets(Graphics g) {
		g.setColor(Color.red);
		for (Tower tower : this.towers) {
			for (Mob mob : this.mobs) {
				this.mobToShoot.add(mob);
				if ((tower.isMobCollision(this.mobToShoot) == true)) {

					g.drawLine((tower.getNearestMob(tower.mobCollision(this.mobs)).getX() + ((tower.getNearestMob(tower.mobCollision(this.mobs)).getWidth() / 2))),
							((tower.getNearestMob(tower.mobCollision(this.mobs)).getY() + (((tower.getNearestMob(tower.mobCollision(this.mobs)).getHeight() / 2))))), tower.getX()
							+ (tower.getWidth() / 2), tower.getY() + (tower.getHeight() / 2));
				}
				this.mobToShoot.remove(mob);
			}
		}
		g.setColor(Color.black);
	}

	public void drawMobs(Graphics g) {
		for (Mob mob : this.mobs) {
			if ((this.waveTime > mob.getSpawnTime()) && !this.detectWorkstationCollision(mob)) {
				this.mobMove(mob);
				mob.draw(g);
			}
		}

		for (Mob mob : this.mobToRemove) {
			this.mobs.remove(mob);
		}
		this.mobToRemove.clear();
	}

	public void drawTerrain(Graphics g) {
		for (Ground ground : this.grounds) {
			ground.draw(g);
		}
	}

	public void drawTowers(Graphics g) {
		for (Tower tower : this.towers) {
			tower.draw(g);
			tower.attack(this.mobs);
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
			DataBase database = new DataBase();
			this.grounds = database.loadTerrain(this.id);
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

	public ArrayList<Tower> getTowers() {
		return this.towers;
	}

	public int getWidth() {
		return this.width;
	}

	public void init() {
		this.fetchTerrain();
		this.mobs = new ArrayList<Mob>();
		this.spawnMobs();

		this.towers = new ArrayList<Tower>();
		TowerShop towerShop = new TowerShop();
		towerShop.addTowerShopListener(new TowerShopListener() {
			@Override
			public void onTowerAdd(int idTower, TowerZone towerZone) {
				try {
					Map.this.towers.add(TowerFactory.createTower(idTower, towerZone.getX(), towerZone.getY()));
					if (!Map.this.towers.get(Map.this.towers.size() - 1).payForTower()) {
						Map.this.towers.remove(Map.this.towers.size() - 1);
						System.out.println("Plus de sous maggle");
						// Ajouter un option pane
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
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
		mob.attack(this.towers);
	}

	public void nextWave() {
		this.waveTime = 0;
		Mob.previousMobSpawnTime = 0;
		setWave(getWave() + 1);
		this.spawnMobs();
	}

	public void setTowers(ArrayList<Tower> towers) {
		this.towers = towers;
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
		mobAmount = (int) (amountAtStart * Math.pow(multiplier, getWave()));
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
