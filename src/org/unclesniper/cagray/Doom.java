package org.unclesniper.cagray;

public class Doom extends Error {

	public Doom(String message) {
		this(message, null);
	}

	public Doom(String message, Throwable cause) {
		super("Programmer fsck(8)ed up: " + message, cause);
	}

}
