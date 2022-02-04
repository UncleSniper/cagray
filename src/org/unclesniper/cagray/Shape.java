package org.unclesniper.cagray;

public interface Shape extends CObject {

	Sample castRay(CVector target) throws MathException;

	Outline getOutline() throws MathException;

}
