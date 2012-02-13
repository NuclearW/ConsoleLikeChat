package com.nuclearw.consolelikechat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;

public class ConsoleLikeChatServerListener implements Listener {
	public static ConsoleLikeChat plugin;

	public ConsoleLikeChatServerListener(ConsoleLikeChat instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onServerCommand(ServerCommandEvent event) {
		String cmd = event.getCommand();
		if(cmd.startsWith("/")) {
			cmd = cmd.substring(1);
		} else {
			cmd = "say "+cmd;
		}
		event.setCommand(cmd);
	}
}