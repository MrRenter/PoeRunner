package com.renter;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class imageConversions {

	public void getMap() throws IOException, AWTException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		CoordFinder cf = new CoordFinder();
		mouseController mc = new mouseController();
		playerActions pa = new playerActions();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		globalVar gv = new globalVar();

		int mapTLx = gv.mapTLx;// 1650;
		int mapTLy = gv.mapTLy; // 30;
		int mapW = gv.mapW;// 265
		int mapH = gv.mapH; // 265
		int angle = gv.angle;
		int sizeOfChar = gv.sizeOfCharOnMinimap;

		ImageIO.write(new Robot().createScreenCapture(new Rectangle(mapTLx,
				mapTLy, mapW, mapH)), "png", new File("dump.png"));

		Mat src = Highgui.imread("dump.png");

		Imgproc.cvtColor(src, src, Imgproc.COLOR_RGB2GRAY);
		Imgproc.threshold(src, src, 50, 255, Imgproc.THRESH_BINARY);
		Highgui.imwrite("dump2.jpg", src);

		// Now divide it and map it

		int xSegments = gv.numOfSegments;
		int ySegments = gv.numOfSegments;
		
		boolean[][] maze = new boolean[gv.numOfSegments][gv.numOfSegments];

		for (int x = 0; x < xSegments; x++) {
			for (int y = 0; y < ySegments; y++) {

				int numOfBlack = 0;
				int numOfWhite = 0;

				for (int i = 0; i < sizeOfChar; i++) {
					for (int j = 0; j < sizeOfChar; j++) {
						if (src.get(i + (x * sizeOfChar), j + (y * sizeOfChar))[0] < 127) {
							numOfBlack++;
						} else {
							numOfWhite++;
						}
					}
				}
				if (numOfWhite > numOfBlack / 2) {
					System.out.print("# ");
					maze[x][y] = true;
				} else {
					System.out.print(". ");
					maze[x][y] = false;
				}
			}
			System.out.println();
		}

		pathFinder pf = new pathFinder();
		pf.setMaze(maze);
	}

}
