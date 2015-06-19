package com.towerdefense.towerdefense.database;

public abstract class DBProcedure {
    public static String loadTerrainWithID() { return "SELECT * FROM terrain WHERE ID_MAP = ?;"; }

    public static String selectAllMaps() { return "SELECT * FROM map;"; }

    public static String selectAllSaves() { return "SELECT * FROM players NATURAL JOIN saves NATURAL JOIN map;"; }

	public static String getIDPlayer(String nickname) { return "SELECT ID_PLAYER FROM players WHERE PSEUDO = \"" + nickname + "\";"; }

	public static String getMap() { return "SELECT * map WHERE ID_MAP = ?;"; }

	public static String getSaveID() { return "SELECT MAX (ID_SAVE) AS ID_SAVE FROM saves;"; }

    public static String loadTowerSaveWithIDSave() { return "SELECT * FROM towers WHERE ID_SAVE = ?;"; }

    public static String savePlayer() { return "INSERT INTO players (PSEUDO) VALUES (?);"; }

	public static String saveTower() { return "INSERT INTO towers (TYPE, LEVEL, HEALTH, X, Y, ID_SAVE) VALUES (?, ?, ?, ?, ?, ?);"; }

    public static String setSave() { return "INSERT INTO saves (WAVE, TIMEE, LIFE, MONEY, ID_MAP, ID_PLAYER) VALUES (?, ?, ?, ?, ?, ?);"; }

	public static String setScore() { return "INSERT INTO scores (TIMEE, ID_PLAYER, ID_MAP, WAVE, KILLED_MOBS) VALUES (?, ?, ?, ?, ?);"; }

    public static String getScore() { return "SELECT * FROM scores NATURAL JOIN players NATURAL JOIN map ORDER BY WAVE DESC, TIMEE ASC;"; }
}
