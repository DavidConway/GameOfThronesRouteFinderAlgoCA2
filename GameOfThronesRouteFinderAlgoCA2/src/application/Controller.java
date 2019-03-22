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
		mapPane.setOnMouseClicked(e-> addNode(e.getX(), e.getY(), mapPane));
	}

	private void addNode(double x, double y, ImageView mapPane) {
		mapAnchor.getChildren().add(createNode(x, y));
	}
	
	private ImageView createNode(double x, double y)
	{
		ImageView node = new ImageView("/images/node.png");
		node.setX(x-12);
		node.setY(y-16);
		return node;
	}

}
