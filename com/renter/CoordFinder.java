package com.renter;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.Point;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class CoordFinder {
	
	final String templateFolder = "C:\\JavaProject\\Samples\\";
	final String wpTemplate = templateFolder + "waypoint.png";
	final String dockIconTemplate = templateFolder + "dockswp.png";
	final String newButtonTemplate = templateFolder + "new.png";
	final String sarnPortalTemplate = templateFolder + "thesarnencampment.png";
	final String frameTimeTemplate = templateFolder + "frametime.png";
	
	final int windowBufferx = 20;
	final int windowBuffery = 30;
	final int screenSizeX = 1280 + windowBufferx;
	final int screenSizeY = 720 + windowBuffery;
	final int match_method = Imgproc.TM_CCOEFF;
	
	public Point findWP() throws AWTException, IOException {

		
		System.out.println("\nGetting WP Coords");
		
		ImageIO.write(new Robot().createScreenCapture(new Rectangle(0,0, screenSizeX, screenSizeY)), "png", new File("dump.png"));

		Mat img = Highgui.imread("dump.png");
		Mat templ = Highgui.imread(wpTemplate);

		// / Create the result matrix
		int result_cols = img.cols() - templ.cols() + 1;
		int result_rows = img.rows() - templ.rows() + 1;
		Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

		// / Do the Matching and Normalize
		Imgproc.matchTemplate(img, templ, result, match_method);
		Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

		// / Localizing the best match with minMaxLoc
		MinMaxLocResult mmr = Core.minMaxLoc(result);

		Point matchLoc = mmr.maxLoc;

		// Get Click Point
		double middlex = matchLoc.x + (templ.cols() / 2);
		double middley = matchLoc.y + (templ.rows() / 2);

		return new Point(middlex, middley);
	}

	public Point findDocksOnMap() throws IOException, AWTException {

		int offsetx = 10;
		int offsety = 10;
		
		System.out.println("\nGetting Dock Icon on maps Coords");

		ImageIO.write(new Robot().createScreenCapture(new Rectangle(0,0, screenSizeX, screenSizeY)), "png", new File("dump.png"));

		Mat img = Highgui.imread("dump.png");
		Mat templ = Highgui.imread(dockIconTemplate);

		// / Create the result matrix
		int result_cols = img.cols() - templ.cols() + 1;
		int result_rows = img.rows() - templ.rows() + 1;
		Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

		// / Do the Matching and Normalize
		Imgproc.matchTemplate(img, templ, result, match_method);
		Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

		// / Localizing the best match with minMaxLoc
		MinMaxLocResult mmr = Core.minMaxLoc(result);

		Point matchLoc = mmr.maxLoc;

		// Get Click Point
		double middlex = matchLoc.x + offsetx;
		double middley = matchLoc.y + offsety;

		return new Point(middlex, middley);
	}

	public Point findNewInstanceButton() throws IOException, AWTException {
		
		mouseController mc = new mouseController();
		
		int offsetx = 10;
		int offsety = 10;
		
		//Need to make sure window is scrolled to the bottom//
		int centerx = screenSizeX / 2;
		int centery = screenSizeY / 2;
		
		mc.moveMouse(centerx, centery);
		mc.scrollMouse(15);
            
		System.out.println("\nGetting New Instance Coords");

		ImageIO.write(new Robot().createScreenCapture(new Rectangle(0,0, screenSizeX, screenSizeY)), "png", new File("dump.png"));

		Mat img = Highgui.imread("dump.png");
		Mat templ = Highgui.imread(newButtonTemplate);

		// / Create the result matrix
		int result_cols = img.cols() - templ.cols() + 1;
		int result_rows = img.rows() - templ.rows() + 1;
		Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

		// / Do the Matching and Normalize
		Imgproc.matchTemplate(img, templ, result, match_method);
		Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

		// / Localizing the best match with minMaxLoc
		MinMaxLocResult mmr = Core.minMaxLoc(result);

		Point matchLoc = mmr.maxLoc;

		// Get Click Point
		double middlex = matchLoc.x + offsetx;
		double middley = matchLoc.y + offsety;

		return new Point(middlex, middley);
	}
	
	public Point findSarnPortal() throws IOException, AWTException {

		int offsetx = 10;
		int offsety = 10;
		
		System.out.println("\nGetting Sarn Portals Coords");

		ImageIO.write(new Robot().createScreenCapture(new Rectangle(0,0, screenSizeX, screenSizeY)), "png", new File("dump.png"));

		Mat img = Highgui.imread("dump.png");
		Mat templ = Highgui.imread(sarnPortalTemplate);

		// / Create the result matrix
		int result_cols = img.cols() - templ.cols() + 1;
		int result_rows = img.rows() - templ.rows() + 1;
		Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

		// / Do the Matching and Normalize
		Imgproc.matchTemplate(img, templ, result, match_method);
		Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

		// / Localizing the best match with minMaxLoc
		MinMaxLocResult mmr = Core.minMaxLoc(result);

		Point matchLoc = mmr.maxLoc;

		// Get Click Point
		double middlex = matchLoc.x + offsetx;
		double middley = matchLoc.y + offsety;

		return new Point(middlex, middley);
	}
	
	public Point findMaxFrameTime() throws IOException, AWTException{
		System.out.println("\nFinding max sarn time");

		ImageIO.write(new Robot().createScreenCapture(new Rectangle(0,0, screenSizeX, screenSizeY)), "png", new File("dump.png"));

		Mat img = Highgui.imread("dump.png");
		Mat templ = Highgui.imread(frameTimeTemplate);

		// / Create the result matrix
		int result_cols = img.cols() - templ.cols() + 1;
		int result_rows = img.rows() - templ.rows() + 1;
		Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

		// / Do the Matching and Normalize
		Imgproc.matchTemplate(img, templ, result, match_method);
		Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

		// / Localizing the best match with minMaxLoc
		MinMaxLocResult mmr = Core.minMaxLoc(result);

		Point matchLoc = mmr.maxLoc;

		// Get Click Point
		double middlex = matchLoc.x - 33;
		double middley = matchLoc.y + 9;

		ImageIO.write(new Robot().createScreenCapture(new Rectangle((int)middlex, (int)middley, 22, 13)), "png", new File("dump.png"));
		
		
		return new Point(middlex, middley);
	}

}
