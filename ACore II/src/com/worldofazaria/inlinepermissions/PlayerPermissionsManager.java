package com.worldofazaria.inlinepermissions;

import org.bukkit.entity.Player;

import com.worldofazaria.dev.core.engine.ACore;

public class PlayerPermissionsManager {
	
	ACore plugin;
	public PlayerPermissionsManager(ACore plugin){
		this.plugin = plugin;
	}
	
	public int getPlayerPermissionLevel(Player p){
		return plugin.config.getInt("players." + p.getName() + ".player");
	}
	public int getPlayerAdminLevel(Player p){
		return plugin.config.getInt("players." + p.getName() + ".admin");
	}
	public boolean getPlayerDonator(Player p){
		return plugin.config.getBoolean("players." + p.getName() + ".donator");
	}

	
}
