package org.unclesniper.cagray.util;

import org.junit.Test;
import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrintUtilsTests {

	@Test
	public void testIndent() throws IOException {
		StringBuilder expected = new StringBuilder();
		for(int i = 0; i <= 3; ++i) {
			StringWriter out = new StringWriter();
			PrintUtils.indent(out, i);
			assertThat(out.toString(), equalTo(expected.toString()));
			expected.append(PrintUtils.INDENT);
		}
	}

	@Test
	public void testIndentNullWriter() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
				() -> PrintUtils.indent(null, 0));
		assertThat(iae.getMessage(), equalTo("Output writer cannot be null"));
	}

	@Test
	public void testIndentNegative() {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
				() -> PrintUtils.indent(new StringWriter(), -2));
		assertThat(iae.getMessage(), equalTo("Cannot indent a negative number of levels: -2"));
	}

	@Test
	public void testEOLValue() {
		assertThat(PrintUtils.EOL, anyOf(equalTo("\n"), equalTo("\r"), equalTo("\r\n")));
	}

	@Test
	public void testEOLNullWriter() throws IOException {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> PrintUtils.eol(null));
		assertThat(iae.getMessage(), equalTo("Output writer cannot be null"));
	}

	@Test
	public void testEOL() throws IOException {
		StringWriter out = new StringWriter();
		PrintUtils.eol(out);
		assertThat(out.toString(), equalTo(PrintUtils.EOL));
	}

	@Test
	public void testEOLIndent() throws IOException {
		StringBuilder expected = new StringBuilder(PrintUtils.EOL);
		for(int i = 0; i <= 3; ++i) {
			StringWriter out = new StringWriter();
			PrintUtils.eolIndent(out, i);
			assertThat(out.toString(), equalTo(expected.toString()));
			expected.append(PrintUtils.INDENT);
		}
	}

	@Test
	public void testEOLIndentNullWriter() throws IOException {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
				() -> PrintUtils.eolIndent(null, 0));
		assertThat(iae.getMessage(), equalTo("Output writer cannot be null"));
	}

	@Test
	public void testEOLIndentNegative() throws IOException {
		IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
				() -> PrintUtils.eolIndent(new StringWriter(), -2));
		assertThat(iae.getMessage(), equalTo("Cannot indent a negative number of levels: -2"));
	}

}
