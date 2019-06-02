package controller;

import java.util.ArrayList;

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
	private DbPlayerCollector dbPlayerCollector;
	
	public PointsController(GameController gameController) {
		this.gameController = gameController;
		this.gameModel = gameController.getGm();
		this.dbPlayerCollector = gameController.getdbPlayerCollector();
		allowCounting();
	}
	
	public void allowCounting()
	{
		pma = gameModel.getPma();
		if (true /*TODO StartSpeler */) {
			if (true /*TODO Einde van de Game */) {
				
				calculatePoints();
			}
		}
	}
	
	private void calculatePoints() {
		// voor elke speler
		for (int i = 0; i < pma.length; i++) {
			int personalObjectivePoints;
			int emptySpotsPenalty;
			int sharedObjectivePoints;
			int paystones;
			int totalPoints = 0;
			
			personalObjectivePoints = getPersonalObjectivePoints(pma[i]); 
			emptySpotsPenalty = getEmptySpotsPenalty(pma[i]);
/*TODO*/	sharedObjectivePoints = getSharedObjectivePoints(pma[i]); 
			paystones = getAmountOfPaystones(pma[i]); 
			
			totalPoints = getTotalPoints(personalObjectivePoints, emptySpotsPenalty, sharedObjectivePoints, paystones);
			System.out.println("Totalpoints for player " + pma[i].getUsername() + " = " + totalPoints);
			pma[i].setScore(totalPoints);
			dbPlayerCollector.setScore(pma[i].getPlayerId(), totalPoints);
		}
		
	}
	
	private int getPersonalObjectivePoints(PlayerModel pm)
	{
		ArrayList<DiceHolderModel> diceHolder;
		ArrayList<DiceModel> dice = new ArrayList<>();
		int personalObjectivePoints = 0;
		diceHolder = gameController.getDiceHolderController().getDhmodels();
		
		for(int i = 0; i < diceHolder.size(); i++) {
			dice.add(diceHolder.get(i).getDie());
		}
		
		for(int i = 0; i < dice.size(); i++) {
			if(dice.get(i).getDieColor().equals(pm.getObjectiveColor())) {
				personalObjectivePoints = personalObjectivePoints + dice.get(i).getEyes();
			}
		}
		return personalObjectivePoints;
	}
	
	private int getAmountOfPaystones(PlayerModel pm) 
	{
		System.out.println("amount of Paystones: " + pm.getPayStones());
		return pm.getPayStones();
	}

	private int getEmptySpotsPenalty(PlayerModel pm)
	{ 
		return pm.getDiceAmountOnFrame() - 20;
	}
	
	private int getSharedObjectivePoints(PlayerModel pm) 
	{
		//TODO
		return 0;
	}
	
	private int getTotalPoints(int personalObjectivePoints, int emptySpotsPenalty, int sharedObjectivePoints, int paystones) {
		int totalPoints = personalObjectivePoints + sharedObjectivePoints + paystones + emptySpotsPenalty;
		return totalPoints;
	}
}
