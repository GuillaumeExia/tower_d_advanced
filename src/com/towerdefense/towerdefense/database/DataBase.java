package com.towerdefense.towerdefense.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.towerdefense.display.PanelMenu;
import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.Map;
import com.towerdefense.towerdefense.Save;
import com.towerdefense.towerdefense.entities.towers.Tower;
import com.towerdefense.towerdefense.objects.Ground;

public class DataBase {
	private DBLink database;

	public DataBase() {
		// super();
		database = new DBLink();
	}

	public Map getMap(int id) {
		database.open();
		Map map = null;
		ResultSet content = database.getMap(id);
		try {
			if (content.next()) {
				map = new Map(content.getString("NAME"),
						content.getInt("WIDTH"), content.getInt("HEIGHT"),
						content.getInt("ID_MAP"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		database.close();
		return map;
	}

	public Map loadSave(int id) {
		return null;
	}

	public ArrayList<Ground> loadTerrain(int id) {
		return database.mapSelection(id);
	}

	public void save(String nickname, Map map) {
		database.open();
		ResultSet idPlayerResult;
		int idPlayer = 0;
		int idSave = 0;

		if (database.getIDPlayer(nickname) == null) {
			database.savePlayer(nickname);
		}
		try {
			idPlayerResult = database.getIDPlayer(nickname);
			idPlayerResult.next();
			idPlayer = idPlayerResult.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		database.setSave(Map.getWave(), PanelMenu.stopwatch.getTimeIsInt(),
				GlobalVariables.life, GlobalVariables.money, map.getId(),
				idPlayer);
		idSave = database.getSaveID();
		saveTowers(map.getTowers(), idSave);
		database.close();
	}

	public void saveTowers(ArrayList<Tower> towers, int idSave) {
		for (Tower tower : towers) {
			database.saveTower(tower.getIdentifier(), tower.getUpgrade(),
					tower.getHealth(), tower.getX(), tower.getY(), idSave);
		}
	}

	public ArrayList<Map> selectAllMaps() {
		database.open();
		ArrayList<Map> mapList = new ArrayList<Map>();
		ResultSet content = database.selectAllMapsProc();
		try {
			while (content.next()) {
				mapList.add(new Map(content.getString("NAME"), content
						.getInt("WIDTH"), content.getInt("HEIGHT"), content
						.getInt("ID_MAP")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		database.close();
		return mapList;
	}

	public ArrayList<Save> selectAllSaves() {
		database.open();
		ArrayList<Save> saveList = new ArrayList<Save>();
		ResultSet content = database.selectAllSavesProc();
		try {
			while (content.next()) {
				saveList.add(new Save(content.getString("PSEUDO"), content
						.getInt("WAVE"), content.getInt("TIMEE"), content
						.getInt("MONEY"), content.getInt("ID_MAP"), content
						.getInt("ID_SAVE"), content.getInt("ID_PLAYER"),
						content.getInt("LIFE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		database.close();
		return saveList;
	}
	
	public void saveScores(String nickname, Map map){
		this.database.open();
		ResultSet idPlayerResult;
		int idPlayer = 0;
		
		if (this.database.getIDPlayer(nickname) == null) {
			this.database.savePlayer(nickname);
		}
		try{
			idPlayerResult = this.database.getIDPlayer(nickname);
			idPlayerResult.next();
			idPlayer = idPlayerResult.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		this.database.setScore(PanelMenu.stopwatch.getTimeIsInt(),idPlayer, map.getId());
		this.database.close();
	}
	
}
