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

	public ArrayList<String> getDieColor(int gameid, int round) {
		ArrayList<String> dieColors = dba.getDieColors(gameid, round);
		return dieColors;
	}
	
	public ArrayList<Integer> getDieNumbers(int gameid, int round) {
		ArrayList<Integer> dieNumbers = dba.getDieNumbers(gameid, round);
		return dieNumbers;
	}

	public void addDieToRound(int eyes, int round, int gameid, int dienumber, String string) {
		
		dba.addDieToRound(eyes, round, gameid, dienumber, string);
	}

	public int getRound(int idgame) {
		int round = dba.getRoundNumber(idgame);
		return round;
	}

	public void addDieToRoundTrack(int round, int gameid, Integer integer, String string) {
		dba.addDieToRoundTrack(round, gameid, integer, string);
	}
	

}
