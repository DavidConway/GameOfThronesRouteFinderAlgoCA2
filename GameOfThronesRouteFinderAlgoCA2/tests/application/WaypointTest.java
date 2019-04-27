package application;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WaypointTest {
	
	Waypoint w1, w2, w3;

	@BeforeEach
	void setUp() throws Exception {
		w1 = new Waypoint();
		w2 = new Waypoint();
		w3 = new Waypoint();
	}

	@Test
	void testAddRoad() {
		Road road = new Road();
		w1.addRoad(road);
		assertTrue(w1.getConnectedRoads().contains(road));
	}
	
	/**
	@Test
	void testRemoveRoad() {
		Road road = new Road();
		w1.addRoad(road);
		w1.removeRoad(road);
		assertTrue(w1.getConnectedRoads().isEmpty());
	}
	**/
	
	@Test
	void testDeleteWaypoint() {
		System.out.println("Waypoints size: "+Waypoint.allWaypoints.size());
		w1.deleteWaypoint();
		assertFalse(Waypoint.allWaypoints.contains(w1));
	}

}
