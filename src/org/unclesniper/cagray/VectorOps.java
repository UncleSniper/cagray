package org.unclesniper.cagray;

import static org.unclesniper.util.ArgUtils.notNull;

public class VectorOps {

	private VectorOps() {}

	public static double dot(CVector lhs, CVector rhs) throws MismatchedDimensionsException {
		int dim = notNull(lhs, "lhs").getDimension();
		if(notNull(rhs, "rhs").getDimension() != dim)
			throw new MismatchedDimensionsException("Cannot take dot product of " + lhs.getTypeName() + " and "
					+ rhs.getTypeName(), lhs.isColumn() ? Axis.ROW : Axis.COLUMN, dim,
					rhs.isColumn() ? Axis.ROW : Axis.COLUMN, rhs.getDimension());
		double accu = 0.0;
		for(int i = 0; i < dim; ++i)
			accu += lhs.getComponent(i) * rhs.getComponent(i);
		return accu;
	}

	public static double length(CVector vector) {
		int dim = notNull(vector, "vector").getDimension();
		double accu = 0.0;
		for(int i = 0; i < dim; ++i) {
			double comp = vector.getComponent(i);
			accu += comp * comp;
		}
		return Math.sqrt(accu);
	}

	public static double angleBetween(CVector lhs, CVector rhs)
			throws ZeroVectorException, MismatchedDimensionsException {
		if(notNull(lhs, "lhs").isZero())
			throw new ZeroVectorException("Cannot take angle between vectors: Bad LHS");
		if(notNull(rhs, "rhs").isZero())
			throw new ZeroVectorException("Cannot take angle between vectors: Bad RHS");
		return Math.acos(VectorOps.dot(lhs, rhs));
	}

}
