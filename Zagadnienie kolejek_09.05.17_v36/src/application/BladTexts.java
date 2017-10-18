package application;

public enum BladTexts {

	NoLiczbaRzeczywista("Proszę wpisać liczbę większą od 0"),
	NoLiczbaRzeczywista0("Proszę wpisać liczbę większą bądź równą 0"),
	NoLiczbaNaturalna("Proszę wpisać liczbę całkowitą większą od 0"),
	NoLiczbaNaturalna0("Proszę wpisać liczbę całkowitą większą bądź równą 0"),
	
	NumerKasy(" do kasy numer "),
	NoJednakoweKasy("Czasy kas powinne być równe"),
	
	WarunekStabilnosciNeutr("Warunek stabilności"),
	WarunekStabilnosciPos("Warunek stabilności jest spełniony"),
	WarunekStabilnosciNeg("Warunek stabilności nie jest spełniony"),
	BrakStabilnosci("brak stabilności"),
	
	NiemozliwyPrzedzial("Niepoprawny przedział"),
	
	NiepoprawnaLiczbaKas("Powinno być 12 albo mniej punktów obsługi"),
	
	ZaCzestoKlientow("Klient powinien przychodzić nie częściej niż raz w 5 sekund"),
	ZaSzybkaKasa("Kasa nie powinna obsługiwać klienta mniej niż 5 sekund");
	
	private String value;
	
	BladTexts(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
