package com.towerdefense.towerdefense.database;

public abstract class DBProcedure {
	public static String getIDPlayer() {
		return "{call getIDPlayer(?)}";
	}

	public static String getSave() {
		return "{? = call loadSaveWithID(?)}";
	}

	public static String getSaveID() {
		return "{? = call getSaveID}";
	}

	public static String getScore() {
		return "{? = call getScore(?)}";
	}

	public static String getTerrain() {
		return "{? = call getterrain(?, ?)}";
	}

	public static String loadPlayerSaveWithIDSave() {
		return "{? = call loadPlayerSaveWithIDSave(?)}";
	}

	public static String loadPlayerWithID() {
		return "{? = call loadPlayerWithID(?)}";
	}

	public static String loadTerrain() {
		return "{call loadTerrain(?)}";
	}

	public static String loadTerrainWithID() {
		return "{? = call loadTerrainWithID(?)}";
	}

	public static String loadTowerSaveWithIDSave() {
		return "{? = call loadTowerSaveWithIDSave(?)}";
	}

	public static String loadTowerWithID() {
		return "{? = call loadTowerWithID(?)}";
	}

	public static String mapSelection() {
		return "{? = call mapSelection(?)}";
	}

	public static String saveMap() {
		return "{call saveMap(?, ?, ?)}";
	}

	public static String savePlayer() {
		return "{call savePlayer(?)}";
	}

	public static String saveTower() {
		return "{call saveMap(?, ?, ?, ?, ?, ?)}";
	}

	public static String selectAllMaps() {
		return "{call selectAllMaps()}";
	}

	public static String selectAllSaves() {
		return "{call selectAllSaves()}";
	}

	public static String setSave() {
		return "{call setSave(?, ?, ?, ?, ?, ?)}";
	}

	public static String setScore() {
		return "{call setScore(?, ?)}";
	}

	public static String setTerrain() {
		return "{call saveTerrain(?, ?, ?, ?)}";
	}

}
