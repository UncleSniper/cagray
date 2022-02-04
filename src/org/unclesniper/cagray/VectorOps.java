package org.unclesniper.cagray;

import static org.unclesniper.util.ArgUtils.notNull;

public class VectorOps {

	private VectorOps() {}

	public static CVector add(CVector lhs, CVector rhs) throws MismatchedDimensionsException {
		boolean lcol = notNull(lhs, "lhs").isColumn(), rcol = notNull(rhs, "rhs").isColumn();
		int ldim = lhs.getDimension(), rdim = rhs.getDimension();
		if(lcol != rcol || ldim != rdim)
			throw new MismatchedDimensionsException("Cannot add " + lhs.getTypeName() + " to "
					+ rhs.getTypeName(), lhs, rhs);
		CVector res = new CVector(ldim, lcol);
		for(int index = 0; index < ldim; ++index)
			res.setComponent(index, lhs.getComponent(index) + rhs.getComponent(index));
		return res;
	}

	public static CVector sub(CVector lhs, CVector rhs) throws MismatchedDimensionsException {
		boolean lcol = notNull(lhs, "lhs").isColumn(), rcol = notNull(rhs, "rhs").isColumn();
		int ldim = lhs.getDimension(), rdim = rhs.getDimension();
		if(lcol != rcol || ldim != rdim)
			throw new MismatchedDimensionsException("Cannot subtract " + lhs.getTypeName() + " to "
					+ rhs.getTypeName(), lhs, rhs);
		CVector res = new CVector(ldim, lcol);
		for(int index = 0; index < ldim; ++index)
			res.setComponent(index, lhs.getComponent(index) - rhs.getComponent(index));
		return res;
	}

	public static CVector neg(CVector vector) {
		int dim = notNull(vector, "vector").getDimension();
		CVector res = new CVector(dim, vector.isColumn());
		for(int index = 0; index < dim; ++index)
			res.setComponent(index, -vector.getComponent(index));
		return res;
	}

	public static double dot(CVector lhs, CVector rhs) throws MismatchedDimensionsException {
		int dim = notNull(lhs, "lhs").getDimension();
		if(notNull(rhs, "rhs").getDimension() != dim)
			throw new MismatchedDimensionsException("Cannot take dot product of " + lhs.getTypeName() + " and "
					+ rhs.getTypeName(), lhs, rhs);
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

	public static double distance(CVector lhs, CVector rhs) throws MismatchedDimensionsException {
		int dim = notNull(lhs, "lhs").getDimension();
		if(notNull(rhs, "rhs").getDimension() != dim)
			throw new MismatchedDimensionsException("Cannot take distance between " + lhs.getTypeName() + " and "
					+ rhs.getTypeName(), lhs, rhs);
		double accu = 0.0;
		for(int i = 0; i < dim; ++i)
			accu += lhs.getComponent(i) * rhs.getComponent(i);
		return Math.sqrt(accu);
	}

}
