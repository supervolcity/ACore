package com.worldofazaria.dev.core.util;

import org.bukkit.ChatColor;

public class MoneyFormat {
	
	public static String format(int money){
		int gold = 0;
		int silver = 0;
		int copper = 0;
		
		while(money > 0){
			copper++;
			
			if(copper == 100){
				silver++;
				copper = 0;
			}
			if(silver == 100){
				gold++;
				silver = 0;
			}
			
			money--;
		}
		
		return "" + gold + " Gold," + silver + " Silver," + copper + "Copper";
	}
	public static String coloredFormat(int money){
		int gold = 0;
		int silver = 0;
		int copper = 0;
		
		while(money > 0){
			copper++;
			
			if(copper == 100){
				silver++;
				copper = 0;
			}
			if(silver == 100){
				gold++;
				silver = 0;
			}
			
			money--;
		}
		
		return "" + ChatColor.YELLOW + gold + ChatColor.WHITE + ", " + ChatColor.GRAY + silver + ChatColor.WHITE + ", " + ChatColor.GOLD + copper;
	}
}
