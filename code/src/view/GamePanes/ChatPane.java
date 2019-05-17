package view.GamePanes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import databeest.DBChatCollector;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ChatPane extends BorderPane {

	//constants
	private double panewidth = (GamePane.windowMaxWidth / 3) / 2;
	private double paneheight = (GamePane.windowMaxHeight);
	private int textareasize = 735;

	
	//instance variables
	private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	
	//Gemaakt door milan
	public ChatPane() {
		
		setUp();
	}

	private void setUp() {
		setPaneSize();
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null))); 

		TextArea textArea = new TextArea();
		textArea.setMinHeight(textareasize);
		TextField textField = new TextField();
		textField.setPromptText("Type here to chat...");
		textArea.setDisable(true);
		textArea.setText("Welcome to Sagrada! \n");
		HBox buttonBar = new HBox();
		Button button = new Button("Submit");
		button.setMinWidth(50);

		
		//Iedere speler kan 1 bericht per TIMESTAMP sturen
		button.setOnAction(action -> {
			
			String date = dateFormat.format(new Date());
			String gethighestdatefromdatabase = date;
			if( date != gethighestdatefromdatabase ){
			textArea.appendText("(" + date + "): " + textField.getText());
			textArea.appendText("\n");
			textField.clear();
			}
		});
		buttonBar.getChildren().addAll(textField, button);
		setTop(textArea);
		setCenter(buttonBar);
	}

	private void setPaneSize() {
		setMinSize(panewidth, paneheight);
		setMaxSize(panewidth, paneheight);
	}
	

}
