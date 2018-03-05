package view;

import java.util.ArrayList;
import acm.graphics.GObject;

public interface Renderable {
	public String title();
	
	public void update(double mouseX, double mouseY, double runtime);
	
	public ArrayList<GObject> toGObjects(double cvsWidth, double cvsHeight);
}
