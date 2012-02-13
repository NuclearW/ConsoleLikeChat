package com.nuclearw.consolelikechat;

import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerListener;

public class ConsoleLikeChatServerListener extends ServerListener {
	public static ConsoleLikeChat plugin;

	public ConsoleLikeChatServerListener(ConsoleLikeChat instance) {
		plugin = instance;
	}

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