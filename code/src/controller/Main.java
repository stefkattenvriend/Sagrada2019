package controller;

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
	
	//instance variables
	/*private DbUserInfoCollector dbUserInfoCollector;*/
	
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		MasterController mc = new MasterController();
	
		MyScene myScene = new MyScene(stage, mc); //gamecontroller wordt aangemaakt datgeen wat er nu instaat wordt in gamecontroller aangemaakt.
		
		// Basic stage stuff
		stage.setResizable(false);
		stage.setScene(myScene);
		stage.show();		
	}

}
