package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {

	private FormulaCalculator formulaCalculator;
	private TextChecker textChecker;
	
	private boolean sekunda;
	private boolean minuta;
	private boolean godzina;
	
	private int iloscKas;
	
	@FXML private TextField ilosc_klientow_text_field; 
	@FXML private TextField okres_czasu_text_field; 
	@FXML private TextField kanal_obslugi_text_field; 
	@FXML private TextField sd_klientow_text_field;
	
	@FXML private Label iloscKlietnowBlad;
	@FXML private Label okresCzasuPrzybyciaKlietnowBlad;
	@FXML private Label iloscKanalowObslugiBlad;
	@FXML private Label sdKlientowBlad;
	@FXML private Label animacjaBlad;
	
	@FXML private TextField wynik_1_1;
	@FXML private TextField wynik_1_2;
	@FXML private TextField parametr_intensywnosci_ruchu_textfield;
	
	@FXML private TextField wynik_1_3;
	@FXML private Label wynik_1_3_label;
	@FXML private VBox potrzeba_restrukturyzacji_VBox;
	
	@FXML private TextField wynik_2_2;
	@FXML private TextField wynik_2_3;

	@FXML private ChoiceBox<String> pr_n_3_1_1_chBox;
	@FXML private TextField input_n_3_1_2;
	@FXML private TextField input_n_3_1_3;
	@FXML private ChoiceBox<String> pr_n_3_1_4_chBox;
	@FXML private TextField wynik_3_1;
	@FXML private Label blad_3_1;
	
	@FXML private ChoiceBox<String>  pr_t_3_2_1_chBox;
	@FXML private TextField input_t_3_2_2;
	@FXML private TextField input_t_3_2_3;
	@FXML private ChoiceBox<String> pr_t_3_2_4_chBox;
	@FXML private TextField wynik_3_2;
	@FXML private Label blad_3_2; 
	
	@FXML private TextField input_t_3_3_1;
	@FXML private TextField input_n_3_3_2;
	@FXML private TextField wynik_3_3;
	@FXML private Label blad_3_3;
	
	
		@FXML private VBox kasa_VBox_1;
		@FXML private VBox kasa_VBox_2;
		@FXML private VBox kasa_VBox_3;
		@FXML private VBox kasa_VBox_4;
		@FXML private VBox kasa_VBox_5;
		@FXML private VBox kasa_VBox_6;
		@FXML private VBox kasa_VBox_7;
		@FXML private VBox kasa_VBox_8;
		@FXML private VBox kasa_VBox_9;
		@FXML private VBox kasa_VBox_10;
		@FXML private VBox kasa_VBox_11;
		@FXML private VBox kasa_VBox_12;
		
		@FXML private TextField czas_kasa_text_field_1;
		@FXML private TextField czas_kasa_text_field_2;
		@FXML private TextField czas_kasa_text_field_3;
		@FXML private TextField czas_kasa_text_field_4;
		@FXML private TextField czas_kasa_text_field_5;
		@FXML private TextField czas_kasa_text_field_6;
		@FXML private TextField czas_kasa_text_field_7;
		@FXML private TextField czas_kasa_text_field_8;
		@FXML private TextField czas_kasa_text_field_9;
		@FXML private TextField czas_kasa_text_field_10;
		@FXML private TextField czas_kasa_text_field_11;
		@FXML private TextField czas_kasa_text_field_12;
		
		@FXML private TextField sd_kasa_text_field_1;
		@FXML private TextField sd_kasa_text_field_2;
		@FXML private TextField sd_kasa_text_field_3;
		@FXML private TextField sd_kasa_text_field_4;
		@FXML private TextField sd_kasa_text_field_5;
		@FXML private TextField sd_kasa_text_field_6;
		@FXML private TextField sd_kasa_text_field_7;
		@FXML private TextField sd_kasa_text_field_8;
		@FXML private TextField sd_kasa_text_field_9;
		@FXML private TextField sd_kasa_text_field_10;
		@FXML private TextField sd_kasa_text_field_11;
		@FXML private TextField sd_kasa_text_field_12;
		
		@FXML private ProgressIndicator kasaProgress_1;
		@FXML private ProgressIndicator kasaProgress_2;
		@FXML private ProgressIndicator kasaProgress_3;
		@FXML private ProgressIndicator kasaProgress_4;
		@FXML private ProgressIndicator kasaProgress_5;
		@FXML private ProgressIndicator kasaProgress_6;
		@FXML private ProgressIndicator kasaProgress_7;
		@FXML private ProgressIndicator kasaProgress_8;
		@FXML private ProgressIndicator kasaProgress_9;
		@FXML private ProgressIndicator kasaProgress_10;
		@FXML private ProgressIndicator kasaProgress_11;
		@FXML private ProgressIndicator kasaProgress_12;
		
	private VBox kasy[] = new VBox[12];
	private TextField czasyKas[] = new TextField[12];
	private TextField sdKas[] = new TextField[12];
	private ProgressIndicator progresKas[] = new ProgressIndicator[12];
	
	private Zegar zegar;
	private Handler handler;
	private Kasa kasyAnimation[] = new Kasa[12];
	
	private float speedMultiplier;
	
	@FXML private AnchorPane anchorBackground;
	
	@FXML private Label timer;
	
	@FXML private Button speedDecreaserbtn;
	@FXML private Button speedIncreaserbtn;
	@FXML private Button playBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sekunda = true;
		minuta  = false;
		godzina = false;
		
		formulaCalculator = new FormulaCalculator();
		textChecker = new TextChecker();
		
		clearWyniki();
		clearBledy();
		
			kasy[0] = kasa_VBox_1;
			kasy[1] = kasa_VBox_2;
			kasy[2] = kasa_VBox_3;
			kasy[3] = kasa_VBox_4;
			kasy[4] = kasa_VBox_5;
			kasy[5] = kasa_VBox_6;
			kasy[6] = kasa_VBox_7;
			kasy[7] = kasa_VBox_8;
			kasy[8] = kasa_VBox_9;
			kasy[9] = kasa_VBox_10;
			kasy[10] = kasa_VBox_11;
			kasy[11] = kasa_VBox_12;
			
			czasyKas[0] = czas_kasa_text_field_1;
			czasyKas[1] = czas_kasa_text_field_2;
			czasyKas[2] = czas_kasa_text_field_3;
			czasyKas[3] = czas_kasa_text_field_4;
			czasyKas[4] = czas_kasa_text_field_5;
			czasyKas[5] = czas_kasa_text_field_6;
			czasyKas[6] = czas_kasa_text_field_7;
			czasyKas[7] = czas_kasa_text_field_8;
			czasyKas[8] = czas_kasa_text_field_9;
			czasyKas[9] = czas_kasa_text_field_10;
			czasyKas[10] = czas_kasa_text_field_11;
			czasyKas[11] = czas_kasa_text_field_12;
			
			sdKas[0] = sd_kasa_text_field_1;
			sdKas[1] = sd_kasa_text_field_2;
			sdKas[2] = sd_kasa_text_field_3;
			sdKas[3] = sd_kasa_text_field_4;
			sdKas[4] = sd_kasa_text_field_5;
			sdKas[5] = sd_kasa_text_field_6;
			sdKas[6] = sd_kasa_text_field_7;
			sdKas[7] = sd_kasa_text_field_8;
			sdKas[8] = sd_kasa_text_field_9;
			sdKas[9] = sd_kasa_text_field_10;
			sdKas[10] = sd_kasa_text_field_11;
			sdKas[11] = sd_kasa_text_field_12;

			progresKas[0] = kasaProgress_1;
			progresKas[1] = kasaProgress_2;
			progresKas[2] = kasaProgress_3;
			progresKas[3] = kasaProgress_4;
			progresKas[4] = kasaProgress_5;
			progresKas[5] = kasaProgress_6;
			progresKas[6] = kasaProgress_7;
			progresKas[7] = kasaProgress_8;
			progresKas[8] = kasaProgress_9;
			progresKas[9] = kasaProgress_10;
			progresKas[10] = kasaProgress_11;
			progresKas[11] = kasaProgress_12;
			
		disableKasy();
		
		speedMultiplier = 1f;
	}

	public void pressedJednostkaCzasuSekunda(ActionEvent e) {
		sekunda = true;
		minuta = false;
		godzina = false;
		stopAnimation();
		clearWyniki();
		clearBledy();
	}
	public void pressedJednostkaCzasuMinuta(ActionEvent e) {
		sekunda = false;
		minuta = true;
		godzina = false;
		stopAnimation();
		clearWyniki();
		clearBledy();
	}
	public void pressedJednostkaCzasuGodzina(ActionEvent e) {
		sekunda = false;
		minuta = false;
		godzina = true;
		stopAnimation();
		clearWyniki();
		clearBledy();
	}
	
	public void pressedOblicz(ActionEvent e) {
		
		stopAnimation();
		clearWyniki();
		clearBledy();
		
		disableKasy();
		
		// Check TextFields for correct input
			if ( !textChecker.checkIffloatNumber(ilosc_klientow_text_field.getText()) ) {
				insertTextToElement(iloscKlietnowBlad, BladTexts.NoLiczbaRzeczywista.getValue());
				return;
			}
			insertTextToElement(iloscKlietnowBlad, "");
			
			if ( !textChecker.checkIffloatNumber(okres_czasu_text_field.getText()) ) {
				insertTextToElement(okresCzasuPrzybyciaKlietnowBlad, BladTexts.NoLiczbaRzeczywista.getValue());
				return;
			}
			insertTextToElement(okresCzasuPrzybyciaKlietnowBlad, "");
	
			if ( !textChecker.checkIfintNumber(kanal_obslugi_text_field.getText()) ) {
				insertTextToElement(iloscKanalowObslugiBlad, BladTexts.NoLiczbaNaturalna.getValue());
				return;
			}
			insertTextToElement(iloscKanalowObslugiBlad, "");
			iloscKas = textChecker.getintNumber(kanal_obslugi_text_field.getText());
			
			enableKasy();
			
		// check time in kanaly obslugi
		{
			int zlaKasa = checkIfAllKasyHaveTimeInputed();
			if ( zlaKasa != -1 ) {
				insertTextToElement(animacjaBlad, (BladTexts.NoLiczbaRzeczywista.getValue() + BladTexts.NumerKasy.getValue() + zlaKasa) );
				return;
			}
			insertTextToElement(animacjaBlad, "");
		}
		// check if all kasy have the same time
			if ( !checkIfAllKasyHaveTheSameTime() ) {
				insertTextToElement(animacjaBlad, BladTexts.NoJednakoweKasy.getValue());
				return;
			}
			insertTextToElement(animacjaBlad, "");
		
		//start calculation
		obliczWyniki_1();
			
			// check Warunek Stabilnosci
			if ( !formulaCalculator.checkWarunekStabilnosci_1_3() ) {
				insertTextToElement(wynik_1_3_label, BladTexts.WarunekStabilnosciNeg.getValue());
				potrzeba_restrukturyzacji_VBox.setVisible(true);
				showBrakStabilnosci();
				return;
			}
			insertTextToElement(wynik_1_3_label, BladTexts.WarunekStabilnosciPos.getValue());
			potrzeba_restrukturyzacji_VBox.setVisible(false);
			
			
		obliczWyniki_2();
	
		
		// tab 3 calculation
			// calculate prawdopodobienstwo osob w ukladzie
				if ( textChecker.checkIfintNumber0(input_n_3_1_2.getText()) ) {
					if ( textChecker.checkIfintNumber0(input_n_3_1_3.getText()) || input_n_3_1_3.getText().equals("inf") ) {
						insertTextToElement(blad_3_1, "");
						obliczWyniki_3( (byte)1 );
					} else {
						insertTextToElement(blad_3_1, BladTexts.NoLiczbaNaturalna0.getValue());
					}
				} else {
					insertTextToElement(blad_3_1, BladTexts.NoLiczbaNaturalna0.getValue());
				}
		
			// calculate prawdopodobienstwo czasu oczekiwania w kolejce
				if ( textChecker.checkIffloatNumber0( input_t_3_2_2.getText() ) ) {
					if ( textChecker.checkIffloatNumber0(input_t_3_2_3.getText()) || input_t_3_2_3.getText().equals("inf") ) {
						insertTextToElement(blad_3_2, "");
						obliczWyniki_3( (byte)2 );
					} else {
						insertTextToElement(blad_3_2, BladTexts.NoLiczbaRzeczywista0.getValue());
					}
				} else {
					insertTextToElement(blad_3_2, BladTexts.NoLiczbaRzeczywista0.getValue());
				}
		
			// calculate prawdopodobienstwo obslugi n klientow w czasie t
				if (textChecker.checkIffloatNumber0( input_t_3_3_1.getText() )) {
					if (textChecker.checkIfintNumber0( input_n_3_3_2.getText() )) {
						insertTextToElement(blad_3_3, "");
						obliczWyniki_3( (byte)3 );
					} else {
						insertTextToElement(blad_3_3, BladTexts.NoLiczbaNaturalna0.getValue());
					}
				} else {
					insertTextToElement(blad_3_3, BladTexts.NoLiczbaRzeczywista0.getValue());
				}
		
	}
	
	public void pressedCopyTimeToAllKasy1(ActionEvent e) {
		stopAnimation();
		inputTextToKasy(1);
	}
	public void pressedCopyTimeToAllKasy2(ActionEvent e) {
		stopAnimation();
		inputTextToKasy(2);
	}
	public void pressedCopyTimeToAllKasy3(ActionEvent e) {
		stopAnimation();
		inputTextToKasy(3);
	}
	public void pressedCopyTimeToAllKasy4(ActionEvent e) {
		stopAnimation();
		inputTextToKasy(4);
	}
	public void pressedCopyTimeToAllKasy5(ActionEvent e) {
		stopAnimation();
		inputTextToKasy(5);
	}
	public void pressedCopyTimeToAllKasy6(ActionEvent e) {
		stopAnimation();
		inputTextToKasy(6);
	}
	public void pressedCopyTimeToAllKasy7(ActionEvent e) {
		stopAnimation();
		inputTextToKasy(7);
	}
	public void pressedCopyTimeToAllKasy8(ActionEvent e) {
		stopAnimation();
		inputTextToKasy(8);
	}
	public void pressedCopyTimeToAllKasy9(ActionEvent e) {
		stopAnimation();
		inputTextToKasy(9);
	}
	public void pressedCopyTimeToAllKasy10(ActionEvent e) {
		stopAnimation();
		inputTextToKasy(10);
	}
	public void pressedCopyTimeToAllKasy11(ActionEvent e) {
		stopAnimation();
		inputTextToKasy(11);
	}
	public void pressedCopyTimeToAllKasy12(ActionEvent e) {
		stopAnimation();
		inputTextToKasy(12);
	}

	private void enableKasy() {
		if ( iloscKas > 12 ) {
			disableKasy();
			kasy[0].setDisable(false);
		} else {
			for (int i = 0; i < iloscKas; i++)
				kasy[i].setDisable(false);
		}
	}
	private void disableKasy() {
		for (int i = 0; i < 12; i++)
			kasy[i].setDisable(true);
	}
	private void inputTextToKasy(int parentKasa) {
		for ( int i = 0; i < ( (this.iloscKas > 12)? 1 : this.iloscKas ); i++) {
			czasyKas[i].setText( czasyKas[parentKasa - 1].getText() );
			sdKas[i].setText( sdKas[parentKasa - 1].getText() );
		}
	}
	private int checkIfAllKasyHaveTimeInputed() { //return -1 if all enabled t are a positive float
		for ( int i = 0; i< (   (this.iloscKas > 12)? 1 : this.iloscKas   ); i++)
			if ( !textChecker.checkIffloatNumber(czasyKas[i].getText()) ) return i+1;

		return -1;
	}
	private int checkIfAllKasyHaveSDInputed() { //return -1 if all enabled sd are a positive float
		for ( int i = 0; i< (   (this.iloscKas > 12)? 1 : this.iloscKas   ); i++)
			if ( !textChecker.checkIffloatNumber0(sdKas[i].getText()) ) return i+1;

		return -1;
	}
	private boolean checkIfAllKasyHaveTheSameTime() {
		if ( iloscKas > 1 && iloscKas <= 12 ) {
			for ( int i = 1; i < iloscKas; i++ ) {
				if ( !czasyKas[0].getText().equals(czasyKas[i].getText())) return false;
			}
		}
		return true;
	}
	private boolean checkIf_t_IsSmallestThan(float t) {
		for ( int i = 0; i < iloscKas; i++) 
			if (textChecker.getfloatNumber(czasyKas[i].getText()) < t) return true;
		
		return false;
	}
	
	private void showBrakStabilnosci() {
		insertTextToElement(wynik_2_2, BladTexts.BrakStabilnosci.getValue());
		insertTextToElement(wynik_2_3, BladTexts.BrakStabilnosci.getValue());
		insertTextToElement(wynik_3_1, BladTexts.BrakStabilnosci.getValue());
		insertTextToElement(wynik_3_2, BladTexts.BrakStabilnosci.getValue());
		insertTextToElement(wynik_3_3, BladTexts.BrakStabilnosci.getValue());
	}
	private void insertTextToElement(Label l, String s) {
		l.setText(s);
	}
	private void insertTextToElement(TextField t, String s) {
		t.setText(s);
	}
	private void insertTextToElement(Button b, String s) {
		b.setText(s);
	}
	
	private void obliczWyniki_1() { // calculate first tab 
		float f;
		
		// calculate Stopa Przybyc
		f = formulaCalculator.computeStopaPrzybyc_1_1( textChecker.getfloatNumber(ilosc_klientow_text_field.getText()), textChecker.getfloatNumber(okres_czasu_text_field.getText()) );
		insertTextToElement(wynik_1_1, f + textChecker.getUnitFormat(sekunda, minuta, godzina));
		
		// calculate Stopa Obslugi
		f = formulaCalculator.computeStopaObslugi_1_2(iloscKas, czasyKas[0]);
		insertTextToElement(wynik_1_2, f + textChecker.getUnitFormat(sekunda, minuta, godzina));

		// calculate Parametr intesnsywnosci ruchu
		f = formulaCalculator.computeParametrIntensywnosciRuchu();
		insertTextToElement(parametr_intensywnosci_ruchu_textfield, Float.toString(f));
		
		// show Warunek Stabilnosci
		String s = formulaCalculator.getSP_SOComparision();
		String s1 = String.format("%.04f", formulaCalculator.getStopaPrzybyc());
		String s2 = String.format("%.04f", formulaCalculator.getStopaObslugi());
		insertTextToElement(wynik_1_3, s1 + s + s2);
	}
	private void obliczWyniki_2() { // calculate second tab
		float f;
		
		formulaCalculator.computeP0_3(iloscKas);
		
		// calculate Srednia liczba klientow oczekujaca na obsluge
		f = formulaCalculator.computePrzecietnaLiczbaKlientowWKolejce_2_2(iloscKas);
		insertTextToElement(wynik_2_2, Float.toString(f));
		
		f = formulaCalculator.computeOdchylenieStandardoweKlientowWKolejce_2_3(iloscKas);
		insertTextToElement(wynik_2_3, Float.toString(f));
	}
	private void obliczWyniki_3(byte key) { // calculate third tab
		
		boolean notInf = true;
		float f = 0;
		
		switch (key) {
			case 1: // calclulate prawdopodobienstwo klientow w ukladzie
				if ( input_n_3_1_3.getText().equals("inf") && !pr_n_3_1_4_chBox.getValue().equals("mniej") ) {
					insertTextToElement(blad_3_1, BladTexts.NiemozliwyPrzedzial.getValue());
					return;
				}
				if ( input_n_3_1_3.getText().equals("inf") ) notInf = false;
					
				if ( notInf && textChecker.getintNumber(input_n_3_1_2.getText()) > textChecker.getintNumber(input_n_3_1_3.getText()) ) {
					insertTextToElement(blad_3_1, BladTexts.NiemozliwyPrzedzial.getValue());
					return;
				}	
				
				if ( notInf && ( !pr_n_3_1_1_chBox.getValue().equals("więcej bądź równo") || !pr_n_3_1_4_chBox.getValue().equals("mniej bądź równo") ) && ( textChecker.getintNumber(input_n_3_1_2.getText()) == textChecker.getintNumber(input_n_3_1_3.getText()) ) ) {
					insertTextToElement(blad_3_1, BladTexts.NiemozliwyPrzedzial.getValue());
					return;
				}
				
				f = formulaCalculator.computePrawdopodobienstwoWUkladzie(iloscKas, pr_n_3_1_1_chBox.getValue(), input_n_3_1_2.getText(), input_n_3_1_3.getText(), pr_n_3_1_4_chBox.getValue());
				insertTextToElement(wynik_3_1, Float.toString(f));
				break;
				
			case 2: // calculate czas oczekiwania w kolejce
				if ( input_t_3_2_3.getText().equals("inf") && !pr_t_3_2_4_chBox.getValue().equals("mniej") ) {
					insertTextToElement(blad_3_2, BladTexts.NiemozliwyPrzedzial.getValue());
					return;
				}
				if ( input_t_3_2_3.getText().equals("inf") ) notInf = false;
				
				if ( notInf && textChecker.getfloatNumber(input_t_3_2_2.getText()) > textChecker.getfloatNumber(input_t_3_2_3.getText()) ) {
					insertTextToElement(blad_3_2, BladTexts.NiemozliwyPrzedzial.getValue());
					return;
				}
				
				if ( notInf && ( !pr_t_3_2_1_chBox.getValue().equals("więcej bądź równo") || !pr_t_3_2_4_chBox.getValue().equals("mniej bądź równo") ) && ( textChecker.getfloatNumber(input_t_3_2_2.getText()) == textChecker.getfloatNumber(input_t_3_2_3.getText()) ) ) {
					insertTextToElement(blad_3_2, BladTexts.NiemozliwyPrzedzial.getValue());
					return;
				}
				
				f = formulaCalculator.computePrawdopodobienstwoCzasuStaniaWKolejce(iloscKas, input_t_3_2_2.getText(), input_t_3_2_3.getText());
				insertTextToElement(wynik_3_2, Float.toString(f));
				break;
				
			case 3: // calculate czas obslugi n osob w czasie t
				f = formulaCalculator.computePrawdopodobienstwoObslugi_n_OsobWCzasie_t_3_3( iloscKas, textChecker.getfloatNumber(input_t_3_3_1.getText()), textChecker.getintNumber(input_n_3_3_2.getText()) );
				insertTextToElement(wynik_3_3, Float.toString(f));
				break;
				
			default:
				break;
		}
	}
	
	private void clearWyniki() {
		insertTextToElement(wynik_1_1, "");
		insertTextToElement(wynik_1_2, "");
		insertTextToElement(wynik_1_3, "");

		insertTextToElement(parametr_intensywnosci_ruchu_textfield, "");
		insertTextToElement(wynik_2_2, "");
		insertTextToElement(wynik_2_3, "");
		
		insertTextToElement(wynik_3_1, "");
		insertTextToElement(wynik_3_2, "");
		insertTextToElement(wynik_3_3, "");
		
		insertTextToElement(wynik_1_3_label, BladTexts.WarunekStabilnosciNeutr.getValue());
		potrzeba_restrukturyzacji_VBox.setVisible(false);
	}
	private void clearBledy() {
		insertTextToElement(iloscKlietnowBlad, "");
		insertTextToElement(okresCzasuPrzybyciaKlietnowBlad, "");
		insertTextToElement(iloscKanalowObslugiBlad, "");
		insertTextToElement(sdKlientowBlad, "");
		insertTextToElement(animacjaBlad, "");
		
		insertTextToElement(blad_3_1, "");
		insertTextToElement(blad_3_2, "");
		insertTextToElement(blad_3_3, "");
	}
	private void clearInsertTextFields() {
		insertTextToElement(ilosc_klientow_text_field, "");
		insertTextToElement(okres_czasu_text_field, "");
		insertTextToElement(kanal_obslugi_text_field, "");
		insertTextToElement(sd_klientow_text_field, "");
		insertTextToElement(input_n_3_1_2, "");
		insertTextToElement(input_n_3_1_3, "");
		insertTextToElement(input_t_3_2_2, "");
		insertTextToElement(input_t_3_2_3, "");
		insertTextToElement(input_t_3_3_1, "");
		insertTextToElement(input_n_3_3_2, "");
		for (int i = 0; i < 12; i++) {
			insertTextToElement(czasyKas[i], "");
			insertTextToElement(sdKas[i], "");
		}
		disableKasy();
//		insertTextToElement(timer, "00:00:00");
	}
	
	private void stopAnimation() {
		if (playBtn.isDisabled()) {
			zegar.stopZegar();
			handler.stopHandler();
			for (int i = 0; i < iloscKas; i++) kasyAnimation[i].stopUpdating();
			playBtn.setDisable(false);
		}
	}
	public void pressedPlay(ActionEvent e) {
		
		playBtn.setDisable(true);
		clearBledy();
		
		//	check if liczba kas <= 12
		if (iloscKas > 12 || iloscKas < 1) {
			insertTextToElement(iloscKanalowObslugiBlad, BladTexts.NiepoprawnaLiczbaKas.getValue());
			playBtn.setDisable(false);
			return;
		}
		insertTextToElement(iloscKanalowObslugiBlad, "");
		
		// check if SD klientow is a float >=0
			if ( !textChecker.checkIffloatNumber0(sd_klientow_text_field.getText()) ) {
				insertTextToElement(sdKlientowBlad, BladTexts.NoLiczbaRzeczywista0.getValue());
				playBtn.setDisable(false);
				return;
			}
			insertTextToElement(sdKlientowBlad, "");
		
		// check time and sd in kanaly obslugi are float
		{
			int zlaKasa = checkIfAllKasyHaveTimeInputed();
			if ( zlaKasa != -1 ) {
				insertTextToElement(animacjaBlad, (BladTexts.NoLiczbaRzeczywista.getValue() + BladTexts.NumerKasy.getValue() + zlaKasa) );
				playBtn.setDisable(false);
				return;
			}
			insertTextToElement(animacjaBlad, "");
			
			zlaKasa = checkIfAllKasyHaveSDInputed();
			if ( zlaKasa != -1 ) {
				insertTextToElement(animacjaBlad, (BladTexts.NoLiczbaRzeczywista0.getValue() + BladTexts.NumerKasy.getValue() + zlaKasa) );
				playBtn.setDisable(false);
				return;
			}
			insertTextToElement(animacjaBlad, "");
		}	
		
		// check if klienty is a float > 0
			if ( !textChecker.checkIffloatNumber(ilosc_klientow_text_field.getText()) ) {
				insertTextToElement(iloscKlietnowBlad, BladTexts.NoLiczbaRzeczywista.getValue());
				playBtn.setDisable(false);
				return;
			}
			insertTextToElement(iloscKlietnowBlad, "");
			
		// check if okres czasu is a float > 0
			if ( !textChecker.checkIffloatNumber(okres_czasu_text_field.getText()) ) {
				insertTextToElement(okresCzasuPrzybyciaKlietnowBlad, BladTexts.NoLiczbaRzeczywista.getValue());
				playBtn.setDisable(false);
				return;
			}
			insertTextToElement(okresCzasuPrzybyciaKlietnowBlad, "");
		
		// check if klienty come not often than 5seconds
			formulaCalculator.computeStopaPrzybyc_1_1( textChecker.getfloatNumber(ilosc_klientow_text_field.getText()), textChecker.getfloatNumber(okres_czasu_text_field.getText()) );
			if (sekunda && (1 / formulaCalculator.getStopaPrzybyc()) < 5f ) {
				insertTextToElement(animacjaBlad, BladTexts.ZaCzestoKlientow.getValue());
				playBtn.setDisable(false);
				return;
			}
			if (minuta && (1 / formulaCalculator.getStopaPrzybyc()) < (1f / 12f) ) {
				insertTextToElement(animacjaBlad, BladTexts.ZaCzestoKlientow.getValue());
				playBtn.setDisable(false);
				return;
			}
			if (godzina && (1 / formulaCalculator.getStopaPrzybyc()) < (1f / 720f) ) {
				insertTextToElement(animacjaBlad, BladTexts.ZaCzestoKlientow.getValue());
				playBtn.setDisable(false);
				return;
			}
			
		// check if kasy work not often than 5seconds
			if (sekunda && checkIf_t_IsSmallestThan(5f)) {
				insertTextToElement(animacjaBlad, BladTexts.ZaSzybkaKasa.getValue());
				playBtn.setDisable(false);
				return;
			}
			if (minuta && checkIf_t_IsSmallestThan(1f / 12f)) {
				insertTextToElement(animacjaBlad, BladTexts.ZaSzybkaKasa.getValue());
				playBtn.setDisable(false);
				return;
			}
			if (godzina && checkIf_t_IsSmallestThan(1f / 720f)) {
				insertTextToElement(animacjaBlad, BladTexts.ZaSzybkaKasa.getValue());
				playBtn.setDisable(false);
				return;
			}
			
		// create Kasy	
			for (int i = 0; i < iloscKas; i++) {
				if (sekunda)
					kasyAnimation[i] = new Kasa( progresKas[i], speedMultiplier, textChecker.getfloatNumber(czasyKas[i].getText()), textChecker.getfloatNumber(sdKas[i].getText()) );
				if (minuta)
					kasyAnimation[i] = new Kasa( progresKas[i], speedMultiplier, textChecker.getfloatNumber(czasyKas[i].getText())*60, textChecker.getfloatNumber(sdKas[i].getText())*60 );
				if (godzina)
					kasyAnimation[i] = new Kasa( progresKas[i], speedMultiplier, textChecker.getfloatNumber(czasyKas[i].getText())*3600, textChecker.getfloatNumber(sdKas[i].getText())*3600 );
			}
			
		if (sekunda)
			handler = new Handler(speedMultiplier, kasyAnimation, iloscKas, anchorBackground, 1 / formulaCalculator.getStopaPrzybyc(), textChecker.getfloatNumber(sd_klientow_text_field.getText()), 1);
		if (minuta)
			handler = new Handler(speedMultiplier, kasyAnimation, iloscKas, anchorBackground, (1 / formulaCalculator.getStopaPrzybyc())*60, textChecker.getfloatNumber(sd_klientow_text_field.getText())*60, 60);
		if (godzina) 
			handler = new Handler(speedMultiplier, kasyAnimation, iloscKas, anchorBackground, (1 / formulaCalculator.getStopaPrzybyc())*3600, textChecker.getfloatNumber(sd_klientow_text_field.getText())*3600, 3600);
		
		zegar = new Zegar(timer, speedMultiplier);
		
		zegar.startZegar();
		handler.spawnKlient();
		handler.checkKlients();
	}
	public void pressedStop(ActionEvent e) {
		stopAnimation();
	}
	public void pressedDecreaseSpeed(ActionEvent e) {
		stopAnimation();
		if (speedMultiplier == 1) {
			speedMultiplier = 0.5f;
			insertTextToElement(speedDecreaserbtn, "x0.5");
			speedDecreaserbtn.setDisable(true);
			insertTextToElement(speedIncreaserbtn, "x1");
			return;
		}
		if (speedMultiplier == 2) {
			speedMultiplier = 1f;
			insertTextToElement(speedDecreaserbtn, "x0.5");
			insertTextToElement(speedIncreaserbtn, "x2");
			return;
		}
		if (speedMultiplier == 3) {
			speedMultiplier = 2f;
			insertTextToElement(speedDecreaserbtn, "x1");
			insertTextToElement(speedIncreaserbtn, "x3");
			return;
		}
		if (speedMultiplier == 4) {
			speedMultiplier = 3f;
			insertTextToElement(speedDecreaserbtn, "x2");
			insertTextToElement(speedIncreaserbtn, "x4");
			return;
		}
		if (speedMultiplier == 5) {
			speedMultiplier = 4f;
			insertTextToElement(speedDecreaserbtn, "x3");
			speedIncreaserbtn.setDisable(false);
			insertTextToElement(speedIncreaserbtn, "x5");
			return;
		}
	}
	public void pressedIncreaseSpeed(ActionEvent e) {
		stopAnimation();
		if (speedMultiplier == 0.5) {
			speedMultiplier = 1f;
			insertTextToElement(speedDecreaserbtn, "x0.5");
			speedDecreaserbtn.setDisable(false);
			insertTextToElement(speedIncreaserbtn, "x2");
			return;
		}
		if (speedMultiplier == 1) {
			speedMultiplier = 2f;
			insertTextToElement(speedDecreaserbtn, "x1");
			insertTextToElement(speedIncreaserbtn, "x3");
			return;
		}
		if (speedMultiplier == 2) {
			speedMultiplier = 3f;
			insertTextToElement(speedDecreaserbtn, "x2");
			insertTextToElement(speedIncreaserbtn, "x4");
			return;
		}
		if (speedMultiplier == 3) {
			speedMultiplier = 4f;
			insertTextToElement(speedDecreaserbtn, "x3");
			insertTextToElement(speedIncreaserbtn, "x5");
			return;
		}
		if (speedMultiplier == 4) {
			speedMultiplier = 5f;
			insertTextToElement(speedDecreaserbtn, "x4");
			speedIncreaserbtn.setDisable(true);
			insertTextToElement(speedIncreaserbtn, "x5");
			return;
		}
	}

	public void pressedZamknij_Menu(ActionEvent e) {
		Platform.exit();
	}
	public void pressedWyczyscWyniki_Menu(ActionEvent e) {
		stopAnimation();
		clearWyniki();
		clearBledy();
	}
	public void pressedWyczyscWszystko_Menu(ActionEvent e) {
		stopAnimation();
		clearWyniki();
		clearBledy();
		clearInsertTextFields();
		
		speedMultiplier = 1f;
		insertTextToElement(speedDecreaserbtn, "x0.5");
		insertTextToElement(speedIncreaserbtn, "x2");
		speedDecreaserbtn.setDisable(false);
		speedIncreaserbtn.setDisable(false);
	}
	public void pressedDokumentacja_Menu(ActionEvent e) {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Documentation.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.setTitle("Symulator kolejki | Dokumentacja");
			
			Main.setIcons(primaryStage);
			
			primaryStage.initModality(Modality.WINDOW_MODAL);
			primaryStage.initOwner(Main.primaryStage);
			primaryStage.show();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	}
	public void pressedOProgramie_Menu(ActionEvent e) {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/AboutProgramm.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();
			primaryStage.setTitle("Symulator kolejki | O programie");
			
			Main.setIcons(primaryStage);
			
			primaryStage.initModality(Modality.WINDOW_MODAL);
			primaryStage.initOwner(Main.primaryStage);
			primaryStage.show();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
