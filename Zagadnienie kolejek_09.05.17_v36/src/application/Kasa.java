package application;


import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;

public class Kasa {

	private ProgressIndicator progressIndicator;
	private boolean isBusy;
	private float speedMultiplier;
	
	private RandomTimeGenerator rtg;
	
	private float t;
	private float sd;
	
	private boolean shouldRun;
	
	public Kasa(ProgressIndicator progIndic, float sm, float t, float sd) {
		progressIndicator = progIndic;
		speedMultiplier = sm;
		
		rtg = new RandomTimeGenerator();
		
		this.t = t;
		this.sd = sd;
		
		isBusy = false;
	}

	public void updateKasaProgress(Klient klient, int kasa) {
		
		isBusy = true;
		shouldRun = true;
		
		float actual_t = 1000 * rtg.generateTime(t, sd);
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {

				for (int i = 0; i < 100; i++) {
					if (!shouldRun) return null;
					updateProgress(i, 100);
					Thread.sleep( (long)((actual_t/speedMultiplier) / 100) );
				}
				updateProgress(100, 100);
				
				if (!shouldRun) return null;
				klient.quitKasa(kasa);
				isBusy = false;
				
				Thread.sleep((long)(1550f/speedMultiplier)); // the max time for actual 600/105 klient moving speed should be 1697f
				return null;
			}
		};
		
		progressIndicator.progressProperty().bind(task.progressProperty());
						
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		
		task.setOnSucceeded(e -> {
			progressIndicator.progressProperty().unbind();
			progressIndicator.setProgress(0);
		});
	}
	public void stopUpdating() {
		shouldRun = false;
	}
	
	public void setIsBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}
	public boolean getIsBusy() {
		return isBusy;
	}
	
}
