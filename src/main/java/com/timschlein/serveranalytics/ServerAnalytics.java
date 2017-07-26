package com.timschlein.serveranalytics;




import org.bukkit.Bukkit;
//import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;



public class ServerAnalytics extends JavaPlugin{

	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		DBSetup.connect();
		getServer().getPluginManager().registerEvents(new OnJoin(), this);
//		OnJoin.onPlayerLogin(e);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
		DBSetup.disconnect();
	}

}
