package application;

import java.util.ArrayList;

public class Route {
static ArrayList<Route> allRouts;
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

}
