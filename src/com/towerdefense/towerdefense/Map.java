package com.towerdefense.towerdefense;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.towerdefense.display.GameOver;
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
		id = save.getIdMap();
		GlobalVariables.life = save.getLife();
		// this.height = database.getMap(save.getIdMap()).height;
		// this.width = database.getMap(save.getIdMap()).width;
		height = 800;
		width = 600;
		Map.setSelectedMap(this);
		init();
		towers = save.getTowers();
		restoreBusy();
	}

	public Map(String name, int width, int height, int id) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.id = id;
	}

	public boolean detectCollision(Mob mob, int newPosX, int newPosY) {
		for (Ground currentGround : grounds) {
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
		if (m.getBounds().intersects(workstation.getActionZone())) {
			mobToRemove.add(m);
			GlobalVariables.life -= mobs.get(m.getIdentifier()).getDamageValue();

			if (GlobalVariables.life == 0) {
				new GameOver();
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		waveTime += 30;
		drawTerrain(g);
		drawWorkstation(g);
		drawMobs(g);
		drawTowers(g);
		drawBullets(g);
		testWaveEnding();
		TowerShop.getTowerShop().draw(g);
	}

	public void drawBullets(Graphics g) {
		g.setColor(Color.red);
		for (Tower tower : towers) {
			for (Mob mob : mobs) {
				mobToShoot.add(mob);
				if ((tower.isMobCollision(mobToShoot) == true)) {

					g.drawLine(
							(tower.getNearestMob(tower.mobCollision(mobs))
									.getX() + ((tower.getNearestMob(
											tower.mobCollision(mobs)).getWidth() / 2))),
											((tower.getNearestMob(tower.mobCollision(mobs))
													.getY() + (((tower.getNearestMob(
															tower.mobCollision(mobs)).getHeight() / 2))))),
															tower.getX() + (tower.getWidth() / 2), tower.getY()
															+ (tower.getHeight() / 2));
				}
				mobToShoot.remove(mob);
			}
		}
		g.setColor(Color.black);
	}

	public void drawMobs(Graphics g) {
		for (Mob mob : mobs) {
			if ((waveTime > mob.getSpawnTime())
					&& !detectWorkstationCollision(mob)) {
				mobMove(mob);
				mob.draw(g);
			}
		}

		for (Mob mob : mobToRemove) {
			mobs.remove(mob);
		}
		mobToRemove.clear();
	}

	public void drawTerrain(Graphics g) {
		for (Ground ground : grounds) {
			ground.draw(g);
		}
	}

	public void drawTowers(Graphics g) {
		for (Tower tower : towers) {
			tower.draw(g);
			tower.attack(mobs);
		}
	}

	public void drawWorkstation(Graphics g) {
		workstation = Workstation.getWorkstation();
		if (workstation != null) {
			workstation.draw(g);
		}
	}

	public void fetchTerrain() {
		if (grounds == null) {
			DataBase database = new DataBase();
			grounds = database.loadTerrain(id);
		}
	}

	public ArrayList<Ground> getGrounds() {
		return grounds;
	}

	public int getHeight() {
		return height;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Mob getRandomMob() {
		Random random = new Random();
		return MobFactory
				.createMob(random.nextInt(MobFactory.MOB_TYPE_AMOUNT) + 1);
	}

	public int getRandomTime() {
		Random rand = new Random();

		return rand.nextInt(2500) + 500;
	}

	public ArrayList<Tower> getTowers() {
		return towers;
	}

	public int getWidth() {
		return width;
	}

	public void init() {
		fetchTerrain();
		mobs = new ArrayList<Mob>();
		spawnMobs();

		towers = new ArrayList<Tower>();
		TowerShop towerShop = new TowerShop();
		towerShop.addTowerShopListener(new TowerShopListener() {
			@Override
			public void onTowerAdd(int idTower, TowerZone towerZone) {
				try {
					Tower t = TowerFactory.createTower(idTower,
							towerZone.getX(), towerZone.getY());
					t.setLinkedTowerZone(towerZone);
					towers.add(t);
					towerZone.setBusy(true);
					if (!towers.get(towers.size() - 1).payForTower()) {
						towers.remove(towers.size() - 1);
						System.out.println("Plus de sous maggle");
						// Ajouter un option pane
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	public void mobMove(Mob mob) {
		int xMob = mob.getX();
		int yMob = mob.getY();
		int speed = mob.getMovementSpeed();

		if (detectCollision(mob, xMob + speed, yMob)
				&& (mob.getLastX() != (xMob + speed))) {
			mob.move(1, 0);
		} else if (detectCollision(mob, xMob, yMob + speed)
				&& (mob.getLastY() != (yMob + speed))) {
			mob.move(0, 1);
		} else if (detectCollision(mob, xMob - speed, yMob)
				&& (mob.getLastX() != (xMob - speed))) {
			mob.move(-1, 0);
		} else if (detectCollision(mob, xMob, yMob - speed)
				&& (mob.getLastY() != (yMob - speed))) {
			mob.move(0, -1);
		}
		mob.attack(towers);
	}

	public void nextWave() {
		waveTime = 0;
		Mob.previousMobSpawnTime = 0;
		setWave(getWave() + 1);
		spawnMobs();
	}

	public void removeTower(Tower tower) {
		tower.getLinkedTowerZone().setBusy(false);
		tower.setAlive(false);
		towers.remove(tower);
	}

	public void setGrounds(ArrayList<Ground> grounds) {
		this.grounds = grounds;
	}

	public void setTowers(ArrayList<Tower> towers) {
		this.towers = towers;
	}

	public boolean spawnMob(final int choice) {
		if (choice == -1) {
			Mob mob = getRandomMob();
			mob.setSpawnTime(getRandomTime());
			mobs.add(mob);
			return true;
		} else if ((choice <= MobFactory.MOB_TYPE_AMOUNT) && (choice >= 0)) {
			mobs.add(MobFactory.createMob(choice));
			return true;
		} else {
			return false;
		}
	}

	public void spawnMobs() {
		int mobAmount = 0;
		int amountAtStart = 3;
		double multiplier = 1.3;
		mobAmount = (int) (amountAtStart * Math.pow(multiplier, getWave()));
		for (int i = 0; i < mobAmount; i++) {
			spawnMob(-1);
		}
	}

	public void testWaveEnding() {
		if (mobs.size() == 0) {
			nextWave();
		}
	}
	
	public void restoreBusy(){
		for(Ground ground : grounds){
            if(ground.getClass() == TowerZone.class){
                for(Tower tower : towers){
                    if(ground.getBounds().intersects(tower.getBounds())){
                        tower.setLinkedTowerZone((TowerZone) ground);
                        ((TowerZone) ground).setBusy(true);
                        System.out.println(tower.getX() + " " + tower.getY());
                    }
                }
            }
        }
	}

	/*
	 * public boolean spawnTower(final int choice) { if ((choice <=
	 * TowerFactory.TOWER_TYPE_AMOUNT) && (choice >= 0)) {
	 * towers.add(TowerFactory.createTower(choice)); return true; } else {
	 * return false; } }
	 */
}
