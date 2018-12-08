package de.elmo.api.misc;

import java.io.File;

import de.elmo.api.managers.YamlManager;
import de.elmo.api.utils.DataFileType;
import de.elmo.api.utils.LanguageType;

public class LanguageParser {
	private LanguageType languageType;
	private File dataFolder;
	private YamlManager yamlManager;

	public LanguageParser(LanguageType languageType, File dataFolder) {
		this.languageType = languageType;
		this.dataFolder = dataFolder;
		DataFile dataFile = new DataFile(DataFileType.YAMLFILE, "language-" + languageType.getLanguage() + ".yml",
				dataFolder);
		this.yamlManager = new YamlManager((File) dataFile.generate());
	}

	public void add(String key, String value) {
		this.yamlManager.setSystemCodeParser(key, value);
	}

	public void remove(String key) {
		this.yamlManager.set(key, "");
	}

	public YamlManager getYamlManager() {
		return this.yamlManager;
	}

	public LanguageType getLanguageType() {
		return this.languageType;
	}
}
