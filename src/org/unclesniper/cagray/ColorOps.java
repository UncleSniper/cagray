package org.unclesniper.cagray;

import static org.unclesniper.util.ArgUtils.notNull;

public class ColorOps {

	private ColorOps() {}

	public static CColor over(CColor lhs, CColor rhs) {
		notNull(lhs, "lhs");
		notNull(rhs, "rhs");
		double lalpha = lhs.getAlpha();
		return new CColor(
			lhs.getR() + rhs.getR() * (1.0 - lalpha),
			lhs.getG() + rhs.getG() * (1.0 - lalpha),
			lhs.getB() + rhs.getB() * (1.0 - lalpha),
			lalpha + rhs.getAlpha() * (1.0 - lalpha),
			true
		);
	}

}
