package org.unclesniper.cagray;

public interface Matrix extends CObject {

	int getRows();

	int getColumns();

	double getComponent(int row, int column);

	void setComponent(int row, int column, double value);

	Matrix transpose();

}
