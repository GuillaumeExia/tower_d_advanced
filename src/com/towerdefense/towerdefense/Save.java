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
	private int id_map;
	private int id_save;
	private int id_player;
	private int life;

	public Save(String pseudo, int wave, int time, int money, int id_map, int id_save, int id_player, int life) {
		this.pseudo = pseudo;
		this.wave = wave;
		this.time = time;
		this.money = money;
		this.id_map = id_map;
		this.id_save = id_save;
		this.id_player = id_player;//
		this.life = life;
	}

	public int getId_map() {
		return this.id_map;
	}

	public int getId_player() {
		return this.id_player;
	}

	public int getId_save() {
		return this.id_save;
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

	public int getTime() {
		return this.time;
	}

	public int getWave() {
		return this.wave;
	}

	public void setId_map(int id_map) {
		this.id_map = id_map;
	}

	public void setId_player(int id_player) {
		this.id_player = id_player;
	}

	public void setId_save(int id_save) {
		this.id_save = id_save;
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
