package com.towerdefense.towerdefense.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBLink {

	private String url = "jdbc:mysql://164.138.29.106/tower_defense";
	private String user = "nicolas";
	private String password = "pnATC39ZjYvxen5Q";
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
				System.out.println("Statement Closed");
			}
			if (connection != null) {
				connection.close();
				System.out.println("Connection Closed");
			}
			if (resultSet != null) {
				resultSet.close();
				System.out.println("ResultSet Closed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Deconnection error");
			e.printStackTrace();
		}
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

	public boolean open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC Driver Loaded");
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the database");
			statement = connection.createStatement();
			System.out.println("Statement Created");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver loading error");
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			System.out.println("Connection error");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void setSave() {
		CallableStatement procedure;
		try {
			procedure = connection.prepareCall(DBProcedure.setSave());
			procedure.setString(1, "First parameter of the procedure");
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
			procedure.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
	}

	public void setTerrain() {
		CallableStatement procedure;
		try {
			procedure = connection.prepareCall(DBProcedure.setTerrain());
			procedure.setString(1, "First parameter of the procedure");
			procedure.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Add valid arguments
	}
}
