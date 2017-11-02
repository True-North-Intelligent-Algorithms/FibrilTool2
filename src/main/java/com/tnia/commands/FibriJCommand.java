package com.tnia.commands;


import net.imagej.ImgPlus;

import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin(type = Command.class, headless = true, menuPath = "Plugins>FibriJ")
public class FibriJCommand implements Command {
	
	@Parameter
	ImgPlus img;
	
	@Parameter(type = ItemIO.OUTPUT)
	Double averageOrientation;
	
	@Parameter(type = ItemIO.OUTPUT)
	Double quality;
	
	public void run() {
		
		System.out.println(img.dimension(2));
	
		// get pixel size
		// pixelWidth=...;
		
		// scale=pixelWidth;
		double scale = 1.0;
		
		//compute x-gradient in "x"
		//selectWindow("Temp");
		//run("Duplicate...","title=x");
		//run("32-bit");
		//run("Translate...", "x=-0.5 y=0 interpolation=Bicubic");
		//run ("Duplicate...","title=x1");
		//run("Translate...", "x=1 y=0 interpolation=None");
		//imageCalculator("substract","x","x1");
		//selectWindow("x1");
		//close();
		
		//compute y-gradient in "y"
		//selectWindow("Temp");
		//run ("Duplicate...","title=y");
		//run("32-bit");
		//run("Translate...", "x=0 y=-0.5 interpolation=Bicubic");
		//run ("Duplicate...","title=y1");
		//run("Translate...", "x=0 y=1 interpolation=None");
		//imageCalculator("substract","y","y1");
		//selectWindow("y1");
		//close();
		
		//compute norm of gradient in "g"
		//selectWindow("x");
		//run("Duplicate...","title=g");
		//imageCalculator("multiply","g","x");
		//selectWindow("y");
		//run("Duplicate...","title=gp");
		//imageCalculator("multiply","gp","y");
		//imageCalculator("add","g","gp");
		//selectWindow("gp");
		//close();
		//selectWindow("g");
		//w = getWidth(); h = getHeight();
		//for (y=0; y<h; y++) {
		//	for (x=0; x<w; x++){
		//		setPixel(x, y, sqrt( getPixel(x, y)));
		//	}
		//}
		
		//set the effect of the gradient to 1/255 when too low ; threshold = thresh
		//double thresh=2.0;
		//selectWindow("g");
		//for (y=0; y<h; y++) {
		//	for (x=0; x<w; x++){
		//		if (getPixel(x,y) < thresh) 
		//			setPixel(x, y, 255);
		//	}
		//}
		
		//normalize "x" and "y" to components of normal
		//imageCalculator("divide","x","g");
		//imageCalculator("divide","y","g");
		
		//compute nxx
		//selectWindow("x");
		//run("Duplicate...","title=nxx");
		//imageCalculator("multiply","nxx","x");
		
		//compute nxy
		//selectWindow("x");
		//run("Duplicate...","title=nxy");
		//imageCalculator("multiply","nxy","y");
		
		//compute nyy
		//selectWindow("y");
		//run("Duplicate...","title=nyy");
		//imageCalculator("multiply","nyy","y");
		
		//closing
		//selectWindow("Temp");
		//close();
		//selectWindow("x");
		//close();
		//selectWindow("y");
		//close();
		//selectWindow("g");
		//close();
		
		//averaging nematic tensor
		//selectWindow("nxx");
		//makeSelection("polygon",vertxloc,vertyloc);
		//getRawStatistics(area,xx);
		//close();

		//selectWindow("nxy");
		//makeSelection("polygon",vertxloc,vertyloc);
		//getRawStatistics(area,xy);
		//close();
		
		//selectWindow("nyy");
		//makeSelection("polygon",vertxloc,vertyloc);
		//getRawStatistics(area,yy);
		//close();
		
		//eigenvalues and eigenvector of texture tensor
		//m = (xx + yy) / 2;
		//d = (xx - yy) / 2;
		//v1 = m + sqrt(xy*xy + d*d);
		//v2 = m - sqrt(xy*xy + d*d);
		//direction
		//tn = - atan((v2 - xx) / xy);
		//score
		//scoren = abs((v1-v2) / 2 / m);
		
		//output
		//tsn=tn*180/pi;
		// nematic tensor tensor
		//sortie = sortie+"\t"+d2s(tsn,pr)+"\t"+d2s(scoren,2*pr);
		
		//polygon coordinates (may not be needed)
		//np = vertx.length;
		//for (i=0; i<np; i++){
		//xp = vertx[i]; yp = verty[i];
		//sortie = sortie+"\t"+d2s(xp,pr)+"\t"+d2s(yp,pr);
		
		
		
		
	}

}
