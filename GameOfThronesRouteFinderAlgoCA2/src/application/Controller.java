package application;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

public class Controller {
	@FXML
	AnchorPane mapAnchor;

	Image map = new Image("/images/map.png");
	ImageView mapPane = new ImageView(map);
	
	@FXML
	public void initialize()
	{
		mapPane.setPreserveRatio(true);
		mapAnchor.getChildren().add(mapPane);
		mapPane.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
		mapAnchor.setMinWidth(mapPane.getFitWidth());
	}

}
