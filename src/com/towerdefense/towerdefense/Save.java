package com.towerdefense.towerdefense;

public class Save {
	private static Save selectedSave;

	public static Save getSelectedSave() {
		return selectedSave;
	}

	public static void setSelectedSave(Save selectedSave) {
		Save.selectedSave = selectedSave;
	}

	private String pseudo;
	private int wave;
	private int time;
	private int money;
	private int idMap;
	private int idSave;
	private int idPlayer;
	private int life;

	public Save(String pseudo, int wave, int time, int money, int idMap, int idSave, int idPlayer, int life) {
		this.pseudo = pseudo;
		this.wave = wave;
		this.time = time;
		this.money = money;
		this.idMap = idMap;
		this.idSave = idSave;
		this.idPlayer = idPlayer;//
		this.life = life;
	}

	public int getIdMap() {
		return this.idMap;
	}

	public int getIdPlayer() {
		return this.idPlayer;
	}

	public int getIdSave() {
		return this.idSave;
	}

	public int getLife() {
		return this.life;
	}

	public int getMoney() {
		return this.money;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public String getStringSave() {
		String stringSave = "Map :" + this.getIdMap() + " | Pseudo : " + this.getPseudo() + " | Wave :" + this.getWave() + " | Life :" + this.getLife() + " | Money :" + this.getMoney() + " | Time :"
				+ this.getTime();
		return stringSave;
	}

	public int getTime() {
		return this.time;
	}

	public int getWave() {
		return this.wave;
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

	public void setWave(int wave) {
		this.wave = wave;
	}

}
