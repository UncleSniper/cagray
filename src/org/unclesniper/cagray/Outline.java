package org.unclesniper.cagray;

import static org.unclesniper.util.ArgUtils.notNull;
import static org.unclesniper.util.PropertyUtils.returnNotNull;

public interface Outline {

	OutlineHit getClosestPoint(CVector target);

	public static double distanceToOutline(Outline outline, CVector point) throws MismatchedDimensionsException {
		OutlineHit hit = returnNotNull(notNull(outline, "outline").getClosestPoint(point),
				outline, "getClosestPoint");
		CVector closest = returnNotNull(hit.getPointOnOutline(), hit, "getPointOnOutline");
		CVector outward = returnNotNull(hit.getOutward(), hit, "getOutward");
		double distance = VectorOps.distance(closest, point);
		double angle;
		try {
			angle = VectorOps.angleBetween(outward, VectorOps.sub(point, closest));
		}
		catch(ZeroVectorException e) {
			if(outward.isZero())
				throw new IllegalStateException("OutlineHit returned zero vector as 'outward' property");
			// point actually lies on outline
			angle = 0.0;
		}
		return angle - Math.PI / 2.0 > 0.0 ? -distance : distance;
	}

}
