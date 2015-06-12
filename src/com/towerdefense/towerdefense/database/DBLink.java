package com.towerdefense.towerdefense.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public java.sql.ResultSet executeQuery(String query) {

		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Connection getConnection() {
		return connection;
	}

	public ResultSet getIDPlayer(String nickname) {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.getIDPlayer(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			procedure.setString(1, nickname);
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		try {
			if (result.first()) {
				result.beforeFirst();
				return result;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResultSet getMap(int id) {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.getMap(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setInt(1, id);
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet getSave() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.getSave(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public int getSaveID() {
		CallableStatement procedure;
		int result = 0;
		try {
			procedure = connection.prepareCall(DBProcedure.getSaveID(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.registerOutParameter("p_id", java.sql.Types.INTEGER);
			procedure.execute();
			result = procedure.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public ResultSet getScore() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.getScore(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ResultSet getTerrain() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.getTerrain(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet loadPlayerSaveWithIDSave() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(
					DBProcedure.loadPlayerSaveWithIDSave(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ResultSet loadPlayerWithID() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.loadPlayerWithID(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ResultSet loadTerrainWithID() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.loadTerrainWithID(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ResultSet loadTowerSaveWithIDSave(int id) {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(
					DBProcedure.loadTowerSaveWithIDSave(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setInt(1, id);
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet loadTowerWithID() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.loadTowerWithID(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		return result;
	}

	public ArrayList<Ground> mapSelection(int id) {
		open();
		ArrayList<Ground> groundList = new ArrayList();
		try {
			CallableStatement cs = connection.prepareCall(DBProcedure
					.loadTerrain());
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return groundList;
	}

	public boolean open() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, user, password);
				statement = connection.createStatement();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		System.out.println("Connection successfull");
		return true;
	}

	public void savePlayer(String nickname) {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.savePlayer(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, nickname);
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments

	}

	public void saveTower(int type, int upgrade, int health, int x, int y,
			int idSave) {
		CallableStatement procedure;
		ResultSet result = null;

		try {
			procedure = connection.prepareCall(DBProcedure.saveTower(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setInt(1, type);
			procedure.setInt(2, upgrade);
			procedure.setInt(3, health);
			procedure.setInt(4, x);
			procedure.setInt(5, y);
			procedure.setInt(6, idSave);
			procedure.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments

	}

	public ResultSet selectAllMapsProc() {
		ResultSet result = null;
		try {
			CallableStatement cs = connection.prepareCall(DBProcedure
					.selectAllMaps());
			result = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet selectAllSavesProc() {
		ResultSet result = null;
		try {
			CallableStatement cs = connection.prepareCall(DBProcedure
					.selectAllSaves());
			result = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setSave(int wave, int time, int life, int money, int id_map,
			int id_player) {
		CallableStatement procedure;

		try {
			procedure = connection.prepareCall(DBProcedure.setSave());
			procedure.setInt(1, wave);
			procedure.setInt(2, time);
			procedure.setInt(3, life);
			procedure.setInt(4, money);
			procedure.setInt(5, id_map);
			procedure.setInt(6, id_player);
			procedure.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
	}

	public void setScore(int timee, int idPlayer, int idMap) {
		CallableStatement procedure;
		try {
			procedure = connection.prepareCall(DBProcedure.setScore());
			procedure.setInt(1, timee);
			procedure.setInt(2, idPlayer);
			procedure.setInt(3, idMap);
			procedure.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
	}

}
