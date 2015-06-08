package com.towerdefense.towerdefense.database;

public abstract class DBProcedure {
    public static String loadTerrain(){
        return "{call loadTerrain(?)}";
    }

    public static String selectAllMaps(){
        return "{call selectAllMaps()}";
    }

    public static String getSave() {
        return "{? = call getsave[(?, ?)]}";
    }

    public static String getScore() {
        return "{? = call getscore[(?, ?)]}";
    }

    public static String getTerrain() {
        return "{? = call getterrain[(?, ?)]}";
    }

    public static String setSave() {
        return "{call setsave[(?, ?)]}";
    }

    public static String setScore() {
        return "{call setscore[(?, ?)]}";
    }

    public static String setTerrain() {
        return "{call setterrain[(?, ?)]}";
    }
}

