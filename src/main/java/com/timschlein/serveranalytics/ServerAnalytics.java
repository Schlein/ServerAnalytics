package com.timschlein.serveranalytics;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import org.bukkit.Bukkit;
//import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerAnalytics extends JavaPlugin{

	public void onEnable() {
		
		DBConnection db = new DBConnection("198.143.155.98", "3306", "mc114471", "mc114471", "4a19d822c3");
		Connection c = null;
		try {
			c = db.openConnection();
		} catch (Exception e) {
			System.out.println("Could not connect");
			
		}
		getLogger().info("onEnable has been invoked!");
		//		DBSetup.connect();
//		getServer().getPluginManager().registerEvents(new OnJoin(), this);    
	}

	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
		DBSetup.disconnect();
	}
	
}
