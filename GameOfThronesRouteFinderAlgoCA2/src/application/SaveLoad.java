package application;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SaveLoad {
	static void save() {
		try {
			FileOutputStream outRoad = new FileOutputStream(new File("Road.xml"));
			XMLEncoder encoR = new XMLEncoder(outRoad);
			encoR.writeObject(Road.allRoutes);
			encoR.close();
			outRoad.close();

			FileOutputStream outWaypoints = new FileOutputStream(new File("Waypoints.xml"));
			XMLEncoder encoW = new XMLEncoder(outWaypoints);
			encoW.writeObject(Waypoint.allWaypoints);
			encoW.close();
			outWaypoints.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void load() {
		try {
			FileInputStream inWaypoint = new FileInputStream(new File("./GameOfThronesRouteFinderAlgoCA2/src/Waypoints.xml"));
			XMLDecoder decoW = new XMLDecoder(inWaypoint);
			Waypoint.allWaypoints = (ArrayList<Waypoint>) decoW.readObject();
			decoW.close();
			inWaypoint.close();

			FileInputStream inRoad = new FileInputStream(new File("./GameOfThronesRouteFinderAlgoCA2/src/Road.xml"));
			XMLDecoder decoR = new XMLDecoder(inRoad);
			Road.allRoutes = (ArrayList<Road>) decoR.readObject();
			decoR.close();
			inRoad.close();
			
		} catch (IOException e) {
			System.out.println("erroer");
		}
	}
}
