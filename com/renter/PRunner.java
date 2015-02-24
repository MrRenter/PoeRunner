package com.renter;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class PRunner {

	public static void main(String[] args) throws AWTException, IOException,
			InterruptedException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// Thread.sleep(5000);

		CoordFinder cf = new CoordFinder();
		mouseController mc = new mouseController();
		playerActions pa = new playerActions();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		imageConversions ic = new imageConversions();
		globalVar gv = new globalVar();
		gv.setVarsFor1080();
		
		ic.getMap();
		pathFinder pf = new pathFinder();
		pf.maze[gv.middleSegment][gv.middleSegment] = false;

		
		//Top
		for (int i = 0; i < gv.numOfSegments; i++) {
			for (int z = 0; z < gv.numberOfBruteForceAttempts; z++) {
				if (pf.findPath(i, 0)) {
					System.out.println(i + ", 0");
					break;
				}
				pf.visited = new boolean[gv.numOfSegments][gv.numOfSegments];
			}
		}
		
		//Right
		for (int i = 0; i < gv.numOfSegments; i++) {
			for (int z = 0; z < gv.numberOfBruteForceAttempts; z++) {
				if (pf.findPath(gv.numOfSegments - 1 ,i)) {
					System.out.println("18, " + i);
					break;
				}
				pf.visited = new boolean[gv.numOfSegments][gv.numOfSegments];
			}
		}
		
		//Bottom
		for (int i = 0; i < gv.numOfSegments; i++) {
			for (int z = 0; z < gv.numberOfBruteForceAttempts; z++) {
				if (pf.findPath(i, gv.numOfSegments - 1)) {
					System.out.println(i + ", 18");
					break;
				}
				pf.visited = new boolean[gv.numOfSegments][gv.numOfSegments];
			}
		}
		
		//Left
		for (int i = 0; i < gv.numOfSegments; i++) {
			for (int z = 0; z < gv.numberOfBruteForceAttempts; z++) {
				if (pf.findPath(i, 0)) {
					System.out.println("0, " + i);
					break;
				}
				pf.visited = new boolean[gv.numOfSegments][gv.numOfSegments];
			}
		}
		
		
		
		
		
		/*
		for (int i = 0; i < gv.numOfSegments; i++) {
			for (int z = 0; z < gv.numberOfBruteForceAttempts; z++) {
				if (pf.findPath(i, gv.numOfSegments - 1)) {
					System.out.println(i + ", 19");
					break;
				}
				pf.visited = new boolean[gv.numOfSegments][gv.numOfSegments];
			}
		}
		*/
	}
}
