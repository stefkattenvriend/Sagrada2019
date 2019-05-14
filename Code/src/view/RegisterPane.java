package view;

import controller.LoginController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
// door TESSSSSSSSSS!!!!
public class RegisterPane extends VBox{
	//constants
	public final static double windowMaxWidth = 300;
	public final static double windowMaxHeight = 400;
	
	// instance variables
	private MyScene myScene;
	private LoginController controller;
	private TextField accountField;
	private TextField passwordField;
		
	public RegisterPane(MyScene myScene, LoginController controller) {
		this.myScene = myScene;
		this.controller = controller;
		setMinSize(windowMaxWidth, windowMaxHeight);
		setMaxSize(windowMaxWidth, windowMaxHeight);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		accountField = new TextField();
		passwordField = new TextField();
		accountField.setMinSize(100, 30);
		accountField.setMaxSize(100, 30);
		passwordField.setMinSize(100, 30);
		passwordField.setMaxSize(100, 30);
		Label accountLabel = new Label("Account: ");
		Label passwordLabel = new Label("Wachtwoord: ");
		accountLabel.setTextFill(Color.DARKRED);
		accountLabel.setFont(Font.font("", FontPosture.ITALIC, 20));
		passwordLabel.setTextFill(Color.DARKRED);
		passwordLabel.setFont(Font.font("", FontPosture.ITALIC, 20));
		Button registerButton = new Button("Registreren");
		registerButton.setOnAction(e -> register());
		getChildren().addAll(accountLabel, accountField, passwordLabel, passwordField, registerButton);
	}
	
	private void register()
	{
		if(controller.CreateAccount(accountField.getText(), passwordField.getText()))
		{
			myScene.setMenuPane();
			return;
		}
		else 
		{
			System.out.println("kan account niet aanmaken");
		}
	}
}
