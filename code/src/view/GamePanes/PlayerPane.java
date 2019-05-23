package view.GamePanes;
//joery

import controller.DiceHolderController;
import controller.PatterncardController;
import controller.PointsController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
	private PatternCardPane patternCardPane;
	private DiceHolderController dhc;
	private PatterncardController dcc;
	private MyScene myScene;
	private PointsController pc;
	
	public PlayerPane(DiceHolderController dhc, PatterncardController dcc, MyScene myScene, PointsController pc) {
		this.dhc = dhc;
		this.dcc = dcc;
		this.myScene = myScene;
		this.pc = pc;
		setBackground(controller.Main.PLAYERPANE); // aanduiding voor pane
		setUp();
	}
	
	private void setUp() {
		setPaneSize();
		setDiceSection();
		setPersonalAttributes();
		setPatternCard();
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

	private void setPatternCard() {
		patternCardPane = new PatternCardPane(dhc, dcc);
		getChildren().add(patternCardPane);
	}

	private void setPersonalAttributes() {
		personalAttributes = new HBox();
		paystoneHolder = new PaystoneHolderPane();
		points = new PointsPane(pc);
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
	
	public PersonalObjectiveCardPane getPOCP() {
		return pocp;
	}

}
