package controller;

import java.util.ArrayList;

import databeest.DbPatternCardInfoCollector;
import helpers.PatterncardType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.GameModel;
import model.PatterncardModel;

public class PatterncardController {

	private ArrayList<PatterncardModel> pcmodels = new ArrayList<PatterncardModel>();
	private ArrayList<PatterncardModel> pcChoiceModels = new ArrayList<PatterncardModel>();
	private int Patternnumber = 0;//wordt alleen in eerste keer dat een pc wordt gekozen gebruikt
	private GameModel gModel;
	private DbPatternCardInfoCollector DatabasePTCCollector;

	public PatterncardController(DbPatternCardInfoCollector DatabasePTCCollector, GameModel gm) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		gModel = gm;
		setup(gm);
	}

	public int numberOfPatternCards() {
		int amount = 0;
		amount = DatabasePTCCollector.numberOfPatCards();
		return amount;
	}

	private void setup(GameModel gm) {
		//getPcModels(gm);
	}

	public int getGameid() {
		int gameid = 0;
		gameid = gModel.getGameId();
		return gameid;
	}

	public int getPlayerID(int gameid, String username) {
		int playerid = 0;
		playerid = DatabasePTCCollector.getPlayerID(gameid, username);
		return playerid;
	}

	public void getPcModels(GameModel gm) {

		ArrayList<PatterncardModel> newPC = new ArrayList<PatterncardModel>();

		for (int i = 0; i < gm.getPma().length; i++) {
			if (gm.getPma()[i] != null) {
				if (gm.getPma()[i].getPatid() != 0) {
					newPC.addAll(DatabasePTCCollector.getPatternCard(gm.getPma()[i].getPatid()));
				}
			}
		}
		if (newPC.size() < pcmodels.size()) {
			return;
		} else {
			pcmodels.clear();
			for (int j = 0; j < newPC.size(); j++) {
				pcmodels.add(newPC.get(j));
				if (newPC.get(j).getNumber() != 0) {
//					System.out.println(newPC.get(j).getX() + newPC.get(j).getY() + newPC.get(j).getNumber());
				}
			}
		}
	}

	

	public PatterncardModel getPcModel(int i) {

		PatterncardModel model = pcmodels.get(i);

		return model;
	}

	public int getPcModelsSize() {
		return pcmodels.size();
	}

	public void addPatternCardChoice(int patID) {
		pcChoiceModels.addAll(DatabasePTCCollector.getPatternCard(patID));

	}

	public BorderPane PatterncardCreate(int x, int y, int PatterncardNumber, int size, PatterncardType pct) {
		BorderPane pane = new BorderPane();
		if (pct == PatterncardType.CHOICE || pct == PatterncardType.PLAYER) {
			pane.setPrefSize(85, 85);
		} else {
			pane.setPrefSize(42, 42);
		}

		// pane.setPrefSize(arg0, arg1);

		ArrayList<PatterncardModel> models = null;

		if (pct == PatterncardType.CHOICE) {
			models = pcChoiceModels;
		} else {
			models = pcmodels;
		}

		for (int i = 0; i < models.size(); i++) {
			if (models.get(i).getPatterncardNumber() == PatterncardNumber && models.get(i).getX() == x
					&& models.get(i).getY() == y) {
				if (models.get(i).getNumber() >= 1 && models.get(i).getNumber() <= 6) {
					Text center = new Text(Integer.toString(models.get(i).getNumber()));
					if (pct == PatterncardType.CHOICE || pct == PatterncardType.PLAYER) {
						center.setScaleX(7);
						center.setScaleY(7);
					} else {
						center.setScaleX(3.5);
						center.setScaleY(3.5);
					}

					pane.setCenter(center);
					break;
				} else if (models.get(i).getColor() != null) {
					Pane center = new Pane();
					center.setPrefSize(80, 80);
					center.setBackground(new Background(new BackgroundFill(models.get(i).getColor(), null, null)));
					pane.setCenter(center);
					break;
				} else {
					break;
				}

			}
		}

		return pane;
	}

	public void givePatternCardToPlayer(int rdInt, int idplayer) {
		String query = "UPDATE `player` SET `patterncard_idpatterncard` = '" + rdInt
				+ "' WHERE (`idplayer` = '" + idplayer + "');";
		DatabasePTCCollector.givePatternCardToPlayer(query);
	}
	
	public void insertChoice(String query) {
        DatabasePTCCollector.insertChoice(query);

    }
	
	public void updatePCa(int pcChosen, PatterncardType pct) {
		if (pct == PatterncardType.PLAYER) {
			for (int j = pcChoiceModels.size() - 1; j >= 0; j--) {
				if (pcChoiceModels.get(j).getPatterncardNumber() == pcChosen) {
					pcChoiceModels.get(j).setPct(pct);
					pcmodels.add(pcChoiceModels.get(j));
					pcChoiceModels.remove(j);
				}
			}
		}
	}


	public int getDifficulty(int rdInt) {
		int diff = DatabasePTCCollector.getDifficulty(rdInt);
		return diff;
		
	}

	public Color getColor(int playerid) {
		String colorString = DatabasePTCCollector.getColor(playerid);
		Color color = Color.WHITE;
		if (colorString != null) {
			switch (colorString) {
			case "geel":
				color = Color.YELLOW;
				break;
			case "groen":
				color = Color.GREEN;
				break;
			case "rood":
				color = Color.RED;
				break;
			case "blauw":
				color = Color.BLUE;
				break;

			case "paars":
				color = Color.PURPLE;
				break;
			}
		}
		else {
			System.out.println("huh??");
			color = Color.WHITE;
		}
		return color;
	}
	
	public boolean checkAllPatternCards() {
		ArrayList<Integer> allPlayersPC = new ArrayList<Integer>();
		int totalPCright = 0;
		
		for (int i = 0; i < gModel.getAmountOfPlayers(); i++) {
			allPlayersPC.add(i);
		}
		
		
		for (int i = 0; i < gModel.getAmountOfPlayers(); i++) {
			for (int j = 0; j < pcmodels.size(); j++) {
				if (pcmodels.get(j).getPatterncardNumber() == gModel.getPcID(i) && gModel.getPcID(i) != 0) {
					totalPCright++;
					break;
				}
			}
			
		}
		
		if (totalPCright == gModel.getAmountOfPlayers()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public void updateCardType(int playerPCid) {
		for (int i = 0; i < pcmodels.size(); i++) {
			if (pcmodels.get(i).getPatterncardNumber() == playerPCid) {
				pcmodels.get(i).setPct(PatterncardType.PLAYER);
			}
		}
	}
}
