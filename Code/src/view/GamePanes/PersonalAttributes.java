package view.GamePanes;

import javafx.scene.layout.HBox;

public class PersonalAttributes extends HBox{
	PlayerPane pp;

	public PersonalAttributes(PlayerPane playerPane) {
		pp = playerPane;
	}

	public void refresh() {
		this.getChildren().clear();
		this.getChildren().addAll(pp.getpaystoneHolder(), pp.getPointsPane(), pp.getpocp());
	}
}
