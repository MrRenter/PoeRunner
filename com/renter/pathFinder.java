package com.renter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class pathFinder {

	final static int mazeSize = 19;
	static boolean maze[][];
	static boolean visited[][] = new boolean[mazeSize][mazeSize];

	public void setMaze(boolean[][] m) {
		maze = m;
	}
	
	public boolean findPath(int y, int x){
		return findPathRecur(x, y);
	}

	public boolean findPathRecur(int j, int i) { // change these args to be a coord object
											// of int,int //Also should return
											// an object of correct path and
											// distance
		globalVar gv = new globalVar();
		
		if (maze[j][i]){
			return false;
		}
		// Char will always be 17,17
		boolean solved = false;
		visited[j][i] = true;
		if (j == gv.middleSegment && i == gv.middleSegment) {
			return true;
		}

		ArrayList<Integer> possibleOptions = new ArrayList(); // 0=left 1=right
																// 2=up 3=down

		try {
			if (maze[j - 1][i] == false && !visited[j - 1][i] && (j-1)>=0) {
				// solved = findPath(i - 1, j);
				possibleOptions.add(0);
			}
		} catch (Exception ex) {
		}
		try {
			if (maze[j + 1][i] == false && !visited[j + 1][i] && (j+1) < maze.length) {
				// solved = findPath(i + 1, j);
				possibleOptions.add(1);
			}
		} catch (Exception ex) {
		}
		try {
			if (maze[j][i - 1] == false && !visited[j][i - 1] && (i-1) >= 0) {
				//solved = findPath(i, j - 1);
				possibleOptions.add(2);
			}
		} catch (Exception ex) {
		}
		try {
			if (maze[j][i + 1] == false && !visited[j][i + 1] && (i+1) < maze.length) {
				//solved = findPath(i, j + 1);
				possibleOptions.add(3);
			}
		} catch (Exception ex) {
		}

		Random r = new Random();
		try{
			int attempted = r.nextInt(possibleOptions.size());
		switch (possibleOptions.get(attempted)) {
		case 0: {
			solved = findPathRecur(j - 1, i);
			break;
		}
		case 1: {
			solved = findPathRecur(j + 1, i);
			break;
		}
		case 2: {
			solved = findPathRecur(j, i - 1);
			break;
		}
		case 3: {
			solved = findPathRecur(j, i + 1);
			break;
		}
		default:
		}
		} catch (Exception ex){
			//ex.printStackTrace();
		}
		return solved;
	}

}
