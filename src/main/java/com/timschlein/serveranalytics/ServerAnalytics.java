package com.timschlein.serveranalytics;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.bukkit.Bukkit;
//import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerAnalytics extends JavaPlugin{
	
	@Override
	public void onEnable() {
		
		DBConnection db = new DBConnection("198.143.155.98", "3306", "mc114471", "mc114471", "4a19d822c3");
		Connection c = null;
		try {
			c = db.openConnection();
			Statement statement = c.createStatement();
			Statement statement2 = c.createStatement();
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS PlayerProfiles " + "("  + "username VARCHAR(36), "  + "UUID VARCHAR(36), " + "ip VARCHAR(30), " + "firstJoinDate VARCHAR(36));");
			statement2.executeUpdate("CREATE TABLE IF NOT EXISTS ServerData " + "("  + "username VARCHAR(36), "  + "UUID VARCHAR(36), " + "timesJoined INT);");
		} catch (Exception e) {
			System.out.println("Could not connect");
			
		}
		new OnJoin(this);
		new JoinInformation(this);
		
		getLogger().info("onEnable has been invoked!");
		//		DBSetup.connect();
		    
	}

	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
//		Database.closeConnection();
	}
}
