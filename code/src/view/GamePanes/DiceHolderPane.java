package view.GamePanes;

import controller.DiceHolderController;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DiceHolderPane extends BorderPane {

	private DiceHolderController dhc;
	private Button numHigher;
	private Button numLower;
	private double size;
	private Button colHigher;
	private Button colLower;

	public DiceHolderPane(double size, DiceHolderController dhc, int id, boolean diceUpdate) {
		super();
		this.size = size;
		this.dhc = dhc;
		this.setPrefSize(size, size);
		this.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(3))));
		this.setOnMouseEntered(e -> dhc.DiceHolderHover(this));
		this.setOnMouseExited(e -> dhc.DiceHolderLeaveHover(this));
		this.setOnMouseClicked(e -> dhc.DiceHolderClick(this));

		if (diceUpdate) {
			placeDice(id);
		}

	}

	public void updateDiceHolderPane() {
		getChildren().clear();
	}

	public void placeDice(int id) {
		this.setCenter(dhc.getDice(id));

	}

	public void addPlusAndMinusAndColor() {
		
		
		
		BorderPane numPlusAndMin = new BorderPane();
		numPlusAndMin.setPrefSize(size / 10, size);
		
		//start number buttons
		numHigher = new Button("+");
		numHigher.setPrefSize(size / 10, size);
		numHigher.setFont(Font.font("Verdana", 10));
		numPlusAndMin.setTop(numHigher);

		numLower = new Button("-");
		numLower.setPrefSize(size / 10, size);
		numLower.setFont(Font.font("Verdana", 10));
		numPlusAndMin.setBottom(numLower);

		setRight(numPlusAndMin);
		
		numHigher.setOnMouseClicked(e -> dhc.higherClicked1());
		numLower.setOnMouseClicked(e -> dhc.lowerClicked1());
		
		//start color buttons
		BorderPane colPlusAndMin = new BorderPane();
		colPlusAndMin.setPrefSize(size / 10, size);
		
		colHigher = new Button("+");
		colHigher.setPrefSize(size / 10, size);
		colHigher.setFont(Font.font("Verdana", 10));
		colPlusAndMin.setTop(numHigher);

		colLower = new Button("-");
		colLower.setPrefSize(size / 10, size);
		colLower.setFont(Font.font("Verdana", 10));
		colPlusAndMin.setBottom(numLower);

		setLeft(colPlusAndMin);
		numHigher.setOnMouseClicked(e -> higherClicked());
		numLower.setOnMouseClicked(e -> lowerClicked());
	
		//start confirm button
		Button confirm = new Button();
		confirm.setPrefSize(size, size / 10);
		
		setBottom(confirm);
//		confirm.setOnMouseClicked(e -> higherClicked());
	}

	public void addPlusAndMinus(int dienr) {
		if (dienr != 6) {
			numHigher = new Button("+");
			numHigher.setPrefSize(size / 10, size);
			numHigher.setFont(Font.font("Verdana", 10));
			this.setRight(numHigher);
			numHigher.setOnMouseClicked(e -> higherClicked());
		}
		if (dienr != 1) {
			numLower = new Button("-");
			numLower.setPrefSize(size / 10, size);
			numLower.setFont(Font.font("Verdana", 10));
			this.setLeft(numLower);
			numLower.setOnMouseClicked(e -> lowerClicked());
		}
	}


	private void higherClicked() {
		this.getChildren().removeAll(numHigher, numLower);
		dhc.higherClicked();
	}

	private void lowerClicked() {
		this.getChildren().removeAll(numHigher, numLower);
		dhc.lowerClicked();
	}

}
