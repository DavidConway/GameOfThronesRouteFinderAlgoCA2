package application;

import java.util.ArrayList;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Waypoint {
	public static ArrayList<Waypoint> allWaypoints = new ArrayList<>();
	private ArrayList<Integer> connectedRoadsIndexes = new ArrayList<>();
	private ArrayList<Road> connectedRoads = new ArrayList<>();
	private double mapX, mapY;
	private double[] length = new double[3];
	private int[] difficulty = new int[3];
	private int[] danger = new int[3];
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

	public void addRoad(Road newRoad) {
		int newRoadID = Road.allRoutes.indexOf(newRoad);
		connectedRoadsIndexes.add(newRoadID);
		connectedRoads.add(newRoad);
	}

	public ArrayList<Integer> getConnectedRoughts() {
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
			Road removeRoad = Road.allRoutes.get(0);
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

	public ArrayList<Road> getConnectedRoads() {
		return connectedRoads;
	}

	public void setConnectedRoads(ArrayList<Road> connectedRoads) {
		this.connectedRoads = connectedRoads;
	}

	public double getLength(int i) {
		return length[i];
	}

	public void setLength(int i, double length) {
		this.length[i] = length;
	}

	public int getDifficulty(int i) {
		return difficulty[i];
	}

	public void setDifficulty(int i, int difficulty) {
		this.difficulty[i] = difficulty;
	}

	public int getDanger(int i) {
		return danger[i];
	}

	public void setDanger(int i, int danger) {
		this.danger[i] = danger;
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
