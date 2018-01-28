package com.tnia.commands;

import com.tnia.ops.transform.InterpolateViaTransformOp;

import net.imagej.ImgPlus;
import net.imagej.ops.OpService;
import net.imagej.ops.special.computer.BinaryComputerOp;
import net.imagej.ops.special.computer.Computers;
import net.imglib2.IterableInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.realtransform.AffineTransform2D;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.FloatType;

import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.log.LogService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UIService;

@Plugin(type = Command.class, headless = true, menuPath = "Plugins>TNIATest")
public class TNIATestCommand<T extends RealType<T> & NativeType<T>> implements Command {

	@Parameter
	OpService ops;

	@Parameter
	LogService log;

	@Parameter
	UIService ui;

	@Parameter
	ImgPlus img;

	@Parameter(type = ItemIO.OUTPUT)
	Double test1;

	@Parameter(type = ItemIO.OUTPUT)
	Double test2;

	BinaryComputerOp<RandomAccessibleInterval<FloatType>, AffineTransform2D, IterableInterval<FloatType>> transformOp = null;

	public void run() {
		test1=363.0;
		test2=354.0;

	}

}
