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
// DOORRR Tesssssss
public class LoginPane extends VBox{
	//constants
		public final static double windowMaxWidth = 300;
		public final static double windowMaxHeight = 400;
		
		private Button loginButton;
		private Button registreerButton;
		private LoginController controller;
		private TextField accountField;
		private TextField wachtwoordField;
		
		public LoginPane(LoginController controller) {
			this.controller=controller;
			setMinSize(windowMaxWidth, windowMaxHeight);
			setMaxSize(windowMaxWidth, windowMaxHeight);
			setAlignment(Pos.CENTER);
			setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
			accountField = new TextField();
			wachtwoordField = new TextField();
			accountField.setMinSize(100, 30);
			accountField.setMaxSize(100, 30);
			wachtwoordField.setMinSize(100, 30);
			wachtwoordField.setMaxSize(100, 30);
			Label accountLabel = new Label("Account: ");
			Label wachtwoordLabel = new Label("Wachtwoord: ");
			accountLabel.setTextFill(Color.DARKRED);
			accountLabel.setFont(Font.font("", FontPosture.ITALIC, 20));
			wachtwoordLabel.setTextFill(Color.DARKRED);
			wachtwoordLabel.setFont(Font.font("", FontPosture.ITALIC, 20));
			createButtons();
			getChildren().addAll(accountLabel, accountField, wachtwoordLabel, wachtwoordField, loginButton, registreerButton);
			
		}


		private void createButtons() {
			loginButton = new Button("Inloggen");
			registreerButton = new Button("Registreren");
			loginButton.setOnAction(e -> controller.login(accountField.getText(), wachtwoordField.getText()));
			
//			registreerButton.setOnAction();     > Moet naar registreerPane gaan
			
		
			
		}
}
