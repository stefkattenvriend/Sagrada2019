package controller;

public class UpdateTimerController implements Runnable {

	private GameUpdateController guc;
	private MenuUpdateController muc;
	private boolean gameRunning = false;

	public UpdateTimerController(GameUpdateController guc, MenuUpdateController muc) {
		this.guc = guc;
		this.muc = muc;
	}

	@Override
	public void run() {
		while (true) {
			muc.checker();
			if (gameRunning == true) {
				guc.checkDiceMovement();
			}

			try {// 3 seconden
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void setGuc(GameUpdateController guc2) {
		this.guc = guc2;
	}
	
	public void setGameRunning(boolean running) {
		
		this.gameRunning = running;
	}
}
