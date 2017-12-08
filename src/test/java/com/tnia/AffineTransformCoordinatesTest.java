package com.tnia;

import net.imglib2.realtransform.AffineTransform2D;

import org.junit.Test;

public class AffineTransformCoordinatesTest {
	
	@Test
	public void TransformCoordinatesTest() {
		
		AffineTransform2D transmx = new AffineTransform2D();
		transmx.translate(0.5, 0);
		
		double[] testPoint=new double[]{0,0};
		double[] translatedPoint=new double[]{0,0};
		
		transmx.apply(testPoint, translatedPoint);
		
		System.out.println("transformed point: "+ translatedPoint[0]+" "+translatedPoint[1]);
		
	}

}
