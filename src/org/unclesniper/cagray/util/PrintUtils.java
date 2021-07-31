package org.unclesniper.cagray.util;

import java.io.Writer;
import java.io.IOException;

public class PrintUtils {

	public static final String INDENT = "    ";

	public static final String EOL = System.getProperty("line.separator");

	private static final char[] EOL_CHARS = EOL.toCharArray();

	private PrintUtils() {}

	public static void indent(Writer out, int level) throws IOException {
		if(out == null)
			throw new IllegalArgumentException("Output writer cannot be null");
		if(level < 0)
			throw new IllegalArgumentException("Cannot indent a negative number of levels: " + level);
		for(; level > 0; --level)
			out.write(PrintUtils.INDENT);
	}

	public static void eol(Writer out) throws IOException {
		if(out == null)
			throw new IllegalArgumentException("Output writer cannot be null");
		out.write(PrintUtils.EOL);
	}

	public static void eolIndent(Writer out, int level) throws IOException {
		PrintUtils.eol(out);
		PrintUtils.indent(out, level);
	}

}
