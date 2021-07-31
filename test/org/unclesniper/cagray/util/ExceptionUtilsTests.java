package org.unclesniper.cagray.util;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExceptionUtilsTests {

	@Test
	public void testChainMessage() {
		assertThat(ExceptionUtils.chainMessage(null), equalTo(""));
		assertThat(ExceptionUtils.chainMessage(new Exception((String)null)), equalTo(""));
		assertThat(ExceptionUtils.chainMessage(new Exception("")), equalTo(""));
		assertThat(ExceptionUtils.chainMessage(new Exception(" ")), equalTo(":  "));
		assertThat(ExceptionUtils.chainMessage(new Exception("foo")), equalTo(": foo"));
	}

}
