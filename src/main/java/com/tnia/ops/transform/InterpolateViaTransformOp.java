package com.tnia.ops.transform;

import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

import net.imagej.ops.Ops;
import net.imagej.ops.special.computer.AbstractBinaryComputerOp;
import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imagej.ops.special.function.Functions;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.Cursor;
import net.imglib2.Dimensions;
import net.imglib2.EuclideanSpace;
import net.imglib2.IterableInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.RealRandomAccess;
import net.imglib2.RealRandomAccessible;
import net.imglib2.img.Img;
import net.imglib2.interpolation.InterpolatorFactory;
import net.imglib2.interpolation.randomaccess.LanczosInterpolatorFactory;
import net.imglib2.interpolation.randomaccess.NLinearInterpolatorFactory;
import net.imglib2.realtransform.RealTransform;
import net.imglib2.type.numeric.RealType;
import net.imglib2.util.Util;
import net.imglib2.view.Views;

/**
 * 
 * Given an input and a transform, this function extends and interpolates the
 * input so it is defined over real space Then each output position is
 * translated to real coordinates, the input is sampled at those coordinates and
 * copied to the output.
 * 
 * @author bnorthan
 *
 * @param <I>
 * @param <R>
 * @param <T>
 */
@Plugin(type = Ops.Transform.InterpolateView.class)
public class InterpolateViaTransformOp<I extends EuclideanSpace, R extends RealTransform, T extends RealType<T>>
		extends AbstractBinaryComputerOp<RandomAccessibleInterval<T>, R, IterableInterval<T>>
		implements Ops.Transform.InterpolateView {

	@Parameter(required = false)
	private InterpolatorFactory<T, I> factory;

	private UnaryFunctionOp<I, RealRandomAccessible<T>> interpolateOp = null;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize() {
		super.initialize();

		if (factory == null) {
			factory = (InterpolatorFactory<T, I>) new NLinearInterpolatorFactory<T>();
		}

		interpolateOp = (UnaryFunctionOp) Functions.unary(ops(), Ops.Transform.InterpolateView.class,
				RealRandomAccessible.class, EuclideanSpace.class, new LanczosInterpolatorFactory<T>());

	}

	@Override
	public void compute(RandomAccessibleInterval<T> in, R t, IterableInterval<T> out) {

		
		RealRandomAccess<T> a = ops().transform().interpolate(Views.extendZero(in), new LanczosInterpolatorFactory<T>())
				.realRandomAccess();

		// create cursor to loop through pixels
		Cursor<T> c = out.cursor();

		double outputindex[] = new double[2];
		double inputposition[] = new double[2];

		// loop through output pixels
		while (c.hasNext()) {
			c.fwd();

			// get current index of the output
			c.localize(outputindex);

			// apply the transform to calculate where to sample the input
			t.apply(outputindex, inputposition);

			// set position on extended-interpolated input
			a.setPosition(inputposition);

			// copy to output
			c.get().set(a.get());

		}

	}

}
