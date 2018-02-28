package model;

/**
 * 2-Dimensional real valued Vector.
 * 
 * @author Alexander Pluska
 *
 */
public class Vector {
	public double x, y;
	
	
	
	/**
	 * Constructs a new Vector with components _1 and _2.
	 * 
	 * @param x
	 *            first component of the Vector
	 * @param y
	 *            second component of the Vector
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double abs() {
		return Math.sqrt(x * x + y * y);
	}
	
	
	public static double distSqr(Vector first, Vector second) {
		Vector delta = first.minus(second);
		return delta.x*delta.x + delta.y*delta.y;
	}
	
	public static double dotP(Vector one, Vector two) {
		return one.x * two.x + one.y * two.y;
	}
	
	
	

	
	public void add(Vector v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	public void mult(double c) {
		this.x *= c;
		this.y *= c;
	}
	

	public Vector plus(Vector v) {
		return new Vector(this.x + v.x, this.y + v.y);
	}

	public Vector minus(Vector v) {
		return new Vector(this.x - v.x, this.y - v.y);
	}

	public Vector times(double c) {
		return new Vector(c * this.x, c * this.y);
	}
	
	
	
}
