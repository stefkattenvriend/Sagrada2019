package controller;

import javafx.application.Platform;

public class MasterRunnable implements Runnable {

	private MenuController menuController;
	
	public MasterRunnable(MenuController menuController, GameController gameController) {
		this.menuController = menuController;
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
            	
//            	System.out.println("aan het checken..");
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
