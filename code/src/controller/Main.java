package controller;

import databeest.DataBaseApplication;
import javafx.application.Application;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.MyScene;

public class Main extends Application{

	//constants (tijdelijk om panelen aan te duiden)
	public final static Background CARDDISPLAYPANE = new Background(new BackgroundFill(Color.GREEN, null, null));
	public final static Background PLAYERPANE = new Background(new BackgroundFill(Color.BLUE, null, null));
	public final static Background ENEMYPANE = new Background(new BackgroundFill(Color.RED, null, null));
	
	public static void main(String[] args) {
		
		
		DataBaseApplication databeest = new DataBaseApplication();
		if ((databeest.loadDataBaseDriver("com.mysql.cj.jdbc.Driver"))
				&& (databeest.makeConnection()))
		{
			databeest.doSomeQuerying();
//			databeest.doSomeUpdating();
		}

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		MyScene myScene = new MyScene(new DiceHolderController(), new PatterncardController());
		stage.setResizable(false);
		stage.setScene(myScene);
		stage.show();		
	}

}
