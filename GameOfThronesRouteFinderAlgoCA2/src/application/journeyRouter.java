package application;

import java.util.ArrayList;
import java.util.LinkedList;

import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;

public class journeyRouter {
static ArrayList<Journey> possibleRoutes;
static ArrayList<Journey> finalRouths;
static int showMode = 0;
	static void router(Waypoint start, Waypoint end, Waypoint[] goTo, Waypoint[] avoid, int show){
		Journey startPoint  = new Journey();
		startPoint.getWaypoints().add(start);
		showMode= show;

	}

	private static void ensureWaypoints(Waypoint[] goTo) {
		// TODO Auto-generated method stub
		
	}

	private static void removeAvoid(Waypoint[] avoid) {
		// TODO Auto-generated method stub
		
	}

	private static void getEasyest() {
		// TODO Auto-generated method stub
		
	}

	private static void getShortest() {
		// TODO Auto-generated method stub
		
	}

	private static void getSafest() {
		// TODO Auto-generated method stub
		
	}

	private static void generateRouts(int maxNumbersOfStops) {
		// TODO Auto-generated method stub
		
	}
	
	private static Journey extendPoint() {
		Journey reternJourney = null;
		switch(showMode) {
		case 0:
			for(Journey check: possibleRoutes) {
				if(check.getLenght() < reternJourney.getLenght() || reternJourney == null) {
					reternJourney = check;
				}
			}
			break;
		case 1:
			for(Journey check: possibleRoutes) {
				if(check.getDanger() < reternJourney.getDanger() || reternJourney == null) {
					reternJourney = check;
				}
			}
			break;
		case 2:
			for(Journey check: possibleRoutes) {
				if(check.getDifaculty() < reternJourney.getDifaculty() || reternJourney == null) {
					reternJourney = check;
				}
			}
			break;
		}
		return reternJourney;
	}
}
