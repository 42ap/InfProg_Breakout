package view;

import acm.graphics.*;
import model.Game;

public class GObjectView {
	public static GCompound show(Game gamestate) {
		GCompound result = new GCompound();
		for (GObject object : gamestate.toGObjects())
			result.add(object);
		return result;
	}
}
