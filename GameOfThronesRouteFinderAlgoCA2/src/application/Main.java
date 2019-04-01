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
		//launch(args);
		Waypoint one = new Waypoint(1,1);
		Waypoint two = new Waypoint(2,2);
		Waypoint three = new Waypoint (3,3);
		Route oneTwo = new Route(one,two,1.0);
		Route twoThree = new Route(two,three,1.0);
		for(Journey j: journeyRouter.router(one, two, 0)) {
			System.out.println(j.toString());
		}
		System.out.println("BattlePass is shit");
	}
	
	
}
