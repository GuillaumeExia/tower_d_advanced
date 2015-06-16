package com.towerdefense.towerdefense.database;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

import com.towerdefense.towerdefense.entities.Workstation;
import com.towerdefense.towerdefense.entities.mobs.Mob;
import com.towerdefense.towerdefense.objects.Grass;
import com.towerdefense.towerdefense.objects.Ground;
import com.towerdefense.towerdefense.objects.Path;
import com.towerdefense.towerdefense.objects.Spawnpoint;
import com.towerdefense.towerdefense.objects.TowerZone;

public class DBLink {

    private URL dBPath = DBLink.class.getResource("/db/towerdefense.db");
    private String url = "jdbc:sqlite:" + dBPath;
	private Connection connection = null;
	private Statement statement = null;
	ResultSet resultSet = null;

	public DBLink() {}

    public boolean open() {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                this.connection = DriverManager.getConnection(url);
                this.statement = connection.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        System.out.println("Connection successfull");
        return true;
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

	public int getIDPlayer(String nickname) {
        try{
            ResultSet resultSet = statement.executeQuery(DBProcedure.getIDPlayer(nickname));
            if(resultSet.next()){
                return resultSet.getInt("ID_PLAYER");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
	}

	public ResultSet getMap(int id) {
        PreparedStatement preparedStatement;
		ResultSet result = null;
		try {
			preparedStatement = connection.prepareStatement(DBProcedure.getMap());
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getSaveID() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
		int result = 0;
		try {
            preparedStatement = connection.prepareStatement(DBProcedure.getSaveID(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getInt("ID_SAVE");
            }
        } catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
    }

	public ResultSet loadTowerSaveWithIDSave(int id) {
		PreparedStatement preparedStatement;
		ResultSet result = null;
		try {
			preparedStatement = connection.prepareStatement(DBProcedure.loadTowerSaveWithIDSave(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Ground> mapSelection(int id) {
		open();
        PreparedStatement preparedStatement;
        ResultSet result = null;
        ArrayList<Ground> groundList = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(DBProcedure.loadTerrainWithID());
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();

            while (result.next()) {

                int xPixel = result.getInt("X") * 32;
                int yPixel = result.getInt("Y") * 32;

                switch (result.getInt("TYPE")) {
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

	public void savePlayer(String nickname) {
        PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(DBProcedure.savePlayer(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, nickname);
            preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	public void saveTower(int type, int upgrade, int health, int x, int y, int idSave) {
		PreparedStatement preparedStatement;
        try {
			preparedStatement = connection.prepareStatement(DBProcedure.saveTower(), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, type);
            preparedStatement.setInt(2, upgrade);
            preparedStatement.setInt(3, health);
            preparedStatement.setInt(4, x);
            preparedStatement.setInt(5, y);
            preparedStatement.setInt(6, idSave);
            preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet selectAllMapsProc() {
		ResultSet result = null;
		try {
			result = statement.executeQuery(DBProcedure.selectAllMaps());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet selectAllSavesProc() {
		ResultSet result = null;
		try {
			result = statement.executeQuery(DBProcedure.selectAllSaves());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setSave(int wave, int time, int life, int money, int id_map, int id_player) {
		PreparedStatement preparedStatement;
        try {
			preparedStatement = connection.prepareStatement(DBProcedure.setSave());
            preparedStatement.setInt(1, wave);
            preparedStatement.setInt(2, time);
            preparedStatement.setInt(3, life);
            preparedStatement.setInt(4, money);
            preparedStatement.setInt(5, id_map);
            preparedStatement.setInt(6, id_player);
            preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setScore(int timee, int idPlayer, int idMap, int wave) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(DBProcedure.setScore());
            preparedStatement.setInt(1, timee);
            preparedStatement.setInt(2, idPlayer);
            preparedStatement.setInt(3, idMap);
            preparedStatement.setInt(4, wave);
            preparedStatement.setInt(5, Mob.killedMobs);
            preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public ResultSet getScore(){
        PreparedStatement preparedStatement;
        ResultSet result = null;
        try{
            preparedStatement = connection.prepareStatement(DBProcedure.getScore());
            result = preparedStatement.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public Connection getConnection() {
        return connection;
    }

}
