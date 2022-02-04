package org.unclesniper.cagray;

import java.io.Writer;
import java.io.IOException;

import static org.unclesniper.util.ArgUtils.notNull;

public class CColor extends AbstractCObject {

	private final double r;

	private final double g;

	private final double b;

	private final double alpha;

	public CColor(double r, double g, double b, double alpha, boolean premultiplied) {
		alpha = CColor.boundChannel(alpha);
		if(premultiplied) {
			this.r = CColor.boundChannel(r, alpha);
			this.g = CColor.boundChannel(g, alpha);
			this.b = CColor.boundChannel(b, alpha);
		}
		else {
			this.r = CColor.boundChannel(r) * alpha;
			this.g = CColor.boundChannel(g) * alpha;
			this.b = CColor.boundChannel(b) * alpha;
		}
		this.alpha = alpha;
	}

	public double getR() {
		return r;
	}

	public double getG() {
		return g;
	}

	public double getB() {
		return b;
	}

	public double getAlpha() {
		return alpha;
	}

	public double getRawR() {
		return alpha == 0.0 ? 0.0 : CColor.boundChannel(r / alpha);
	}

	public double getRawG() {
		return alpha == 0.0 ? 0.0 : CColor.boundChannel(g / alpha);
	}

	public double getRawB() {
		return alpha == 0.0 ? 0.0 : CColor.boundChannel(b / alpha);
	}

	@Override
	public void printTo(Writer out, int level) throws IOException {
		notNull(out, "out").write("%<");
		out.write(String.valueOf(getRawR()));
		out.write(", ");
		out.write(String.valueOf(getRawG()));
		out.write(", ");
		out.write(String.valueOf(getRawB()));
		if(alpha > 0.0) {
			out.write(", ");
			out.write(String.valueOf(alpha));
		}
		out.write('>');
	}

	@Override
	public String getTypeName() {
		return "Color";
	}

	public static double boundChannel(double component) {
		if(component < 0.0)
			return 0.0;
		if(component > 1.0)
			return 1.0;
		return component;
	}

	public static double boundChannel(double component, double upperBound) {
		if(component < 0.0)
			return 0.0;
		if(component > upperBound)
			return upperBound;
		return component;
	}

}
