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
	private int i = 0;
	
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
		TextField textField = new TextField("Type here...");
		textArea.setDisable(true);
		textArea.setText("Welcome to Sagrada! \n");
		HBox buttonBar = new HBox();
		Button button = new Button("Submit");
		button.setMinWidth(50);

		button.setOnAction(action -> {
			i++;
			textArea.appendText(textField.getText() + " " + i + " : " + dateFormat.format(new Date()));
			textArea.appendText("\n");
			
		});
		buttonBar.getChildren().addAll(textField, button);
		setCenter(textArea);
		setBottom(buttonBar);
	}

	private void setPaneSize() {
		setMinSize(panewidth, paneheight);
		setMaxSize(panewidth, paneheight);
	}
	

}
