package application;

import java.util.Random;

public class RandomTimeGenerator {

	Random r;
	
	public float generateTime(float t, float sd) {
		r = new Random();
		
		if (sd != 0) sd *= r.nextFloat();
		
		if ( r.nextBoolean() ) t -= sd; else t += sd;
		
		t = clampTime(t);
		
		return t;
	}	
	
	private float clampTime(float t) {
		if (t < 5) return 5;
		return t;
	}
	
}
