package application;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Waypoint {
public static ArrayList<Waypoint> allWaypoints = new ArrayList<>();
private ArrayList<Integer> connectedRoutesIndexes;
private double mapX,mapY;
private TextField textField;
private ImageView iView;
private String waypointName;

	Waypoint(double x, double y){
		mapX = x;
		mapY = y;
		
		iView = new ImageView("/images/waypoint.png");
		iView.setPickOnBounds(true);
		iView.setFitWidth(20);
		iView.setPreserveRatio(true);
		iView.setX(x-12);
		iView.setY(y-16);
		//allWaypoints.add(this);
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
	
	public ImageView getIView()
	{
		return iView;
	}
	
}
