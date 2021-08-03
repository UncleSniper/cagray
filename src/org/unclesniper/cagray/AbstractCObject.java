package org.unclesniper.cagray;

public abstract class AbstractCObject implements CObject {

	public AbstractCObject() {}

	@Override
	public String toString() {
		return toString(0);
	}

}
