package application;

import java.util.ArrayList;

public class Route {
static ArrayList<Route> allRouts;
static int nextRouteIndex = 0;
Waypoint waypointOne,waypointTwo;
int dangerLevel,roughtLenght,roughtDifficulty,index;

	Route(Waypoint start, Waypoint end, int danger, int lenght,int difficulty){
		waypointOne = start;
		waypointTwo = end;
		dangerLevel = danger;
		roughtLenght = lenght;
		roughtDifficulty = difficulty;
		
		index = nextRouteIndex;
		nextRouteIndex++;
		allRouts.add(this);
	}

}
