package controller;

public class UpdateTimerController implements Runnable {

	@Override
	public void run() {
		while(true) {
			System.out.println("test");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
