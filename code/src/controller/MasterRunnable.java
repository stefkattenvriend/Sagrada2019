package controller;

import javafx.application.Platform;

public class MasterRunnable implements Runnable {

	private MenuController menuController;
	private GameController gameController;
	
	public MasterRunnable(MenuController menuController, GameController gameController) {
		this.menuController = menuController;
		this.gameController = gameController;
	}
	
	
	@Override
    public void run() {
		boolean test = true;
        Runnable updater = new Runnable() {

            @Override
            public void run() {
            	
            	menuController.updateIncomingInvite();
            	menuController.updateActiveGames();
            	menuController.updateWaitedGames();
            	if(!menuController.getOpened()) {
            		menuController.updatePlayerlist();
            	}
            	gameController.updatePaystones();
            	
//            	System.out.println("aan het checken..");
                gameController.updateGameRound();
                gameController.updatePaystones();
            	gameController.updatePC();
            	gameController.setMyColor();
            	gameController.updateCardPane();
            	gameController.updateDiceOffer();
            	gameController.updateDicePlacement();
            	
//            	gameController.updateFirstDice(); // ga ik in menucontroller new game zetten
            	
            }
        };

        while (test) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
            }

            // UI update is run on the Application thread
            Platform.runLater(updater);
        }
    }



}
