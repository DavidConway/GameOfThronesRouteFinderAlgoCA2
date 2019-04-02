package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JourneyTest {
	
	Waypoint w1 = new Waypoint();
	Waypoint w2 = new Waypoint();
	Waypoint w3 = new Waypoint();
	Waypoint w4 = new Waypoint();
	Waypoint w5 = new Waypoint();
	Route r1 = new Route(w1,w2,5.0);
	Route r6 = new Route(w1,w4,3.0);
	Route r3 = new Route(w1,w3,1.0);
	
	Route r2 = new Route(w2,w5,3.0);
	
	
	Route r4 = new Route(w3,w5,1.0);
	Route r5 = new Route(w3,w2,1.0);
	
	Route r7 = new Route(w4,w5,3.0);
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		
		
		
	}
	

	@Test
	void journey() {
		JJourney journey = new JJourney(w1);
		ArrayList<Waypoint> routes = new ArrayList<>();
		routes.add(w2);
		routes.add(w3);
		routes.add(w1);
		
		assertEquals(routes, journey.shortestDistance(w2));
	}
	@Test
	void journeyDist() {
		JJourney journey = new JJourney(w1);

		
		assertEquals(w2.getLength(), 2);
	}
	

}
