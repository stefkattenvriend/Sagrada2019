package view.GamePanes;

import controller.DiceHolderController;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class DiceHolderPane extends BorderPane{
	
	private DiceHolderController dhc;

	public DiceHolderPane(double size, DiceHolderController dhc, int id, boolean diceUpdate) {
		super();
		this.dhc = dhc;
		this.setPrefSize(size, size);
		this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(3) )));
		this.setOnMouseEntered(e -> dhc.DiceHolderHover(this));
		this.setOnMouseExited(e -> dhc.DiceHolderLeaveHover(this));
		this.setOnMouseClicked(e -> dhc.DiceHolderClick(this));
		
		if (diceUpdate) {
			placeDice(id);
		}
		
	}

	public void updateDiceHolderPane(){
		getChildren().clear();
	}
	
	public void placeDice(int id) {
			this.setCenter(dhc.getDice(id));
		
	}
}
