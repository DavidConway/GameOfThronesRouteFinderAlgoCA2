package application;

import java.util.ArrayList;

public class JJourney {

	Waypoint beginning;
	ArrayList<Waypoint> visited = new ArrayList<>();
	ArrayList<Waypoint> queue = new ArrayList<>();
	ArrayList<Waypoint> tempQueue = new ArrayList<>();
	ArrayList<Waypoint> removeQueue = new ArrayList<>();

	public JJourney(Waypoint beginning) {
		this.beginning = beginning;
		queue.add(beginning);
		for (int i = 0; i < Waypoint.allWaypoints.size(); i++) {
			for (Waypoint w : queue) {
				for (Road r : w.getConnectedRoads()) {
					if (visited.contains(r.getOpposite(w))) {
						Waypoint waypoint = r.getOpposite(w);

						if (waypoint.getLength() > w.getLength() + r.getLength()) {
							assignLength(w, r, waypoint);
						}
						if (waypoint.getDanger() > w.getDanger() + r.getDanger()) {
							assignDanger(w, r, waypoint);
						}
						if (waypoint.getDifficulty() > w.getDifficulty() + r.getDifficulty()) {
							assignDifficulty(w, r, waypoint);
						}

						continue;
					}
					if (queue.contains(r.getOpposite(w))) {
						continue;
					}
					Waypoint waypoint = r.getOpposite(w);
					assignLength(w, r, waypoint);
					assignDanger(w, r, waypoint);
					assignDifficulty(w, r, waypoint);
					tempQueue.add(waypoint);
				}
				visited.add(w);
				removeQueue.add(w);
			}
			queue.removeAll(removeQueue);
			queue.addAll(tempQueue);
		}
	}

	private void assignLength(Waypoint w, Road r, Waypoint w2) {
		w2.setLengthPrevious(w);
		w2.setLength(w.getLength() + r.getLength());
	}

	private void assignDanger(Waypoint w, Road r, Waypoint w2) {

		w2.setDangerPrevious(w);
		w2.setDanger(r.getDanger() + w2.getDangerPrevious().getDanger());
	}

	private void assignDifficulty(Waypoint w, Road r, Waypoint w2) {
		w2.setDifficultyPrevious(w);
		w2.setDifficulty(r.getDifficulty() + w2.getLengthPrevious().getDifficulty());

	}
	
	public ArrayList<Waypoint> shortestDistance(Waypoint waypoint) {
		ArrayList<Waypoint> roads = new ArrayList<>();
		roads.add(waypoint);
		while (waypoint.getLengthPrevious() != null) {
			roads.add(waypoint.getLengthPrevious());
			waypoint = waypoint.getLengthPrevious();
		}
		return roads;
	}

	public ArrayList<Waypoint> leastDangerous(Waypoint waypoint) {
		ArrayList<Waypoint> roads = new ArrayList<>();
		roads.add(waypoint);
		while (waypoint.getLengthPrevious() != null) {
			roads.add(waypoint.getDangerPrevious());
			waypoint = waypoint.getDangerPrevious();
		}
		return roads;
	}

	public ArrayList<Waypoint> leastDifficult(Waypoint waypoint) {
		ArrayList<Waypoint> roads = new ArrayList<>();
		roads.add(waypoint);
		while (waypoint.getLengthPrevious() != null) {
			roads.add(waypoint.getDifficultyPrevious());
			waypoint = waypoint.getDifficultyPrevious();
		}
		return roads;
	}
}