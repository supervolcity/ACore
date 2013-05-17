package com.worldofazaria.dev.core.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.worldofazaria.dev.core.engine.ACore;
import com.worldofazaria.dev.core.util.Constants;
import com.worldofazaria.dev.core.util.Timestamp;

public class CorePlayerEvents implements Listener {
	
	ACore plugin;
	public CorePlayerEvents(ACore plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		
		if(!plugin.config.contains("players." + p.getName())){
			String[] notes = {"Account created >> " + Timestamp.getTimestampFromLong(p.getFirstPlayed())};
			plugin.config.set("players." + p.getName() + ".level", 1);
			plugin.config.set("players." + p.getName() + ".display", "(( " + p.getName() + " ))");
			plugin.config.set("players." + p.getName() + ".money", 10);
			plugin.config.set("players." + p.getName() + ".title", "");
			plugin.config.set("players." + p.getName() + ".player", 1);
			plugin.config.set("players." + p.getName() + ".donator", false);
			plugin.config.set("players." + p.getName() + ".donatorlevel", "None");
			plugin.config.set("players." + p.getName() + ".admin", -1);
			plugin.config.set("players." + p.getName() + ".flag-afk", false);
			plugin.config.set("players." + p.getName() + ".flag-pvp", true);
			plugin.config.set("players." + p.getName() + ".lastonline", "Never");
			plugin.config.set("players." + p.getName() + ".firstonline", Timestamp.getTimestampFromLong(p.getFirstPlayed()));
			plugin.config.set("players." + p.getName() + ".race", "Spirit");
			plugin.config.set("players." + p.getName() + ".class", "Wanderer");
			plugin.config.set("players." + p.getName() + ".spec", "Unspecialized");
			plugin.config.set("players." + p.getName() + ".state", "Alive");
			plugin.config.set("players." + p.getName() + ".spawn.custom-spawn-enabled", false);
			plugin.config.set("players." + p.getName() + ".spawn.x", 0.00);
			plugin.config.set("players." + p.getName() + ".spawn.y", 0.00);
			plugin.config.set("players." + p.getName() + ".spawn.z", 0.00);
			plugin.config.set("players." + p.getName() + ".jail", false);
			plugin.config.set("players." + p.getName() + ".ajail", false);
			plugin.config.set("players." + p.getName() + ".banned", false);
			plugin.config.set("players." + p.getName() + ".banreason", "NOT BANNED");
			plugin.config.set("players." + p.getName() + ".mute", false);
			plugin.config.set("players." + p.getName() + ".notes", notes);
			
			Bukkit.broadcastMessage(ChatColor.YELLOW + "New Player " + p.getName() + " has appeared in the realm.");
			plugin.log.info(Timestamp.getTimestampSimple(System.currentTimeMillis()) + " New Player [" + p.getName() + "] has been created.");
		}
		else {
			if(plugin.config.getBoolean("players." + p.getName() + ".banned")){
				p.kickPlayer("Banned: " + plugin.config.getString("players." + p.getName() + ".banreason"));
				plugin.log.info(Timestamp.getTimestampSimple(System.currentTimeMillis()) + " Banned Player [" + p.getName() + "] has just tried to join the server.");

			}	
			if(plugin.config.getBoolean("players." + p.getName() + ".jail")){
				p.teleport(Constants.LOCATION_PLAYERJAIL);
			}
			if(plugin.config.getBoolean("players." + p.getName() + ".ajail")){
				p.teleport(Constants.LOCATION_ADMINJAIL);
				p.sendMessage(Constants.ERR_AJAIL_WELCOME1);
				p.sendMessage(Constants.ERR_AJAIL_WELCOME2);
			}
			
			plugin.config.set("players." + p.getName() + ".lastonline", Timestamp.getTimestampFromLong(System.currentTimeMillis()));
			
		}
	}
	
		// handler for ajail
		@EventHandler (priority = EventPriority.LOWEST)
		public void onPlayerMove(PlayerMoveEvent event){
			Player p = event.getPlayer();
			if(plugin.config.getBoolean("players." + p.getName() + ".ajail")){
				p.sendMessage(Constants.ERR_AJAIL);
				event.setCancelled(true);
			}
		}
		// handler for ajail
		@EventHandler (priority = EventPriority.LOWEST)
		public void onPlayerPlaceBlock(BlockPlaceEvent event){
			Player p = event.getPlayer();
			if(plugin.config.getBoolean("players." + p.getName() + ".ajail")){
				p.sendMessage(Constants.ERR_AJAIL);
				event.setCancelled(true);
			}
		}
		// handler for ajail
		@EventHandler (priority = EventPriority.LOWEST)
		public void onPlayerInteract(PlayerInteractEvent event){
			Player p = event.getPlayer();
			if(plugin.config.getBoolean("players." + p.getName() + ".ajail")){
				p.sendMessage(Constants.ERR_AJAIL);
				event.setCancelled(true);
			}
		}
		// handler for ajail
		@EventHandler (priority = EventPriority.LOWEST)
		public void onPlayerTeleport(PlayerTeleportEvent event){
			Player p = event.getPlayer();
			if(plugin.config.getBoolean("players." + p.getName() + ".ajail")){
				p.sendMessage(Constants.ERR_AJAIL);
				p.teleport(Constants.LOCATION_ADMINJAIL);
			}
		}
		

}
