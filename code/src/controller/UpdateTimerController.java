package controller;

public class UpdateTimerController implements Runnable {
	
	private GameUpdateController guc;
	private MenuUpdateController muc;
	
	public UpdateTimerController(GameUpdateController guc, MenuUpdateController muc) {
		this.guc = guc;
		this.muc = muc;
	}

	@Override
	public void run() {
		while(true) {
			guc.testRun();
			muc.checker();
			try {//3 seconden
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
