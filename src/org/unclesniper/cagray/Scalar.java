package org.unclesniper.cagray;

import java.io.Writer;
import java.io.IOException;

import static org.unclesniper.util.ArgUtils.notNull;

public class Scalar extends AbstractCObject {

	private final double value;

	public Scalar(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@Override
	public void printTo(Writer out, int level) throws IOException {
		notNull(out, "out").write(String.valueOf(value));
	}

	@Override
	public String getTypeName() {
		return "Scalar";
	}

}
