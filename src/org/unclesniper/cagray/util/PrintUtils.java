package org.unclesniper.cagray.util;

import java.io.Writer;
import java.io.IOException;
import org.unclesniper.util.SystemProperties;

import static org.unclesniper.util.ArgUtils.notNull;
import static org.unclesniper.util.ArgUtils.notNegative;

public class PrintUtils {

	public static final String INDENT = "    ";

	private static final char[] EOL_CHARS = SystemProperties.LINE_SEPARATOR.toCharArray();

	private PrintUtils() {}

	public static void indent(Writer out, int level) throws IOException {
		notNull(out, "out");
		notNegative(level, "level");
		for(; level > 0; --level)
			out.write(PrintUtils.INDENT);
	}

	public static void eol(Writer out) throws IOException {
		notNull(out, "out").write(SystemProperties.LINE_SEPARATOR);
	}

	public static void eolIndent(Writer out, int level) throws IOException {
		PrintUtils.eol(out);
		PrintUtils.indent(out, level);
	}

}
