package model.objects;

import java.util.Collection;

import model.Vector;

/**
 * All objects that may collide with the Ball must implement this Interface.
 * 
 * @author Alexander Pluska
 *
 */
public abstract class Collidable extends GameObject {
	CollisionEvent collisionEvent;

	public void onCollision(Vector impactPosition, Ball ball, Collection<Collidable> objects) {
		switch (this.collisionEvent) {
			case REMOVE:
				objects.remove(this);
			case DEFLECT:
				this.deflect(impactPosition, ball);
			case NOTHING:
		}
	}

	public abstract boolean contains(Vector point);

	abstract void deflect(Vector impactPosition, Ball ball);
}

enum CollisionEvent {
	NOTHING,
	REMOVE,
	DEFLECT;
}