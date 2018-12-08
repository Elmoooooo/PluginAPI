package de.elmo.api.misc;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.bukkit.entity.Player;

import de.elmo.api.managers.YamlManager;
import de.elmo.api.utils.DataFileType;
import de.elmo.api.utils.LanguageType;

public class LanguagePrinter {
	private File dataFolder;
	private Player p;
	private String key;
	private YamlManager yamlManager;
	private String value;

	public LanguagePrinter(File dataFolder, Player p, String key) {
		this.dataFolder = dataFolder;
		this.p = p;
		this.key = key;

		LanguageType languageType = LanguageType.ENGLISH;

		if (getLanguage().equalsIgnoreCase("de")) {
			languageType = LanguageType.GERMAN;
		} else if (getLanguage().equalsIgnoreCase("en")) {
			languageType = LanguageType.ENGLISH;
		}

		DataFile dataFile = new DataFile(DataFileType.YAMLFILE, "language-" + languageType.getLanguage() + ".yml",
				dataFolder);
		this.yamlManager = new YamlManager((File) dataFile.generate());
		try {
			this.value = this.yamlManager.getSystemCodeParser(key).toParagraph();
		} catch (Exception e) {
			this.value = "Error while reading language file.";
		}
	}

	public String toString() {
		return this.value;
	}

	private String getLanguage() {
		try {
			Object obj = getMethod("getHandle", this.p.getClass()).invoke(this.p, (Object[]) null);
			Field f = obj.getClass().getDeclaredField("locale");
			f.setAccessible(true);
			return (String) f.get(obj);
		} catch (Exception e) {
		}
		return "en";
	}

	private Method getMethod(String n, Class<?> c) {
		for (Method m : c.getDeclaredMethods()) {
			if (m.getName().equals(n))
				return m;
		}
		return null;
	}
}
