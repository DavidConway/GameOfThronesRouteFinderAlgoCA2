package application;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Screen;

public class Controller {
	@FXML
	AnchorPane mapAnchor;
	@FXML
	private Label journeyStart, journeyEnd;
	@FXML
	private Label buildStart, buildEnd;
	@FXML
	private Label routeStart, routeEnd, routeLength;
	@FXML
	private TextField routeDifficulty, routeDanger;
	@FXML
	private Label SDEase, SDDanger, SDDistance;
	@FXML
	private Label LDDistance, LDDanger, LDEase;
	@FXML
	private Label MEDistance, MEDanger, MEEase;


	ContextMenu activeMenu = new ContextMenu();
	JJourney activeJourney;
	Waypoint beginning, destination;
	Route activeRoute = new Route();
	Waypoint startRoute,endRoute;

	Image map = new Image("/images/map.png");
	ImageView mapPane = new ImageView(map);

	@FXML
	public void initialize() {
		mapPane.setPreserveRatio(true);
		mapAnchor.getChildren().add(mapPane);
		mapPane.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
		mapAnchor.setMinWidth(mapPane.getFitWidth());
		mapPane.setOnMouseClicked(e -> baseMenu(e.getX(), e.getY()).show(mapAnchor, null, e.getX(), e.getY()));
	}

	public ContextMenu baseMenu(double x, double y) {
		if (activeMenu.isShowing()) {
			activeMenu.hide();
		}
		activeMenu = new ContextMenu();
		MenuItem item0 = new MenuItem("Add Waypoint");
		item0.setOnAction(e -> addNode(x, y));
		activeMenu.getItems().clear();
		activeMenu.getItems().addAll(item0);
		return activeMenu;
	}

	private void addNode(double x, double y) {
		Waypoint waypoint = createNode(x, y);
		mapAnchor.getChildren().add(waypoint.getIView());
		mapAnchor.getChildren().add(waypoint.getName());
		waypoint.getName().requestFocus();
	}

	private Waypoint createNode(double x, double y) {
		Waypoint waypoint = new Waypoint(x, y);
		ImageView iView = waypoint.getIView();
		iView.setOnMouseClicked(e -> nodeMenu(waypoint).show(iView, Side.BOTTOM, 0, 0));
		return waypoint;
	}
	
	private void setName(Waypoint waypoint) {
		if (mapAnchor.getChildren().contains(waypoint.getName())) {
			waypoint.getName().requestFocus();
		} else {
			mapAnchor.getChildren().add(waypoint.getName());
			waypoint.getName().requestFocus();
		}
	}

	public ContextMenu nodeMenu(Waypoint waypoint) {
		if (activeMenu.isShowing()) {
			activeMenu.hide();
		}
		activeMenu = new ContextMenu();

		MenuItem item1 = new MenuItem("Begin Journey");
		item1.setOnAction(e -> setStart(waypoint));
		MenuItem item2 = new MenuItem("Set Destination");
		item2.setOnAction(e -> setEnd(waypoint));
		MenuItem item3 = new MenuItem("Set Route Beginning");
		item3.setOnAction(e -> beginConnection(waypoint));
		MenuItem item4 = new MenuItem("Set Route End");
		item4.setOnAction(e -> endConnection(waypoint));
		MenuItem item5 = new MenuItem("Edit Name");
		item5.setOnAction(e -> setName(waypoint));
		MenuItem item6 = new MenuItem("Remove Waypoint");
		item6.setOnAction(e -> remove(waypoint));
		activeMenu.getItems().addAll(item1, item2, item3, item4, item5, item6);
		return activeMenu;
	}
	public ContextMenu journeyMenu(Waypoint waypoint)
	{
		if (activeMenu.isShowing()) {
			activeMenu.hide();
		}
		activeMenu = new ContextMenu();
		MenuItem item1 = new MenuItem("Shortest");
		item1.setOnAction(e -> activeJourney.shortestDistance(waypoint));
		MenuItem item2 = new MenuItem("Safest");
		item1.setOnAction(e -> activeJourney.leastDangerous(waypoint));
		MenuItem item3 = new MenuItem("Easiest");
		item1.setOnAction(e -> activeJourney.leastDifficult(waypoint));
		activeMenu.getItems().addAll(item1, item2, item3);
		return activeMenu;
	}

	private void endConnection(Waypoint waypoint) {
		endRoute = waypoint;
		activeRoute.setEnd(waypoint);
		buildEnd.setText(waypoint.getName().getText());
		if (checkConnection()) {
			buildRoute();
		}
	}

	private boolean checkConnection() {
		if (activeRoute.getStart() != null && activeRoute.getEnd() != null) {
			return true;
		} else
			return false;
	}

	private void beginConnection(Waypoint waypoint) {
		startRoute = waypoint;
		activeRoute.setStart(waypoint);
		buildStart.setText(waypoint.getName().getText());
		if (checkConnection()) {
			buildRoute();
		}
	}

	private void buildRoute() {
		double w, h; // width, height
		w = Math.abs(startRoute.getMapX() - endRoute.getMapX());
		h = Math.abs(startRoute.getMapY() - endRoute.getMapY());

		Route route = new Route(startRoute, endRoute, (Math.sqrt(w * w + h * h)));
		Line line = new Line(startRoute.getMapX(), startRoute.getMapY(), endRoute.getMapX(), endRoute.getMapY());
		line.setOnMousePressed(e -> displayRoute(route));
		line.setOnContextMenuRequested(e -> routeMenu(route));
		line.setStrokeWidth(2);
		mapAnchor.getChildren().add(line);
		buildStart.setText("(Empty)");
		buildEnd.setText("(Empty)");
		activeRoute.setStart(null);
		activeRoute.setEnd(null);
	}
	
	private void routeMenu(Route route)
	{
		if (activeMenu.isShowing()) {
			activeMenu.hide();
		}
		activeMenu = new ContextMenu();

		MenuItem item1 = new MenuItem("Remove Route");
		item1.setOnAction(e -> route.deleteRoute());
		activeMenu.getItems().clear();
		activeMenu.getItems().addAll(item1);
	}

	private void displayRoute(Route route) {
		routeStart.setText(route.getStart().getName().getText());
		routeEnd.setText(route.getEnd().getName().getText());
		routeDanger.setText(route.getDanger() + "");
		routeDanger.setOnAction(e-> route.setDanger(Integer.parseInt(routeDanger.getText())));
		routeDifficulty.setText(route.getDifficulty() + "");
		routeDifficulty.setOnAction(e-> route.setDifficulty(Integer.parseInt(routeDifficulty.getText())));
		routeLength.setText(route.getLength() + "");
	}

	private void remove(Waypoint waypoint) {
		mapAnchor.getChildren().remove(waypoint.getIView());
		mapAnchor.getChildren().remove(waypoint.getName());
	}

	private void setEnd(Waypoint waypoint) {
		journeyEnd.setText(waypoint.getName().getText());
		//journeyMenu(waypoint).show(waypoint.getIView(), Side.BOTTOM, 0, 0);
		SDEase.setText(activeJourney.shortestDistance(waypoint).get(0).getDifficulty()+"");
		SDDanger.setText(activeJourney.shortestDistance(waypoint).get(0).getDanger()+"");
		SDDistance.setText(activeJourney.shortestDistance(waypoint).get(0).getLength()+"");
		LDDistance.setText(activeJourney.leastDangerous(waypoint).get(0).getLength()+"");
		LDDanger.setText(activeJourney.leastDangerous(waypoint).get(0).getDanger()+"");
		LDEase.setText(activeJourney.leastDangerous(waypoint).get(0).getDifficulty()+"");
		MEDistance.setText(activeJourney.leastDifficult(waypoint).get(0).getLength()+"");
		MEDanger.setText(activeJourney.leastDifficult(waypoint).get(0).getDanger()+"");
		MEEase.setText(activeJourney.leastDifficult(waypoint).get(0).getDifficulty()+"");
	}

	private void setStart(Waypoint waypoint) {
		journeyStart.setText(waypoint.getName().getText());
		activeJourney = new JJourney(waypoint);
	}

}
