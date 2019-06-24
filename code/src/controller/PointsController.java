package controller;

import java.util.ArrayList;

import databeest.DbPlayerCollector;
import helpers.DiceHolderType;
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
		pma = gameModel.getPma();
		this.dbPlayerCollector = gameController.getdbPlayerCollector();
	}



	public void allowCounting(PlayerModel pm) {

			calculatePoints(pm);			

	}

	private void calculatePoints(PlayerModel pm) {

			int personalObjectivePoints;
			int emptySpotsPenalty;
			int sharedObjectivePoints;
			int paystones;
//			System.out.println("--- hier volgen de punten ---");
			personalObjectivePoints = getPersonalObjectivePoints(/*pma[i]*/pm);
//			System.out.println("Persoonlijke punten: " + personalObjectivePoints);
			emptySpotsPenalty = getEmptySpotsPenalty(/*pma[i]*/pm);
//			System.out.println("Strafpunten: " + emptySpotsPenalty);
			
		
			sharedObjectivePoints = getSharedObjectivePoints(/*pma[i]*/); //Jami's methode haalt score van alle drie doelkaarten op voor degene die deze methode aanroept.
			System.out.println("Jami punten: " + sharedObjectivePoints + " @ pointscontroller 59");
			
			paystones = getAmountOfPaystones(/*pma[i]*/pm); 
			
			setPrivatePoints(pm, personalObjectivePoints, emptySpotsPenalty, sharedObjectivePoints, paystones);
			setPublicPoints(pm, sharedObjectivePoints, emptySpotsPenalty, paystones);
			System.out.println("Publicpoints for player " + pm.getUsername() + " = " + getPublicPoints(pm));
			System.out.println("Privatepoints for player " + pm.getUsername() + " = " + getPrivatePoints(pm));
			
			dbPlayerCollector.setScore(pm.getPlayerId(), getPublicPoints(pm));

	}

	private int getPersonalObjectivePoints(PlayerModel pm) {
		ArrayList<DiceHolderModel> diceHolder;
		ArrayList<DiceModel> dice = new ArrayList<>();
		int personalObjectivePoints = 0;
		diceHolder = gameController.getDiceHolderController().getDhmodels();

		for (int i = 0; i < diceHolder.size(); i++) {
			if(diceHolder.get(i).getType() == DiceHolderType.PLAYERWINDOW ) {
			dice.add(diceHolder.get(i).getDie());
			}
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
//		System.out.println("amount of Paystones: " + pm.getPayStones());
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

	public String getTotalPoints() {
		String scoreString = (new StringBuilder()).append("").toString();
		for(int i = 0; i < pma.length; i++) {
		scoreString = scoreString + (new StringBuilder()).append((pma[i].getUsername() + ": " + pma[i].getPublicPoints() + "\n").toString());
		}
		return scoreString;
	}

}
