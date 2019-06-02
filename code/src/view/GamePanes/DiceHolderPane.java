package view.GamePanes;

import controller.DiceHolderController;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DiceHolderPane extends BorderPane{
	
	private DiceHolderController dhc;
	private Button higher;
	private Button lower;
	private double size;

	public DiceHolderPane(double size, DiceHolderController dhc, int id, boolean diceUpdate) {
		super();
		this.size = size;
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
	public void addPlusAndMinus(int dienr) {
		if(dienr != 6) {
			higher = new Button("+");
			higher.setPrefSize(size / 10, size);
			higher.setFont(Font.font("Verdana", 10));
			this.setRight(higher);
			higher.setOnMouseClicked(e -> higherClicked());
		}
		if(dienr != 1) {
			lower = new Button("-");
			lower.setPrefSize(size / 10, size);
			lower.setFont(Font.font("Verdana", 10));
			this.setLeft(lower);
			lower.setOnMouseClicked(e -> lowerClicked());
		}
	}

	
	private void higherClicked() {
		this.getChildren().removeAll(higher, lower);
		dhc.higherClicked();
	}
	
	private void lowerClicked() {
		this.getChildren().removeAll(higher, lower);
		dhc.lowerClicked();
	}
	
}
