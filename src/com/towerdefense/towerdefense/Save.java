package com.towerdefense.towerdefense;

public class Save {
	private static Save selectedSave;
	
	public static Save getSelectedSave() {
		return selectedSave;
	}
	
	public static void setSelectedSave (Save selectedSave){
		Save.selectedSave = selectedSave;
	}
	
	private String pseudo;
	private int wave;
	private int time;
	private int money;
	private int id_map;
	private int id_save;
	private int id_player;
	
	public Save(String pseudo, int wave, int time, int money, int id_map,int id_save, int id_player){
		this.pseudo = pseudo;
		this.wave = wave;
		this.time = time;
		this.money = money;
		this.id_map = id_map;
		this.id_save = id_save;
		this.id_player = id_player;
	}
	
}
