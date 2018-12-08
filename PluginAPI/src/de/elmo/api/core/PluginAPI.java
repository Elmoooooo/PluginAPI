package de.elmo.api.core;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginAPI extends JavaPlugin {

	private String prefix;

	public void onEnable() {
		this.prefix = "PluginAPI >>";
		System.out.println(this.prefix + "API gestartet.");
	}

	public void onDisable() {
		System.out.println(this.prefix + "API gestoppt.");
	}

	public String getPrefix() {
		return this.prefix;
	}
}
