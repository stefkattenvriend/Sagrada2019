package view.GamePanes;
import controller.CardsController;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


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
	
	public CardDisplayPane(CardsController cc) {
		this.cc = cc;
		//setUp();
		
		setBackground(controller.Main.CARDDISPLAYPANE); // aanduiding voor pane
	}

	private void setUp() {
		
		setPaneSize();
		vbox1 = new VBox();
		vbox2 = new VBox();
		
		toolCard1 = new CardPane(cc.getTc1(), true, cc, cc.getTc1Nr());	//toolcards
		toolCard2 = new CardPane(cc.getTc2(), true, cc, cc.getTc2Nr());
		toolCard3 = new CardPane(cc.getTc3(), true, cc, cc.getTc3Nr());
		
		targetCard1 = new CardPane(cc.getTgc1(), false, cc, cc.getTgc1Nr()); //targetcards
		targetCard2 = new CardPane(cc.getTgc2(), false, cc, cc.getTgc2Nr());
		targetCard3 = new CardPane(cc.getTgc3(), false, cc, cc.getTgc3Nr());
		
		
		vbox1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		vbox2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
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
