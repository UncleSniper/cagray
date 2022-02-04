package org.unclesniper.cagray;

import java.io.Writer;
import java.io.IOException;
import org.unclesniper.cagray.util.PrintUtils;

import static org.unclesniper.util.ArgUtils.notNull;

public class CMatrix extends AbstractMatrix {

	private final double[] components;

	private final int columns;

	public CMatrix(int rows, int columns) {
		if(rows < 1)
			throw new IllegalArgumentException("Cannot create matrix with " + rows + " rows");
		if(columns < 1)
			throw new IllegalArgumentException("Cannot create matrix with " + columns + " columns");
		components = new double[rows * columns];
		this.columns = columns;
	}

	public CMatrix(int dimension) {
		this(dimension, dimension);
	}

	public CMatrix(double[] components, int rows, int columns) {
		if(rows < 1)
			throw new IllegalArgumentException("Cannot create matrix with " + rows + " rows");
		if(columns < 1)
			throw new IllegalArgumentException("Cannot create matrix with " + columns + " columns");
		int count = rows * columns;
		if(components.length < count)
			throw new IllegalArgumentException("Insufficient components provided: " + components.length
					+ " < " + rows + " * " + columns);
		this.components = new double[count];
		for(int i = 0; i < count; ++i)
			this.components[i] = components[i];
		this.columns = columns;
	}

	private CMatrix(double[] components, int columns) {
		int rows = components.length / columns;
		this.components = new double[components.length];
		for(int r = 0; r < rows; ++r) {
			for(int c = 0; c < columns; ++c)
				this.components[c * columns + r] = components[r * rows + c];
		}
		this.columns = rows;
	}

	@Override
	public int getRows() {
		return components.length / columns;
	}

	@Override
	public int getColumns() {
		return columns;
	}

	@Override
	public double getComponent(int row, int column) {
		if(row < 0)
			throw new IllegalArgumentException("Illegal row index: " + row + " < 0");
		int rows = components.length / columns;
		if(row >= rows)
			throw new IllegalArgumentException("Illegal row index: " + row + " >= " + rows);
		if(column < 0)
			throw new IllegalArgumentException("Illegal column index: " + column + " < 0");
		if(column >= columns)
			throw new IllegalArgumentException("Illegal column index: " + column + " >= " + columns);
		return components[row * rows + column];
	}

	@Override
	public void setComponent(int row, int column, double value) {
		if(row < 0)
			throw new IllegalArgumentException("Illegal row index: " + row + " < 0");
		int rows = components.length / columns;
		if(row >= rows)
			throw new IllegalArgumentException("Illegal row index: " + row + " >= " + rows);
		if(column < 0)
			throw new IllegalArgumentException("Illegal column index: " + column + " < 0");
		if(column >= columns)
			throw new IllegalArgumentException("Illegal column index: " + column + " >= " + columns);
		components[row * rows + column] = value;
	}

	@Override
	public CMatrix transpose() {
		return new CMatrix(components, columns);
	}

	@Override
	public boolean isZero() {
		for(double c : components) {
			if(c != 0.0)
				return false;
		}
		return true;
	}

	@Override
	public void printTo(Writer out, int level) throws IOException {
		notNull(out, "out").write("[[");
		int rows = components.length / columns;
		for(int r = 0; r < rows; ++r) {
			if(r > 0) {
				out.write(']');
				PrintUtils.eolIndent(out, level);
				out.write(" [");
			}
			for(int c = 0; c < columns; ++c) {
				if(c > 0)
					out.write(", ");
				out.write(String.valueOf(components[r * rows + c]));
			}
		}
		out.write("]]");
	}

	@Override
	public String getTypeName() {
		StringBuilder builder = new StringBuilder();
		builder.append("Matrix(");
		builder.append(String.valueOf(components.length / columns));
		builder.append(", ");
		builder.append(String.valueOf(columns));
		builder.append(')');
		return builder.toString();
	}

}
