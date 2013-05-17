package com.worldofazaria.dev.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.worldofazaria.dev.core.engine.ACore;
import com.worldofazaria.dev.core.util.Constants;
import com.worldofazaria.dev.core.util.Timestamp;

public class ReportCommand implements CommandExecutor {
	
	ACore plugin;
	public ReportCommand(ACore plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("report")){
			if(args.length < 2){
				p.sendMessage(Constants.ERR_SYNTAX);
				return true;
			}
			else {
				
				for(Player player : Bukkit.getOnlinePlayers()){
					// Checking for online admins
					if(plugin.perms.getPlayerAdminLevel(player) >= 1){
						player.sendMessage(ChatColor.YELLOW + "[NEW REPORT] " + Timestamp.getTimestampFromLong(System.currentTimeMillis()) + " - - - - - - - - -");
						player.sendMessage(ChatColor.YELLOW + "Reporter: " + p.getName());
						player.sendMessage(ChatColor.YELLOW + "Report Details: " + args);
						
						plugin.log.info(Timestamp.getTimestampFromLong(System.currentTimeMillis()) + " **REPORT [" + p.getName() + "] -- " + args);
					}
				}
				
				return true;
			}
		}
		return false;
	}
}
