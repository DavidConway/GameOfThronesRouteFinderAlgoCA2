package application;

import java.util.ArrayList;

public class Road {
	static ArrayList<Road> allRouts = new ArrayList<>();
	Waypoint start, end;
	int danger = 0, difficulty = 0;
	double length = 0;

	Road(Waypoint start, Waypoint end, int danger, double length, int difficulty) {
		this.start = start;
		this.end = end;
		this.danger = danger;
		this.length = length;
		this.difficulty = difficulty;

		allRouts.add(this);
		start.addRoad(this);
		end.addRoad(this);
	}

	Road(Waypoint start, Waypoint end, double length) {
		this.start = start;
		this.end = end;
		this.length = length;
		
		allRouts.add(this);
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
		Integer thisIndex = Road.allRouts.indexOf(this);
		for (Waypoint check : Waypoint.allWaypoints) {
			ArrayList<Integer> checksRoad = check.getConnectedRoughts();
			if (checksRoad.contains(thisIndex)) {
				checksRoad.remove(thisIndex);
			}
			for (Integer checkIndex : check.getConnectedRoughts()) {
				if (checkIndex > thisIndex) {
					checkIndex--;
				}
			}
		}
		allRouts.remove(this);
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

}
