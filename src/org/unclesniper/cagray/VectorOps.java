package org.unclesniper.cagray;

public class VectorOps {

	private VectorOps() {}

	public static double dot(CVector lhs, CVector rhs) throws MismatchedDimensionsException {
		if(lhs == null)
			throw new IllegalArgumentException("Left-hand vector cannot be null");
		if(rhs == null)
			throw new IllegalArgumentException("Right-hand vector cannot be null");
		int dim = lhs.getDimension();
		if(rhs.getDimension() != dim)
			throw new MismatchedDimensionsException("Cannot take dot product of " + lhs.getTypeName() + " and "
					+ rhs.getTypeName(), lhs.isColumn() ? Axis.ROW : Axis.COLUMN, dim,
					rhs.isColumn() ? Axis.ROW : Axis.COLUMN, rhs.getDimension());
		double accu = 0.0;
		for(int i = 0; i < dim; ++i)
			accu += lhs.getComponent(i) * rhs.getComponent(i);
		return accu;
	}

	public static double length(CVector vector) {
		if(vector == null)
			throw new IllegalArgumentException("Vector cannot be null");
		int dim = vector.getDimension();
		double accu = 0.0;
		for(int i = 0; i < dim; ++i) {
			double comp = vector.getComponent(i);
			accu += comp * comp;
		}
		return Math.sqrt(accu);
	}

}
