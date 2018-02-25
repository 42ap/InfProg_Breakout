package model;

import java.util.Collection;

/**
 * All objects that may collide with the Ball must implement this Interface.
 * 
 * @author Alexander Pluska
 *
 */
abstract class CollisionObject extends GameObject {
	CollisionEvent collisionEvent;

	void collisionEvent(Vector impactPosition, Ball ball, Collection<CollisionObject> objects) {
		switch (this.collisionEvent) {
		case REMOVE:
			objects.remove(this);
		case DEFLECT:
			this.deflect(impactPosition, ball);
		case NOTHING:
		}
	}

	abstract boolean contains(Vector point);

	abstract void deflect(Vector impactPosition, Ball ball);
}

enum CollisionEvent {
	NOTHING,
	REMOVE,
	DEFLECT;
}