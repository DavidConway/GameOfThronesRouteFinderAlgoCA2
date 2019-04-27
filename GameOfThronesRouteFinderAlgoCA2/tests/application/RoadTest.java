package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.shape.Line;

class RoadTest {
	Road r1, r2, r3;
	Waypoint w1, w2;
	Line line;

	@BeforeEach
	void setUp() throws Exception {
		w1 = new Waypoint();
		w2 = new Waypoint();
		line = new Line();
		r1 = new Road(w1, w2, 5, line);
		
	}
	

	@Test
	void testDeleteRoad() {
		r1.deleteRoad();
		assertTrue(Road.allRoutes.isEmpty());
	}
	
	@Test
	void testGetOpposite() {
		assertEquals(r1.getOpposite(w1), w2);
	}

}
