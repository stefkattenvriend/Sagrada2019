package view;

import controller.GameController;
import controller.LayerController;
import controller.LoginController;
import controller.PatterncardController;
import databeest.DbPatternCardInfoCollector;
import helpers.PatterncardType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.GamePanes.GamePane;


public class LayerPane extends BorderPane{//deze moet nog voor de gamepane worden aangemaakt(dus in de scene). deze pane geeft de mogelijkheid om "pop-ups" te cre�ren(voor bijvoorbeeld een pauze menu, patroonkaart keuze en een scorebord op einde van game ~Rens
	
	//constants
	public final static double windowMaxWidth = 1280;
	public final static double windowMaxHeight = 800;
	
	public LayerController lyc;
	private PatterncardController pcc;
	private FlowPane buttonPane;
	private Button button;
	private Button buttonMenu;
	private FlowPane chooserPane = new FlowPane();
	private int[] randomPat;
	private int playerid; 
	private LoginController logc;
	private GameController gameController;
	private MyScene myScene;
	
	public LayerPane(LayerController controller, PatterncardController pcc, LoginController loginController, MyScene myscene, GameController gameController) {
//		randomPat = controller.getRandomPat();
		this.myScene = myscene;
		this.gameController = gameController;
		logc = loginController;
		this.lyc = controller;
		this.pcc = pcc;
		setUp();
		playerid = pcc.getPlayerID(pcc.getGameid(), logc.getCurrentAccount());
		System.out.println("dit is de playerid: " + playerid);
	}
	
	public void setUp() {
		setLayerSize();
		setDesign();
		setButton();
		setChooserPane();
		setLeft(buttonPane);
		setRight(chooserPane);
		viewOffer();
		
		
	}
	
	private void setDesign() {
		setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7), null, null)));
	}
	
	private void setButton() {
		buttonPane = new FlowPane();
		button = new Button("Standaard");
		buttonMenu = new Button("Menu");
		buttonMenu.setPrefSize(80, 40);
		button.setPrefSize(80, 40);
		buttonMenu.setOnAction(e -> backToMenu());
		button.setOnAction(e -> viewOffer()); //bij druk op de knop worden de randomkaarten pas zichtbaar.
		buttonPane.setAlignment(Pos.CENTER_LEFT);
		buttonPane.setPrefSize(120, windowMaxHeight);
		buttonPane.setBorder(new Border(new BorderStroke(Color.BLACK, null, null, null)));
		buttonPane.getChildren().addAll(buttonMenu/*, button*/);
	}
	
	private void backToMenu() {
		myScene.goToMenuPane();
		lyc.setGameRunning(false);
	}

	private void setChooserPane() {
		chooserPane.setPrefSize(windowMaxWidth - 120, windowMaxHeight);
		
	} 
	
	private void viewOffer() {
//		lyc.generateRdmPatternCards();
//		randomPat = lyc.getRandomPat();
//		for(int i = 0; i < randomPat.length; i++) {
//			lyc.insertChoice(i, playerid);					// zet keuzes in database
//			System.out.println("patterncardID = : " + randomPat[i]);	//syso welke patterncards kunnen gekozen worden
//			
//		}
		randomPat = lyc.getRandomPat();
		chooserPane.getChildren().clear();
		chooserPane.getChildren().addAll(createPatternCard(String.valueOf(randomPat[0])), createPatternCard(String.valueOf(randomPat[1])), createPatternCard(String.valueOf(randomPat[2])), createPatternCard(String.valueOf(randomPat[3])));
		chooserPane.setAlignment(Pos.CENTER_RIGHT);
		buttonPane.getChildren().clear();// haalt de button weg
		buttonPane.getChildren().add(buttonMenu);
	}
	
	private Pane createPatternCard(String rdInt) {
		TilePane patternCard = new TilePane();
		patternCard.setPrefWidth(500);
		patternCard.setPrefHeight(400);
		patternCard.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		patternCard.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 6; j++) {
				double size = ((GamePane.windowMaxWidth / 3) / 5) - 1; 
			patternCard.getChildren().add(pcc.PatterncardCreate(j, i, Integer.parseInt(rdInt), (int)size, PatterncardType.CHOICE));
			}	
		}
		
		//int label
//		Label label = new Label(rdInt);
//		label.setFont(new Font("Arial", 80));
//		patternCard.getChildren().add(label);
		StackPane overlapPane = new StackPane();
		patternCard.setAlignment(Pos.CENTER);
		overlapPane.setOnMouseClicked(e -> { 
			pcc.givePatternCardToPlayer(Integer.parseInt(rdInt), playerid); //Wanneer je klikt op de tilepane krijg je die id in de database bij player
			System.out.println("The patterncardId: " + Integer.parseInt(rdInt));
			gameController.getPayStoneController().giveStones(Integer.parseInt(rdInt));
			//get paystones
			myScene.setGamePane(); //setgamePane
			lyc.updatePCid(Integer.parseInt(rdInt));
			lyc.setGameRunning(true);
			
		});
		
		
		VBox stipjes = new VBox();
		int difficulty = pcc.getDifficulty(Integer.parseInt(rdInt));
		Label label = new Label(" Moeilijkheidsgraad: " + difficulty);
		label.setFont(new Font("TimesRoman", 20));
		
		stipjes.getChildren().add(label);
		
		overlapPane.getChildren().addAll(patternCard, stipjes);
		return overlapPane;
	}
	
	private void setLayerSize() {
		setMinSize(windowMaxWidth, windowMaxHeight);
		setMaxSize(windowMaxWidth, windowMaxHeight);
	}
	
}
