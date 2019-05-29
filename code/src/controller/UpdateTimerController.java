package controller;

import javafx.application.Platform;
import javafx.concurrent.Task;

public class UpdateTimerController extends Task<Void> {

	private GameUpdateController guc;
	private MenuUpdateController muc;
	private boolean gameRunning = false;

	public UpdateTimerController(GameUpdateController guc, MenuUpdateController muc) {
		this.guc = guc;
		this.muc = muc;
	}

	public void update() {
		muc.checker();
		if (gameRunning == true) {
			System.out.println("test");
			guc.checkDiceMovement();
		}

	}

	public void setGuc(GameUpdateController guc2) {
		this.guc = guc2;
	}

	public void setGameRunning(boolean running) {

		this.gameRunning = running;
	}

	@Override
	protected Void call() throws InterruptedException {

		try {
			
		for (int i = 0; i <= 1; i = 0) {
			this.update();
			Thread.sleep(3000);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
