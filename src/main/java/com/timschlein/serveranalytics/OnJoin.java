package com.timschlein.serveranalytics;

import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;


public final class OnJoin implements Listener{
	public static Connection connection;
	public static String host, database, username, password;
	public static int port;
	public static String playerIP;
	static ConsoleCommandSender console = Bukkit.getConsoleSender();

	@EventHandler
	public void onPlayerLogin (PlayerLoginEvent e) {

		Player p = e.getPlayer();
		console.sendMessage("hello");
		System.out.println("fuck this shit");
		//		String test = p.toString();
		//		System.out.println(test);
		if (p.hasPlayedBefore()) {
			playerIP = p.getAddress().getHostName();
			//			System.out.println(playerIP);
			//		System.out.println(playerIp);
//			DBSetup.connect();
			//					String test = "SELECT * FROM PlayerProfiles";
//			String addIP = "INSERT INTO PlayerProfiles " + "(ip) " +"VALUES ('Hello')";

//			Statement stmt = null;
//			try {
//				stmt = connection.createStatement();
//				stmt.executeUpdate(addIP);
//			} catch (SQLException exc) {
//				System.out.println("Fuck this shit");
//				exc.printStackTrace();
//				//			} 	
//			}
		}
	}
		public static void connect() {
			host = "198.143.155.98";
			port = 3306;
			database = "mc114471";
			username = "mc114471";
			password = "4a19d822c3";
			
			
			try {
				openConnection();
				addIP();
//				Statement statement = connection.createStatement();
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		public static void addIP() {
			String addIP = "INSERT INTO PlayerProfiles " + "(ip) " +"VALUES (" +playerIP+ ")";
			
//			not NULL"; 
		//+ "ip VARCHAR(30)";
			Statement stmt = null;
			try {
				stmt = connection.createStatement();
				stmt.executeUpdate(addIP);
			} catch (SQLException e) {
				System.out.println("Fuck this shit");
				e.printStackTrace();
			} 	
			
		}
		public static void openConnection() throws SQLException, ClassNotFoundException {
		    if (connection != null && !connection.isClosed()) {
		        return;
		    }
		        if (connection != null && !connection.isClosed()) {
		            return;
		        } 
		        Class.forName("com.mysql.jdbc.Driver");
		        connection = DriverManager.getConnection("jdbc:mysql://" + host+ ":" + port + "/" + database, username, password);
		    }

	}


