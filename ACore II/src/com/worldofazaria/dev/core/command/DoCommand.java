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

public class DoCommand implements CommandExecutor {
	
	ACore plugin;
	public DoCommand(ACore plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		// THIS COMMAND IS IN CHARACTER.
		
		if(cmd.getName().equalsIgnoreCase("do")){
			// Check for Mute
			if(plugin.config.getBoolean("players." + p.getName() + ".mute")){
				// If they're muted, they can't use IC features.
				p.sendMessage(Constants.ERR_MUTED);
				return true;
			}
			else if(plugin.config.getString("players." + p.getName() + ".display").contains(p.getName())){
				// If their IGN is in their display name, their name is flagged as OOC and they are unable to use IC features.
				p.sendMessage(Constants.WARN_UNAVAILABLE);
				return true;
			}
			
			p.sendMessage(ChatColor.LIGHT_PURPLE + p.getDisplayName() + " " + args);
			plugin.log.info(Timestamp.getTimestampSimple(System.currentTimeMillis()) + " PLAYER COMMAND [" + p.getName() + "] -- (ME) " + args);
			for(Player player : Bukkit.getOnlinePlayers()){
				if(p.getLocation().distance(player.getLocation()) <= Constants.CHAT_RANGE_DO){
					player.sendMessage(ChatColor.LIGHT_PURPLE + "** " + args + "(( " + plugin.config.getString("players." + p.getName() + ".display") + " ))");
					plugin.log.info(Timestamp.getTimestampSimple(System.currentTimeMillis()) + " Player [" + player.getName() + "] received SPECIAL_DO_MESSAGE from " + p.getName() + ".");
				}
			}
			return true;
		}
		
		return false;
	}

}
