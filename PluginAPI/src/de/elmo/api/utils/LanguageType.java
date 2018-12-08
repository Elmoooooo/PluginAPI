package de.elmo.api.utils;

public enum LanguageType {
	GERMAN("german"), ENGLISH("english");

	private String language;

	private LanguageType(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return this.language;
	}
}
