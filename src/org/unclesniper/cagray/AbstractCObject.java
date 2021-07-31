package org.unclesniper.cagray;

import java.io.IOException;
import java.io.StringWriter;
import org.unclesniper.cagray.util.ExceptionUtils;

public abstract class AbstractCObject implements CObject {

	public AbstractCObject() {}

	public String toString(int level) {
		StringWriter out = new StringWriter();
		try {
			printTo(out, level);
		}
		catch(IOException ioe) {
			throw new Doom("Encountered IOException while printing to StringWriter"
					+ ExceptionUtils.chainMessage(ioe), ioe);
		}
		return out.toString();
	}

	@Override
	public String toString() {
		return toString(0);
	}

}
