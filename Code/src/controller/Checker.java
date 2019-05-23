package controller;

public class Checker implements Runnable {

	@Override
	public void run() {
		while(true) {
			System.out.println("test");
			
			
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
