package model;


/**
 * Helper class with static methods.
 */
public class Helper {

	/**
	 * Forces value to be between two bounds.
	 * @param val The value
	 * @param low The lower bound
	 * @param high The upper bound
	 * @return The clamped value (old value if already between, otherwise one of the bounds)
	 */
	public static double clamp(double val, double low, double high) {
		if (val < low)
			return low;
		else if (val > high)
			return high;
		
		return val;
	}
	
	
	/**
	 * Checks if value is between two bounds.
	 * @param val The value
	 * @param low The lower bound
	 * @param high The upper bound
	 * @return true if (val <= high && val >= low)
	 */
	public static boolean isBetween(double val, double low, double high) {
		return val <= high && val >= low;
	}
	
	
	/**
	 * Maps value from one range to another.
	 * @param val The value
	 * @param oldlow The old lower bound
	 * @param oldhigh The old upper bound
	 * @param newlow The new lower bound
	 * @param newhigh The new upper bound
	 * @return The mapped value.
	 */
	public static double map(double val, double oldlow, double oldhigh, double newlow, double newhigh) {
        double scale = (newhigh - newlow) / (oldhigh - oldlow);
        return (val  - oldlow) * scale + newlow;
	}
}
