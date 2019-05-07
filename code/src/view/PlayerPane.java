package view;
//joery

import controller.DiceHolderController;
import controller.PatterncardController;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class PlayerPane extends VBox{
	
	private FlowPane diceSection;
	private RoundTrackPane roundTrackPane;
	private DiceOfferPane diceOfferPane;
	private HBox personalAttributes;
	private PaystoneHolder paystoneHolder;
	private Points points;
	private TargetColor targetColor;
	private PatternCardPane patternCardPane;
	private DiceHolderController dhc;
	private PatterncardController dcc;
	
	public PlayerPane(DiceHolderController dhc, PatterncardController dcc) {
		this.dhc = dhc;
		this.dcc = dcc;
		setBackground(controller.Main.PLAYERPANE); // aanduiding voor pane
		setUp();
	}
	
	private void setUp() {
		setPaneSize();
		setDiceSection();
		setPersonalAttributes();
		setPatternCard();
	}

	private void setPatternCard() {
		patternCardPane = new PatternCardPane(dhc, dcc);
		getChildren().add(patternCardPane);
	}

	private void setPersonalAttributes() {
		personalAttributes = new HBox();
		paystoneHolder = new PaystoneHolder();
		points = new Points();
		targetColor = new TargetColor();
		personalAttributes.getChildren().addAll(paystoneHolder, points, targetColor);
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
