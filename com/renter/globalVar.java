package com.renter;

public class globalVar {
	
	final int numberOfBruteForceAttempts = 1000;
	
	
	//Stats for image conversion class
	static int mapTLx;
	static int mapTLy;
	static int mapW;
	static int mapH;
	static int angle;
	static int sizeOfCharOnMinimap;
	static int numOfSegments;
	static int middleSegment;
	
	public void setVarsFor1080(){
		mapTLx = 1706;
		mapTLy = 86;
		mapW = 153;
		mapH = 153;
		angle = 39;
		sizeOfCharOnMinimap = 8;
		numOfSegments = 19;
		middleSegment = 9;
	}
}
