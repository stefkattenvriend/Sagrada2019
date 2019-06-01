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

	public int getGameDice(int gameid) {
		int i =dba.getAmountOfGameDie(gameid);
		return i;
	}

	public ArrayList<String> getDieColor(int gameid) {
		ArrayList<String> dieColors = dba.getDieColors(gameid);
		return dieColors;
	}
	
	public ArrayList<Integer> getDieNumbers(int gameid) {
		ArrayList<Integer> dieNumbers = dba.getDieNumbers(gameid);
		return dieNumbers;
	}

	public void addDieToRound(int eyes, int round, int gameid, int dienumber, String string) {
		
		dba.addDieToRound(eyes, round, gameid, dienumber, string);
	}

	public int getRound(int idgame) {
		int round = dba.getRoundNumber(idgame);
		return round;
	}
	

}
