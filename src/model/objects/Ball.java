package model.objects;

import java.util.ArrayList;
import acm.graphics.GOval;
import model.Vector;

public class Ball extends GameObject {

	double radius;
	Vector velocity;
	public ArrayList<Vector> contactPoints;

	public Ball(Vector center, double radius, Vector velocity) {
		super.center = center;
		this.radius = radius;
		this.velocity = velocity;
		
		this.contactPoints = new ArrayList<Vector>();
		for (int i = 0; i < 8; i++)
			this.contactPoints
					.add(center.plus(new Vector(Math.cos(Math.PI / 4 * i), Math.sin(Math.PI / 4 * i)).times(radius)));
		
		for (Vector point : contactPoints)
			System.out.println(Double.toString(point.getX()) + " " + Double.toString(point.getY()));
	}

	@Override
	public GOval toGObject() {
		return new GOval(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);
	}

	
	public void update() {
		center.add(velocity);
		for (Vector point : contactPoints) {
			point.add(velocity);
		}
	}

}
