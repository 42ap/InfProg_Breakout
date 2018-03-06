package model;

public class Helper {

	public static double clamp(double val, double low, double high) {
		if (val < low)
			return low;
		else if (val > high)
			return high;
		
		return val;
	}
	
	
	public static boolean isBetween(double val, double low, double high) {
		return val <= high && val >= low;
	}
	
	
	
	public static double map(double val, double oldlow, double oldhigh, double newlow, double newhigh) {
        double scale = (newhigh - newlow) / (oldhigh - oldlow);
        return (val  - oldlow) * scale + newlow;
	}
}
