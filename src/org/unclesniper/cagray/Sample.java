package org.unclesniper.cagray;

import static org.unclesniper.util.ArgUtils.notNull;

public class Sample {

	private final CColor borderColor;

	private final CColor fillColor;

	private final HitSet hitSet;

	public Sample(CColor borderColor, CColor fillColor, HitSet hitSet) {
		this.borderColor = borderColor;
		this.fillColor = fillColor;
		this.hitSet = notNull(hitSet, "hitSet");
	}

	public CColor getBorderColor() {
		return borderColor;
	}

	public CColor getFillColor() {
		return fillColor;
	}

	public HitSet getHitSet() {
		return hitSet;
	}

}
