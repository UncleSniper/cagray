package org.unclesniper.cagray;

import static org.unclesniper.util.ArgUtils.notNull;
import static org.unclesniper.util.ArgUtils.notNegative;

public class MismatchedDimensionsException extends MathException {

	private final Axis leftAxis;

	private final int leftDimension;

	private final Axis rightAxis;

	private final int rightDimension;

	public MismatchedDimensionsException(String message, Axis leftAxis, int leftDimension,
			Axis rightAxis, int rightDimension) {
		super(MismatchedDimensionsException.makeMessage(message, leftAxis, leftDimension,
				rightAxis, rightDimension));
		this.leftAxis = notNull(leftAxis, "leftAxis");
		this.leftDimension = notNegative(leftDimension, "leftDimension");
		this.rightAxis = notNull(rightAxis, "rightAxis");
		this.rightDimension = notNegative(rightDimension, "rightDimension");
	}

	private static String makeMessage(String message, Axis leftAxis, int leftDimension,
			Axis rightAxis, int rightDimension) {
		if(leftAxis == null || rightAxis == null)
			return null;
		StringBuilder builder = new StringBuilder();
		if(message != null && message.length() > 0) {
			builder.append(message);
			builder.append(": ");
		}
		builder.append("Left-hand operand has ");
		builder.append(String.valueOf(leftDimension));
		builder.append(' ');
		builder.append(leftAxis.getNameRowColumn());
		if(leftDimension != 1)
			builder.append('s');
		builder.append(", right-hand operand has ");
		builder.append(String.valueOf(rightDimension));
		builder.append(' ');
		builder.append(rightAxis.getNameRowColumn());
		if(rightDimension != 1)
			builder.append('s');
		return builder.toString();
	}

}
