package view.GamePanes;
//joery

import controller.DiceHolderController;
import controller.GameController;
import controller.PatterncardController;
import controller.PayStoneController;
import controller.PlayerPaneController;
import controller.PointsController;
import controller.TurnController;
import helpers.DiceHolderType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.MyScene;
import view.MenuPanes.MenuPane;


public class PlayerPane extends VBox{
	
	private FlowPane diceSection;
	private RoundTrackPane roundTrackPane;
	private DiceOfferPane diceOfferPane;
	private PersonalAttributes personalAttributes;
	private PointsPane points;
	private PersonalObjectiveCardPane pocp;
	private PlayerBoardPane playerBoardPane;
	private DiceHolderController dhc;
	private PatterncardController dcc;
	private GameController gc;
	private MyScene myScene;
	private PointsController pc;
	private TurnController tc;
	private Button pass;
	private PayStoneController psc;
	private PaystoneHolderPane psh;
	private Label turn;
	
	public PersonalAttributes getPersonalAttributes() {
		return personalAttributes;
	}
	
	public PlayerPane(DiceHolderController dhc, PatterncardController dcc, MyScene myScene, GameController gc, PointsController pc, TurnController tc, PayStoneController psc) {
		this.dhc = dhc;
		this.psc = psc;
		this.dcc = dcc;
		this.gc = gc;
		this.myScene = myScene;
		this.pc = pc;
		this.tc = tc;
		personalAttributes = new PersonalAttributes(this);
		setBackground(controller.Main.PLAYERPANE); // aanduiding voor pane
		setUp(gc.getPlayerPaneController());
		tc.givePane(this);
		tc.TurnAdmissionGiving();
		tc.startThread();
	}
	
	private void setUp(PlayerPaneController ppc) {
		setPaneSize();
		setDiceSection();
		setPersonalAttributes();
		setPlayerBoardPane();
		setTinyButtonSection(ppc);
	}

	private void setTinyButtonSection(PlayerPaneController ppc) {
		BorderPane section = new BorderPane();
		
		pass = new Button("Beurt beëindigen");
		pass.setMinSize(120, 30);
		pass.setMaxSize(120, 30);
		pass.setOnAction(e -> ppc.pass(tc, this, gc));
		
		Button menu = new Button("Menu");
		menu.setMinSize(60, 30);
		menu.setMaxSize(60, 30);
		menu.setOnAction(e -> ppc.menuAction(myScene, tc, gc));
		
		pass.setAlignment(Pos.CENTER_LEFT);
		menu.setAlignment(Pos.CENTER_RIGHT);
		pass.setVisible(false);
		
		section.setLeft(pass);
		section.setRight(menu);
		getChildren().add(section);
	}

	private void menuAction() {
		myScene.goToMenuPane();
		tc.stopThread();
		gc.setGameRunning(false);
//		gc.setGenerateOffer(true);
	}

	public void yourTurn() {
		pass.setVisible(true);
	}
	
	private void pass() {
		tc.updatePass(); 
		tc.updateSeqnrAndTurn();
		pass.setVisible(false);
	}
	
	public void setPassVisible() {
		pass.setVisible(false);
	}

	private void setPlayerBoardPane() {
		int patID = gc.getGm().getPlayerModel(DiceHolderType.PLAYERWINDOW).getPatid();
		playerBoardPane = new PlayerBoardPane(dhc, dcc, patID);
		getChildren().add(playerBoardPane);
	}

	private void setPersonalAttributes() {
		gc.setPersonalAttributes(personalAttributes);
		psh = new PaystoneHolderPane(psc, psc.getPlayerStones());
		points = new PointsPane(pc);
		turn = new Label("Aan de beurt: ");
		pocp = new PersonalObjectiveCardPane();
		personalAttributes.getChildren().addAll(getpaystoneHolder(), turn, points, pocp);
		personalAttributes.setMinHeight(75);
		personalAttributes.setMinWidth(MenuPane.paneWidth / 3);
		getChildren().add(personalAttributes);
	}
	
	public PaystoneHolderPane getpaystoneHolder() {
		psh = new PaystoneHolderPane(psc, psc.getPlayerStones());
		return psh;
	}
	
	public PointsPane getPointsPane() {
		return points;
	}
	
	public PersonalObjectiveCardPane getpocp() {
		return pocp;
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
	
	public void updatePC() {
		playerBoardPane.updatePC();
		
	}

	public void setMyColor(Color color) {
		pocp.setMyColor(color);		
	}
	
	public void updatePCid(int i) {
		playerBoardPane.updatePCid(i);
		
	}

	public TurnController getTc() {
		return tc;
	}
	
	public Label getTurn() {
		return turn;
	}

	public void setLabel(String text) {
//		turn.setText(text);
	}

	
	public void setDisable()
	{
		
	}

	public void redrawDice() {
		playerBoardPane.redrawDice();
		roundTrackPane.redrawDice();
		diceOfferPane.redrawDice();
		
	}

	public void redrawOffer() {
		diceOfferPane.redrawDice();
		
	}
}
