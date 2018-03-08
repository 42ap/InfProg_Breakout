package model;

/**
 * 2-Dimensional real valued Vector.
 */
public class Vector {
	
	// ***** FIELDS *****
	public double x, y;
	
	
	
	/**
	 * Constructs a new Vector.
	 * @param x X part of the vector
	 * @param y Y part of the vector
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	
	
	
	
	
	// ***** MAIN METHODS *****
	
	
	/**
	 * Returns the absolute value (the length) of the vector
	 * @return
	 */
	public double abs() {
		return Math.sqrt(x * x + y * y);
	}
	
	
	/**
	 * Returns the distance between two vectors (interpreted as points) squared(!).
	 * @param first The first vector
	 * @param second The second vector
	 * @return 
	 */
	public static double distSqr(Vector first, Vector second) {
		Vector delta = plus(first, times(second, -1));
		return delta.x*delta.x + delta.y*delta.y;
	}
	
	/**
	 * Calculates the dot product of two vectors.
	 * @param one First vector
	 * @param two Second vector
	 * @return 
	 */
	public static double dotP(Vector one, Vector two) {
		return one.x * two.x + one.y * two.y;
	}
	
	
	
	// ***** CALCULATION *****
	
	
	/**
	 * Adds vector to this instance.
	 * @param v Vector to be added.
	 */
	public void add(Vector v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	/**
	 * Multiplies this vector by a scalar.
	 * @param c The scalar.
	 */
	public void mult(double c) {
		this.x *= c;
		this.y *= c;
	}
	

	
	// ***** STATIC METHODS *****
	
	/**
	 * Adds two vectors.
	 * @return
	 */
	public static Vector plus(Vector v1, Vector v2) {
		return new Vector(v1.x + v2.x, v1.y + v2.y);
	}

	
	
	/**
	 * Scales one vector.
	 * @param v The scalar
	 * @return
	 */
	public static Vector times(Vector v, double c) {
		return new Vector(c * v.x, c * v.y);
	}	
}
