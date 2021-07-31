package org.unclesniper.cagray.util;

public class ExceptionUtils {

	private ExceptionUtils() {}

	public static String chainMessage(Throwable cause) {
		if(cause == null)
			return "";
		String message = cause.getMessage();
		if(message == null || message.length() == 0)
			return "";
		return ": " + message;
	}

}
