package application;

import java.util.ArrayList;

import javafx.scene.shape.Line;

public class Road {
	public static ArrayList<Road> allRoutes = new ArrayList<>();
	public Waypoint start, end;
	public int danger = 0, difficulty = 0;
	public double length = 0;
	public Line line;

	Road(Waypoint start, Waypoint end, int danger, double length, int difficulty) {
		this.start = start;
		this.end = end;
		this.danger = danger;
		this.length = length;
		this.difficulty = difficulty;

		allRoutes.add(this);
		start.addRoad(this);
		end.addRoad(this);
	}

	Road(Waypoint start, Waypoint end, double length, Line line) {
		this.start = start;
		this.end = end;
		this.length = length;
		this.setLine(line);
		line.setOnMouseClicked(e-> deleteRoad(line));
		
		
		allRoutes.add(this);
		start.addRoad(this);
		end.addRoad(this);

	}

	public Road() {

	}

	public Waypoint getStart() {
		return start;
	}

	public void setStart(Waypoint waypoint) {
		this.start = waypoint;
	}

	public Waypoint getEnd() {
		return end;
	}

	public void setEnd(Waypoint end) {
		this.end = end;
	}

	public void deleteRoad() {
		Integer thisIndex = Road.allRoutes.indexOf(this);
		for (Waypoint check : Waypoint.allWaypoints) {
			ArrayList<Integer> checksRoad = check.getConnectedRoads();
			if (checksRoad.contains(thisIndex)) {
				checksRoad.remove(thisIndex);
			}
			//if(check.getConnectedRoads().size()>0) {
				for (Integer checkIndex : check.getConnectedRoads()) {
					if (checkIndex > thisIndex) {
						int update = check.getConnectedRoads().indexOf((Integer)checkIndex);
						Integer change =  check.getConnectedRoads().get(update);
						change--;
						check.getConnectedRoads().set(update, change);
						// that thing did not want to update
					}
			//	}
			}
			
		}
		allRoutes.remove(this);
		
	}
	public void deleteRoad(Line line) {
		line.setDisable(true);
		line.setVisible(false);
		deleteRoad();
	}

	public Waypoint getOpposite(Waypoint in) {
		if (in == start) {
			return end;
		} else {
			return start;
		}
	}

	public int getDanger() {
		return danger;
	}

	public void setDanger(int danger) {
		this.danger = danger;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

}
