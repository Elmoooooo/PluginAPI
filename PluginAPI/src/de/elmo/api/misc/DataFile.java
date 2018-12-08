package de.elmo.api.misc;

import java.io.File;
import java.io.IOException;

import de.elmo.api.managers.YamlManager;
import de.elmo.api.utils.DataFileType;

public class DataFile {
	private DataFileType dataFileType;
	private String fileName;
	private File dataFolder;
	private File file;

	public DataFile(DataFileType dataFileType, String fileName, File dataFolder) {
		this.dataFileType = dataFileType;
		this.fileName = fileName;
		this.dataFolder = dataFolder;

		if (!dataFolder.exists()) {
			dataFolder.mkdir();
		}

		this.file = new File(dataFolder, fileName);

		if (!this.file.exists()) {
			try {
				this.file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Object generate() {
		if (this.dataFileType.equals(DataFileType.YAMLFILE)) {
			return new YamlManager(this.file);
		}
		return null;
	}

	public DataFileType getDataFileType() {
		/* 55 */ return this.dataFileType;
	}

	public String getFileName() {
		return this.fileName;
	}

	public File getDataFolder() {
		return this.dataFolder;
	}

	public File getFile() {
		return this.file;
	}
}
