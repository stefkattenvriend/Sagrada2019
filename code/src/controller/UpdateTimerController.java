package controller;

public class UpdateTimerController implements Runnable {
	
	private GameUpdateController guc;
	
	public UpdateTimerController(GameUpdateController guc) {
		this.guc = guc;
	}

	@Override
	public void run() {
		while(true) {
			guc.testRun();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
