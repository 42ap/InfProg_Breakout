package model;

/**
 * 2-Dimensional real valued Vector.
 * 
 * @author Alexander Pluska
 *
 */
class Vector {
	protected double _1, _2;

	/**
	 * Constructs a new Vector with components _1 and _2.
	 * 
	 * @param _1
	 *            first component of the Vector
	 * @param _2
	 *            second component of the Vector
	 */
	Vector(double x, double y) {
		this._1 = x;
		this._2 = y;
	}

	double abs() {
		return Math.sqrt(_1 * _1 + _2 * _2);
	}

	void add(Vector v) {
		this._1 += v._1;
		this._2 += v._2;
	}

	Vector plus(Vector v) {
		return new Vector(this._1 + v._1, this._2 + v._2);
	}

	Vector minus(Vector v) {
		return new Vector(this._1 - v._1, this._2 - v._2);
	}

	void mult(double c) {
		this._1 *= c;
		this._2 *= c;
	}

	Vector times(double c) {
		return new Vector(c * this._1, c * this._2);
	}
}
