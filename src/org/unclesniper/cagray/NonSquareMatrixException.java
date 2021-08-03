package org.unclesniper.cagray;

public class NonSquareMatrixException extends MathException {

	public NonSquareMatrixException(String message) {
		super((message == null || message.length() == 0 ? "" : message + ": ") + "Matrix is not square");
	}

}
