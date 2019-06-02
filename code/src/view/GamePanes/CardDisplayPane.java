package view.GamePanes;
import controller.CardsController;
import controller.GameController;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class CardDisplayPane extends BorderPane{

	private VBox vbox1;
	private VBox vbox2;
	private CardPane toolCard1;
	private CardPane toolCard2;
	private CardPane toolCard3;
	private CardPane targetCard1;
	private CardPane targetCard2;
	private CardPane targetCard3;
	private CardsController cc;
	private GameController gc;
	
	public CardDisplayPane(CardsController cc, GameController gc) {
		this.cc = cc;
		this.gc = gc;
		setUp();
		
		setBackground(controller.Main.CARDDISPLAYPANE); // aanduiding voor pane
	}

	private void setUp() {
		
		setPaneSize();
		vbox1 = new VBox();
		vbox2 = new VBox();
		
		toolCard1 = new CardPane(cc.getTc1(), true, cc, cc.getTc1Nr(), gc);	//toolcards
		toolCard2 = new CardPane(cc.getTc2(), true, cc, cc.getTc2Nr(), gc);
		toolCard3 = new CardPane(cc.getTc3(), true, cc, cc.getTc3Nr(), gc);
		
		targetCard1 = new CardPane(cc.getTgc1(), false, cc, cc.getTgc1Nr(), gc); //targetcards
		targetCard2 = new CardPane(cc.getTgc2(), false, cc, cc.getTgc2Nr(), gc);
		targetCard3 = new CardPane(cc.getTgc3(), false, cc, cc.getTgc3Nr(), gc);
		
		

		vbox1.setPrefSize((GamePane.windowMaxWidth / 6), GamePane.windowMaxHeight);
		vbox2.setPrefSize((GamePane.windowMaxWidth / 6), GamePane.windowMaxHeight);
		vbox1.getChildren().addAll(toolCard1, toolCard2, toolCard3);
		vbox2.getChildren().addAll(targetCard1, targetCard2, targetCard3);
		setLeft(vbox1);
		setRight(vbox2);
	}
	
	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}
}
