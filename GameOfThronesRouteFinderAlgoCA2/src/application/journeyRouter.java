package application;

import java.util.ArrayList;
import java.util.LinkedList;

public class journeyRouter {
	static ArrayList<Journey> possibleRoutes = new ArrayList<Journey>();
	static ArrayList<Journey> finalRouths = new ArrayList<Journey>();
	static Waypoint[] avoidWaypoints = null;
	static Waypoint[] goToWaypoints = null;
	static int showMode = 0;
	private static Journey currentJourney;

	public static ArrayList<Journey> router(Waypoint start, Waypoint end, Waypoint[] goTo, Waypoint[] avoid, int show) {
		avoidWaypoints = avoid;
		Journey startPoint = new Journey();
		showMode = show;
		startPoint.getWaypoints().add(start);
		possibleRoutes.add(startPoint);
		while (finalRouths.size() != 3) {
			currentJourney = extendPoint();
			currentJourney.extenOut();
			if (currentJourney.getWaypoints().getLast() == end) {
				if (currentJourney.goseToAllPoints()) {
					finalRouths.add(currentJourney);
				}
				possibleRoutes.remove(currentJourney);
			}
		}
		return finalRouths;

	}
	
	public static ArrayList<Journey> router(Waypoint start, Waypoint end, int show) {
		Journey startPoint = new Journey();
		showMode = show;
		startPoint.getWaypoints().add(start);
		possibleRoutes.add(startPoint);
		while (finalRouths.size() != 1) {
			currentJourney = extendPoint();
			currentJourney.extenOut();
			if (currentJourney.getWaypoints().getLast() == end) {
				finalRouths.add(currentJourney);
				possibleRoutes.remove(currentJourney);
			}
		}
		return finalRouths;

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
		switch (showMode) {
		case 0:
			for (Journey check : possibleRoutes) {
				if(reternJourney == null) {
					reternJourney = check;
				}
				else if (check.getLenght() < reternJourney.getLenght() || reternJourney == null) {
					reternJourney = check;
				}
			}
			break;
		case 1:
			for (Journey check : possibleRoutes) {
				if (check.getDanger() < reternJourney.getDanger() || reternJourney == null) {
					reternJourney = check;
				}
			}
			break;
		case 2:
			for (Journey check : possibleRoutes) {
				if (check.getDifficlty() < reternJourney.getDifficlty() || reternJourney == null) {
					reternJourney = check;
				}
			}
			break;
		}
		return reternJourney;
	}
}
