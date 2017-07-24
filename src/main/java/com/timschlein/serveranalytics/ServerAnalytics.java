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

	
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		DBSetup.connect();
//		
	}
//	
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
		DBSetup.disconnect();
	}

}
