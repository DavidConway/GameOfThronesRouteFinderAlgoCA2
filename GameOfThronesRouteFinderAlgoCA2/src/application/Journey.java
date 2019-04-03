package application;

import java.util.LinkedList;

public class Journey {
	public LinkedList<Waypoint> waypoints = new LinkedList<Waypoint>();
	double lenght = 0;
	int danger = 0;
	int difficlty = 0;

	public Journey() {
	}

	public void extenOut() {// creats a new journeys that imclude the new waypoint at the end of each rode
		Waypoint endPoint = waypoints.getLast(); // gets the end point of the current journey
		for (Integer roadIndex : endPoint.getConnectedRoughts()) {
			Road newRoad = Road.allRouts.get(roadIndex); // gets the Road that will be used
			Waypoint extendOut = newRoad.getOpposite(endPoint);// gets the next waypoint
			Journey newJourney = new Journey(); // sets up for the new journey

			newJourney.addPreviousWaypoints();// Copys the waypoints to the new journey
			if (newJourney.checkNotDoubleBack(extendOut) && checkNotAvoid(extendOut)) {
				newJourney.getWaypoints().addLast(extendOut);// adds the next waypoint
				newJourney.lenght = this.lenght + newRoad.length;// Fingers out the journey values
				newJourney.danger = this.danger + newRoad.danger;
				newJourney.difficlty = this.difficlty + newRoad.difficulty;
				journeyRouter.possibleRoutes.add(newJourney);// adds the journey to the possible routes
			}

		}
		journeyRouter.possibleRoutes.remove(this);// removes the current journey sins it has been extended
	}

	private void addPreviousWaypoints() {
		for(Waypoint adding: journeyRouter.currentJourney.waypoints) {
			waypoints.addLast(adding);
		}
		
	}

	private boolean checkNotAvoid(Waypoint check) {
		boolean safe = true;
		if (journeyRouter.avoidWaypoints != null) {
			for (Waypoint avoid : journeyRouter.avoidWaypoints) {
				if (check == avoid) {
					safe = false;
				}
			}
		}
		return safe;
	}

	public boolean goseToAllPoints() {
		boolean allPoints = true;
		if (journeyRouter.avoidWaypoints != null) {
			for (Waypoint goTo : journeyRouter.goToWaypoints) {
				if (!this.getWaypoints().contains(goTo)) {
					allPoints = false;
				}
			}
		}
		return allPoints;
	}

	private boolean checkNotDoubleBack(Waypoint check) {
		if (this.getWaypoints().contains(check)) {
			return false;
		} else {
			return true;
		}
	}

	public LinkedList<Waypoint> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(LinkedList<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

	public double getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public int getDanger() {
		return danger;
	}

	public void setDanger(int danger) {
		this.danger = danger;
	}

	public int getDifficlty() {
		return difficlty;
	}

	public void setDifficlty(int difficlty) {
		this.difficlty = difficlty;
	}

	@Override
	public String toString() {
		String Out = "";
		for (Waypoint point : this.waypoints) {
			Out = Out + "X" + point.getMapX() + "Y" + point.getMapY() + "-->";
		}
		return Out;
	}
}
