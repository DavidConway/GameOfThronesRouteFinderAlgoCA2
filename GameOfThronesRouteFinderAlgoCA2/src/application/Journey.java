package application;

import java.util.LinkedList;

public class Journey {
	static LinkedList<Waypoint> waypoints;
	int lenght = 0;
	int danger = 0;
	int difficlty = 0;
	
	
	

	public Journey(){
	}
	
	public void extenOut() {
		Waypoint endPoint = waypoints.getLast();
		for(Integer routeIndex: endPoint.getConnectedRoughts()) {
			Route newRoute = Route.allRouts.get(routeIndex);
			Waypoint extendOut = newRoute.getOpposite(endPoint);
			Journey newJourney = new Journey();
			
			newJourney.setWaypoints(this.getWaypoints());
			newJourney.getWaypoints().addLast(extendOut);
			
			newJourney.lenght= this.lenght + newRoute.roughtLenght;
			newJourney.danger= this.danger + newRoute.dangerLevel;
			newJourney.difficlty= this.difficlty + newRoute.roughtDifficulty;
			journeyRouter.possibleRoutes.add(newJourney);
		}
		journeyRouter.possibleRoutes.remove(this);
	}
	
	public LinkedList<Waypoint> getWaypoints() {
		return waypoints;
	}
	public void setWaypoints(LinkedList<Waypoint> waypoints) {
		Journey.waypoints = waypoints;
	}
	public int getLenght() {
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
}
