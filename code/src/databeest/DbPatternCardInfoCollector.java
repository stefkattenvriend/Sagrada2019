package databeest;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import model.PatterncardModel;

public class DbPatternCardInfoCollector {
    
    private DataBaseApplication dataBaseApplication;
    
    public DbPatternCardInfoCollector(DataBaseApplication dataBaseApplication) {
        this.dataBaseApplication = dataBaseApplication;
    }
    
    public ArrayList<PatterncardModel> getPatternCard(int pcnumber) {
        return dataBaseApplication.getPaternCard(pcnumber);
    }
    
    public int numberOfPatCards() {
    	int amountPCS = dataBaseApplication.numberOfPatternCards();
    	return amountPCS;
    }

	public void givePatternCardToPlayer(String query) { //aangeroepen door patterncardcontroller
		dataBaseApplication.insertQuery(query);
		
	}

	public int getPlayerID(int gameid, String username) {
		int playerid = dataBaseApplication.getPlayerID(username, gameid);
		return playerid;
	}

	public void insertChoice(String query) {
		dataBaseApplication.insertQuery(query);
		
	}

	public int getDifficulty(int rdInt) {
		int diff = dataBaseApplication.getDifficulty(rdInt);
		return diff;
	}

	public String getColor(int playerid) {
		String color = dataBaseApplication.getPlayerColor(playerid);
		return color;
	}
	
	public int countCards() {
		return dataBaseApplication.countCards();
	}
	
	public ArrayList<Integer> getPatternCards(int playerId) {
		return dataBaseApplication.getPlayerPatternCards(playerId);
	}
    
}