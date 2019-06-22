package controller;

import java.util.ArrayList;
import java.util.Collections;

import databeest.DbDieCollector;
import helpers.DiceHolderType;
import model.DiceModel;
import view.GamePanes.DicePane;

public class DiceController {
	
	private ArrayList<DiceModel> dmodels;
	private ArrayList<DicePane> dpanes = new ArrayList<DicePane>();
	private DbDieCollector ddc;
	private DiceHolderController dhc;
	
	public DiceController(DbDieCollector ddc, int gameid, DiceHolderController dhc) {
		this.ddc = ddc;
		this.dhc = dhc;
		setup(gameid);
	}
	
	
	private void setup(int gameid) {
		createDieModel(gameid);
		createDicePane();
	}



	public void createDieModel(int gameid) {
		dmodels = ddc.getDiceModdelArray(gameid);
	}
	
	public void createDicePane() {
		DicePane dp;
		
		for (int i = 0; i < dmodels.size(); i++) {
			dp = new DicePane(40, dmodels.get(i).getDieColor(), dmodels.get(i).getEyes());
			dpanes.add(dp);
			}
	}
	
	public DicePane getDicePane(int i) {
		return dpanes.get(i);
	}
	
	public DiceModel getDiceModel(int i) {
		return dmodels.get(i);
	}
	
	public ArrayList<DiceModel> getDMAL() {
		return dmodels;
	}
	
	/*public void putDieOnRoundTrack(int gameid) {
		int round = getRound(gameid);
		ArrayList<String> diecolor = ddc.getDieColor(gameid, round);
		ArrayList<Integer> dienumber = ddc.getDieNumbers(gameid, round);
	
		for (int i = 0; i < dienumber.size(); i++) {
			ddc.addDieToRoundTrack(round, gameid, dienumber.get(i), diecolor.get(i));
//			System.out.println("Adding " + dienumber.get(i)+ " to roundtrack: " + round);
		}
	}*/
	
	public void generateOffer(int amountOfPlayers, int gameid) {
		int amountOfDice = amountOfPlayers * 2 + 1;

		ArrayList<String> diecolor = ddc.getDieColor(gameid, 0);
		ArrayList<Integer> dienumber = ddc.getDieNumbers(gameid, 0);
		
		int round = getRound(gameid);
		ArrayList<Integer> list = generateRandomNumbers(dienumber.size(), amountOfDice);
		
		for(int i = 0; i < list.size();i++) {
			int eyes = 1 + (int) (Math.random() * 6);
//			System.out.println(eyes);
			ddc.addDieToRound(eyes, round, gameid, dienumber.get(list.get(i)), diecolor.get(list.get(i)));
		}
		
	}
	
	public int getRound(int gameid) {
		int round = ddc.getRound(gameid);
		return round;
	}
	
	private ArrayList<Integer> generateRandomNumbers(int totaal, int aantal) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < totaal; i++) {
			list.add(i);
		}

		Collections.shuffle(list);

		int x = list.size() - aantal;
		for (int i = 0; i < x; i++) {
			list.remove(0);

		}
		return list;
	}
	
	public void reloadDicePanes() {
		dpanes.clear();
		createDicePane();
	}



	public DicePane getDicePaneFromModel(DiceModel die) {
		for (int i = 0; i < dmodels.size(); i++) {
			if (dmodels.get(i) == die) {//moet mischien .equals zijn
				return dpanes.get(i);
			}
		}
		return null;
	}


	public void addDieToRoundTrack(int round, int gameid, Integer dienumber, String color) {
		ddc.addDieToRoundTrack(round, gameid, dienumber, color);
		
	}
	
}
