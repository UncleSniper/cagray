package org.unclesniper.cagray;

import static org.unclesniper.util.ArgUtils.notNull;

public class MatrixOps {

	private static final double NEAR_DEGENERATE_TOLERANCE = 1.0E-7;

	private MatrixOps() {}

	public static Matrix mul(Matrix lhs, Matrix rhs) throws MismatchedDimensionsException {
		int lrows = notNull(lhs, "lhs").getRows(), lcols = lhs.getColumns();
		int rrows = notNull(rhs, "rhs").getRows(), rcols = rhs.getColumns();
		if(lcols != rrows)
			throw new MismatchedDimensionsException("Cannot multiply " + lhs.getTypeName() + " with "
					+ rhs.getTypeName(), Axis.COLUMN, lcols, Axis.ROW, rrows);
		Matrix res = Matrix.make(lrows, rcols);
		for(int drow = 0; drow < lrows; ++drow) {
			for(int dcol = 0; dcol < rcols; ++dcol) {
				double accu = 0.0;
				for(int i = 0; i < lcols; ++i)
					accu += lhs.getComponent(drow, i) * rhs.getComponent(i, dcol);
				res.setComponent(drow, dcol, accu);
			}
		}
		return res;
	}

	public static Matrix add(Matrix lhs, Matrix rhs) throws MismatchedDimensionsException {
		int lrows = notNull(lhs, "lhs").getRows(), lcols = lhs.getColumns();
		int rrows = notNull(rhs, "rhs").getRows(), rcols = rhs.getColumns();
		if(lrows != rrows)
			throw new MismatchedDimensionsException("Cannot add " + lhs.getTypeName() + " to "
					+ rhs.getTypeName(), Axis.ROW, lrows, Axis.ROW, rrows);
		if(lcols != rcols)
			throw new MismatchedDimensionsException("Cannot add " + lhs.getTypeName() + " to "
					+ rhs.getTypeName(), Axis.COLUMN, lcols, Axis.COLUMN, rcols);
		Matrix res = Matrix.make(lrows, lcols);
		for(int drow = 0; drow < lrows; ++drow) {
			for(int dcol = 0; dcol < lcols; ++dcol)
				res.setComponent(drow, dcol, lhs.getComponent(drow, dcol) + rhs.getComponent(drow, dcol));
		}
		return res;
	}

	public static Matrix sub(Matrix lhs, Matrix rhs) throws MismatchedDimensionsException {
		int lrows = notNull(lhs, "lhs").getRows(), lcols = lhs.getColumns();
		int rrows = notNull(rhs, "rhs").getRows(), rcols = rhs.getColumns();
		if(lrows != rrows)
			throw new MismatchedDimensionsException("Cannot subtract " + rhs.getTypeName() + " from "
					+ lhs.getTypeName(), Axis.ROW, lrows, Axis.ROW, rrows);
		if(lcols != rcols)
			throw new MismatchedDimensionsException("Cannot subtract " + rhs.getTypeName() + " from "
					+ lhs.getTypeName(), Axis.COLUMN, lcols, Axis.COLUMN, rcols);
		Matrix res = Matrix.make(lrows, lcols);
		for(int drow = 0; drow < lrows; ++drow) {
			for(int dcol = 0; dcol < lcols; ++dcol)
				res.setComponent(drow, dcol, lhs.getComponent(drow, dcol) - rhs.getComponent(drow, dcol));
		}
		return res;
	}

	private static void lupDecompose(double[][] a, int[] p, String degenerateMessage)
			throws DegenerateMatrixException {
		for(int i = 0; i <= a.length; ++i)
			p[i] = i;
		for(int i = 0; i < a.length; ++i) {
			double maxA = 0.0;
			int imax = i;
			for(int k = i; k < a.length; ++k) {
				double absA = Math.abs(a[k][i]);
				if(absA > maxA) {
					maxA = absA;
					imax = k;
				}
			}
			if(maxA < MatrixOps.NEAR_DEGENERATE_TOLERANCE)
				throw new DegenerateMatrixException(degenerateMessage);
			if(imax != i) {
				int j = p[i];
				p[i] = p[imax];
				p[imax] = j;
				double[] ptr = a[i];
				a[i] = a[imax];
				a[imax] = ptr;
				++p[a.length];
			}
			for(int j = i + 1; j < a.length; ++j) {
				a[j][i] /= a[i][i];
				for(int k = i + 1; k < a.length; ++k)
					a[j][k] -= a[j][i] * a[i][k];
			}
		}
	}

	private static Matrix lupInvert(double[][] a, int[] p) {
		Matrix ia = Matrix.make(a.length, a.length);
		for(int j = 0; j < a.length; ++j) {
			for(int i = 0; i < a.length; ++i) {
				ia.setComponent(i, j, p[i] == j ? 1.0 : 0.0);
				for(int k = 0; k < i; ++k)
					ia.setComponent(i, j, ia.getComponent(i, j) - a[i][k] * ia.getComponent(k, j));
			}
			for(int i = a.length - 1; i >= 0; --i) {
				for(int k = i + 1; k < a.length; ++k)
					ia.setComponent(i, j, ia.getComponent(i, j) - a[i][k] * ia.getComponent(k, j));
				ia.setComponent(i, j, ia.getComponent(i, j) / a[i][i]);
			}
		}
		return ia;
	}

	private static double lupDeterminant(double[][] a, int[] p) {
		double det = a[0][0];
		for(int i = 1; i < a.length; ++i)
			det *= a[i][i];
		return (p[a.length] - a.length) % 2 == 0 ? det : -det;
	}

	private static double[][] prepareLUP(Matrix matrix, int rows) {
		double[][] a = new double[rows][];
		for(int row = 0; row < rows; ++row) {
			double[] rcomp = new double[rows];
			for(int col = 0; col < rows; ++col)
				rcomp[col] = matrix.getComponent(row, col);
			a[row] = rcomp;
		}
		return a;
	}

	public static Matrix inv(Matrix matrix) throws NonSquareMatrixException, DegenerateMatrixException {
		int rows = notNull(matrix, "matrix").getRows();
		String failMessage = "Cannot invert " + matrix.getTypeName();
		if(rows != matrix.getColumns() || rows == 0)
			throw new NonSquareMatrixException(failMessage);
		double[][] a = MatrixOps.prepareLUP(matrix, rows);
		int[] p = new int[rows + 1];
		MatrixOps.lupDecompose(a, p, failMessage);
		return MatrixOps.lupInvert(a, p);
	}

	public static Matrix neg(Matrix matrix) {
		int rows = notNull(matrix, "matrix").getRows(), cols = matrix.getColumns();
		Matrix res = Matrix.make(rows, cols);
		for(int row = 0; row < rows; ++row) {
			for(int col = 0; col < cols; ++col)
				res.setComponent(row, col, -matrix.getComponent(row, col));
		}
		return res;
	}

	public static double det(Matrix matrix) throws NonSquareMatrixException, DegenerateMatrixException {
		int rows = notNull(matrix, "matrix").getRows();
		String failMessage = "Cannot compute determinant of " + matrix.getTypeName();
		if(rows != matrix.getColumns() || rows == 0)
			throw new NonSquareMatrixException(failMessage);
		double[][] a = MatrixOps.prepareLUP(matrix, rows);
		int[] p = new int[rows + 1];
		MatrixOps.lupDecompose(a, p, failMessage);
		return MatrixOps.lupDeterminant(a, p);
	}

}
