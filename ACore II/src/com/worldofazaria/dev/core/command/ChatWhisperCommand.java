package com.worldofazaria.dev.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.worldofazaria.dev.core.engine.ACore;

public class ChatWhisperCommand implements CommandExecutor {
	
	ACore plugin;
	public ChatWhisperCommand(ACore plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		return false;
	}
}
