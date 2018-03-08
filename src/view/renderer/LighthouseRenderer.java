package view.renderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import acm.graphics.GObject;
import model.GameModel;
import view.View;
import view.renderer.LightHouseAPI.LighthouseDisplay;

/**
 * A renderer for the lighthouse.
 */
public class LighthouseRenderer implements Renderer {
	
	// ***** FIELDS *****
	
	private View view;
	private LighthouseDisplay display;
	
	
	
	
	/**
	 * Constructor, tries to connect with the lighthouse.
	 * @param view The initial view
	 */
	public LighthouseRenderer(View view) {
		this.view = view;

		// Try connecting to the display
		//display = new LighthouseDisplay("be42ap", "API-TOK_15X9-H9yq-9LJF-Y5c/-5+cI");
		display = new LighthouseDisplay("stu210310", "API-TOK_nbsG-oBel-WVXl-dP5K-0j1x");
		try {
		    display.connect();
		} catch (Exception e) {
		    System.out.println("Connection failed: " + e.getMessage());
		    e.printStackTrace();
		}
	}
	
	
	/**
	 * Set a new view.
	 * @param view The new view
	 */
	public void setView(View view) {
		this.view = view;
	}
	
	
	/**
	 * Render the view to the lighthouse
	 */
	public void render() {	
		// Data array
		byte[] data = new byte[14 * 28 * 3];
				
		// Fill data if view is existent
		if (view != null) {
			
			HashMap<String, GObject> gobjects = view.toGObjects(28, 14);
			for(String key : gobjects.keySet()) {
				if (key.equals("LEVEL") || key.equals("BOUNDS"))
					continue;
				
				// Loop over each pixel of in the current object
				GObject object = gobjects.get(key); 		
				for(int i = 0; i < object.getWidth(); i++) {
					for(int j = 0; j < object.getHeight(); j++) {
						
						// Get index of pixel and set rgb values
						int pixelIndex = ((int)object.getX() + i) + 28*((int)object.getY() + j);
						data[3 * pixelIndex] = (byte)object.getColor().getRed();
						data[3 * pixelIndex + 1] = (byte)object.getColor().getGreen();
						data[3 * pixelIndex + 2] = (byte)object.getColor().getBlue(); 
					}
				}
			}
		}
			
				
		// Send data to the display
		try {
		    display.send(data);
		} catch (IOException e) {
		    System.out.println("Connection failed: " + e.getMessage());
		    e.printStackTrace();
		}
	}
}