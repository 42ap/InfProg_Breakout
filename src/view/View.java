package view;


import java.util.TreeMap;
import acm.graphics.GObject;

/**
 * Interface which describes a possible view which can be rendered
 */
public interface View {
	
	// The name of the view
	public String title();
	
	// Updating
	public void update(double runtime);
	
	// To GObjects for the Renderer, TreeMap to keep things sorted
	public TreeMap<String, GObject> toGObjects(int cvsWidth, int cvsHeight);
}
