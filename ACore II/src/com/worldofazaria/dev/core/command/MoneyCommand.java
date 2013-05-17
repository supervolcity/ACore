package com.worldofazaria.dev.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.worldofazaria.dev.core.engine.ACore;
import com.worldofazaria.dev.core.util.MoneyFormat;

public class MoneyCommand implements CommandExecutor {
	
	ACore plugin;
	public MoneyCommand(ACore plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("money")){
			p.sendMessage(MoneyFormat.coloredFormat(plugin.config.getInt("players." + p.getName() + ".money")));
			return true;
		}
		return false;
	}
}
