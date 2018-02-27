package model;

/**
 * 2-Dimensional real valued Vector.
 * 
 * @author Alexander Pluska
 *
 */
public class Vector {
	protected double x, y;

	public double getX() { return this.x; }
	public double getY() { return this.y; }
	
	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y; }
	
	
	
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
