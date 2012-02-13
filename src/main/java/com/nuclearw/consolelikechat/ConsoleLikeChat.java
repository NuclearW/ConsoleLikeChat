package com.nuclearw.consolelikechat;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ConsoleLikeChat extends JavaPlugin {
	static String mainDirectory = "plugins" + File.separator + "ConsoleLikeChat";
	static File versionFile = new File(mainDirectory + File.separator + "VERSION");

	private final ConsoleLikeChatServerListener serverListener = new ConsoleLikeChatServerListener(this);

	Logger log = Logger.getLogger("Minecraft");

	public void onEnable() {
		new File(mainDirectory).mkdir();

		if(!versionFile.exists()) {
			updateVersion();
		} else {
			String vnum = readVersion();
			if(vnum.equalsIgnoreCase("0.1")) updateVersion();
		}

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(serverListener,this);

		log.info("[ConsoleLikeChat] Version "+this.getDescription().getVersion()+" enabled.");
	}

	public void onDisable() {
		log.info("[ConsoleLikeChat] Version "+this.getDescription().getVersion()+" disabled.");
	}

	public void updateVersion() {
		try {
			versionFile.createNewFile();
			BufferedWriter vout = new BufferedWriter(new FileWriter(versionFile));
			vout.write(this.getDescription().getVersion());
			vout.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (SecurityException ex) {
			ex.printStackTrace();
		}
	}

	public String readVersion() {
		byte[] buffer = new byte[(int) versionFile.length()];
		BufferedInputStream f = null;
		try {
			f = new BufferedInputStream(new FileInputStream(versionFile));
			f.read(buffer);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (f != null) try { f.close(); } catch (IOException ignored) { }
		}

		return new String(buffer);
	}
}