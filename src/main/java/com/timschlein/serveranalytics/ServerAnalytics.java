package com.timschlein.serveranalytics;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;

import java.sql.ResultSet;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;


public class ServerAnalytics extends JavaPlugin{
	private Connection connection;
	private String host, database, username, password;
	private int port;
	
	@Override
	public void onEnable() {
		host = "198.143.155.98";
		port = 3306;
		database = "mc114471";
		username = "mc114471";
		password = "4a19d822c3";
		
		
		try {
			openConnection();
			DatabaseMetaData md = connection.getMetaData();
			ResultSet rs = md.getTables(null, null, "PlayerProfiles", null);
			createTable();
			while (rs.next()) {
				String tName = rs.getString("PlayerProfiles");
				if (tName !=null && tName.equals("PlayerProfiles")) {
					break;
				} else {
					createTable();
				}
			}
//			Statement statement = connection.createStatement();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getLogger().info("onEnable has been invoked!");
	}
	private void createTable() {
		String setupTable = "CREATE TABLE PlayerProfiles " + "(id INTEGER not NULL)";
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(setupTable);
		} catch (SQLException e) {
			System.out.println("Fuck this shit");
			e.printStackTrace();
		} 	
		
	}
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}

	public void openConnection() throws SQLException, ClassNotFoundException {
	    if (connection != null && !connection.isClosed()) {
	        return;
	    }
	 
	    synchronized (this) {
	        if (connection != null && !connection.isClosed()) {
	            return;
	        } 
	        Class.forName("com.mysql.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://" + this.host+ ":" + this.port + "/" + this.database, this.username, this.password);
	    }
	}
}
