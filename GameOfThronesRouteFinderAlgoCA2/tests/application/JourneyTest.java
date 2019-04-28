package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JourneyTest {
	
	Waypoint A;
	Waypoint B;
	Waypoint C;
	Waypoint D;
	Waypoint E;
	
	Road AB;
	Road AC;
	Road AE;
	
	Road DB;
	Road DC;
	Road DE;
	
	Road EB;
	Road EC;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Waypoint.allWaypoints = new ArrayList<>();
		Road.allRoutes = new ArrayList<>();
		A = new Waypoint();
		B = new Waypoint();
		C = new Waypoint();
		D = new Waypoint();
		E = new Waypoint();
		
		AB = new Road(A,B,2,1,3);
		AC = new Road(A,C,3,2,1);
		AE = new Road(A,E,1,3,2);
		
		DB = new Road(D,B,2,1,3);
		DC = new Road(D,C,3,2,1);
		DE = new Road(D,E,1,3,2);
		
		EB = new Road(E,B,20,20,20);
		EC = new Road(E,C,20,20,20);
		
		
		
	}
	

	@Test
	void journeyDan() {
		Journey journey1 = new Journey();
		journey1.waypoints.addLast(A);
		journey1.waypoints.addLast(E);
		journey1.waypoints.addLast(D);
		
		Journey journey2 = new Journey();
		journey2.waypoints.addLast(A);
		journey2.waypoints.addLast(B);
		journey2.waypoints.addLast(D);
		
		Journey journey3 = new Journey();
		journey3.waypoints.addLast(A);
		journey3.waypoints.addLast(C);
		journey3.waypoints.addLast(D);
		
		JourneyRouter.router(A, D, null, null, 1);
		
		assertTrue(JourneyRouter.finalRouths.get(0).waypoints.equals(journey1.waypoints));
		assertTrue(JourneyRouter.finalRouths.get(1).waypoints.equals(journey2.waypoints));
		assertTrue(JourneyRouter.finalRouths.get(2).waypoints.equals(journey3.waypoints));
	}
	
	@Test
	void journeyLen() {
		Journey journey1 = new Journey();
		journey1.waypoints.addLast(A);
		journey1.waypoints.addLast(E);
		journey1.waypoints.addLast(D);
		
		Journey journey2 = new Journey();
		journey2.waypoints.addLast(A);
		journey2.waypoints.addLast(B);
		journey2.waypoints.addLast(D);
		
		Journey journey3 = new Journey();
		journey3.waypoints.addLast(A);
		journey3.waypoints.addLast(C);
		journey3.waypoints.addLast(D);
		
		JourneyRouter.router(A, D, null, null, 0);
		
		assertTrue(JourneyRouter.finalRouths.get(0).waypoints.equals(journey2.waypoints));
		assertTrue(JourneyRouter.finalRouths.get(1).waypoints.equals(journey3.waypoints));
		assertTrue(JourneyRouter.finalRouths.get(2).waypoints.equals(journey1.waypoints));
	}
	
	@Test
	void journeyDif() {
		Journey journey1 = new Journey();
		journey1.waypoints.addLast(A);
		journey1.waypoints.addLast(E);
		journey1.waypoints.addLast(D);
		
		Journey journey2 = new Journey();
		journey2.waypoints.addLast(A);
		journey2.waypoints.addLast(B);
		journey2.waypoints.addLast(D);
		
		Journey journey3 = new Journey();
		journey3.waypoints.addLast(A);
		journey3.waypoints.addLast(C);
		journey3.waypoints.addLast(D);
		
		JourneyRouter.router(A, D, null, null, 2);
		
		assertTrue(JourneyRouter.finalRouths.get(0).waypoints.equals(journey3.waypoints));
		assertTrue(JourneyRouter.finalRouths.get(1).waypoints.equals(journey1.waypoints));
		assertTrue(JourneyRouter.finalRouths.get(2).waypoints.equals(journey2.waypoints));
	}
	
	@Test
	void journeyDel() {
		
		Journey journey1 = new Journey();
		journey1.waypoints.addLast(A);
		journey1.waypoints.addLast(B);
		journey1.waypoints.addLast(D);
		
		Journey journey2 = new Journey();
		journey2.waypoints.addLast(A);
		journey2.waypoints.addLast(C);
		journey2.waypoints.addLast(D);
		
		E.deleteWaypoint();
		
		JourneyRouter.router(A, D, null, null, 1);
		
		assertTrue(JourneyRouter.finalRouths.get(0).waypoints.equals(journey1.waypoints));
		assertTrue(JourneyRouter.finalRouths.get(1).waypoints.equals(journey2.waypoints));
	}
	
	@Test
	void journeyAvoid() {
		
		Journey journey1 = new Journey();
		journey1.waypoints.addLast(A);
		journey1.waypoints.addLast(B);
		journey1.waypoints.addLast(D);
		
		Journey journey2 = new Journey();
		journey2.waypoints.addLast(A);
		journey2.waypoints.addLast(C);
		journey2.waypoints.addLast(D);
		
		Waypoint[] avoid = new Waypoint[1];
		
		avoid[0] = E;
		
		JourneyRouter.router(A, D, null, avoid, 1);
		
		assertTrue(JourneyRouter.finalRouths.get(0).waypoints.equals(journey1.waypoints));
		assertTrue(JourneyRouter.finalRouths.get(1).waypoints.equals(journey2.waypoints));
	}
	
	@Test
	void journeyGoTo() {
		
		Journey journey1 = new Journey();
		journey1.waypoints.addLast(A);
		journey1.waypoints.addLast(E);
		journey1.waypoints.addLast(D);
		
		Journey journey2 = new Journey();
		journey2.waypoints.addLast(A);
		journey2.waypoints.addLast(E);
		journey2.waypoints.addLast(B);
		journey2.waypoints.addLast(D);
		
		Journey journey3 = new Journey();
		journey3.waypoints.addLast(A);
		journey3.waypoints.addLast(B);
		journey3.waypoints.addLast(E);
		journey3.waypoints.addLast(D);
		
		
		Waypoint[] goTo = new Waypoint[1];
		
		goTo[0] = E;
		
		JourneyRouter.router(A, D, goTo, null, 1);
		
		assertTrue(JourneyRouter.finalRouths.get(0).waypoints.equals(journey1.waypoints));
		assertTrue(JourneyRouter.finalRouths.get(1).waypoints.equals(journey2.waypoints));
		assertTrue(JourneyRouter.finalRouths.get(2).waypoints.equals(journey3.waypoints));
	}
	

}
