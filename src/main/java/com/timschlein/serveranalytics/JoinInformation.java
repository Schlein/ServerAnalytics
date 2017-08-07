package com.timschlein.serveranalytics;

//import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.Statement;
//import java.util.UUID;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class JoinInformation implements Listener {
	int timesJoined;
	public JoinInformation (ServerAnalytics plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onLogin(PlayerJoinEvent event) {


		Player player = event.getPlayer();
		if (player.hasPlayedBefore() == false) {	
			timesJoined = 1;
			String username = player.getDisplayName();
			System.out.println(username);
			String uuid = player.getUniqueId().toString();
			System.out.println(uuid);
			String query = "INSERT INTO ServerData " + "(username, UUID, timesJoined) VALUES (" 
					+ "'" + username + "'" + ", " 
					+ "'" + uuid + "'" +  ", " 
					+ timesJoined + ");"; 
			DBConnection db = new DBConnection("198.143.155.98", "3306", "mc114471", "mc114471", "4a19d822c3");
			Connection c = null;
			try {
				c = db.openConnection();
				System.out.println("hello world");
				Statement statement = c.createStatement();
				statement.executeUpdate(query);
				c.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Could not connect");

			}
			
		} else {
			String playerName = "'" + player.getDisplayName() + "'";
			timesJoined++;
			System.out.println(timesJoined);
			String query = "UPDATE ServerData SET" + " timesJoined = "  
					+ timesJoined + " WHERE username" +  " = " + playerName + ";";
			DBConnection db = new DBConnection("198.143.155.98", "3306", "mc114471", "mc114471", "4a19d822c3");
			Connection c = null;
			try {
				c = db.openConnection();
				System.out.println("hello world");
				Statement statement = c.createStatement();
				statement.executeUpdate(query);
				c.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Could not connect");

			}
			System.out.println(query);
			
			
		}
		
	}
}

