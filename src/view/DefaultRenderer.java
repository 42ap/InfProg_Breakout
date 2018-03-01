package view;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import model.GameModel;

public class DefaultRenderer implements Renderer {
	
	GCanvas canvas;
	private GCompound last;
	
	public DefaultRenderer(GCanvas canvas) {
		this.canvas = canvas;
		last = new GCompound();
	}
	
	
	public void render(GameModel game) {		
		canvas.remove(last);
		
		GCompound entities = new GCompound();
		for (GObject object : game.toGObjects(canvas.getWidth(), canvas.getHeight())) {
			entities.add(object);
		}
		canvas.add(entities);
		
		last = entities;
	}
}
