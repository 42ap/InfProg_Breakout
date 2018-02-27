package model.objects;

public class Bounds extends Rectangle {
	public Bounds(double xPos, double yPos, double width, double height) {
		super(xPos, yPos, width, height);
		super.collisionEvent = CollisionEvent.DEFLECT;
	}
}
