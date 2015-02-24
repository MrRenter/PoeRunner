package com.renter;

import java.awt.AWTException;
import java.io.IOException;

import org.opencv.core.Point;

public class playerActions {
	
	public void createNewDocks() throws AWTException, IOException, InterruptedException{
		CoordFinder cf = new CoordFinder();
		mouseController mc = new mouseController();
		
		Point wpCoord = cf.findWP();
		mc.moveThenClickMouse(wpCoord.x, wpCoord.y, 1);
		System.out.println("Waypoint has been clicked");

		// Find then click docks icon
		Thread.sleep(500);
		Point dockCoord = cf.findDocksOnMap();
		mc.moveThenCtrlClickMouse(dockCoord.x, dockCoord.y, 1);
		System.out.println("Docks icon has been control clicked");

		// Find and click the new instance button
		Thread.sleep(500);
		Point newButtonCoord = cf.findNewInstanceButton();
		mc.moveThenClickMouse(newButtonCoord.x, newButtonCoord.y, 1);
		System.out.println("New instance has been clicked");
		
		Thread.sleep(3000);
	}
	
	public void gotoTownWithPortal() throws IOException, AWTException, InterruptedException{
		CoordFinder cf = new CoordFinder();
		mouseController mc = new mouseController();
		
		mc.openPortal();
		
		Thread.sleep(3000);
		Point sarnCoord = cf.findSarnPortal();
		mc.moveThenClickMouse(sarnCoord.x, sarnCoord.y, 1);
	}
	
	public void recreateNewDockWithPortal() throws IOException, AWTException, InterruptedException{
		CoordFinder cf = new CoordFinder();
		mouseController mc = new mouseController();
		
		mc.openPortal();
		
		Thread.sleep(3000);
		Point sarnCoord = cf.findSarnPortal();
		mc.moveThenClickMouse(sarnCoord.x, sarnCoord.y, 1);
		
		Thread.sleep(3000);
		
		createNewDocks();
	}

}
