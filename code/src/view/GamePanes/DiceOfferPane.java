package view.GamePanes;

import controller.DiceHolderController;
import helpers.DiceHolderType;
import javafx.scene.layout.FlowPane;

public class DiceOfferPane extends FlowPane {

	private DiceHolderController dhc;

	public DiceOfferPane(DiceHolderController dhc) {
		this.dhc = dhc;
		setUp();

	}

	private void setUp() {
		setPaneSize();
		// aanduiding();//engels
		addDiceHolders();

		// addDie();
	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, 200);
		setMaxSize(GamePane.windowMaxWidth / 3, 200);
	}

	private void addDiceHolders() {
		for (int i = 1; i < 10; i++) {
			double size = ((GamePane.windowMaxWidth / 3) / 5) - 1;
			this.getChildren().add(dhc.CreateDiceHolder(size, i, 0, DiceHolderType.OFFER));
		}
	}

	public void redrawDice() {
		this.getChildren().clear();
		for (int x = 1; x < 10; x++) {
			this.getChildren().add(dhc.getPlayerWindowDiceHolders(x, 0, DiceHolderType.OFFER));
		}
	}
}