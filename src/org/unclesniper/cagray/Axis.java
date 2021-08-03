package org.unclesniper.cagray;

public enum Axis {

	ROW("row", "x", "horizontal"),
	COLUMN("column", "y", "vertical");

	private final String nameRowColumn;

	private final String nameXY;

	private final String nameHorizontalVertical;

	private Axis(String nameRowColumn, String nameXY, String nameHorizontalVertical) {
		this.nameRowColumn = nameRowColumn;
		this.nameXY = nameXY;
		this.nameHorizontalVertical = nameHorizontalVertical;
	}

	public String getNameRowColumn() {
		return nameRowColumn;
	}

	public String getNameXY() {
		return nameXY;
	}

	public String getNameHorizontalVertical() {
		return nameHorizontalVertical;
	}

	public Axis flip() {
		return this == Axis.ROW ? Axis.COLUMN : Axis.ROW;
	}

}
