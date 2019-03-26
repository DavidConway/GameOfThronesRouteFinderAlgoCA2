package application;

import java.util.ArrayList;
import java.util.LinkedList;

public class journeyRouter {
static ArrayList<LinkedList<Waypoint>> possibleRoutes;
static ArrayList<LinkedList<Waypoint>> finalRouths;

	static void router(Waypoint start, Waypoint end, Waypoint[] goTo, Waypoint[] avoid, int maxLenght, int maxDanger, int maxDifficulty, int maxNumbersOfStops, int show){
		generateRouts(maxNumbersOfStops);
		removeAvoid(avoid);
		ensureWaypoints(goTo);
		
		switch(show) {
		case 1:
			getEasyest();
			break;
		case 2: 
			getShortest();
			break;
		case 3:
			getSafest();
			break;
		}
		finalRouths = possibleRoutes;
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
}
