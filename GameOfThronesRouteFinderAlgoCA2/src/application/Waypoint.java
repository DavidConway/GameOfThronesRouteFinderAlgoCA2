package application;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Waypoint {
	public static ArrayList<Waypoint> allWaypoints = new ArrayList<>();
	private ArrayList<Integer> connectedRoutesIndexes = new ArrayList<>();
	private ArrayList<Route> connectedRoutes = new ArrayList<>();
	private double mapX, mapY, length;
	private int difficulty, danger;
	private TextField nameField;
	private ImageView iView;
	private Waypoint lengthPrevious, difficultyPrevious, dangerPrevious;

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

	public void addRoute(Route newRoute) {
		int newRoughtID = Route.allRouts.indexOf(newRoute);
		connectedRoutesIndexes.add(newRoughtID);
		connectedRoutes.add(newRoute);
	}

	public void removeRoute(Route oldRoute) {
		Integer removeInt = Route.allRouts.indexOf(oldRoute);
		connectedRoutesIndexes.remove(removeInt);
	}

	public ArrayList<Integer> getConnectedRoughts() {
		return this.connectedRoutesIndexes;
	}

	public double getMapX() {
		return mapX;
	}

	public double getMapY() {
		return mapY;
	}

	public void deleteWaypoint() {
		while (this.connectedRoutesIndexes != null) {
			Route removeRoute = Route.allRouts.get(0);
			removeRoute.deleteRoute();
		}
		allWaypoints.remove(this);
	}

	public ImageView getIView() {
		return iView;
	}

	public TextField getName() {
		return nameField;
	}

	public ArrayList<Route> getConnectedRoutes() {
		return connectedRoutes;
	}

	public void setConnectedRoutes(ArrayList<Route> connectedRoutes) {
		this.connectedRoutes = connectedRoutes;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getDanger() {
		return danger;
	}

	public void setDanger(int danger) {
		this.danger = danger;
	}

	public Waypoint getLengthPrevious() {
		return lengthPrevious;
	}

	public void setLengthPrevious(Waypoint lengthPrevious) {
		this.lengthPrevious = lengthPrevious;
	}

	public Waypoint getDifficultyPrevious() {
		return difficultyPrevious;
	}

	public void setDifficultyPrevious(Waypoint difficultyPrevious) {
		this.difficultyPrevious = difficultyPrevious;
	}

	public Waypoint getDangerPrevious() {
		return dangerPrevious;
	}

	public void setDangerPrevious(Waypoint dangerPrevious) {
		this.dangerPrevious = dangerPrevious;
	}

}
