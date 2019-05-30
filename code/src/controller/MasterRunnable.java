package controller;

import javafx.application.Platform;

public class MasterRunnable implements Runnable {

	private MenuController menuController;
	
	public MasterRunnable(MenuController menuController, GameController gameController) {
		this.menuController = menuController;
	}
	
	
	@Override
    public void run() {
        Runnable updater = new Runnable() {

            @Override
            public void run() {
            	
            	menuController.updateIncomingInvite();
            	//plaats hier je updatemethod vanuit de bijbehorende controler.
            	
//            	menuController.updateActiveGames(); //dit zou moeten werken, laadtijd van query moet gefixt worden.
            	System.out.println("aan het checken..");
            }
        };

        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }

            // UI update is run on the Application thread
            Platform.runLater(updater);
        }
    }



}
