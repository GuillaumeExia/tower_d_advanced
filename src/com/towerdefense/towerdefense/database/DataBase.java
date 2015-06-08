package com.towerdefense.towerdefense.database;

import com.towerdefense.towerdefense.Map;

public class DataBase {
	private DBLink database;

	public DataBase() {
		//super();
		database = new DBLink();
		database.open();
		System.out.println("Connection Successfull");
		database.close();
	}

	public Map loadMap(int id) {
		return null;
	}

	public Map loadSave(int id) {
		return null;
	}

	public void save(Map map) {
		return;
	}
}
