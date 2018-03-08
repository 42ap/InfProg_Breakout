package view.renderer;

import java.util.HashMap;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import model.GameModel;
import view.View;

/**
 * Default renderer, renders to monitor.
 */
public class DefaultRenderer implements Renderer {
	
	// ***** FIELDS *****
	
	private GCanvas canvas;
	private View view;
	private GCompound last;
	
	
	
	
	
	/**
	 * Constructor.
	 * @param view The view to be rendered
	 * @param canvas The canvas to render on.
	 */
	public DefaultRenderer(View view, GCanvas canvas) {
		this.canvas = canvas;
		this.view = view;
		last = new GCompound();
	}
	
	
	/**
	 * Update the view.
	 */
	public void setView(View view) {
		this.view = view;
	}
	
	
	/**
	 * Render the view to the canvas
	 */
	public void render() {		
		// Create and add new one compound
		GCompound entities = new GCompound();
		HashMap<String, GObject> gobjects = view.toGObjects(canvas.getWidth(), canvas.getHeight());
		for (GObject object : gobjects.values()) {
			entities.add(object);
		}
		
		// Removing last compound, add new one
		canvas.remove(last);
		canvas.add(entities);
		
		last = entities;
	}
}
