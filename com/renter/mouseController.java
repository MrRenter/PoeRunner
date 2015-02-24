package com.renter;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class mouseController {
	
	final int portalGemKey = KeyEvent.VK_R;

	public void clickMouse(int mb) throws AWTException {
		Robot r = new Robot();
		if (mb == 1){
			r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} else if (mb == 2) {
			r.mousePress(InputEvent.BUTTON2_DOWN_MASK);
			r.mouseRelease(InputEvent.BUTTON2_DOWN_MASK);
		}
	}

	public void scrollMouse(int scrollTicks) throws AWTException {
		Robot r = new Robot();
		r.mouseWheel(scrollTicks);
	}

	public void moveMouse(double x2, double y2) throws AWTException {
		Robot r = new Robot();

		int x = 0, y = 0;
		while (x < x2 || y < y2) {
			if (x < x2) {
				x++;
			}
			if (y < y2) {
				y++;
			}
			r.mouseMove(x, y);
		}
	}
	
	public void moveThenClickMouse(double x, double y, int mb) throws AWTException, InterruptedException{
		moveMouse(x,y);
		Thread.sleep(500);
		clickMouse(mb);
	}
	
	public void moveThenCtrlClickMouse(double x, double y, int mb) throws AWTException, InterruptedException{
		Robot r = new Robot();
		moveMouse(x,y);
		Thread.sleep(500);
		r.keyPress(KeyEvent.VK_CONTROL);
		clickMouse(mb);
		r.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	public void openPortal() throws AWTException{
		Robot r = new Robot();
		r.keyPress(portalGemKey);
		r.keyRelease(portalGemKey);
	}
	

}
