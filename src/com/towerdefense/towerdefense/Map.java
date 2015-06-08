package com.towerdefense.towerdefense;

import com.towerdefense.display.TowerShop;
import com.towerdefense.events.TowerShopListener;
import com.towerdefense.towerdefense.database.DBLink;
import com.towerdefense.towerdefense.entities.Workstation;
import com.towerdefense.towerdefense.entities.mobs.Bug;
import com.towerdefense.towerdefense.entities.mobs.Mob;
import com.towerdefense.towerdefense.entities.mobs.MobFactory;
import com.towerdefense.towerdefense.entities.towers.Tower;
import com.towerdefense.towerdefense.entities.towers.TowerFactory;
import com.towerdefense.towerdefense.objects.Ground;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Map {
	private int width;
	private int height;
	private String name;
    private int id;
    private ArrayList<Ground> grounds;
	private ArrayList<Tower> towers;
    private Tower workstation;
	private ArrayList<Mob> mobs;
    private static Map selectedMap;
    private int wave = 1;
    private int waveTime = 0;

    public Map(String name, int width, int height, int id) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.id = id;
    }

    public void init(){
        fetchTerrain();
        mobs = new ArrayList();
        spawnMobs();

        towers = new ArrayList();
        TowerShop towerShop = new TowerShop();
        towerShop.addTowerShopListener(new TowerShopListener() {
            @Override
            public void onTowerAdd(int idTower, int x, int y) {
                System.out.println(idTower);
                towers.add(TowerFactory.createTower(idTower, x, y));
            }
        });
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public String getName() { return name; }

    public static Map getSelectedMap() {
        return selectedMap;
    }

    public static void setSelectedMap(Map selectedMap) {
        Map.selectedMap = selectedMap;
    }

    public int getId() { return id; }

    public void fetchTerrain(){
        if(this.grounds == null){
            DBLink dbLink = new DBLink();
            this.grounds = dbLink.mapSelection(this.id);
        }
    }

    public void drawTerrain(Graphics g){
        for(Ground ground : grounds){
            ground.draw(g);
        }
    }

    public void drawTowers(Graphics g){
        for(Tower tower : towers){
            tower.draw(g);
        }
    }

    public void drawWorkstation(Graphics g){
        workstation = Workstation.getWorkstation();
        if(workstation != null){ workstation.draw(g); }
    }

    public void drawMobs(Graphics g){
        for(int i = 0; i < mobs.size(); i++) {
            if(!detectWorkstationCollision(i) && waveTime > mobs.get(i).getSpawnTime()) {
                mobMove(mobs.get(i));
                mobs.get(i).draw(g);
            }
        }
    }

    public void mobMove(Mob mob){
        int xMob = mob.getX();
        int yMob = mob.getY();
        int speed = mob.getMovementSpeed();

        if(detectCollision(mob, xMob + speed, yMob) && mob.getLastX() != (xMob + speed)){
            mob.move(1, 0);
        }
        else if(detectCollision(mob, xMob, yMob + speed) && mob.getLastY() != (yMob + speed)){
            mob.move(0, 1);
        }
        else if(detectCollision(mob, xMob - speed, yMob) && mob.getLastX() != (xMob - speed)){
            mob.move(-1, 0);
        }
        else if(detectCollision(mob, xMob, yMob - speed) && mob.getLastY() != (yMob - speed)){
            mob.move(0, -1);
        }
    }

    public boolean detectCollision(Mob mob, int newPosX, int newPosY){
        for(Ground currentGround : grounds){
            Rectangle newPosMob = new Rectangle(newPosX, newPosY, mob.getWidth(), mob.getHeight());
            Rectangle ground = currentGround.getBounds();
            if(ground.intersects(newPosMob)){
                //currentGround.debugColor = Color.green;
                //currentGround.debug = true;
                if(!currentGround.isWalkable()){
                    currentGround.debugColor = Color.red;
                    return false;
                }
            }
        }
        return true;
    }

    public boolean detectWorkstationCollision(int i){
        if(mobs.get(i).getBounds().intersects(workstation.getActionZone())){
            mobs.remove(i);
            return true;
        }
        return false;
    }

    public void draw(Graphics g){
        waveTime += 30;
        drawTerrain(g);
        drawWorkstation(g);
        drawMobs(g);
        drawTowers(g);
        testWaveEnding();
        TowerShop.getTowerShop().draw(g);
        g.drawString("Wave : " +  wave, 200, 10);
    }

    public void nextWave() {
        waveTime = 0;
        Mob.previousMobSpawnTime = 0;
        wave++;
        spawnMobs();
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

    public int getRandomTime(){
        Random rand = new Random();

        return rand.nextInt(2500) + 500;
    }

    public void testWaveEnding(){
        if(mobs.size() == 0){
            nextWave();
        }
    }

    public void spawnMobs() {
        int mobAmount = 0;
        int amountAtStart = 3;
        double multiplier = 1.3;
        mobAmount = (int) (amountAtStart * Math.pow(multiplier, wave));
        for (int i = 0; i < mobAmount; i++) {
            spawnMob(-1);
        }
    }

    public Mob getRandomMob() {
        Random random = new Random();
        return MobFactory.createMob(random.nextInt(MobFactory.MOB_TYPE_AMOUNT) + 1);
    }

    /*public boolean spawnTower(final int choice) {
        if ((choice <= TowerFactory.TOWER_TYPE_AMOUNT) && (choice >= 0)) {
            towers.add(TowerFactory.createTower(choice));
            return true;
        } else {
            return false;
        }
    }*/
}
