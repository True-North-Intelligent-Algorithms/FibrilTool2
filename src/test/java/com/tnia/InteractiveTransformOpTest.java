package com.tnia;

import com.tnia.ops.transform.InterpolateViaTransformOp;

import java.io.IOException;

import net.imagej.ImageJ;
import net.imagej.ops.special.computer.BinaryComputerOp;
import net.imagej.ops.special.computer.Computers;
import net.imglib2.IterableInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.realtransform.AffineTransform2D;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;

public class InteractiveTransformOpTest<T extends RealType<T> & NativeType<T>> {
	
	static String workingDir = "./Images/";

	static String imName = workingDir + "fibers.tif";
	
	public static <T extends RealType<T> & NativeType<T>> void main(final String[] args) throws IOException {

		ImageJ ij;

		ij = net.imagej.Main.launch();

		Img<T> image = (Img<T>) (ij.dataset().open(imName).getImgPlus().getImg());

		Img<T> imageTransformed = ij.op().create().img(image);

		ij.ui().show("Original", image);
		
		BinaryComputerOp<RandomAccessibleInterval<T>, AffineTransform2D, IterableInterval<T>> transformOp=null;
		
		try {
			transformOp = (BinaryComputerOp) Computers.binary(ij.op(), InterpolateViaTransformOp.class,
					IterableInterval.class, RandomAccessibleInterval.class, AffineTransform2D.class);
		} catch (Exception ex) {
			ij.log().info(ex);
		}
		
		// define a affine transform
		AffineTransform2D trans = new AffineTransform2D();
		
		trans.translate(-5.,0);
		
		transformOp.compute(image, trans, imageTransformed);
		
		ij.ui().show(imageTransformed);

	}

}
