package de.elmo.api.managers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;

import de.elmo.api.misc.SystemCodeParser;
import de.elmo.api.utils.FileOptions;

public class YamlManager {
	private File file;
	private YamlConfiguration yamlConfiguration;

	public YamlManager(File file) {
		this.file = file;
		this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);
	}

	public void setOption(FileOptions fileOptions, Object value) {
		if (fileOptions.equals(FileOptions.COPYDEFAULTS)) {
			this.yamlConfiguration.options().copyDefaults(((Boolean) value).booleanValue());
			saveYamlConfiguration();
		} else if (fileOptions.equals(FileOptions.HEADER)) {
			this.yamlConfiguration.options().header((String) value);
			saveYamlConfiguration();
		}
	}

	public void addDefault(String path, Object value) {
		this.yamlConfiguration.addDefault(path, value);
		saveYamlConfiguration();
	}

	public void addDefaultSystemCodeParser(String path, Object value) {
		String parsed = new SystemCodeParser((String) value).toAnd();
		this.yamlConfiguration.addDefault(path, parsed);
		saveYamlConfiguration();
	}

	public void set(String path, Object value) {
		this.yamlConfiguration.set(path, value);
		saveYamlConfiguration();
	}

	public void setSystemCodeParser(String path, Object value) {
		String parsed = new SystemCodeParser((String) value).toAnd();
		this.yamlConfiguration.set(path, parsed);
		saveYamlConfiguration();
	}

	public SystemCodeParser getSystemCodeParser(String path) {
		return new SystemCodeParser(this.yamlConfiguration.getString(path));
	}

	public String getString(String path) {
		return this.yamlConfiguration.getString(path);
	}

	public Integer getInteger(String path) {
		return Integer.valueOf(this.yamlConfiguration.getInt(path));
	}

	public List getList(String path) {
		return this.yamlConfiguration.getList(path);
	}

	public Object get(String path) {
		return this.yamlConfiguration.get(path);
	}

	public YamlConfiguration getYamlConfiguration() {
		return this.yamlConfiguration;
	}

	private void saveYamlConfiguration() {
		try {
			this.yamlConfiguration.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
