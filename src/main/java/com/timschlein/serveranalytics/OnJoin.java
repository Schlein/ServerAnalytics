package com.timschlein.serveranalytics;

//import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.Statement;
//import java.util.UUID;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public final class OnJoin implements Listener {
	int timesJoined;
	public OnJoin (ServerAnalytics plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onLogin(PlayerJoinEvent event) {


		Player player = event.getPlayer();
		if (player.hasPlayedBefore() == true) {	
			timesJoined = 1;
			String username = player.getDisplayName();
			System.out.println(username);
			String uuid = player.getUniqueId().toString();
			System.out.println(uuid);
			String ip = player.getAddress().toString();
			String formattedIP = ip.replace("/", "");
			formattedIP = formattedIP.split(":")[0];

			String firstJoinDate = java.time.LocalDate.now().toString();
			System.out.println(formattedIP);

			String query = "INSERT INTO PlayerProfiles " + "(username, UUID, ip, firstJoinDate) VALUES (" 
					+ "'" + username + "'" + ", " 
					+ "'" + uuid + "'" + ", " 
					+ "'" + formattedIP + "'" + ", " 
					+ "'" + firstJoinDate + "'" + ");";

			System.out.println(query);
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
		} 
	}
}
