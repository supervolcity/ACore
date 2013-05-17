package com.worldofazaria.dev.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.worldofazaria.dev.core.engine.ACore;
import com.worldofazaria.dev.core.util.Constants;

public class AdminsCommand implements CommandExecutor {
	
	ACore plugin;
	public AdminsCommand(ACore plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("admins")){
			p.sendMessage(ChatColor.GRAY + "Admins Online:");
			for(Player player : Bukkit.getOnlinePlayers()){
				if(plugin.perms.getPlayerAdminLevel(player) >= 1){
					if(!plugin.adminduty.get(player)){
						p.sendMessage(ChatColor.GRAY + plugin.config.getString("players." + player.getName() + ".display") + "[Level: " + plugin.perms.getPlayerAdminLevel(player) + "][OFF DUTY]");
					}
					else {
						p.sendMessage(ChatColor.DARK_GREEN + plugin.config.getString("players." + player.getName() + ".display") + "[Level: " + plugin.perms.getPlayerAdminLevel(player) + "][ON DUTY]");
					}
				}
			}
			return true;
		}
		else if(cmd.getName().equalsIgnoreCase("adminduty")){
			if(plugin.perms.getPlayerAdminLevel(p) < 1){
				p.sendMessage(Constants.ERR_PERMS_DENIED);
				return true;
			}
			
			if(!plugin.adminduty.containsKey(p) || !plugin.adminduty.get(p)){
				plugin.adminduty.put(p, true);
				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + " is now on Admin Duty. " + ChatColor.RED + "Do not use /pm to contact this admin. Use /report.");
				return true;
			}
			else if(plugin.adminduty.get(p)){
				plugin.adminduty.put(p, false);
				Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + " is no longer on Admin Duty.");
				return true;
			}
		}
		return false;
	}
}
