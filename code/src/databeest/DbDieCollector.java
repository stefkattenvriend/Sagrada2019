package databeest;

import java.util.ArrayList;

import model.DiceModel;
import model.PlayerFieldFrameModel;

public class DbDieCollector {
	private DataBaseApplication dba;

	public DbDieCollector(DataBaseApplication dba) {
		this.dba = dba;
	}

	public ArrayList<DiceModel> getDiceModdelArray(int gameid){
		return dba.getDice(gameid);	
	}

	public ArrayList<PlayerFieldFrameModel> getPlayerFrame(int gameid) {
		return dba.getPlayerFrame(gameid);
	}
	

}
