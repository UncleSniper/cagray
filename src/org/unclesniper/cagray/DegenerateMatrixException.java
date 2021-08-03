package org.unclesniper.cagray;

public class DegenerateMatrixException extends MathException {

	public DegenerateMatrixException(String message) {
		super((message == null || message.length() == 0 ? "" : message + ": ") + "Matrix is degenerate");
	}

}
