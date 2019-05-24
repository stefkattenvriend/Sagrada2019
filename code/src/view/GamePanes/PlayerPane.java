package view.GamePanes;
//joery

import controller.DiceHolderController;
import controller.GameController;
import controller.PatterncardController;
import helpers.DiceHolderType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.MyScene;


public class PlayerPane extends VBox{
	
	private FlowPane diceSection;
	private RoundTrackPane roundTrackPane;
	private DiceOfferPane diceOfferPane;
	private HBox personalAttributes;
	private PaystoneHolderPane paystoneHolder;
	private PointsPane points;
	private PersonalObjectiveCardPane pocp;
	private PlayerBoardPane patternCardPane;
	private DiceHolderController dhc;
	private PatterncardController dcc;
	private GameController gc;
	private MyScene myScene;
	
	public PlayerPane(DiceHolderController dhc, PatterncardController dcc, MyScene myScene, GameController gc) {
		this.dhc = dhc;
		this.dcc = dcc;
		this.gc = gc;
		this.myScene = myScene;
		setBackground(controller.Main.PLAYERPANE); // aanduiding voor pane
		setUp();
	}
	
	private void setUp() {
		setPaneSize();
		setDiceSection();
		setPersonalAttributes();
		setPlayerBoardPane();
		setTinyButtonSection();
	}

	private void setTinyButtonSection() {
		BorderPane section = new BorderPane();
		
		Button pass = new Button("Pass");
		pass.setMinSize(60, 30);
		pass.setMaxSize(60, 30);
		
		Button menu = new Button("Menu");
		menu.setMinSize(60, 30);
		menu.setMaxSize(60, 30);
		menu.setOnAction(e -> myScene.setMenuPane());
		
		pass.setAlignment(Pos.CENTER_LEFT);
		menu.setAlignment(Pos.CENTER_RIGHT);
		
		section.setLeft(pass);
		section.setRight(menu);
		getChildren().add(section);
	}

	private void setPlayerBoardPane() {
		int patID = gc.getGm().getPlayerModel(DiceHolderType.PLAYERWINDOW).getPatid();
		patternCardPane = new PlayerBoardPane(dhc, dcc, patID);
		getChildren().add(patternCardPane);
	}

	private void setPersonalAttributes() {
		personalAttributes = new HBox();
		paystoneHolder = new PaystoneHolderPane();
		points = new PointsPane();
		pocp = new PersonalObjectiveCardPane(Color.PURPLE);
		personalAttributes.getChildren().addAll(paystoneHolder, points, pocp);
		personalAttributes.setMinHeight(75);
		getChildren().add(personalAttributes);
	}

	private void setDiceSection() {
		diceSection = new FlowPane();
		roundTrackPane = new RoundTrackPane(dhc);
		diceOfferPane = new DiceOfferPane(dhc);
		diceSection.getChildren().addAll(roundTrackPane, diceOfferPane);
		getChildren().add(diceSection);
	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}

}
