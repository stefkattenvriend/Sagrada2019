package view.GamePanes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerPayStone extends Pane {
	
	PlayerPayStone(CardPane cp) {
		ImageView bg = new ImageView(new Image("/PlayerPayStones/0.png"));
		setPrefSize(cp.getWidth() / 4,cp.getWidth() / 4);
		this.getChildren().addAll(bg);
		bg.fitHeightProperty().bind(this.heightProperty());
		bg.fitWidthProperty().bind(this.widthProperty());
	}
}
