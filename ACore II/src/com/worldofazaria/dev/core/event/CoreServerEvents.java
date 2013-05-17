package com.worldofazaria.dev.core.event;

import org.bukkit.event.Listener;

import com.worldofazaria.dev.core.engine.ACore;

public class CoreServerEvents implements Listener {

	ACore plugin;
	public CoreServerEvents(ACore plugin) {
		this.plugin = plugin;
	}

}
