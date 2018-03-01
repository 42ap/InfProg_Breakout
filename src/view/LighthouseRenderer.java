package view;

import java.io.IOException;
import java.util.ArrayList;

import acm.graphics.GObject;
import model.GameModel;
import view.LightHouseAPI.LighthouseDisplay;

public class LighthouseRenderer implements Renderer {
	
	LighthouseDisplay display;
	
	public LighthouseRenderer() {
		display = new LighthouseDisplay("be42ap", "API-TOK_15X9-H9yq-9LJF-Y5c/-5+cI");
		// Try connecting to the display
		try {
		    display.connect();
		} catch (Exception e) {
		    System.out.println("Connection failed: " + e.getMessage());
		    e.printStackTrace();
		}
	}
	
	
	public void render(GameModel game) {
		
		byte[] data = new byte[14 * 28 * 3];
		
		ArrayList<GObject> entities = new ArrayList<GObject>();
		for (GObject object : game.toGObjects(28, 14)) {
			entities.add(object);
		}
		
		for(GObject object : entities)
			for(int i = 0; i < object.getWidth(); i++)
				for(int j = 0; j < object.getHeight(); j++)
					data[3*(((int)object.getX() + i) + 28*((int)object.getY() + j))] = (byte) 255;
		
		// Send data to the display
		try {
		    display.send(data);
		} catch (IOException e) {
		    System.out.println("Connection failed: " + e.getMessage());
		    e.printStackTrace();
		}
	}
}