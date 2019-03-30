package application;

import java.util.LinkedList;

public class Journey {
	static LinkedList<Waypoint> waypoints;
	
	public Journey(){
		
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
	public int getDifaculty() {
		return difaculty;
	}
	public void setDifaculty(int difaculty) {
		this.difaculty = difaculty;
	}
	int lenght = 0;
	int danger = 0;
	int difaculty = 0;
	
	
}
