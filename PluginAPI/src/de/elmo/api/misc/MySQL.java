package de.elmo.api.misc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	private String HOSTNAME;
	private int PORT;
	private String DATABASENAME;
	private String USERNAME;
	private String PASSWORD;
	private Connection connection;

	public MySQL(String HOSTNAME, int PORT, String DATABASENAME, String USERNAME, String PASSWORD) {
		this.HOSTNAME = HOSTNAME;
		this.PORT = PORT;
		this.DATABASENAME = DATABASENAME;
		this.USERNAME = USERNAME;
		this.PASSWORD = PASSWORD;
	}

	public void connect() {
		if (!isConnected()) {
			try {
				this.connection = java.sql.DriverManager.getConnection(
						"jdbc:mysql://" + this.HOSTNAME + ":" + this.PORT + "/" + this.DATABASENAME, this.USERNAME,
						this.PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void disconnect() {
		if (isConnected()) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isConnected() {
		return this.connection != null;
	}

	public void update(String qry) {
		if (isConnected()) {
			try {
				Statement st = this.connection.createStatement();
				st.executeUpdate(qry);
				st.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public ResultSet query(String qry) {
		ResultSet rs = null;
		if (isConnected()) {
			try {
				Statement st = this.connection.createStatement();
				rs = st.executeQuery(qry);
			} catch (SQLException ex) {
				ex.printStackTrace();
				connect();
			}
		}
		return rs;
	}
}
