package view.windowelement;

import acm.graphics.GCompound;
import model.Vector;

public interface WindowElement {
	public GCompound toGCompound(Vector scale);
}
