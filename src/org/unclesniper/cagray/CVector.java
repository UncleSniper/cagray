package org.unclesniper.cagray;

import java.io.Writer;
import java.io.IOException;

import static org.unclesniper.util.ArgUtils.notNull;

public class CVector extends AbstractMatrix {

	private final double[] components;

	private final boolean column;

	public CVector(int dimension, boolean column) {
		if(dimension < 1)
			throw new IllegalArgumentException("Cannot create " + dimension + "-dimensional vector");
		components = new double[dimension];
		this.column = column;
	}

	public CVector(double[] components, boolean column) {
		int dimension = components == null ? 0 : components.length;
		if(dimension < 1)
			throw new IllegalArgumentException("Cannot create " + dimension + "-dimensional vector");
		this.components = new double[components.length];
		for(int i = 0; i < components.length; ++i)
			this.components[i] = components[i];
		this.column = column;
	}

	public int getDimension() {
		return components.length;
	}

	public double getComponent(int index) {
		if(index < 0)
			throw new IllegalArgumentException("Illegal vector index: " + index + " < 0");
		if(index >= components.length)
			throw new IllegalArgumentException("Illegal vector index: " + index + " >= " + components.length);
		return components[index];
	}

	public void setComponent(int index, double value) {
		if(index < 0)
			throw new IllegalArgumentException("Illegal vector index: " + index + " < 0");
		if(index >= components.length)
			throw new IllegalArgumentException("Illegal vector index: " + index + " >= " + components.length);
		components[index] = value;
	}

	public boolean isColumn() {
		return column;
	}

	public Axis getAxis() {
		return column ? Axis.COLUMN : Axis.ROW;
	}

	public double dot(CVector other) throws MathException {
		return VectorOps.dot(this, other);
	}

	public double getLength() {
		return VectorOps.length(this);
	}

	public CVector add(CVector other) throws MathException {
		return VectorOps.add(this, other);
	}

	public CVector sub(CVector other) throws MathException {
		return VectorOps.sub(this, other);
	}

	@Override
	public CVector neg() {
		return VectorOps.neg(this);
	}

	@Override
	public int getRows() {
		return column ? components.length : 1;
	}

	@Override
	public int getColumns() {
		return column ? 1 : components.length;
	}

	@Override
	public double getComponent(int row, int column) {
		if(row < 0)
			throw new IllegalArgumentException("Illegal row index: " + row + " < 0");
		if(column < 0)
			throw new IllegalArgumentException("Illegal column index: " + column + " < 0");
		if(this.column) {
			if(row >= components.length)
				throw new IllegalArgumentException("Illegal row index: " + row + " >= " + components.length);
			if(column > 0)
				throw new IllegalArgumentException("Illegal column index: " + column + " > 0");
			return components[row];
		}
		else {
			if(row > 0)
				throw new IllegalArgumentException("Illegal row index: " + row + " > 0");
			if(column >= components.length)
				throw new IllegalArgumentException("Illegal column index: " + column + " >= " + components.length);
			return components[column];
		}
	}

	@Override
	public void setComponent(int row, int column, double value) {
		if(row < 0)
			throw new IllegalArgumentException("Illegal row index: " + row + " < 0");
		if(column < 0)
			throw new IllegalArgumentException("Illegal column index: " + column + " < 0");
		if(this.column) {
			if(row >= components.length)
				throw new IllegalArgumentException("Illegal row index: " + row + " >= " + components.length);
			if(column > 0)
				throw new IllegalArgumentException("Illegal column index: " + column + " > 0");
			components[row] = value;
		}
		else {
			if(row > 0)
				throw new IllegalArgumentException("Illegal row index: " + row + " > 0");
			if(column >= components.length)
				throw new IllegalArgumentException("Illegal column index: " + column + " >= " + components.length);
			components[column] = value;
		}
	}

	@Override
	public CVector transpose() {
		return new CVector(components, !column);
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
		notNull(out, "out").write('[');
		for(int i = 0; i < components.length; ++i) {
			if(i > 0)
				out.write(", ");
			out.write(String.valueOf(components[i]));
		}
		out.write(']');
	}

	@Override
	public String getTypeName() {
		StringBuilder builder = new StringBuilder();
		builder.append(column ? "ColumnVector(" : "RowVector(");
		builder.append(String.valueOf(components.length));
		builder.append(')');
		return builder.toString();
	}

}
