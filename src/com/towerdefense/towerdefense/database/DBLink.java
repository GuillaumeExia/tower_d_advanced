package com.towerdefense.towerdefense.database;

import com.towerdefense.towerdefense.GlobalVariables;
import com.towerdefense.towerdefense.Map;
import com.towerdefense.towerdefense.Save;
import com.towerdefense.towerdefense.entities.Workstation;
import com.towerdefense.towerdefense.objects.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.swing.Timer;

public class DBLink {

	private String url = "jdbc:mysql://localhost/tower_defense";
	private String user = "root";
	private String password = "";
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

	public boolean open() {
        if(connection == null) {
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

    public java.sql.ResultSet executeQuery(String query){

        try {
            return this.statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public ArrayList<Map> selectAllMapsProc(){
        open();
        ArrayList<Map> mapList= new ArrayList();
        try {
            CallableStatement cs = connection.prepareCall(DBProcedure.selectAllMaps());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                mapList.add(new Map(rs.getString("NAME"), rs.getInt("WIDTH"), rs.getInt("HEIGHT"), rs.getInt("ID_MAP")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return mapList;
    }
    
    public ArrayList<Save> selectAllSavesProc(){
        open();
        ArrayList<Save> saveList= new ArrayList();
        try {
            CallableStatement cs = connection.prepareCall(DBProcedure.selectAllSaves());
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                saveList.add(new Save(rs.getString("PSEUDO"), rs.getInt("WAVE"), rs.getInt("TIMEE"), rs.getInt("MONEY"), rs.getInt("ID_MAP"), rs.getInt("ID_SAVE"), rs.getInt("ID_PLAYER")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return saveList;
    }

    public ArrayList<Ground> mapSelection(int id){
        open();
        ArrayList<Ground> groundList= new ArrayList();
        try {
            CallableStatement cs = connection.prepareCall(DBProcedure.loadTerrain());
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while(rs.next()) {

                int xPixel = rs.getInt("X") * 32;
                int yPixel = rs.getInt("Y") * 32;

                switch(rs.getInt("TYPE")){
                    case 1 : groundList.add(new Grass(xPixel, yPixel));break;
                    case 2 : groundList.add(new Path(xPixel, yPixel));break;
                    case 3 : groundList.add(new Spawnpoint(xPixel, yPixel));break;
                    case 4 : new Workstation(xPixel, yPixel);break;
                    case 5 : groundList.add(new TowerZone(xPixel, yPixel));break;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return groundList;
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
    
    public ResultSet getIDPlayer() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.getIDPlayer(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			procedure.setString(1, GlobalVariables.nickname);
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
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
		// TODO Add valid arguments
		return result;
	}
	
	public ResultSet loadPlayerSaveWithIDSave() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.loadPlayerSaveWithIDSave(),
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
    
    public ResultSet loadTowerSaveWithIDSave() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.loadTowerSaveWithIDSave(),
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
	
	public void saveMap() {
		CallableStatement procedure;
		ResultSet result = null;
		try {
			procedure = connection.prepareCall(DBProcedure.saveMap(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, "First parameter of the procedure");
			procedure.setString(2, "Second parameter of the procedure");
			procedure.setString(3, "Third parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		
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
	
	public void saveTower() {
		CallableStatement procedure;
		ResultSet result = null;
		
		
		try {
			procedure = connection.prepareCall(DBProcedure.saveTower(),
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			procedure.setString(1, "");
			procedure.setString(2, "Second parameter of the procedure");
			procedure.setString(3, "Third parameter of the procedure");
			procedure.setString(4, "4 parameter of the procedure");
			procedure.setString(5, "5 parameter of the procedure");
			procedure.setString(6, "6 parameter of the procedure");
			procedure.execute();
			result = procedure.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
		
	}
	
	public void setSave(int wave, int time, int life, int money, int id_map, int id_player) {
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

	public void setScore() {
		CallableStatement procedure;
		try {
			procedure = connection.prepareCall(DBProcedure.setScore());
			procedure.setString(1, "First parameter of the procedure");
			procedure.setString(2, "Second parameter of the procedure");
			procedure.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
	}




}
