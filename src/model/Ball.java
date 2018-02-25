package model;

import java.util.ArrayList;
import acm.graphics.GOval;

class Ball extends GameObject {

	double radius;
	Vector velocity;
	ArrayList<Vector> contactPoints;

	Ball(Vector center, double radius, Vector velocity) {
		super.center = center;
		this.radius = radius;
		this.velocity = velocity;
		this.contactPoints = new ArrayList<Vector>();
		for (int i = 0; i < 8; i++)
			this.contactPoints
					.add(center.plus(new Vector(Math.cos(Math.PI / 4 * i), Math.sin(Math.PI / 4 * i)).times(radius)));
		for (Vector point : contactPoints)
			System.out.println(Double.toString(point._1) + " " + Double.toString(point._2));
	}

	@Override
	GOval toGObject() {
		return new GOval(super.center._1 - radius, super.center._2 - radius, 2 * radius, 2 * radius);
	}

	void move() {
		center.add(velocity);
		for (Vector point : contactPoints) {
			point.add(velocity);
		}
	}

}
