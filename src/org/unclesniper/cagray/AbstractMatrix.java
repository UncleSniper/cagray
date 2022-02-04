package org.unclesniper.cagray;

public abstract class AbstractMatrix extends AbstractCObject implements Matrix {

	public AbstractMatrix() {}

	@Override
	public Matrix mul(Matrix other) throws MathException {
		return MatrixOps.mul(this, other);
	}

	@Override
	public Matrix add(Matrix other) throws MathException {
		return MatrixOps.add(this, other);
	}

	@Override
	public Matrix sub(Matrix other) throws MathException {
		return MatrixOps.sub(this, other);
	}

	@Override
	public Matrix inv() throws MathException {
		return MatrixOps.inv(this);
	}

	@Override
	public Matrix neg() {
		return MatrixOps.neg(this);
	}

	@Override
	public double det() throws MathException {
		return MatrixOps.det(this);
	}

}
