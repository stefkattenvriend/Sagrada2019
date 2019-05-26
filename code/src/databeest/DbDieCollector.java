package databeest;

import java.util.ArrayList;

import model.DiceModel;

public class DbDieCollector {
	private DataBaseApplication dba;
	
	public DbDieCollector(DataBaseApplication dba) {
		this.dba = dba;
	}
	
	public ArrayList<DiceModel> getDiceModdelArray(int gameid){
		return dba.getDice(gameid);
		
	}
	
}
