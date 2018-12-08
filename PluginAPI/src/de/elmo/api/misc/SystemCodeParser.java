package de.elmo.api.misc;

public class SystemCodeParser {
	private String message;

	public SystemCodeParser(String message) {
		this.message = message;
	}

	public String toAnd() {
		return this.message.replaceAll("§", "&");
	}

	public String toParagraph() {
		return this.message.replaceAll("&", "§");
	}
}
