package com.siriusif.model.helpers;

import java.math.MathContext;

public class MathContextSinglton {
	private static MathContext RoundedSum;
	public static MathContext getCalculationRules() {
		RoundedSum = null;
		RoundedSum = new MathContext(2);
		
		return RoundedSum;
		
	}
	public static MathContext getRoundedSum() {
		return RoundedSum;
	}

}
