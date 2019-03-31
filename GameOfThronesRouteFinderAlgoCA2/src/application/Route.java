package application;

import java.util.ArrayList;

public class Route {
static ArrayList<Route> allRouts = new ArrayList<>();
Waypoint waypointOne,waypointTwo;
int dangerLevel,roughtLenght,roughtDifficulty,index;

	Route(Waypoint start, Waypoint end, int danger, int lenght,int difficulty){
		waypointOne = start;
		waypointTwo = end;
		dangerLevel = danger;
		roughtLenght = lenght;
		roughtDifficulty = difficulty;
		
		allRouts.add(this);
		start.addRoute(this);
		end.addRoute(this);
	}
	
	
	public void deleteRoute() {
		Integer thisIndex = Route.allRouts.indexOf(this);
		for(Waypoint check: Waypoint.allWaypoints) {
			ArrayList<Integer> checksRoute = check.getConnectedRoughts();	
			if (checksRoute.contains(thisIndex)){
				checksRoute.remove(thisIndex);
			}
			for(Integer checkIndex: check.getConnectedRoughts()) {
				if (checkIndex > thisIndex) {
					checkIndex--;
				}
			}
		}
		allRouts.remove(this);
	}
	
	public Waypoint getOpposite(Waypoint in) {
		if(in == waypointOne) {
			return waypointTwo;
		}
		else {
			return waypointOne;
		}
	}

}
