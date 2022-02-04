package org.unclesniper.cagray;

public interface Matrix extends CObject {

	int getRows();

	int getColumns();

	double getComponent(int row, int column);

	void setComponent(int row, int column, double value);

	Matrix transpose();

	Matrix mul(Matrix other) throws MathException;

	Matrix add(Matrix other) throws MathException;

	Matrix sub(Matrix other) throws MathException;

	Matrix inv() throws MathException;

	double det() throws MathException;

	boolean isZero();

	public static Matrix make(int rows, int columns) {
		if(columns == 1)
			return new CVector(rows, false);
		else if(rows == 1)
			return new CVector(columns, true);
		else
			return new CMatrix(rows, columns);
	}

}
