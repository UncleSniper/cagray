package org.unclesniper.cagray;

import static org.unclesniper.util.ArgUtils.notNull;

public class OutlineHit {

	private final CVector pointOnOutline;

	private final CVector outward;

	public OutlineHit(CVector pointOnOutline, CVector outward) {
		this.pointOnOutline = notNull(pointOnOutline, "pointOnOutline");
		this.outward = notNull(outward, "outward");
	}

	public CVector getPointOnOutline() {
		return pointOnOutline;
	}

	public CVector getOutward() {
		return outward;
	}

}
