package org.unclesniper.cagray;

import java.io.Writer;
import java.io.IOException;

public interface CObject {

	void printTo(Writer out, int level) throws IOException;

}
