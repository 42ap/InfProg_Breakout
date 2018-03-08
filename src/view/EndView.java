package view;

import java.awt.Color;
import java.util.TreeMap;

import acm.graphics.GRect;
import acm.graphics.GObject;

/**
 * View of the end sequence (the rainbow).
 */
public class EndView implements View {
	
	// ***** FIELDS *****
	float offset = 0;
	

	
	
	// ***** MAIN METHODS *****

	/**
	 * Updates the view, meaning updating the offset of the rainbow.
	 * @param runtime Current runtime of the animation
	 */
	@Override
	public void update(double runtime) {
		offset += 0.2;
	}

	
	/**
	 * Converts this view into displayable GObjects.
	 * @param cvsWidth The width of the canvas
	 * @param cvsHeight The height of the canvas
	 */
	@Override
	public TreeMap<String, GObject>  toGObjects(int cvsWidth, int cvsHeight) {	
		TreeMap<String, GObject>  list = new TreeMap<String, GObject>();
		
		for (int i = 0; i < cvsHeight; i++) {
			GRect bar = new GRect(0, i, cvsWidth, 1);
			bar.setColor(Color.getHSBColor((i + offset) / (float)cvsHeight, 1f, 1f));
			list.put("LINE" + i, bar);
		}
		
	
		return list;
	}
	
	
	/**
	 * Returns the title of this view.
	 */
	@Override
	public String title() {
		return "END";
	}
}
