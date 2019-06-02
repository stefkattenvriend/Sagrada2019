package view.GamePanes;

import javafx.scene.layout.HBox;

public class PersonalAttributes extends HBox{
	private PlayerPane pp;

	public PersonalAttributes(PlayerPane playerPane) {
		pp = playerPane;
	}
	
	

	public void refresh() {
		this.getChildren().clear();
		this.getChildren().addAll(pp.getpaystoneHolder(), pp.getTurn(), pp.getPointsPane(), pp.getpocp());
	}
}
