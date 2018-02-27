package model.objects;

public class Brick extends Rectangle {
	
	public Brick(double xPos, double yPos, double width, double height) {
		super(xPos, yPos, width, height);
		super.collisionEvent = CollisionEvent.REMOVE;
	}
}