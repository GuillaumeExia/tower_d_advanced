package com.towerdefense.towerdefense.database;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.Map;
import com.towerdefense.towerdefense.entities.towers.Tower;
import com.towerdefense.towerdefense.database.DBLink;;

public class DataBase {
	private DBLink database;

	public DataBase() {
		//super();
		database = new DBLink();
	}

	public Map loadMap(int id) {
		return null;
	}

	public Map loadSave(int id) {
		return null;
	}

	public void save(String nickname) {
		database.open();
		ResultSet id_player;
		ResultSet id_save;
		
		//rechercher si le pseudo est dans la liste si le pseudo n'existe pas faire le save palyer et relancer la vérif. si le pseudo existe déjà on pourra récupérer l'id du joueur pour le reste de la sauvegarde
		/*if(dbLink.getIDPlayer() == null) {
		
			dbLink.savePlayer();
		
			id_player = dbLink.getIDPlayer();
		}
		else {
			
			id_player = dbLink.getIDPlayer();
			//System.out.println(id_player); 
		}	*/	
		
		database.savePlayer(nickname);
		database.setSave(Map.getWave(), 120, GlobalVariables.life, GlobalVariables.money, 1, 3);
    	System.out.println(Map.getWave());
		//dbLink.saveTower();
		//return;
    	database.close();
	}
}
