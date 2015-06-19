package com.towerdefense.towerdefense;

import java.util.ArrayList;

import com.towerdefense.towerdefense.entities.towers.Tower;

public class Save {
	private static Save selectedSave;

	public static Save getSelectedSave() {
		return selectedSave;
	}

	public static void setSelectedSave(Save selectedSave) {
		Save.selectedSave = selectedSave;
	}

	private String mapName;
	private String pseudo;
	private int wave;
	private int time;
	private int money;
	private int idMap;
	private int idSave;
	private int idPlayer;
	private int life;
	private ArrayList<Tower> towers = new ArrayList<Tower>();

	public Save(String mapName, String pseudo, int wave, int time, int money, int idMap, int idSave, int idPlayer, int life, ArrayList<Tower> towers) {
		this.mapName = mapName;
		this.pseudo = pseudo;
		this.wave = wave;
		this.time = time;
		this.money = money;
		this.idMap = idMap;
		this.idSave = idSave;
		this.idPlayer = idPlayer;
		this.life = life;
		this.towers = towers;
	}

	public String getMapName() {
		return mapName;
	}
	
	public int getIdMap() {
		return idMap;
	}

	public int getIdPlayer() {
		return idPlayer;
	}

	public int getIdSave() {
		return idSave;
	}

	public int getLife() {
		return life;
	}

	public int getMoney() {
		return money;
	}

	public String getPseudo() {
		return pseudo;
	}

	public String getStringSave() {
		String stringSave = "Map :" + getIdMap() + " | Pseudo : " + getPseudo()
				+ " | Wave :" + getWave() + " | Life :" + getLife()
				+ " | Money :" + getMoney() + " | Time :" + getTime();
		return stringSave;
	}

	public int getTime() {
		return time;
	}

	public ArrayList<Tower> getTowers() {
		return towers;
	}

	public int getWave() {
		return wave;
	}

	public void setIdMap(int idMap) {
		this.idMap = idMap;
	}

	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

	public void setIdSave(int idSave) {
		this.idSave = idSave;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setTowers(ArrayList<Tower> towers) {
		this.towers = towers;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}

}
