package org.unclesniper.cagray;

public class ZeroVectorException extends MathException {

	public ZeroVectorException(String message) {
		super((message == null || message.length() == 0 ? "" : message + ": ") + "Zero vector has no direction");
	}

}
