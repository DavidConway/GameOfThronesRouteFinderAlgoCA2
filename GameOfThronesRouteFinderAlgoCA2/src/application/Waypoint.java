package application;

import java.util.ArrayList;

public class Waypoint {
static ArrayList<Waypoint> allWaypoints;
ArrayList<Integer> connectedRoutesIndexes;
double mapX,mapY;
String waypointName;

	Waypoint(double X, double Y, String name){
		mapX = X;
		mapY = Y;
		waypointName = name;
		allWaypoints.add(this);
	}
	
	void addRoute(Route newRout){
		int newRoughtID = Route.allRouts.indexOf(newRout);
		connectedRoutesIndexes.add(newRoughtID);
	}
}
