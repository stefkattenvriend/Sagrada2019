package view.GamePanes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerPayStone extends Pane {
	
	PlayerPayStone(CardPane cp) {
		ImageView bg = new ImageView(new Image("/PlayerPayStones/0.png"));
		setPrefSize(GamePane.windowMaxWidth / 25, GamePane.windowMaxWidth / 25);
		this.getChildren().addAll(bg);
		bg.fitHeightProperty().bind(this.prefWidthProperty());
		bg.fitWidthProperty().bind(this.prefHeightProperty());
	}
}
