package application;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
	private MenuButton findMode;

	ContextMenu activeMenu = new ContextMenu();
	JJourney activeJourney;
	Waypoint beginning, destination;
	Road activeRoad = new Road();
	Waypoint startRoad, endRoad;
	int searchSetting;
	
	Waypoint[] goTo = null, avoid = null;
	ArrayList<Waypoint> goToHold = new ArrayList<Waypoint>();
	ArrayList<Waypoint> avoidHold = new ArrayList<Waypoint>();

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

		// choicebox stuf//
		findMode.getItems().clear();
		MenuItem item1 = new MenuItem("Length");
		item1.setOnAction(e -> setChoice(0));
		MenuItem item2 = new MenuItem("Danger");
		item2.setOnAction(e -> setChoice(1));
		MenuItem item3 = new MenuItem("Difficulty");
		item3.setOnAction(e -> setChoice(2));
		findMode.getItems().addAll(item1, item2, item3);
	}

	private void setChoice(int i) {
		this.searchSetting = i;
		//runChoice();
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
		
		iView.setOnMouseClicked(new EventHandler<MouseEvent>(){
		    @Override
		    public void handle(MouseEvent t) {
		        if(t.getButton() == MouseButton.PRIMARY) nodeMenu(waypoint).show(iView, Side.BOTTOM, 0, 0);
		        if(t.getButton() == MouseButton.SECONDARY) editNodeMenu(waypoint).show(iView, Side.BOTTOM, 0, 0);
		    }
		});
		
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
	public ContextMenu editNodeMenu(Waypoint waypoint) {
		if (activeMenu.isShowing()) {
			activeMenu.hide();
		}
		activeMenu = new ContextMenu();

		MenuItem item3 = new MenuItem("Set as Road Start");
		item3.setOnAction(e -> beginConnection(waypoint));
		MenuItem item4 = new MenuItem("Set Road End");
		item4.setOnAction(e -> endConnection(waypoint));
		MenuItem item5 = new MenuItem("Edit Name");
		item5.setOnAction(e -> setName(waypoint));
		MenuItem item6 = new MenuItem("Remove Waypoint");
		item6.setOnAction(e -> remove(waypoint));
		activeMenu.getItems().addAll(item3, item4, item5, item6);
		return activeMenu;
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

		activeMenu.getItems().addAll(item1, item2);
		return activeMenu;
	}

	public ContextMenu journeyMenu(Waypoint waypoint) {
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
		if (activeRoad.getStart() != null && activeRoad.getEnd() != null && activeRoad.getStart() != activeRoad.getEnd()) {
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
		w = startRoad.getMapX() - endRoad.getMapX();
		h = startRoad.getMapY() - endRoad.getMapY();

		double startX = startRoad.getMapX();
		double startY = startRoad.getMapY();
		double endX = endRoad.getMapX();
		double endY = endRoad.getMapY();

		startX = startX - (20 * (startX - endX)) / Math.sqrt(w * w + h * h);
		startY = startY - (20 * (startY - endY)) / Math.sqrt(w * w + h * h);
		endX = endX - (20 * (endX - startX)) / Math.sqrt(w * w + h * h);
		endY = endY - (20 * (endY - startY)) / Math.sqrt(w * w + h * h);

		Line line = new Line(startX, startY, endX, endY);
		line.setStrokeLineCap(StrokeLineCap.ROUND);
		line.setStrokeType(StrokeType.OUTSIDE);
		line.setStroke(Color.BEIGE.darker().darker());

		line.setStrokeWidth(3);
		line.getStrokeDashArray().addAll(25d, 15d);

		Road road = new Road(startRoad, endRoad, (Math.sqrt(w * w + h * h)), line);

		line.setOnMousePressed(e -> displayRoad(road));
		line.setOnContextMenuRequested(e -> roadMenu(road));

		print("RoadStart: " + road.getStart().getName().getText());

		mapAnchor.getChildren().add(line);
		buildStart.setText("(Empty)");
		buildEnd.setText("(Empty)");
		activeRoad.setStart(null);
		activeRoad.setEnd(null);
	}

	void print(String string) {
		System.out.println(string);
	}

	private void roadMenu(Road road) {
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
		roadDanger.setOnAction(e -> road.setDanger(Integer.parseInt(roadDanger.getText())));
		roadDifficulty.setText(road.getDifficulty() + "");
		roadDifficulty.setOnAction(e -> road.setDifficulty(Integer.parseInt(roadDifficulty.getText())));
		roadLength.setText(road.getLength() + "");
	}

	private void remove(Waypoint waypoint) {
		mapAnchor.getChildren().remove(waypoint.getIView());
		mapAnchor.getChildren().remove(waypoint.getName());
	}

	private void setEnd(Waypoint waypoint) {
		destination = waypoint;
		System.out.println("" + destination.getMapX() + "" + destination.getMapY());
		journeyEnd.setText(waypoint.getName().getText());
		setJourneyInfo(waypoint);

		//runChoice();
	}

	/*private void runChoice() {
		for (Waypoint w: Waypoint.allWaypoints)
		{
			for (Road r : w.getConnectedRoads()) {
				r.getLine().setStroke(Color.BEIGE.darker().darker());
			}
		}
		if (searchSetting == 0) {
			for (Waypoint w : activeJourney.shortestDistance(destination)) {
				for (Road r : w.getConnectedRoads()) {
					if (r.getEnd() == w.getLengthPrevious() || r.getStart() == w.getLengthPrevious()) {
						r.getLine().setStroke(Color.RED);
					}
				}
			}
		} else if (searchSetting == 1) {
			for (Waypoint w : activeJourney.leastDangerous(destination)) {
				for (Road r : w.getConnectedRoads()) {
					if (r.getEnd() == w.getDangerPrevious() || r.getStart() == w.getDangerPrevious()) {
						r.getLine().setStroke(Color.RED);
					}
				}
			}
		} else if (searchSetting == 2) {
			for (Waypoint w : activeJourney.leastDifficult(destination)) {
				for (Road r : w.getConnectedRoads()) {
					if (r.getEnd() == w.getDifficultyPrevious() || r.getStart() == w.getDifficultyPrevious()) {
						r.getLine().setStroke(Color.RED);
					}
				}
			}
		}
	}*/

	private void setJourneyInfo(Waypoint waypoint) {
		SDEase.setText(waypoint.getDifficulty(0) + "");
		SDDanger.setText(waypoint.getDanger(0) + "");
		SDDistance.setText(waypoint.getLength(0) + "");
		LDDistance.setText(waypoint.getLength(1) + "");
		LDDanger.setText(waypoint.getDanger(1) + "");
		LDEase.setText(waypoint.getDifficulty(1) + "");
		MEDistance.setText(waypoint.getLength(2) + "");
		MEDanger.setText(waypoint.getDanger(2) + "");
		MEEase.setText(waypoint.getDifficulty(2) + "");
	}

	private void setStart(Waypoint waypoint) {
		beginning = waypoint;
		System.out.println("" + beginning.getMapX() + "" + beginning.getMapY());
		journeyStart.setText(waypoint.getName().getText());
		//activeJourney = new JJourney(waypoint);
	}

	@FXML
	private void generate(ActionEvent event) {
		
		goTo = goToHold.toArray(new Waypoint[goToHold.size()]);
		avoid = avoidHold.toArray(new Waypoint[goToHold.size()]);
		
		System.out.println("ding");
		int color = 0;
		if (beginning != null && destination != null) {
			for (Journey current : journeyRouter.router(beginning, destination,goTo,avoid, searchSetting)) {
				for (Waypoint currentPoint : current.getWaypoints()) {
					for (Road r : currentPoint.getConnectedRoads()) {
						if (current.waypoints.contains(r.getOpposite(currentPoint))) {//finds with road to color
							if(color == 0) {
								r.getLine().setStroke(Color.RED);
							}
							else if(color == 1) {
								r.getLine().setStroke(Color.GREEN);
							}
							else if(color == 2){
								r.getLine().setStroke(Color.BLUE);
							}
						}
					}
					System.out.println(""+color+" : "+ currentPoint.getMapX()+" "+currentPoint.getMapY());
				}
				color++;//change the color as it gose
			}
		}
	}
	
	
	
	@FXML
	void save() {
			SaveLoad.save();
	}



	@FXML
	void clear() {
		Waypoint.allWaypoints = new ArrayList<Waypoint>();
		Road.allRouts = new ArrayList<Road>();
	}


	@FXML
	void load() {
		SaveLoad.load();
		loadRoadsOnMap();
		loadWaypointsMap();
	}

	private void loadWaypointsMap() {
		for (Waypoint i: Waypoint.allWaypoints) {
			Waypoint waypoint = i;
			mapAnchor.getChildren().add(waypoint.getIView());
			mapAnchor.getChildren().add(waypoint.getName());
			waypoint.getName().requestFocus();
		}
		
	}

	private void loadRoadsOnMap() {
		for(Road i: Road.allRouts) {
			Line line = new Line(i.start.getMapX(), i.start.getMapY(), i.end.getMapX(), i.end.getMapY());
			line.setStrokeLineCap(StrokeLineCap.ROUND);
			line.setStrokeType(StrokeType.OUTSIDE);
			line.setStroke(Color.BEIGE.darker().darker());

			line.setStrokeWidth(3);
			line.getStrokeDashArray().addAll(25d, 15d);
			
			line.setOnMousePressed(e -> displayRoad(i));
			line.setOnContextMenuRequested(e -> roadMenu(i));
			
			print("RoadStart: " + i.getStart().getName().getText());

			mapAnchor.getChildren().add(line);
			buildStart.setText("(Empty)");
			buildEnd.setText("(Empty)");
			activeRoad.setStart(null);
			activeRoad.setEnd(null);
		}
		
	}

}
