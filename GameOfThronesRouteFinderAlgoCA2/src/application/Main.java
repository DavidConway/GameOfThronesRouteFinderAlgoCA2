package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Main extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane) FXMLLoader.load(Main.class.getResource("scene.fxml"));
			Scene scene = new Scene(root, 1200, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
		/*Waypoint one = new Waypoint(1,2);
		Waypoint one = new Waypoint(1,1);
		Waypoint two = new Waypoint(2,2);
		Waypoint three = new Waypoint (2,3);
		Waypoint four = new Waypoint(2,1);
		Waypoint five = new Waypoint(3,2);
		
		Route oneTwo = new Route(one,two,3.0);
		Route twoFive = new Route(two,five,3.0);
		
		Route oneThree = new Route(one,three,1.0);
		Route threeFive = new Route(three,five,1.0);
		Route threeTwo = new Route(three,two,1.0);
		
		Route oneFour = new Route(one,four,3.0);
		Route fourFive = new Route(four,five,3.0);
		Route twoFour = new Route(two,four,3.0);
		
		for(Journey j: journeyRouter.router(one, three, 0)) {
			System.out.println(j.toString());
		}*/
		System.out.println("BattlePass is shit");
	}
	
	
}
