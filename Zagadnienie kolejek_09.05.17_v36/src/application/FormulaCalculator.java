package application;

import javafx.scene.control.TextField;

public class FormulaCalculator {

	private float stopaPrzybyc;

	private float stopaObslugi;
	private float stopaObslugi_mi;
	
	private float przecietnaLiczbaKlientowWKolejce;
	
	private float p0;
	
	private long factorial(int i) {
		if (i==0) return 1;
		return i*factorial(i-1);
	}

	public float computeStopaPrzybyc_1_1(float klienci, float czas) {
		stopaPrzybyc = klienci / czas;
		
		//System.out.println("############################");
		//System.out.println("<<Stopa Przybyc>>");
		//System.out.println("<<Stopa Przybyc>>");
		//System.out.println("klienci: " + klienci);
		//System.out.println("czas: " + czas);
		//System.out.println("stopaPrzybyc = klienci / czas: " + klienci / czas);
		//System.out.println("stopaPrzybyc: " + stopaPrzybyc + "\n");
		
		return stopaPrzybyc;
	}
	public float computeStopaObslugi_1_2(int r, TextField t) {
		TextChecker tc = new TextChecker();
		
		stopaObslugi_mi = 1 / tc.getfloatNumber(t.getText());
		stopaObslugi = stopaObslugi_mi * r;
		
		//System.out.println("<<Stopa Obslugi>>");
		//System.out.println("r: " + r);
		//System.out.println("stopaObslugi: " + stopaObslugi);
		//System.out.println("stopaObslugi_mi: " + stopaObslugi_mi + "\n");
		
		return stopaObslugi;
	}

	public boolean checkWarunekStabilnosci_1_3() {
		if ( stopaPrzybyc < stopaObslugi ) { return true; }
		else { return false; }
	}

	public float computeParametrIntensywnosciRuchu() {
		//System.out.println("<<Parametr intensywnosci ruchu>>");
		//System.out.println("Parametr intensywnosci ruchu = stopaPrzybyc / stopaObslugi: " + stopaPrzybyc / stopaObslugi + "\n");
		return stopaPrzybyc / stopaObslugi;
	}
	public float computePrzecietnaLiczbaKlientowWKolejce_2_2(int r) {
		float f1, f2;
		
		f1 = (float)Math.pow(stopaPrzybyc / stopaObslugi_mi, r + 1) * p0;
		f2 = (float)Math.pow(r - stopaPrzybyc / stopaObslugi_mi, 2) * factorial(r - 1);
		
		przecietnaLiczbaKlientowWKolejce = f1 / f2;
		
		return przecietnaLiczbaKlientowWKolejce;
	}
	public float computeOdchylenieStandardoweKlientowWKolejce_2_3(int r) {
		float f;
		
		f = (r * stopaObslugi_mi + stopaPrzybyc) / (r * stopaObslugi_mi - stopaPrzybyc);
		f -= przecietnaLiczbaKlientowWKolejce;
		f *= przecietnaLiczbaKlientowWKolejce;
		
		return (float)Math.sqrt(f);
	}
	
	public void computeP0_3(int r) {
		float f1 = 0;
		float f2;
		
		//System.out.println("<<P0>>");
		//System.out.println("r: " + r);
		
		for ( int i = 0; i <= (r-1); i++) {
			f1 += (1f / (float)factorial(i)) * Math.pow((stopaPrzybyc / stopaObslugi_mi), i);
			//System.out.println("f1_" + i + ": " + f1);
		}
		
		f2 = (float)Math.pow((stopaPrzybyc / stopaObslugi_mi), r) / ( (float)factorial(r-1) * (r - stopaPrzybyc / stopaObslugi_mi) );
		//System.out.println("f2: " + f2);
		
		p0 = 1 / (f1 + f2);
		//System.out.println("p0 = 1 / (f1 + f1): " + (1 / (f1 + f2)) );
		//System.out.println("p0: " + p0);
	}
	
	public float computePrawdopodobienstwoWUkladzie(int r, String b1, String t1, String t2, String b2) {
		int p1, p2, n1, n2;
		TextChecker tc = new TextChecker();
		
		float f1, f2;
		
		if (b1.equals("więcej")) p1 = 0; else p1 = 1; 
		if (b2.equals("mniej")) p2 = 0; else p2 = 1;
		
		n1 = tc.getintNumber(t1);
		if ( t2.equals("inf") ) {
			n2 = -1;
		} else {
			n2 = tc.getintNumber(t2);
		}
		
		if (n1 == n2 && n1 == 0) return computeP_n(r, n1); // [0; 0]
		
		if (n1 == n2) return computeP_n(r, n1); // [n1; n2]
		
		if (n2 == -1 && p1 == 0) return computeP_greater(r, n1); // (n1, inf)
		
		if (n2 == -1 && p1==1) return (computeP_greater(r, n1) + computeP_n(r, n1)); // [n1; inf)
		
		if (p1 == 0 && p2 == 0) { // (n1; n2)
			f1 = 1 - ( computeP_greater(r, n2) + computeP_n(r, n2) );
			f2 = 1 - computeP_greater(r, n1);
			return (f1 - f2);
		}
			
		if (p1 == 1 && p2 == 0) { //[n1; n2)
			f1 = 1 - ( computeP_greater(r, n2) + computeP_n(r, n2) );
			f2 = 1 - ( computeP_greater(r, n1) + computeP_n(r, n1) );
			return (f1 - f2);
		}
		
		if (p1 == 0 && p2 == 1) { // (n1; n2]
			f1 = 1 - computeP_greater(r, n2);
			f2 = 1 - computeP_greater(r, n1);
			return (f1 - f2);
		}
		
		if (p1 == 1 && p2 == 1) { // [n1; n2]
			f1 = 1 - computeP_greater(r, n2);
			f2 = 1 - ( computeP_greater(r, n1) + computeP_n(r, n1) );
			return (f1 - f2);
		}
		
		return -1.1f;
	}
	private float computeP_n(int r, int n) {
		float f1;
		
		if (n < r) {
			if (n == 0) return p0;
			
			f1 = 1f / (float)factorial(n);
			f1 *= Math.pow(stopaPrzybyc / stopaObslugi_mi, n);
			f1 *= p0;
			return f1;
		}
		if (n >= r) {
			f1 = 1f / (float)( factorial(r) * Math.pow(r, n-r) );
			f1 *= Math.pow(stopaPrzybyc / stopaObslugi_mi, n);
			f1 *= p0;
			return f1;
		}
		return -1;
	}
	private float computeP_greater(int r, int n) {
		float f1, f2;
			
		if ( n >= (r - 1) ) {
			//System.out.println(">>P greater Fc");
			f1 = (float)Math.pow(r, r - n);
			f1 *= (float)Math.pow(stopaPrzybyc / stopaObslugi_mi, n + 1);
			f1 *= p0;
			
			f2 = r - stopaPrzybyc / stopaObslugi_mi;
			f2 *= (float)factorial(r);
			
			return f1 / f2;
		}
		
		if ( n < (r - 1) ) {
			//System.out.println(">>P greater Sc");
			f1 = computeP_n(r, n + 1);
			//System.out.println("n: " + n + "\tf1: " + f1);
			f1 += computeP_greater(r, n + 1);
			return f1;
		}
		
		return -1;
	}
	
	public float computePrawdopodobienstwoCzasuStaniaWKolejce(int r, String tx1, String tx2) {
		float t1, t2, f1, f2;
		TextChecker tc = new TextChecker();
		
		t1 = tc.getfloatNumber(tx1);
		if ( tx2.equals("inf") ) {
			t2 = -1;
		} else {
			t2 = tc.getfloatNumber(tx2);
		}
		
		if (t1 == t2) return 0; // [t1, t2]
		
		if (t2 == -1) return copmuteP_t_greater(r, t1); // ... t1, inf) 
		
		if ( (t1 != t2) && t2 != -1 ) { // ... t1, t2 ...
			f1 = copmuteP_t_greater(r, t2);
			f1 = 1 - f1;
			f2 = copmuteP_t_greater(r, t1);
			f2 = 1 - f2;
			
			return f1 - f2;
		}
		
		return -1.1f;
	}
	private float copmuteP_t_greater(int r, float t) {
		float f1, f2;
		
		f1 = computeP_greater(r, r-1);
		f2 = r - stopaPrzybyc / stopaObslugi_mi;
		f2 *= -1 * (stopaObslugi_mi * t);
		
		return f1 * (float)Math.exp(f2);
	}
	
	public float computePrawdopodobienstwoObslugi_n_OsobWCzasie_t_3_3(int r, float t, int n) {
		float f;
		
		f = stopaObslugi_mi * r * t;
		f = (float)Math.pow(f, n);
		f *= (float)Math.exp(-1 * stopaObslugi_mi * r * t);
		
		return f / (float)factorial(n);
	}
	
	public float getStopaObslugi() {
		return stopaObslugi;
	}
	public float getStopaPrzybyc() {
		return stopaPrzybyc;
	}
	public String getSP_SOComparision() {
		if ( stopaPrzybyc > stopaObslugi ) return ">";
		if ( stopaPrzybyc < stopaObslugi ) return "<";
		if ( stopaPrzybyc == stopaObslugi ) return "=";
		return "ERROR";
	}
	
}