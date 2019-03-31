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
	
	ContextMenu activeMenu = new ContextMenu();

	Image map = new Image("/images/map.png");
	ImageView mapPane = new ImageView(map);
	
	@FXML
	public void initialize()
	{
		
		mapPane.setPreserveRatio(true);
		mapAnchor.getChildren().add(mapPane);
		mapPane.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
		mapAnchor.setMinWidth(mapPane.getFitWidth());
		mapPane.setOnMouseClicked(e-> baseMenu(e.getX(), e.getY()).show(mapAnchor, null, e.getX(), e.getY()));
	}
		
	
	public ContextMenu baseMenu(double x, double y)
	{
		if (activeMenu.isShowing())
		{
		activeMenu.hide();
		}
		activeMenu = new ContextMenu();
		MenuItem item0 = new MenuItem("Add Waypoint");
		item0.setOnAction(e-> addNode(x,y, mapPane));
		activeMenu.getItems().clear();
		activeMenu.getItems().addAll(item0);
		
		return activeMenu;
		
	}

	private void addNode(double x, double y, ImageView mapPane) {
		mapAnchor.getChildren().add(createNode(x, y).getIView());
	}
	
	private Waypoint createNode(double x, double y)
	{
		Waypoint waypoint = new Waypoint(x, y);
		ImageView iView = waypoint.getIView();
		iView.setOnMouseClicked(e-> newMenu(waypoint).show(iView, Side.BOTTOM, 0, 0));
		return waypoint;
	}

	
	public ContextMenu newMenu(Waypoint waypoint)
	{
		if (activeMenu.isShowing())
		{
		activeMenu.hide();
		}
		activeMenu = new ContextMenu();
		
		MenuItem item1 = new MenuItem("Set as starting positon");
		item1.setOnAction(e-> setStart(waypoint));
		MenuItem item2 = new MenuItem("Set as destination");
		item1.setOnAction(e-> setEnd(waypoint));
		MenuItem item3 = new MenuItem("Add route");
		MenuItem item4 = new MenuItem("Remove route");
		MenuItem item5 = new MenuItem("Edit Name");
		item5.setOnAction(e-> setName(waypoint));
		MenuItem item6 = new MenuItem("Remove Waypoint");
		item6.setOnAction(e-> remove(waypoint));
		activeMenu.getItems().addAll(item1, item2, item3, item4, item5, item6);
		return activeMenu;
	}

	private void setName(Waypoint waypoint)
	{
		if (mapAnchor.getChildren().contains(waypoint.getName()))
		{
			waypoint.getName().requestFocus(); 
		}
		else
		{
		mapAnchor.getChildren().add(waypoint.getName());
		waypoint.getName().requestFocus();
		}
	}
	
	private void remove(Waypoint waypoint)
	{
		mapAnchor.getChildren().remove(waypoint.getIView());
		mapAnchor.getChildren().remove(waypoint.getName());
	}
	private Object setEnd(Waypoint waypoint) {
		return null;
	}

	private Object setStart(Waypoint waypoint) {

		return null;
	}

}
