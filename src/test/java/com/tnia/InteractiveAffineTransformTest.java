package com.tnia;


import java.io.IOException;

//import com.doublehelixoptics.ops.transform.InterpolateViaTransformOp;

import net.imagej.ImageJ;
import net.imglib2.Cursor;
import net.imglib2.FinalDimensions;
import net.imglib2.RealRandomAccess;
import net.imglib2.img.Img;
import net.imglib2.interpolation.randomaccess.LanczosInterpolatorFactory;
import net.imglib2.realtransform.AffineTransform2D;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.util.Util;
import net.imglib2.view.Views;

public class InteractiveAffineTransformTest {

	static String workingDir = "./Images/";

	static String imName = workingDir + "fibers.tif";

	public static <T extends RealType<T> & NativeType<T>> void main(final String[] args) throws IOException {

		ImageJ ij;

		ij = net.imagej.Main.launch();

		Img<T> image = (Img<T>) (ij.dataset().open(imName).getImgPlus().getImg());

		Img<T> image_transformed = ij.op().create().img(image);

		ij.ui().show("Original", image);

		AffineTransform2D translate1 = new AffineTransform2D();
		
		translate1.translate(0.5, 0);
		
		RealRandomAccess<T> a = ij.op().transform().interpolate(Views.extendZero(image), new LanczosInterpolatorFactory<T>())
				.realRandomAccess();

		// create cursor to loop through pixels
		Cursor<T> c = image_transformed.cursor();

		double outputposition[] = new double[2];
		double inputposition[] = new double[2];

		// loop through output pixels
		while (c.hasNext()) {
			c.fwd();

			// get current integer position
			c.localize(outputposition);

			translate1.apply(outputposition, inputposition);
			
			// set position on extended-interpolated input
			a.setPosition(inputposition);

			// copy to output
			c.get().set(a.get());

		}

		ij.ui().show("Image Transformed", image_transformed);

	};

}
