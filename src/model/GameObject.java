package model;

import acm.graphics.GObject;

public abstract class GameObject {
	Vector center;
	abstract GObject toGObject();
}
