package view.GamePanes;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CardPane extends Pane{

	
	public CardPane(ImageView background) {
		setPrefSize((GamePane.windowMaxWidth / 6), (GamePane.windowMaxHeight - 40) / 3);
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		this.getChildren().addAll(background);
		background.fitHeightProperty().bind(this.heightProperty());
	}
}
