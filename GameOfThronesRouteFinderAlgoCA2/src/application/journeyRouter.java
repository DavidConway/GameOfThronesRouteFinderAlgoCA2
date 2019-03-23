package application;

import java.util.ArrayList;

public class journeyRouter {
ArrayList<Waypoint[]> possibleRoutes;
static ArrayList<Waypoint[]> finalRouths;

	void router(Waypoint start, Waypoint end, Waypoint[] goTo, Waypoint[] avoid, int maxLenght, int maxDanger, int maxDifficulty, int maxNumbersOfStops){
		generateRouts(maxNumbersOfStops);
		removeDanger(maxDanger);
		removeLong(maxLenght);
		removeHard(maxDifficulty);
		removeAvoid(avoid);
		ensureWaypoints(goTo);
		finalRouths = possibleRoutes;
	}

	private void ensureWaypoints(Waypoint[] goTo) {
		// TODO Auto-generated method stub
		
	}

	private void removeAvoid(Waypoint[] avoid) {
		// TODO Auto-generated method stub
		
	}

	private void removeHard(int maxDifficulty) {
		// TODO Auto-generated method stub
		
	}

	private void removeLong(int maxLenght) {
		// TODO Auto-generated method stub
		
	}

	private void removeDanger(int maxDanger) {
		// TODO Auto-generated method stub
		
	}

	private void generateRouts(int maxNumbersOfStops) {
		// TODO Auto-generated method stub
		
	}
}
