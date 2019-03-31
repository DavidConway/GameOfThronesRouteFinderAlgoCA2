package application;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
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
		//Waypoint waypoint = new Waypoint(x, y);
		ImageView node = new ImageView("/images/waypoint.png");
		node.setMouseTransparent(false);
		node.setPickOnBounds(true);
		node.setFitWidth(20);
		node.setPreserveRatio(true);
		node.setX(x-12);
		node.setY(y-16);
		node.setOnMouseClicked(e-> newMenu(node).show(node, Side.BOTTOM, 0, 0));
		return node;
	}

	
	public ContextMenu newMenu(ImageView node)
	{
		final ContextMenu contextMenu = new ContextMenu();
		MenuItem item1 = new MenuItem("Set as starting positon");
		item1.setOnAction(e-> setStart(node));
		MenuItem item2 = new MenuItem("Set as destination");
		item1.setOnAction(e-> setEnd(node));
		MenuItem item3 = new MenuItem("Add route");
		MenuItem item4 = new MenuItem("Remove route");
		MenuItem item5 = new MenuItem("Edit Name");
		contextMenu.getItems().addAll(item1, item2, item3, item4, item5);
		return contextMenu;
	}

	private Object setEnd(ImageView node) {
		return null;
	}

	private Object setStart(ImageView node) {

		return null;
	}

}
