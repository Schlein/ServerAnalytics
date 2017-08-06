package com.timschlein.serveranalytics;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.Bukkit;

public class DBSetup {
	public static Connection connection;
	public static String host, database, username, password;
	public static int port;
	static ConsoleCommandSender console = Bukkit.getConsoleSender();

public static void connect() {
	host = "198.143.155.98";
	port = 3306;
	database = "mc114471";
	username = "mc114471";
	password = "4a19d822c3";
	
	
	try {
		openConnection();
		createTable();
//		Statement statement = connection.createStatement();
		
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
public static void createTable() {
	String setupTable = "CREATE TABLE IF NOT EXISTS PlayerProfiles " + "(id INTEGER not NULL,"  + "username VARCHAR(36), "  + "UUID VARCHAR(36), " + "ip VARCHAR(30))";
	
//	not NULL"; 
//+ "ip VARCHAR(30)";
	Statement stmt = null;
	try {
		stmt = connection.createStatement();
		stmt.executeUpdate(setupTable);
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

public static boolean isConnected() {
    return (connection == null ? false : true);
}
public static void disconnect() {
    if (isConnected()) {
        try {
            connection.close();
            console.sendMessage("MySQL Database Disconnected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	}
}
