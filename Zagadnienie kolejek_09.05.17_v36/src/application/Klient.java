package application;


import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Klient implements KlientMovements {

	/**	The <u>linePosition</u> specifies where the client is.
	*	<li>-2 should be removed
 	*	<li> -1 is moving
	*	<li>0 just spawned
	*	<li>1-14 place in queue
	*	<li>15-26 number of kasy
	*/
	private int linePosition;
	private boolean canMove;
	
	private float speedMultiplier;
	private float timeForOnePixel;
	
	private AnchorPane anchorBackground;
	private AnchorPane anchorPane;
	
	private Kasa kasy[] = new Kasa[12];
	
	
	public Klient(float sm, Kasa[] kasy, AnchorPane ap) {
		speedMultiplier = sm;
		this.kasy = kasy;
		anchorBackground = ap;
		
		linePosition = 0;
		canMove = true;
		timeForOnePixel = (float)KlientCoordinates.Time.getValue() / (float)KlientCoordinates.Distance.getValue();
	}

	public void showKlient(String time, int klientCounter) {
		
		Circle circle = new Circle(20, 20, 20, Color.web("#5aa3e8"));
			circle.setStroke(Color.BLACK);
		
		Label klientNumber = new Label(String.valueOf(klientCounter));
			klientNumber.setFont(new Font(9));
		
		Label klientTime = new Label(time);
			klientTime.setFont(new Font(11));
		
		VBox vBox = new VBox();
			vBox.setAlignment(Pos.CENTER);
			vBox.setPrefWidth(40);
			vBox.setPrefHeight(40);
			
			vBox.getChildren().add(klientNumber);
			vBox.getChildren().add(klientTime);
		
		anchorPane = new AnchorPane();
			anchorPane.getChildren().add(circle);
			anchorPane.getChildren().add(vBox);
		
		anchorBackground.getChildren().add(anchorPane);
		
		anchorPane.setLayoutX(KlientCoordinates.StartPositionX.getValue());
		anchorPane.setLayoutY(KlientCoordinates.StartPositionY.getValue());
	}
	public void removeKlient() {
		anchorBackground.getChildren().remove(anchorPane);
	}
	
	@Override
	public void moveInQueue(int position, int previousPosition) {
		if (!canMove) return;
		
		if (position == 0) {
			quitQueue();
		} else {
			linePosition = position;
			canMove = false;
			TranslateTransition klientMovement = new TranslateTransition(new Duration((KlientCoordinates.Line0X.getValue() + KlientCoordinates.PositionInLineX.getValue() * (position - previousPosition)) * (timeForOnePixel / speedMultiplier)), anchorPane);
			klientMovement.setToX(KlientCoordinates.Line0X.getValue() + KlientCoordinates.PositionInLineX.getValue() * position);
			klientMovement.play();
			klientMovement.setOnFinished(e -> {
				canMove = true;
			});
		}
	}
	@Override
	public void moveToKasa(int kasa) {
		if (!canMove) return;
		
		kasy[kasa].setIsBusy(true);
		linePosition = -1;

		TranslateTransition klientMovement = new TranslateTransition(new Duration(KlientCoordinates.DistanceToCross.getValue() * (timeForOnePixel / speedMultiplier)), anchorPane);
		klientMovement.setToX(KlientCoordinates.CrossPositionX.getValue());
		klientMovement.play(); // move to Cross
		
		klientMovement.setOnFinished(e -> { // reach Cross
			klientMovement.setDuration(new Duration(KlientCoordinates.AfterCrossPositionY.getValue() * (timeForOnePixel / speedMultiplier)));
			if ( ((kasa + 1) % 2) == 0 ) klientMovement.setToY(KlientCoordinates.AfterCrossPositionY.getValue());
			if ( ((kasa + 1) % 2) == 1 ) klientMovement.setToY(-1 * KlientCoordinates.AfterCrossPositionY.getValue());
			klientMovement.play(); // move up or down
			
			klientMovement.setOnFinished(e1 -> { // reach Up or Down
				if ( (kasa + 1) == 1 || (kasa + 1) == 2 ) {
					klientMovement.setDuration(new Duration(KlientCoordinates.DistanceToKasa1.getValue() * (timeForOnePixel / speedMultiplier)));
					klientMovement.setToX(KlientCoordinates.Kasa1X.getValue());
				}
				if ( (kasa + 1) == 3 || (kasa + 1) == 4 ) {
					klientMovement.setDuration(new Duration((KlientCoordinates.DistanceToKasa1.getValue() + KlientCoordinates.DistanceToKasy.getValue() * 1) * (timeForOnePixel / speedMultiplier)));
					klientMovement.setToX(KlientCoordinates.Kasa3X.getValue());
				}
				if ( (kasa + 1) == 5 || (kasa + 1) == 6 ) {
					klientMovement.setDuration(new Duration((KlientCoordinates.DistanceToKasa1.getValue() + KlientCoordinates.DistanceToKasy.getValue() * 2) * (timeForOnePixel / speedMultiplier)));
					klientMovement.setToX(KlientCoordinates.Kasa5X.getValue());
				}
				if ( (kasa + 1) == 7 || (kasa + 1) == 8 ) {
					klientMovement.setDuration(new Duration((KlientCoordinates.DistanceToKasa1.getValue() + KlientCoordinates.DistanceToKasy.getValue() * 3) * (timeForOnePixel / speedMultiplier)));
					klientMovement.setToX(KlientCoordinates.Kasa7X.getValue());
				}
				if ( (kasa + 1) == 9 || (kasa + 1) == 10 ) {
					klientMovement.setDuration(new Duration((KlientCoordinates.DistanceToKasa1.getValue() + KlientCoordinates.DistanceToKasy.getValue() * 4) * (timeForOnePixel / speedMultiplier)));
					klientMovement.setToX(KlientCoordinates.Kasa9X.getValue());
				}
				if ( (kasa + 1) == 11 || (kasa + 1) == 12 ) {
					klientMovement.setDuration(new Duration((KlientCoordinates.DistanceToKasa1.getValue() + KlientCoordinates.DistanceToKasy.getValue() * 5) * (timeForOnePixel / speedMultiplier)));
					klientMovement.setToX(KlientCoordinates.Kasa11X.getValue());
				}
				klientMovement.play(); // move to Kasa
				
				klientMovement.setOnFinished(e2 -> { // reach Kasa
					klientMovement.setDuration(new Duration(KlientCoordinates.EnterInKasaDistance.getValue() * (timeForOnePixel / speedMultiplier)));
					if ( ((kasa + 1) % 2) == 0 ) klientMovement.setToY(KlientCoordinates.EnterInKasaPositionY.getValue());
					if ( ((kasa + 1) % 2) == 1 ) klientMovement.setToY(-1 * KlientCoordinates.EnterInKasaPositionY.getValue());
					klientMovement.play(); // enter in Kasa
					
					klientMovement.setOnFinished(e3 -> { // entered in Kasa
						linePosition = 15 + kasa;
						kasy[kasa].updateKasaProgress(this, kasa);
					});
					
				});
			});
		}); 
		
		
	}
	@Override
	public void quitQueue() {
		
		linePosition = -1;
		TranslateTransition klientMovement = new TranslateTransition(new Duration(KlientCoordinates.Line0X.getValue() * (timeForOnePixel / speedMultiplier)), anchorPane);
		
		klientMovement.setToX(KlientCoordinates.Line0X.getValue());
		klientMovement.play();
		klientMovement.setOnFinished(e -> {
			klientMovement.setToX(0);
			klientMovement.play();
			klientMovement.setOnFinished(e1 -> linePosition = -2); // should be removed
		});
	
	}
	@Override
	public void quitKasa(int kasa) {
		linePosition = -1;
		
		TranslateTransition klientMovement = new TranslateTransition(new Duration(KlientCoordinates.QuitKasaDistance.getValue() * (timeForOnePixel / speedMultiplier)), anchorPane);
		if ( ((kasa + 1) % 2) == 0 ) klientMovement.setToY(KlientCoordinates.QuitKasaPositionY.getValue());
		if ( ((kasa + 1) % 2) == 1 ) klientMovement.setToY(-1 * KlientCoordinates.QuitKasaPositionY.getValue());
		klientMovement.play(); // quit Kasa
		
		klientMovement.setOnFinished(e -> linePosition = -2); // qiuted Kasa
	}

	@Override
	public int getLinePosition() {
		return linePosition;
	}

}
