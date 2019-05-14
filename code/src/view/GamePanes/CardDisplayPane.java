package view.GamePanes;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class CardDisplayPane extends BorderPane{
	
	public CardDisplayPane(VBox vbox1, VBox vbox2, CardPane toolCard1, CardPane toolCard2, CardPane toolCard3, CardPane targetCard1, CardPane targetCard2, CardPane targetCard3) {
		setBackground(controller.Main.CARDDISPLAYPANE); // aanduiding voor pane
		setPaneSize();	
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
