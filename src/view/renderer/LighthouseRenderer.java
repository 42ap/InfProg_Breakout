package view.renderer;

import java.io.IOException;
import java.util.ArrayList;

import acm.graphics.GObject;
import model.GameModel;
import view.Renderable;
import view.renderer.LightHouseAPI.LighthouseDisplay;

public class LighthouseRenderer implements Renderer {
	
	private Renderable view;
	LighthouseDisplay display;
	
	public LighthouseRenderer(Renderable view) {
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
	
	
	public void setView(Renderable view) {
		this.view = view;
	}
	
	
	
	public void render() {	
		// Fill data
		byte[] data = new byte[14 * 28 * 3];
				
		if (view != null) {
			for(GObject object : view.toGObjects(28, 14)) {
				for(int i = 0; i < object.getWidth(); i++) {
					for(int j = 0; j < object.getHeight(); j++) {
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