package org.unclesniper.cagray;

import java.io.Writer;
import java.io.IOException;
import java.io.StringWriter;
import org.unclesniper.cagray.util.ExceptionUtils;

public interface CObject {

	void printTo(Writer out, int level) throws IOException;

	String getTypeName();

	default String toString(int level) {
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

}
