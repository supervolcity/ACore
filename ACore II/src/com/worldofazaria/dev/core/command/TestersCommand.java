package com.worldofazaria.dev.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.worldofazaria.dev.core.engine.ACore;

public class TestersCommand implements CommandExecutor {
	
	ACore plugin;
	public TestersCommand(ACore plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("testers")){
			p.sendMessage(ChatColor.GRAY + "Online Testers:");
			for(Player player : Bukkit.getOnlinePlayers()){
				if(plugin.perms.getPlayerAdminLevel(player) >= 0){
						p.sendMessage(ChatColor.DARK_GREEN + plugin.config.getString("players." + player.getName() + ".display"));
				}
			}
			return true;
		}
		return false;
	}
}
