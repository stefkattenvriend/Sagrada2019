package view.GamePanes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import controller.ChatController;
import databeest.DbChatCollector;
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
	private int playerid = 2; //hoort de id op te halen van de speler die chat...
	private int buttonwidth = 40;
	
	//instance variables
	private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private ChatController cc;
	
	//Gemaakt door milan
	public ChatPane(ChatController cc) {
		this.cc = cc;
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
		Button submitbutton = new Button("Submit");
		submitbutton.setMinWidth(buttonwidth);
		
		Button getchatbutton = new Button("get Chat");
		getchatbutton.setMinWidth(buttonwidth);
		
		buttonBar.getChildren().addAll(textField, submitbutton, getchatbutton);
		setTop(textArea);
		setCenter(buttonBar);
		
		//submit button (puts your message in public chat)
		submitbutton.setOnAction(action -> {
			
			String date = dateFormat.format(new Date());
			String gethighestdatefromdatabase = date;   //Iedere speler kan 1 bericht per TIMESTAMP sturen
			if( date == gethighestdatefromdatabase ){
			
			textArea.appendText("(" + date + "): " + textField.getText());	//getChatFromDatabase first, 
			textArea.appendText("\n");
			cc.sendChatToDatabase(playerid, "NOW()", textField.getText());
			textField.clear();
			
			}
		});
		
		getchatbutton.setOnAction(action -> {
			cc.getchat();
		}
		);
		
		
	}

	private void setPaneSize() {
		setMinSize(panewidth, paneheight);
		setMaxSize(panewidth, paneheight);
	}
	

}
