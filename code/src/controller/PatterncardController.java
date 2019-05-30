package controller;

import java.util.ArrayList;

import databeest.DbPatternCardInfoCollector;
import helpers.PatterncardType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.GameModel;
import model.PatterncardModel;

public class PatterncardController {

	private ArrayList<PatterncardModel> pcmodels = new ArrayList<PatterncardModel>();
	private int Patternnumber = 0;
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
		getPcModels(gm);
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

	private void getPcModels(GameModel gm) {
		
		for (int i = 0; i < gm.getPma().length; i++) {
			if (gm.getPma()[i] != null) {
				if (gm.getPma()[i].getPatid() != 0) {
					ArrayList<PatterncardModel> newPC = DatabasePTCCollector.getPatternCard(gm.getPma()[i].getPatid());
					for (int j = 0; j < newPC.size(); j++) {
						pcmodels.add(newPC.get(j));
						if(newPC.get(j).getNumber() != 0) {
							System.out.println(newPC.get(j).getX() + newPC.get(j).getY() + newPC.get(j).getNumber());
						}
						
					}
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
	
	
	
	public BorderPane PatterncardCreate(int x, int y, int PatterncardNumber, int size, PatterncardType pct) {
		BorderPane pane = new BorderPane();
		if(pct == PatterncardType.CHOICE || pct == PatterncardType.PLAYER) {
			pane.setPrefSize(85, 85);
		}else {
			pane.setPrefSize(42, 42);
		}
		
		//pane.setPrefSize(arg0, arg1);
		
		
		
		for (int i = 0; i < pcmodels.size(); i++) {
			if (pcmodels.get(i).getPatterncardNumber() == PatterncardNumber && pcmodels.get(i).getX() == x && pcmodels.get(i).getY() == y) {
				if(pcmodels.get(i).getNumber() >= 1 && pcmodels.get(i).getNumber() <= 6) {
					Text center = new Text(Integer.toString(pcmodels.get(i).getNumber()));
					if(pct == PatterncardType.CHOICE || pct == PatterncardType.PLAYER) {
						center.setScaleX(7);
						center.setScaleY(7);
					}else {
						center.setScaleX(3.5);
						center.setScaleY(3.5);	
					}
					
					pane.setCenter(center);
					break;
				}
				else if(pcmodels.get(i).getColor() != null) {
					Pane center = new Pane();
					center.setPrefSize(80, 80);
					center.setBackground(new Background( new BackgroundFill(pcmodels.get(i).getColor(), null, null)));
					pane.setCenter(center);
					break;
				}
				else {
					break;
				}
				
			}
		}
		
		return pane;
	}

	public void givePatternCardToPlayer(int rdInt, int idplayer) {
		String query = "UPDATE `mwmastbe_db2`.`player` SET `patterncard_idpatterncard` = '" + rdInt + "' WHERE (`idplayer` = '" + idplayer + "');";
		DatabasePTCCollector.givePatternCardToPlayer(query);
	}

	public void insertChoice(String query) {
		DatabasePTCCollector.insertChoice(query);
		
	}
}
