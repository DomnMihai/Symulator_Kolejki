package application;

import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.scene.layout.AnchorPane;

public class Handler {

	private float speedMultiplier;
	private float t;
	private float sd;
	private int timeFormatDevider;
	
	private int iloscKas;
	private int klientCounter;
	
	private AnchorPane anchorBackground;
	
	Klient klient;
	private Kasa kasy[] = new Kasa[12];
	private ArrayList<Klient> klienty;
	RandomTimeGenerator rtg;
	
	private boolean shouldRun;
	
	public Handler(float sm, Kasa[] kasy, int iloscKas, AnchorPane anchorBackground, float t, float sd, int tfd) {
		speedMultiplier = sm;
		this.kasy = kasy;
		this.iloscKas = iloscKas;
		this.anchorBackground = anchorBackground;
		klienty = new ArrayList<Klient>(iloscKas * 2 + 14 + 1);
		this.t = t;
		this.sd = sd;
		timeFormatDevider = tfd;

		klientCounter = 0;
		rtg = new RandomTimeGenerator();
		shouldRun = true;
	}
	
	public void checkKlients() {
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				return null;
			}
		};
		
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		
		task.setOnSucceeded(e -> {
			int size = klienty.size();
			int linePosition;
			if (size != 0) {
				for (int i = 0; i < size; i++) {
					linePosition = klienty.get(i).getLinePosition();

					if (linePosition == 0) klienty.get(i).moveInQueue(checkForFreePosition(size), linePosition); // move in queue after spawn
					if (linePosition >= 1 && linePosition <= 13) { // move in queue
						int moveTo = checkForFreePosition(size, linePosition);
						if (moveTo != linePosition) klienty.get(i).moveInQueue(moveTo, linePosition);
					}
					if (linePosition == 14) { // move to Kasa
						int freeKasa = checkForFreeKasa();
						if (freeKasa != -1) klienty.get(i).moveToKasa(freeKasa);
					}
					if (linePosition == -2) { // remove klient
						klienty.get(i).removeKlient(); 
						klienty.remove(i);
						break;
					}
				}
			}
				
			if (shouldRun) checkKlients();
			else 
				if (size != 0) {
					for (int i = 0; i < size; i++) klienty.get(i).removeKlient(); // remove klient
					klienty.clear();
				}
			
			return;
		});
	}
	private int checkForFreePosition(int size) {
		int linePosition;
		boolean freePaths[] = new boolean[14];
		int moveTo;

		for (int i = 0; i < 14; i++) freePaths[i] = true;
		
		for (int i = 0; i < size; i++) {
			linePosition = klienty.get(i).getLinePosition();
			if (linePosition >= 1 && linePosition <=14) 
				freePaths[linePosition-1] = false;
		}
		
		moveTo = 14;
		for (int i = 0; i < 14; i++) {
			if (!freePaths[i]) {
				moveTo = i;
				break;
			}
		}
		
		return moveTo;
	}
	private int checkForFreePosition(int size, int actualPosition) {
		int linePosition;
		boolean freePaths[] = new boolean[14];
		int moveTo;

		for (int i = 0; i < 14; i++) freePaths[i] = true;
		
		for (int i = 0; i < size; i++) {
			linePosition = klienty.get(i).getLinePosition();
			if (linePosition >= 1 && linePosition <=14) 
				freePaths[linePosition-1] = false;
		}
		
		moveTo = 14;
		for (int i = actualPosition; i < 14; i++) {
			if (!freePaths[i]) {
				moveTo = i;
				break;
			}
		}
		
		return moveTo;
	}
	private int checkForFreeKasa() {
		for (int i = 0; i < iloscKas; i++) {
			if (!kasy[i].getIsBusy()) return i;
		}
		return -1;
	}
	
	public void spawnKlient() {
		
		float actual_t = 1000 * rtg.generateTime(t, sd);
			if (!shouldRun) return;
		
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				Thread.sleep((long)(actual_t / speedMultiplier));
				return null;
			}
		};
		
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		
		task.setOnSucceeded(e -> {
			if (!shouldRun) return;
			klientCounter++;
			klient = new Klient(speedMultiplier, kasy, anchorBackground);
			String time = String.format("%.1f", (actual_t / 1000) / timeFormatDevider);
			if (!shouldRun) return;
			klient.showKlient(time, klientCounter);
			klienty.add(klient);
			if (shouldRun) spawnKlient();
			return;
		});
	}

	public void stopHandler() {
		shouldRun = false;
	}
}
