package com.worldofazaria.dev.core.engine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.worldofazaria.dev.core.command.ADemoteCommand;
import com.worldofazaria.dev.core.command.AFKFlagCommand;
import com.worldofazaria.dev.core.command.APromoteCommand;
import com.worldofazaria.dev.core.command.AccountCommand;
import com.worldofazaria.dev.core.command.AdminsCommand;
import com.worldofazaria.dev.core.command.AjailCommand;
import com.worldofazaria.dev.core.command.BanCommand;
import com.worldofazaria.dev.core.command.CastCommand;
import com.worldofazaria.dev.core.command.ChatAOOCCommand;
import com.worldofazaria.dev.core.command.ChatAdminCommand;
import com.worldofazaria.dev.core.command.ChatLowCommand;
import com.worldofazaria.dev.core.command.ChatPMCommand;
import com.worldofazaria.dev.core.command.ChatSayCommand;
import com.worldofazaria.dev.core.command.ChatShoutCommand;
import com.worldofazaria.dev.core.command.ChatWhisperCommand;
import com.worldofazaria.dev.core.command.ClassAdminCommand;
import com.worldofazaria.dev.core.command.ClassCommand;
import com.worldofazaria.dev.core.command.DemoteCommand;
import com.worldofazaria.dev.core.command.DoCommand;
import com.worldofazaria.dev.core.command.HelpmeCommand;
import com.worldofazaria.dev.core.command.JailCommand;
import com.worldofazaria.dev.core.command.KickCommand;
import com.worldofazaria.dev.core.command.MeCommand;
import com.worldofazaria.dev.core.command.MoneyCommand;
import com.worldofazaria.dev.core.command.MuteCommand;
import com.worldofazaria.dev.core.command.PVPFlagCommand;
import com.worldofazaria.dev.core.command.PromoteCommand;
import com.worldofazaria.dev.core.command.RaceCommand;
import com.worldofazaria.dev.core.command.ReloadServerCommand;
import com.worldofazaria.dev.core.command.ReportCommand;
import com.worldofazaria.dev.core.command.SpecCommand;
import com.worldofazaria.dev.core.command.TempbanCommand;
import com.worldofazaria.dev.core.command.TestersCommand;
import com.worldofazaria.dev.core.event.ChatHandlerEvents;
import com.worldofazaria.dev.core.event.ClassEvents;
import com.worldofazaria.dev.core.event.CorePlayerEvents;
import com.worldofazaria.dev.core.event.CoreServerEvents;
import com.worldofazaria.inlinepermissions.PlayerPermissionsManager;

public class ACore extends JavaPlugin {
	
	public FileConfiguration config;
	public PluginManager pm;
	public Logger log = Logger.getLogger("Minecraft");
	public static Economy economy = null;
	public PlayerPermissionsManager perms;
	public HashMap<Player,Boolean> adminduty = new HashMap<Player,Boolean>();
	public HashMap<Player,Boolean> FLAG_PVP = new HashMap<Player,Boolean>();
	public HashMap<Player,Boolean> FLAG_AFK = new HashMap<Player,Boolean>();
	
	@Override
	public void onDisable(){
		
	}
	
	@Override
	public void onEnable(){
		// TODO: Organization
		// This is how this plugin is organized.  Reference to this to figure out where to store certain files:
		// Package -- What goes in the package
		// com.worldofazaria.dev.admcmd -- Everything related to Admin Commands, including events
		// com.worldofazaria.dev.chat -- Everything related to Chat Commands and Chatter in general excluding error messages and log output
		// com.worldofazaria.dev.classes -- Everything related to Class commands, events, etc.
		// com.worldofazaria.dev.configuration -- Deprecated.  Nothing goes here.  Everything which was here has been refactored already (DON'T TOUCH)
		// com.worldofazaria.dev.core.command -- Commands for the core
		// com.worldofazaria.dev.core.engine -- Only ACore.class and a few more things go here
		// com.worldofazaria.dev.core.event -- All events for the core go in here
		// com.worldofazaria.dev.core.util -- All utilities (for core, chat, classes, etc.) [A utility is something like a calculator or file for generic constants]
		// com.worldofazaria.inlinepermissions -- All permissions management stuff (don't touch)
		
		// Reference points for floats (scheduler timing):
		// (delay in milliseconds) -> delay in ticks (1 second = 20 ticks)
		// So, if L = 20 (floating point) ticks ==> (delay in milliseconds)L -> delay in seconds
		
		// Notice for schedulers:
		// DO NOT ACCESS BUKKIT API EVENTS (even indirectly) IF YOU ARE USING AN ASYNC SCHEDULER EVENT
		
		
		config = getConfig();
		
		try {
			config.save(this.getDataFolder() + File.separator + "configuration.yml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!config.contains("server.enabled")){
			config.set("server.enabled", true);
			String[] fullaccess = {"supervolcity", "itzfusiionx"};
			config.set("server.full-access", fullaccess);
			
			
			try {
				config.save(this.getDataFolder() + File.separator + "configuration.yml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		perms = new PlayerPermissionsManager(this);
		
		// Module: Autosave
		// Finished
		// Create automatic backups of the world in addition to the normal save, jam them into the main folder
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

			@Override
			public void run() {
				List<World> worlds = Bukkit.getWorlds();
				for(int itterations = 0; itterations <= worlds.size(); itterations++){
					World w = worlds.get(itterations);
					w.save();
					w.setAutoSave(true);
				}
				
			}
			
			
		}, 72000L, 72000L);
		
		// Module: Vault Linking
		// Economy System Linkage (for global compatability of Game Economy)
		// depend: [Vault]
		if(!setupEconomy()){
			log.severe("Vault Economy Linkage failed.");
		}
		
		
		// Module: Unconditional Events
		// Not finished
		// These events are fired without extraneous conditions.  For example, if a player joins the server.
		// These are native bukkit events, not custom events.  Custom Events would be events for InlinePermissions
		pm = this.getServer().getPluginManager();
		// Core Player Events
		pm.registerEvents(new CorePlayerEvents(this), this); // Not finished
		// Core Server Events [also handles AdmCmd's events]
		pm.registerEvents(new CoreServerEvents(this), this); // Not finished
		// Chat Events
		pm.registerEvents(new ChatHandlerEvents(this),this);
		// Class-Related Events
		pm.registerEvents(new ClassEvents(this), this);
		
		
		
		// Module: Commands (Registration)
		// Not finished
		// Register all commands (including ones for addons) here.
		// getCommand("commandname").setExecutor(new CommandExecutorClassHere(this));
		// Make sure the command is also registered with plugin.yml
		// Note - handle all permissions using Inline Permissions ONLY (do not include bukkit perms)
		
		// CORE COMMANDS
		getCommand("me").setExecutor(new MeCommand(this)); // Finished
		getCommand("do").setExecutor(new DoCommand(this)); // Finished
		getCommand("account").setExecutor(new AccountCommand(this)); // Finished
		getCommand("report").setExecutor(new ReportCommand(this)); // Finished
		getCommand("admins").setExecutor(new AdminsCommand(this)); // Finished
		getCommand("testers").setExecutor(new TestersCommand(this)); // Finished
		getCommand("adminduty").setExecutor(new AdminsCommand(this)); // Finished
		getCommand("money").setExecutor(new MoneyCommand(this)); // Finished
		getCommand("helpme").setExecutor(new HelpmeCommand(this)); // Not finished
		getCommand("afk").setExecutor(new AFKFlagCommand(this)); // Not finished
		getCommand("pvp").setExecutor(new PVPFlagCommand(this)); // Not finished
		
		// ADMIN COMMANDS
		getCommand("ban").setExecutor(new BanCommand(this)); // not finished
		getCommand("kick").setExecutor(new KickCommand(this)); // not finished
		getCommand("tempban").setExecutor(new TempbanCommand(this)); // not finished
		getCommand("mute").setExecutor(new MuteCommand(this)); // not finished
		getCommand("ajail").setExecutor(new AjailCommand(this)); // not finished
		getCommand("apromote").setExecutor(new APromoteCommand(this)); // not finished
		getCommand("ademote").setExecutor(new ADemoteCommand(this)); // not finished
		getCommand("promote").setExecutor(new PromoteCommand(this)); // not finished
		getCommand("demote").setExecutor(new DemoteCommand(this)); // not finished
		getCommand("reload").setExecutor(new ReloadServerCommand(this)); // Not finished
		
		// CHAT COMMANDS
		getCommand("w").setExecutor(new ChatWhisperCommand(this)); // not finished
		getCommand("low").setExecutor(new ChatLowCommand(this)); // not finished
		getCommand("l").setExecutor(new ChatSayCommand(this)); // not finished
		getCommand("s").setExecutor(new ChatShoutCommand(this)); // not finished
		getCommand("o").setExecutor(new ChatShoutCommand(this)); // not finished
		getCommand("ao").setExecutor(new ChatAOOCCommand(this)); // not finished
		getCommand("a").setExecutor(new ChatAdminCommand(this)); // not finished
		getCommand("pm").setExecutor(new ChatPMCommand(this)); // not finished
		
		// CHARACTER COMMANDS
		getCommand("jail").setExecutor(new JailCommand(this)); // not finished
		getCommand("setname").setExecutor(new ClassAdminCommand(this)); // not finished
		getCommand("setclass").setExecutor(new ClassAdminCommand(this)); // not finished
		getCommand("setrace").setExecutor(new ClassAdminCommand(this)); // not finished
		getCommand("setspec").setExecutor(new ClassAdminCommand(this)); // not finished
		getCommand("setlevel").setExecutor(new ClassAdminCommand(this)); // not finished
		getCommand("class").setExecutor(new ClassCommand(this)); // not finished
		getCommand("race").setExecutor(new RaceCommand(this)); // not finished
		getCommand("spec").setExecutor(new SpecCommand(this)); // not finished
		getCommand("cast").setExecutor(new CastCommand(this)); // not finished
		
		
		
	}
	private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

		
}
