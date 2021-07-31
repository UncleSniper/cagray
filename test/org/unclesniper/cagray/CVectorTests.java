package org.unclesniper.cagray;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CVectorTests {

	private void assertComponentsZero(CVector vector, int dimension) {
		for(int i = 0; i < dimension; ++i)
			assertThat(vector.getComponent(i), equalTo(0.0));
	}

	@Test
	public void testCreateDimVector() {
		for(int i = 1; i <= 5; ++i) {
			CVector rowVector = new CVector(i, false);
			assertThat(rowVector.getDimension(), equalTo(i));
			assertThat(rowVector.isColumn(), equalTo(false));
			assertThat(rowVector.getRows(), equalTo(1));
			assertThat(rowVector.getColumns(), equalTo(i));
			assertComponentsZero(rowVector, i);
			CVector columnVector = new CVector(i, true);
			assertThat(columnVector.getDimension(), equalTo(i));
			assertThat(columnVector.isColumn(), equalTo(true));
			assertThat(columnVector.getRows(), equalTo(i));
			assertThat(columnVector.getColumns(), equalTo(1));
			assertComponentsZero(columnVector, i);
		}
	}

	//TODO

}
