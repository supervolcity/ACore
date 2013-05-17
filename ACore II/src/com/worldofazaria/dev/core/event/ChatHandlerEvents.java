package com.worldofazaria.dev.core.event;

import org.bukkit.event.Listener;

import com.worldofazaria.dev.core.engine.ACore;

public class ChatHandlerEvents implements Listener {

	ACore plugin;
	public ChatHandlerEvents(ACore plugin) {
		this.plugin = plugin;
	}

}
