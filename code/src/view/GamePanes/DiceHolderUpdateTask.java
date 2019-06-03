package view.GamePanes;

import javafx.concurrent.Task;

public class DiceHolderUpdateTask extends Task<Void> {

	@Override
	protected Void call() throws InterruptedException {

		for (int i = 0; i <= 100; i = 0) {

			Thread.sleep(3000);

		}
		return null;
	}
}
