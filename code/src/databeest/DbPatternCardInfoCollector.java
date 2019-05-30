package databeest;

import java.util.ArrayList;
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
    
}