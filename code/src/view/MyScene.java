package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene{
	
	public MyScene() {
		super(new Pane());
		
		setRoot(new GamePane());//de root begint natuurlijk eerst met het menu.
	}
}
