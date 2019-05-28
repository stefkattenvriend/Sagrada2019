package view.GamePanes;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controller.ChatController;
import controller.LoginController;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;



public class ChatPane extends BorderPane {

	// constants
	private double panewidth = (GamePane.windowMaxWidth / 3) / 2;
	private double paneheight = (GamePane.windowMaxHeight);
	private int textareasize = 735;
	private int playerid; // hoort de id op te halen van de speler die chat...
	private int buttonwidth = 35;

	// instance variables
	private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private ChatController cc;
	private ArrayList<String> chat;
	private ArrayList<String> chatdate;
	private LoginController loginController;
	private ScrollPane scrollPane;
	
	// Gemaakt door milan
	public ChatPane(ChatController cc, LoginController loginController) {
		this.loginController = loginController;
		this.cc = cc;
		setUp();
		playerid = 1;//waar haal je playerid!?
	}

	private void setUp() {
		setPaneSize();
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));

		TextArea textArea = new TextArea();
		scrollPane = new ScrollPane();
		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
//		scrollPane.setFitToWidth(true);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setMinHeight(textareasize);
		textArea.setMinHeight(textareasize);
		TextField textField = new TextField();
		textField.setPromptText("Type here to chat...");
		textArea.setDisable(true);
		textArea.setText("Welcome to Sagrada! \n");
		HBox buttonBar = new HBox();
		Button submitbutton = new Button("Submit");
		submitbutton.setMinWidth(buttonwidth);

		Button getchatbutton = new Button("update");
		getchatbutton.setMinWidth(buttonwidth);
		scrollPane.setContent(textArea);
		buttonBar.getChildren().addAll(textField, getchatbutton);
		setTop(scrollPane);
		setCenter(buttonBar);

		// submit button (puts your message in public chat)
		submitbutton.setOnAction(action -> {

			String message = textField.getText();
			String date = dateFormat.format(new Date());

			if (message.length() > 0) {
				String name = loginController.getUsername();
				textArea.appendText("(" + date + ") " + name + ": " + message); // getChatFromDatabase first,
				textArea.appendText("\n");
				cc.sendChatToDatabase(playerid, "NOW()", message);
				textField.clear();
				
			}
			buttonBar.getChildren().clear();
			
			buttonBar.getChildren().addAll(textField, getchatbutton);
		});

		getchatbutton.setOnAction(action -> {
			
			chat = cc.getchat();
			chatdate = cc.getchatDate();
			ArrayList<Integer> idchat = cc.getPlayerIDs();
			textArea.clear();
			for(int i = 0; i < chat.size(); i++) {
				
				String name = cc.getUsername(idchat.get(i));
				textArea.appendText("(" + chatdate.get(i) + ") " + name + ": ");
				textArea.appendText(chat.get(i) + "\n");
			}
			buttonBar.getChildren().clear();
			buttonBar.getChildren().addAll(textField, submitbutton);

		});

	}

	private void setPaneSize() {
		setMinSize(panewidth, paneheight);
		setMaxSize(panewidth, paneheight);
	}

}
