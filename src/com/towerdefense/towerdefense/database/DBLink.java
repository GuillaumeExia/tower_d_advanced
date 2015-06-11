package com.towerdefense.towerdefense.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.Map;
import com.towerdefense.towerdefense.Save;
import com.towerdefense.towerdefense.entities.Workstation;
import com.towerdefense.towerdefense.objects.Grass;
import com.towerdefense.towerdefense.objects.Ground;
import com.towerdefense.towerdefense.objects.Path;
import com.towerdefense.towerdefense.objects.Spawnpoint;
import com.towerdefense.towerdefense.objects.TowerZone;

public class DBLink {

	private String url = "jdbc:mysql://164.138.29.106/tower_defense";
	private String user = "adrien";
	private String password = "Mu74KC65OA";
	private Connection connection = null;
	private Statement statement = null;
	ResultSet resultSet = null;

	public DBLink() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void close() {
		try {
			if (this.statement != null) {
				this.statement.close();
			}
			if (this.connection != null) {
				this.connection.close();
			}
			if (this.resultSet != null) {
				this.resultSet.close();
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public java.sql.ResultSet executeQuery(String query) {

		try {
			return this.statement.executeQuery(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public ResultSet getIDPlayer() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = this.connection.prepareCall(DBProcedure.getIDPlayer(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			procedure.setString(1, GlobalVariables.nickname);
			procedure.execute();
			result = procedure.getResultSet();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ResultSet getSave() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = this.connection.prepareCall(DBProcedure.getSave(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ResultSet getScore() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = this.connection.prepareCall(DBProcedure.getScore(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ResultSet getTerrain() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = this.connection.prepareCall(DBProcedure.getTerrain(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ResultSet loadPlayerSaveWithIDSave() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = this.connection.prepareCall(DBProcedure.loadPlayerSaveWithIDSave(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ResultSet loadPlayerWithID() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = this.connection.prepareCall(DBProcedure.loadPlayerWithID(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ResultSet loadTerrainWithID() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = this.connection.prepareCall(DBProcedure.loadTerrainWithID(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ResultSet loadTowerSaveWithIDSave() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = this.connection.prepareCall(DBProcedure.loadTowerSaveWithIDSave(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ResultSet loadTowerWithID() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = this.connection.prepareCall(DBProcedure.loadTowerWithID(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ArrayList<Ground> mapSelection(int id) {
		this.open();
		ArrayList<Ground> groundList = new ArrayList();
		try {
			CallableStatement cs = this.connection.prepareCall(DBProcedure.loadTerrain());
			cs.setInt(1, id);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {

				int xPixel = rs.getInt("X") * 32;
				int yPixel = rs.getInt("Y") * 32;

				switch (rs.getInt("TYPE")) {
					case 1:
						groundList.add(new Grass(xPixel, yPixel));
						break;
					case 2:
						groundList.add(new Path(xPixel, yPixel));
						break;
					case 3:
						groundList.add(new Spawnpoint(xPixel, yPixel));
						break;
					case 4:
						new Workstation(xPixel, yPixel);
						break;
					case 5:
						groundList.add(new TowerZone(xPixel, yPixel));
						break;
				}

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		this.close();
		return groundList;
	}

	public boolean open() {
		if (this.connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				this.connection = DriverManager.getConnection(this.url, this.user, this.password);
				this.statement = this.connection.createStatement();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		System.out.println("Connection successfull");
		return true;
	}

	public void saveMap() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = this.connection.prepareCall(DBProcedure.saveMap(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, "First parameter of the procedure");
			procedure.setString(2, "Second parameter of the procedure");
			procedure.setString(3, "Third parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments

	}

	public void savePlayer(String nickname) {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = this.connection.prepareCall(DBProcedure.savePlayer(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, nickname);
			procedure.execute();
			result = procedure.getResultSet();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments

	}

	public void saveTower() {
		CallableStatement procedure;
		ResultSet result = null;

		try {
			procedure = this.connection.prepareCall(DBProcedure.saveTower(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, "");
			procedure.setString(2, "Second parameter of the procedure");
			procedure.setString(3, "Third parameter of the procedure");
			procedure.setString(4, "4 parameter of the procedure");
			procedure.setString(5, "5 parameter of the procedure");
			procedure.setString(6, "6 parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments

	}

	public ArrayList<Map> selectAllMapsProc() {
		this.open();
		ArrayList<Map> mapList = new ArrayList();
		try {
			CallableStatement cs = this.connection.prepareCall(DBProcedure.selectAllMaps());
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				mapList.add(new Map(rs.getString("NAME"), rs.getInt("WIDTH"), rs.getInt("HEIGHT"), rs.getInt("ID_MAP")));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		this.close();
		return mapList;
	}

	public ArrayList<Save> selectAllSavesProc() {
		this.open();
		ArrayList<Save> saveList = new ArrayList();
		try {
			CallableStatement cs = this.connection.prepareCall(DBProcedure.selectAllSaves());
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				saveList.add(new Save(rs.getString("PSEUDO"), rs.getInt("WAVE"), rs.getInt("TIMEE"), rs.getInt("MONEY"), rs.getInt("ID_MAP"), rs.getInt("ID_SAVE"), rs.getInt("ID_PLAYER")));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		this.close();
		return saveList;
	}

	public void setSave(int wave, int time, int life, int money, int id_map, int id_player) {
		CallableStatement procedure;

		try {
			procedure = this.connection.prepareCall(DBProcedure.setSave());
			procedure.setInt(1, wave);
			procedure.setInt(2, time);
			procedure.setInt(3, life);
			procedure.setInt(4, money);
			procedure.setInt(5, id_map);
			procedure.setInt(6, id_player);
			procedure.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
	}

	public void setScore() {
		CallableStatement procedure;
		try {
			procedure = this.connection.prepareCall(DBProcedure.setScore());
			procedure.setString(1, "First parameter of the procedure");
			procedure.setString(2, "Second parameter of the procedure");
			procedure.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
	}

}
