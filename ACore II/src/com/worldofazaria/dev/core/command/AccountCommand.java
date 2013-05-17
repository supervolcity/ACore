package com.worldofazaria.dev.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.worldofazaria.dev.core.engine.ACore;
import com.worldofazaria.dev.core.util.Constants;
import com.worldofazaria.dev.core.util.MoneyFormat;

public class AccountCommand implements CommandExecutor {
	
	ACore plugin;
	public AccountCommand(ACore plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		if(cmd.getName().equalsIgnoreCase("account")){
			if(args.length == 0){
				Player p = (Player) sender;
				p.sendMessage(ChatColor.DARK_GREEN + "==== " + ChatColor.GREEN + p.getName() + " (" + plugin.config.getString("players." + p.getName() + ".display") + ")" + ChatColor.DARK_GREEN + " ====");
				if(plugin.config.getBoolean("players." + p.getName() + ".banned")){
					p.sendMessage(ChatColor.DARK_RED + ">> User is banned. Reason: " + plugin.config.getString("players." + p.getName() + ".banreason"));
				}
				if(plugin.config.getBoolean("players." + p.getName() + ".ajail")){
					p.sendMessage(ChatColor.DARK_RED + ">> User is admin jailed.");
				}
				if(plugin.config.getBoolean("players." + p.getName() + ".mute")){
					p.sendMessage(ChatColor.DARK_RED + ">> User is currently muted.");
				}
				p.sendMessage(ChatColor.GREEN + "First Joined:" + plugin.config.getString("players." + p.getName() + ".firstonline"));
				p.sendMessage(ChatColor.GREEN + "Last Joined:" + plugin.config.getString("players." + p.getName() + ".lastonline"));
				p.sendMessage(ChatColor.GREEN + "Level " + plugin.config.getInt("players." + p.getName() + ".level") + " " + plugin.config.getString("players." + p.getName() + ".spec") + " " + plugin.config.getString("players." + p.getName() + ".race") + " " + plugin.config.getString("players." + p.getName() + ".class"));
				if(plugin.config.getInt("players." + p.getName() + ".money") > 1000 && plugin.config.getInt("players." + p.getName() + ".level") < 5){
					p.sendMessage(ChatColor.GREEN + "Money: " + MoneyFormat.coloredFormat(plugin.config.getInt("players." + p.getName() + ".money")) + ChatColor.DARK_RED + " // Flagged for Exploits." );
				}
				else {
					p.sendMessage(ChatColor.GREEN + "Money: " + MoneyFormat.coloredFormat(plugin.config.getInt("players." + p.getName() + ".money")));
				}
				
				p.sendMessage(ChatColor.GREEN + "Rank: " + plugin.config.getInt("players." + p.getName() + ".player"));
				p.sendMessage(ChatColor.GREEN + "Donator: " + plugin.config.getBoolean("players." + p.getName() + ".donator"));
				if(plugin.config.getInt("players." + p.getName() + ".admin") == 0){
					p.sendMessage(ChatColor.AQUA + "Admin Rank: " + Constants.RANK_ADMIN0);
				}
				else if(plugin.config.getInt("players." + p.getName() + ".admin") == 1){
					p.sendMessage(ChatColor.AQUA + "Admin Rank: " + Constants.RANK_ADMIN1);
				}
				else if(plugin.config.getInt("players." + p.getName() + ".admin") == 2){
					p.sendMessage(ChatColor.AQUA + "Admin Rank: " + Constants.RANK_ADMIN2);
				}
				else if(plugin.config.getInt("players." + p.getName() + ".admin") == 3){
					p.sendMessage(ChatColor.AQUA + "Admin Rank: " + Constants.RANK_ADMIN3);
				}
				else if(plugin.config.getInt("players." + p.getName() + ".admin") == 4){
					p.sendMessage(ChatColor.AQUA + "Admin Rank: " + Constants.RANK_ADMIN4);
				}
				else if(plugin.config.getInt("players." + p.getName() + ".admin") >= 5){
					p.sendMessage(ChatColor.AQUA + "Admin Rank: " + Constants.RANK_ADMIN5);
				}
				
				return true;
			}
			else if(args.length == 1){
				Player p = Bukkit.getPlayer(args[0]);
				Player player = (Player) sender;
				
				if(plugin.perms.getPlayerAdminLevel(player) < 1){
					// Administrator Level 1 required to pass this conditional
					player.sendMessage(Constants.ERR_PERMS_DENIED);
					return true;
				}
				if(!p.hasPlayedBefore()){
					player.sendMessage(Constants.WARN_UNAVAILABLE);
					return true;
				}
				
				player.sendMessage(ChatColor.DARK_GREEN + "==== " + ChatColor.GREEN + p.getName() + " (" + plugin.config.getString("players." + p.getName() + ".display") + ")" + ChatColor.DARK_GREEN + " ===");
				if(plugin.config.getBoolean("players." + p.getName() + ".banned")){
					player.sendMessage(ChatColor.DARK_RED + ">> User is banned. Reason: " + plugin.config.getString("players." + p.getName() + ".banreason"));
				}
				if(plugin.config.getBoolean("players." + p.getName() + ".ajail")){
					player.sendMessage(ChatColor.DARK_RED + ">> User is admin jailed.");
				}
				if(plugin.config.getBoolean("players." + p.getName() + ".mute")){
					player.sendMessage(ChatColor.DARK_RED + ">> User is currently muted.");
				}
				player.sendMessage(ChatColor.GREEN + "First Joined:" + plugin.config.getString("players." + p.getName() + ".firstonline"));
				player.sendMessage(ChatColor.GREEN + "Last Joined:" + plugin.config.getString("players." + p.getName() + ".lastonline"));
				player.sendMessage(ChatColor.GREEN + "Level " + plugin.config.getInt("players." + p.getName() + ".level") + " " + plugin.config.getString("players." + p.getName() + ".spec") + " " + plugin.config.getString("players." + p.getName() + ".race") + " " + plugin.config.getString("players." + p.getName() + ".class"));
				if(plugin.config.getInt("players." + p.getName() + ".money") > 1000 && plugin.config.getInt("players." + p.getName() + ".level") < 5){
					player.sendMessage(ChatColor.GREEN + "Money: " + MoneyFormat.coloredFormat(plugin.config.getInt("players." + p.getName() + ".money")) + ChatColor.DARK_RED + " // Flagged for Exploits." );
				}
				else {
					player.sendMessage(ChatColor.GREEN + "Money: " + MoneyFormat.coloredFormat(plugin.config.getInt("players." + p.getName() + ".money")));
				}
				
				player.sendMessage(ChatColor.GREEN + "Rank: " + plugin.config.getInt("players." + p.getName() + ".player"));
				player.sendMessage(ChatColor.GREEN + "Donator: " + plugin.config.getBoolean("players." + p.getName() + ".donator"));
				if(plugin.config.getInt("players." + p.getName() + ".admin") == 0){
					player.sendMessage(ChatColor.AQUA + "Admin Rank: " + Constants.RANK_ADMIN0);
				}
				else if(plugin.config.getInt("players." + p.getName() + ".admin") == 1){
					player.sendMessage(ChatColor.AQUA + "Admin Rank: " + Constants.RANK_ADMIN1);
				}
				else if(plugin.config.getInt("players." + p.getName() + ".admin") == 2){
					player.sendMessage(ChatColor.AQUA + "Admin Rank: " + Constants.RANK_ADMIN2);
				}
				else if(plugin.config.getInt("players." + p.getName() + ".admin") == 3){
					player.sendMessage(ChatColor.AQUA + "Admin Rank: " + Constants.RANK_ADMIN3);
				}
				else if(plugin.config.getInt("players." + p.getName() + ".admin") == 4){
					player.sendMessage(ChatColor.AQUA + "Admin Rank: " + Constants.RANK_ADMIN4);
				}
				else if(plugin.config.getInt("players." + p.getName() + ".admin") >= 5){
					player.sendMessage(ChatColor.AQUA + "Admin Rank: " + Constants.RANK_ADMIN5);
				}
				
				return true;
			}
		}
		return false;
	}
}
