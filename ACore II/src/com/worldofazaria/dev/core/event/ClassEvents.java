package com.worldofazaria.dev.core.event;

import org.bukkit.event.Listener;

import com.worldofazaria.dev.core.engine.ACore;

public class ClassEvents implements Listener {

	ACore plugin;
	public ClassEvents(ACore plugin) {
		this.plugin = plugin;
	}

}
