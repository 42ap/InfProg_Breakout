package view;


import java.awt.Font;
import java.util.TreeMap;

import acm.graphics.GLabel;
import acm.graphics.GObject;



/**
 * View of the start sequence (the countdown).
 */
public class StartView implements View {

	// ***** FIELDS *****
	int countdown = 3;
	
	
	
	
	// ***** MAIN METHODS *****
	
	
	/**
	 * Updates the view, meaning updating the counter
	 * @param runtime Current runtime of the animation
	 */
	@Override
	public void update(double runtime) {
		countdown = 3 - (int)(runtime / 1000 / 1000 / 1000);
	}
	
	
	/**
	 * Converts this view into displayable GObjects.
	 * @param cvsWidth The width of the canvas
	 * @param cvsHeight The height of the canvas
	 */
	@Override
	public TreeMap<String, GObject> toGObjects(int cvsWidth, int cvsHeight) {
		TreeMap<String, GObject> objects = new TreeMap<String, GObject>();
		
		GLabel label = new GLabel("" + countdown, cvsWidth / 2, cvsHeight / 2);
		label.setFont(new Font("Arial", 1, 30));
		objects.put("Label", label);
		
		return objects;
	}

	/**
	 * Returns the title of this view.
	 */
	@Override
	public String title() {
		return "MAIN";
	}




}
