package view.GamePanes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controller.ChatController;
import controller.GameController;
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
	private int playerid;
	private int buttonwidth = 35;

	// instance variables
	private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private ChatController cc;
	private ArrayList<String> chat;
	private ArrayList<Integer> chatnames;
	private ArrayList<String> chat_OLD;
	private ArrayList<String> chatdate;
	private LoginController loginController;
	private ScrollPane scrollPane;
	private int gameid;
	private TextArea textArea;
	private Button getchatbutton;
	private HBox buttonBar;
	private Button submitbutton;
	private TextField textField;
	private GameController gc;

	// Gemaakt door milan
	public ChatPane(GameController gameController, ChatController cc, LoginController loginController) {
		this.loginController = loginController;
		this.cc = cc;
		this.gc = gameController;
		setUp();
		gameid = cc.getGameid();
		playerid = cc.getPlayerID(gameid, loginController.getCurrentAccount());
		chat_OLD = new ArrayList<>();
	}

	private void setUp() {
		setPaneSize();
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));

		textArea = new TextArea();
		scrollPane = new ScrollPane();
		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setMinHeight(textareasize);
		scrollPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		textArea.setMinHeight(textareasize);
		textField = new TextField();
		textField.setPromptText("Type here to chat...");
		textArea.setDisable(true);
		textArea.setText("Welcome to Sagrada! \n");
		buttonBar = new HBox();
		submitbutton = new Button("Submit");
		submitbutton.setMinWidth(buttonwidth);

		getchatbutton = new Button("update");
		getchatbutton.setMinWidth(buttonwidth);
		scrollPane.setContent(textArea);
		// buttonBar.getChildren().addAll(textField, getchatbutton);
		buttonBar.getChildren().addAll(textField, submitbutton);
		setTop(scrollPane);
		setCenter(buttonBar);

		// submit button (puts your message in public chat)
		submitbutton.setOnAction(action -> {

			String message = textField.getText();
			String date = dateFormat.format(new Date());

			if (message.length() > 0) {
				// String name = loginController.getUsername();
				// textArea.appendText("(" + date + ") " + name + ": " + message); //
				// getChatFromDatabase first,
				// textArea.appendText("\n");
				cc.sendChatToDatabase(playerid, "NOW()", message);
				textField.clear();
				updateChat();

			}
			// buttonBar.getChildren().clear();

			// buttonBar.getChildren().addAll(textField, getchatbutton);
		});

		// getchatbutton.setOnAction(action -> updateChat());
		gc.giveChatPane(this);

	}

	public void updateChat() {
		int[] players = cc.whichPlayers(gameid);
		int amountOfPlayers = 0;
		for (int x = 0; x < players.length; x++) {
			if (players[x] != 0) {
				amountOfPlayers = amountOfPlayers + 1;
			}
		}
		int playerid1 = 0;
		int playerid2 = 0;
		int playerid3 = 0;
		int playerid4 = 0;
		
		if (players != null) {
			
			playerid1 = players[0];
			playerid2 = players[1];
			
			if (amountOfPlayers == 3) {
				playerid3 = players[2];
			}
			if (amountOfPlayers == 4) {
				playerid4 = players[3];
			}
		}
		chat = cc.getchat(amountOfPlayers, playerid1, playerid2, playerid3, playerid4);
		chatdate = cc.getchatDate(amountOfPlayers, playerid1, playerid2, playerid3, playerid4);
		chatnames = cc.getPlayers(amountOfPlayers, playerid1, playerid2, playerid3, playerid4);
		if (chat.size() != chat_OLD.size()) {
			textArea.clear();
			try {
				for (int x = 0; x < chat.size(); x++) {
					String name = cc.getUsername(chatnames.get(x));
					textArea.appendText("(" + chatdate.get(x) + ") " + name + ": ");
					textArea.appendText(chat.get(x) + "\n");
				}
				chat_OLD = chat;
			} catch (Exception e) {
				System.out.println("Something went wrong with the chat.");
			}

		}
	}

	private void setPaneSize() {
		setMinSize(panewidth, paneheight);
		setMaxSize(panewidth, paneheight);
	}

}
