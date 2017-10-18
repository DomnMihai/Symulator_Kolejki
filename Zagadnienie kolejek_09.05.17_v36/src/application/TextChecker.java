package application;
/*
 * Check if text meet certain criteria
 */


public class TextChecker {

	public boolean checkIffloatNumber(String s) {
		float f;
		try {
			f = Float.parseFloat(s);
			if (f <= 0) return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public boolean checkIfintNumber(String s) {
		int i;
		try {
			i = Integer.parseInt(s);
			if (i <= 0 ) return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public boolean checkIffloatNumber0(String s) {
		float f;
		try {
			f = Float.parseFloat(s);
			if (f < 0) return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public boolean checkIfintNumber0(String s) {
		int i;
		try {
			i = Integer.parseInt(s);
			if (i < 0 ) return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public float getfloatNumber(String s) {
		return Float.parseFloat(s);
	}
	public int getintNumber(String s) {
		return Integer.parseInt(s);
	}
	
	public String getUnitFormat(boolean s, boolean m, boolean h) {
		if (s) return " kl/sec";
			else if (m) return " kl/min";
				else if (h) return " kl/h";
		return "ERROR";
	}
	
}
