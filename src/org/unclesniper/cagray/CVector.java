package org.unclesniper.cagray;

public class CVector implements Matrix {

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

	public boolean isColumn() {
		return column;
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
	public CVector transpose() {
		return new CVector(components, !column);
	}

}
