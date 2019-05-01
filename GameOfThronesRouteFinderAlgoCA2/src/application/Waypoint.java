package application;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Waypoint {
	public static ArrayList<Waypoint> allWaypoints = new ArrayList<>();
	private ArrayList<Integer> connectedRoadsIndexes = new ArrayList<>();
	private double mapX, mapY;
	private TextField nameField;
	private ImageView iView;

	Waypoint(double x, double y) {
		mapX = x;
		mapY = y;
		iView = new ImageView("/images/waypoint.png");
		iView.setPickOnBounds(true);
		iView.setFitWidth(20);
		iView.setPreserveRatio(true);
		iView.setX(x - 12);
		iView.setY(y - 15);
		nameField = new TextField("");
		nameField.getStyleClass().add("mapName");
		nameField.setLayoutY(y + 10);
		nameField.setLayoutX(x - 20);
		allWaypoints.add(this);
	}

	public Waypoint() {
		allWaypoints.add(this);
	}

	public void addRoad(Road newRoad) {
		int newRoadID = Road.allRoutes.indexOf(newRoad);
		connectedRoadsIndexes.add(newRoadID);
	}

	public ArrayList<Integer> getConnectedRoads() {
		return this.connectedRoadsIndexes;
	}

	public double getMapX() {
		return mapX;
	}

	public double getMapY() {
		return mapY;
	}

	public void deleteWaypoint() {
		while (this.connectedRoadsIndexes.size()>0) {
			Road removeRoad = Road.allRoutes.get(connectedRoadsIndexes.get(0));
			removeRoad.deleteRoad();
		}
		allWaypoints.remove(this);
	}
	
	public ImageView getIView() {
		return iView;
	}

	public TextField getName() {
		return nameField;
	}
}
