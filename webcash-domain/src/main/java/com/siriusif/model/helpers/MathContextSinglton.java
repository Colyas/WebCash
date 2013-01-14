package com.siriusif.model.helpers;

import java.math.MathContext;

public class MathContextSinglton {
	public static MathContext getRoundedSum() {
		MathContext ms = new MathContext(4);
		
		return ms;
		
	}

}
