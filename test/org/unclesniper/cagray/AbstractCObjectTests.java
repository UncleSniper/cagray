package org.unclesniper.cagray;

import org.junit.Test;
import java.io.Writer;
import java.io.IOException;
import org.unclesniper.cagray.AbstractCObject;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AbstractCObjectTests {

	private static class ToStringOfIntCObject extends AbstractCObject {

		@Override
		public void printTo(Writer out, int level) {
			throw new UnsupportedOperationException("Should not be called");
		}

		@Override
		public String toString(int level) {
			return "[toString(" + level + ")]";
		}

	}

	private static class PrintToCObject extends AbstractCObject {

		@Override
		public void printTo(Writer out, int level) throws IOException {
			out.write("[printTo(" + level + ")]");
		}

	}

	@Test
	public void testToStringOfInt() {
		assertThat(new ToStringOfIntCObject().toString(4), equalTo("[toString(4)]"));
	}

	@Test
	public void testPrintTo() {
		assertThat(new PrintToCObject().toString(5), equalTo("[printTo(5)]"));
	}

}
