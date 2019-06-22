package controller;

import java.util.ArrayList;

import com.sun.management.GcInfo;

import databeest.DbPlayerCollector;
import model.DiceHolderModel;
import model.DiceModel;
import model.GameModel;
import model.PlayerModel;

//Tjess Wjest & Stjef vjan Ojsch
public class PointsController {

	private PlayerModel[] pma;
	private GameModel gameModel;
	private GameController gameController;
	private boolean gameEnd;
	private DbPlayerCollector dbPlayerCollector;
	
	public PointsController(GameController gameController) {
		this.gameController = gameController;
		this.gameModel = gameController.getGm();
		gameEnd = false;
		this.dbPlayerCollector = gameController.getdbPlayerCollector();
		allowCounting();
	}

	public void setEnd(Boolean end) { 
		gameEnd = end;
	}

	public void allowCounting() {
		pma = gameModel.getPma();
		// if (true /*TODO StartSpeler */) {
//		if (gameEnd) {
			calculatePoints();
//		}
	}

	private void calculatePoints() {
		// voor elke speler
		for (int i = 0; i < pma.length; i++) {
//			int i = 0;
			int personalObjectivePoints;
			int emptySpotsPenalty;
			int sharedObjectivePoints;
			int paystones;
			
			personalObjectivePoints = getPersonalObjectivePoints(pma[i]); 
			emptySpotsPenalty = getEmptySpotsPenalty(pma[i]);
			sharedObjectivePoints = getSharedObjectivePoints(/*pma[i]*/); //Jami's methode haalt score van alle drie doelkaarten op voor degene die deze methode aanroept.
			paystones = getAmountOfPaystones(pma[i]); 
			
			setPrivatePoints(pma[i], personalObjectivePoints, emptySpotsPenalty, sharedObjectivePoints, paystones);
			setPublicPoints(pma[i], sharedObjectivePoints, emptySpotsPenalty, paystones);
			System.out.println("Privatepoints for player " + pma[i].getUsername() + " = " + getPrivatePoints(pma[i]));
			System.out.println("Totalpoints for player " + pma[i].getUsername() + " = " + getPublicPoints(pma[i]));
			dbPlayerCollector.setScore(pma[i].getPlayerId(), getPublicPoints(pma[i]));
		}
	}

	private int getPersonalObjectivePoints(PlayerModel pm) {
		ArrayList<DiceHolderModel> diceHolder;
		ArrayList<DiceModel> dice = new ArrayList<>();
		int personalObjectivePoints = 0;
		diceHolder = gameController.getDiceHolderController().getDhmodels();

		for (int i = 0; i < diceHolder.size(); i++) {
			dice.add(diceHolder.get(i).getDie());
		}
		
		for (int i = 0; i < dice.size(); i++) {
			if(dice.get(i) != null) {	
			if (dice.get(i).getDieColor().equals(pm.getObjectiveColor())) {
				personalObjectivePoints = personalObjectivePoints + dice.get(i).getEyes();
			}
		}
		}
		return personalObjectivePoints;
	}

	private int getAmountOfPaystones(PlayerModel pm) {
		System.out.println("amount of Paystones: " + pm.getPayStones());
		return pm.getPayStones();
	}

	private int getEmptySpotsPenalty(PlayerModel pm) {
		return pm.getDiceAmountOnFrame() - 20;
	}

	private int getSharedObjectivePoints(/*PlayerModel pm*/) {
//		int points = 0;
//		points = pm.getSharedObjectivePoints();
		return gameController.getCardsController().getSharedObjectivePoints();
//		return points;
	}

	
	private void setPrivatePoints(PlayerModel pm, int personalObjectivePoints, int emptySpotsPenalty, int sharedObjectivePoints, int paystones) 
	{
		int totalPoints = personalObjectivePoints + sharedObjectivePoints + paystones + emptySpotsPenalty;
		
		pm.setTotalPoints(totalPoints);
	}
	
	public int getPrivatePoints(PlayerModel pm) 
	{
		return pm.getTotalPoints();
	}
	
	private void setPublicPoints(PlayerModel pm, int sharedObjectivePoints, int emptySpotsPenalty, int paystones)
	{
		int publicPoints = sharedObjectivePoints + emptySpotsPenalty + paystones;
		
		pm.setPublicPoints(publicPoints);
	}
	
	public int getPublicPoints(PlayerModel pm) 
	{
		return pm.getPublicPoints();
	}

}
