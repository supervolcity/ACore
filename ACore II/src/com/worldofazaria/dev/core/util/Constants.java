package com.worldofazaria.dev.core.util;

import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Constants {
	
	// Chat Ranges
	public static int CHAT_RANGE_ME = 30;
	public static int CHAT_RANGE_SHOUT_HIGH = 30;
	public static int CHAT_RANGE_SHOUT_MED = 40;
	public static int CHAT_RANGE_SHOUT_LOW = 50;
	public static int CHAT_RANGE_SHOUT_MAX = 55;
	public static int CHAT_RANGE_DO = 30;
	public static int CHAT_RANGE_WHISPER = 2;
	public static int CHAT_RANGE_LOW_HIGH = 3;
	public static int CHAT_RANGE_LOW_MED = 5;
	public static int CHAT_RANGE_LOW_LOW = 7;
	public static int CHAT_RANGE_LOW_MAX = 10;
	public static int CHAT_RANGE_SAY_HIGH = 10;
	public static int CHAT_RANGE_SAY_MED = 15;
	public static int CHAT_RANGE_SAY_LOW = 20;
	public static int CHAT_RANGE_SAY_MAX = 25;
	
	// Special Locations
	public static Location LOCATION_ADMINJAIL = new Location(null, 0.00, 0.00, 0.00);
	public static Location LOCATION_PLAYERJAIL = new Location(null, 0.00, 0.00, 0.00);
	public static Location LOCATION_SPAWN = new Location(null, 0.00, 0.00, 0.00);
	
	// Money
	public static String[] MONEY_CURRENCYNAME = {"Copper", "Silver", "Gold"};
	// MONEY_CURRENCYNAME[0] = "Copper";
	// MONEY_CURRENCYNAME[1] = "Silver";
	
	// String names for ranks
	// Player Ranks (admin level -1)
	public static String RANK_PLAYER_DEFAULT = "Visitor";
	public static String RANK_PLAYER_NORMAL = "Player";
	public static String RANK_PLAYER_VIP = "VIP";
	public static String RANK_PLAYER_DONATOR1 = "Donator";
	public static String RANK_PLAYER_DONATOR2 = "Sponsor";
	// Admin Ranks
	public static String RANK_ADMIN0 = "Tester"; // handles admin level 0
	public static String RANK_ADMIN1 = "Junior Administrator"; // admin level 1
	public static String RANK_ADMIN2 = "Administrator"; // admin level 2
	public static String RANK_ADMIN3 = "Senior Administrator"; // admin level 3
	public static String RANK_ADMIN4 = "Head Administrator"; // admin level 4
	public static String RANK_ADMIN5 = "Executive Administrator"; // handles admin level >= 5
	public static String RANK_PROMOTED_ADMIN = "" + ChatColor.GRAY + "You have been promoted to a higher admin level.";
	public static String RANK_DEMOTED_ADMIN = "" + ChatColor.GRAY + "You have been demoted to a lower admin level.";
	// Error Messages
	public static String ERR_PERMS_DENIED = "" + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "You do not have permission for that feature. Contact a Level 2 Adminstrator or higher for assistance.";
	public static String ERR_CMD_BLOCKED = "" + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "You are currently blocked from using that feature. Contact a Level 1 Administrator or higher for assistance.";
	public static String ERR_AJAIL = "" + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "You cannot do that while in admin jail. Contact a Level 1 Administrator or higher for assistance.";
	public static String ERR_AJAIL_WELCOME1 = "" + ChatColor.DARK_RED + "Welcome to Admin Jail. You are here for breaking the rules in a severe manner. During your time, you should review the rules.";
	public static String ERR_AJAIL_WELCOME2 = "" + ChatColor.DARK_RED + "When you are ready to be released, use the /report command to appeal to a Tester or Administrator.";
	public static String ERR_JAIL = "" + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "You cannot do that while in player jail. Contact a Level 1 Administrator or higher for assistance.";
	public static String WARN_UNAVAILABLE = "" + ChatColor.GOLD + "Warning: " + ChatColor.YELLOW + "This feature is not available to you at this time. Contact a Tester for assistance.";
	public static String ERR_MUTED = "" + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "You are currently muted. Contact a Level 1 Administrator or higher for assistance.";
	
	public static String ERR_COOLDOWN = "" + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "That ability is currently on cooldown.";
	public static String ERR_SYNTAX = "" + ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Syntax error in command. Please check command usage and try again.";
}
