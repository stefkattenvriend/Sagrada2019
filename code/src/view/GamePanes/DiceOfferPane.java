package view.GamePanes;
import controller.DiceHolderController;
//joery
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class DiceOfferPane extends FlowPane{
		
		private DiceHolderController dhc;
	
	public DiceOfferPane(DiceHolderController dhc) {
		this.dhc = dhc;
		setUp();
		
		
	}
	
	private void setUp() {
		setPaneSize();
		//aanduiding();//engels
		addDiceHolders();
		addDie();
		}
	
	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, 200);
		setMaxSize(GamePane.windowMaxWidth / 3, 200);
	}
	
	private void aanduiding() { // deze method wordt uiteindelijk verwijderd
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); //aanduiding van chatvak
		Label text = new Label();
		text.setText("DiceOfferPane");
		getChildren().addAll(text);
	}
	
	private void addDiceHolders() {
		for (int i = 1; i < 10; i++) {
			double size = ((GamePane.windowMaxWidth / 3) / 5) - 1; 
			this.getChildren().add(dhc.CreateDiceHolder(size, i, 0, 3));
			}
	}
	
	private void addDie() {//test methode wordt later verwijderdt
		dhc.addDie(3, 1, 0, 1, 3, true);
	}
	}