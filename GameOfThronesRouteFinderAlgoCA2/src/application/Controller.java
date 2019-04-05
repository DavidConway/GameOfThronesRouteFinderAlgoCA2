package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.stage.Screen;

public class Controller {
	
	
	@FXML
	AnchorPane mapAnchor;
	@FXML
	private Label journeyStart, journeyEnd;
	@FXML
	private Label buildStart, buildEnd;
	@FXML
	private Label roadStart, roadEnd, roadLength;
	@FXML
	private TextField roadDifficulty, roadDanger;
	@FXML
	private Label SDEase, SDDanger, SDDistance;
	@FXML
	private Label LDDistance, LDDanger, LDEase;
	@FXML
	private Label MEDistance, MEDanger, MEEase;
	
	//
	@FXML
	private ChoiceBox<String> findMode;


	ContextMenu activeMenu = new ContextMenu();
	JJourney activeJourney;
	Waypoint beginning, destination;
	Road activeRoad = new Road();
	Waypoint startRoad,endRoad;
	
	ArrayList<Line> routhLines = new ArrayList<Line>();

	Image map = new Image("/images/map.png");
	ImageView mapPane = new ImageView(map);

	@FXML
	public void initialize() {
		mapPane.setPreserveRatio(true);
		mapAnchor.getChildren().add(mapPane);
		mapPane.setFitWidth(Screen.getPrimary().getVisualBounds().getWidth());
		mapAnchor.setMinWidth(mapPane.getFitWidth());
		mapPane.setOnMouseClicked(e -> baseMenu(e.getX(), e.getY()).show(mapAnchor, null, e.getX(), e.getY()));
		
		//choicebox stuf//
		findMode.getItems().addAll("lenth","danger","dificulty");
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
		MenuItem item3 = new MenuItem("Set Road Beginning");
		item3.setOnAction(e -> beginConnection(waypoint));
		MenuItem item4 = new MenuItem("Set Road End");
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
		endRoad = waypoint;
		activeRoad.setEnd(waypoint);
		buildEnd.setText(waypoint.getName().getText());
		if (checkConnection()) {
			buildRoad();
		}
	}

	private boolean checkConnection() {
		if (activeRoad.getStart() != null && activeRoad.getEnd() != null) {
			return true;
		} else
			return false;
	}

	private void beginConnection(Waypoint waypoint) {
		startRoad = waypoint;
		activeRoad.setStart(waypoint);
		buildStart.setText(waypoint.getName().getText());
		if (checkConnection()) {
			buildRoad();
		}
	}

	private void buildRoad() {
		double w, h; // width, height
		w = Math.abs(startRoad.getMapX() - endRoad.getMapX());
		h = Math.abs(startRoad.getMapY() - endRoad.getMapY());

		double startX = startRoad.getMapX();
		double startY = startRoad.getMapY();
		double endX = endRoad.getMapX();
		double endY = endRoad.getMapY();
		
		startX = startX - (20*(startX-endX))/Math.sqrt(w * w + h * h);
		startY = startY - (20*(startY-endY))/Math.sqrt(w * w + h * h);
		endX = endX - (20*(endX-startX))/Math.sqrt(w * w + h * h);
		endY = endY - (20*(endY-startY))/Math.sqrt(w * w + h * h);
		
		Line line = new Line(startX, startY, endX, endY);
		line.setStrokeLineCap(StrokeLineCap.ROUND);
		line.setStrokeType(StrokeType.OUTSIDE);
		line.setStroke(Color.BEIGE.darker().darker());
		
		line.setStrokeWidth(3);
		line.getStrokeDashArray().addAll(25d, 15d);
		
		Road road = new Road(startRoad, endRoad, (Math.sqrt(w * w + h * h)), line);
		
		line.setOnMousePressed(e -> displayRoad(road));
		line.setOnContextMenuRequested(e -> roadMenu(road));
		
		print("RoadStart: " +road.getStart().getName().getText());
		
		mapAnchor.getChildren().add(line);
		buildStart.setText("(Empty)");
		buildEnd.setText("(Empty)");
		activeRoad.setStart(null);
		activeRoad.setEnd(null);
	}
	void print(String string)
	{
		System.out.println(string);
	}
	
	private void roadMenu(Road road)
	{
		if (activeMenu.isShowing()) {
			activeMenu.hide();
		}
		activeMenu = new ContextMenu();

		MenuItem item1 = new MenuItem("Remove Road");
		item1.setOnAction(e -> road.deleteRoad());
		activeMenu.getItems().clear();
		activeMenu.getItems().addAll(item1);
	}

	private void displayRoad(Road road) {
		roadStart.setText(road.getStart().getName().getText());
		roadEnd.setText(road.getEnd().getName().getText());
		roadDanger.setText(road.getDanger() + "");
		roadDanger.setOnAction(e-> road.setDanger(Integer.parseInt(roadDanger.getText())));
		roadDifficulty.setText(road.getDifficulty() + "");
		roadDifficulty.setOnAction(e-> road.setDifficulty(Integer.parseInt(roadDifficulty.getText())));
		roadLength.setText(road.getLength() + "");
	}

	private void remove(Waypoint waypoint) {
		mapAnchor.getChildren().remove(waypoint.getIView());
		mapAnchor.getChildren().remove(waypoint.getName());
	}

	private void setEnd(Waypoint waypoint) {
		destination = waypoint;
		System.out.println(""+destination.getMapX()+""+destination.getMapY());
		journeyEnd.setText(waypoint.getName().getText());
		setJourneyInfo(waypoint);
	}

	private void setJourneyInfo(Waypoint waypoint) {
		SDEase.setText(waypoint.getDifficulty(0)+"");
		SDDanger.setText(waypoint.getDanger(0)+"");
		SDDistance.setText(waypoint.getLength(0)+"");
		LDDistance.setText(waypoint.getLength(1)+"");
		LDDanger.setText(waypoint.getDanger(1)+"");
		LDEase.setText(waypoint.getDifficulty(1)+"");
		MEDistance.setText(waypoint.getLength(2)+"");
		MEDanger.setText(waypoint.getDanger(2)+"");
		MEEase.setText(waypoint.getDifficulty(2)+"");
	}

	private void setStart(Waypoint waypoint) {
		beginning = waypoint;
		System.out.println(""+beginning.getMapX()+""+beginning.getMapY());
		journeyStart.setText(waypoint.getName().getText());
		activeJourney = new JJourney(waypoint);
	}
	
	@FXML
	private void generate(ActionEvent event) {
		System.out.println("ding");
		int mode = 0;
		if(findMode.getValue().toString() == "lenth" ) {
			mode = 0;
		}
		else if(findMode.getValue().toString() == "danger") {
			mode = 1;
		}
		else if(findMode.getValue().toString() == "dificulty") {
			mode = 2;
		}
		
		if(beginning != null && destination !=null) {
			for(Journey current: journeyRouter.router(beginning, destination, mode)) {
				Waypoint start = null;
				Waypoint end = null;
				for(Waypoint currentPoint: current.getWaypoints()) {
					if(end == null) {//STOPS THINGS FROM BRAKING
						end = currentPoint;
						start = currentPoint;
					}
					else{
						end = start;
						start = currentPoint;
					}
					
					//TO DO USE START AND END POINTS TO CREATE COLORED LINES//
					
					//
					
				}
			}
		}
	}
	
	

}
