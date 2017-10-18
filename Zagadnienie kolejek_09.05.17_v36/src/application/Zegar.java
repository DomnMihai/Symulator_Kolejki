package application;

import javafx.concurrent.Task;
import javafx.scene.control.Label;

public class Zegar {

	private Label timer;
	private boolean shouldRun;
	private float speedMultiplier;
	
	public Zegar (Label t, float sm) {
		timer = t;
		speedMultiplier = sm;
	}

	public void startZegar() {
		
		shouldRun = true;
		
		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				short h;
				byte m, s;
				h = 0;
				m = 0;
				s = 0;
				
				updateMessage("00:00:00");
				while (shouldRun) {
					Thread.sleep((long)(1000f/speedMultiplier));
					s++;
					if (s > 59) {
						s = 0;
						m++;
						if (m > 59) {
							m = 0;
							h++;
						}
					}
					updateMessage(insertTime(h, m, s));
				}
				
				return null;
			}
			
		};
		
		timer.textProperty().bind(task.messageProperty());
		
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
		
		task.setOnSucceeded(e -> timer.textProperty().unbind());
	}
	public void stopZegar() {
		shouldRun = false;
	}
	
	private String insertTime(short h, byte m, byte s) {
		String t;
		
		if (h < 10) t = 0 + String.valueOf(h);
			else t = String.valueOf(h);
		
		t = t + ":";
		
		if (m < 10) t = t + 0 + String.valueOf(m);
			else t = t + String.valueOf(m);
		
		t = t + ":";
		
		if (s < 10) t = t + 0 + String.valueOf(s);
			else t = t + String.valueOf(s);
		
		return t;
	}

}
