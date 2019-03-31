package application;

import java.util.ArrayList;

public class Waypoint {
public static ArrayList<Waypoint> allWaypoints;
private ArrayList<Integer> connectedRoutesIndexes;
private double mapX,mapY;
private String waypointName;

	Waypoint(double X, double Y){
		mapX = X;
		mapY = Y;
		allWaypoints.add(this);
	}
	
	public void addRoute(Route newRoute){
		int newRoughtID = Route.allRouts.indexOf(newRoute);
		connectedRoutesIndexes.add(newRoughtID);
	}
	
	public void removeRoute(Route oldRoute) {
		Integer removeInt = Route.allRouts.indexOf(oldRoute);
		connectedRoutesIndexes.remove(removeInt);
	}
	
	public ArrayList<Integer> getConnectedRoughts(){
		return this.connectedRoutesIndexes;
	}
	
	public double getMapX() {
		return mapX;
	}
	
	public double getMapY() {
		return mapY;
	}
	
	public void deleteWaypoint() {
		while(this.connectedRoutesIndexes != null) {
			Route removeRoute = Route.allRouts.get(0);
			removeRoute.deleteRoute();
		}
		allWaypoints.remove(this);
	}
	
}
