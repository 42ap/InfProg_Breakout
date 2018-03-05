package view.renderer;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import model.GameModel;
import view.Renderable;

public class DefaultRenderer implements Renderer {
	
	private GCanvas canvas;
	private Renderable view;
	private GCompound last;
	
	public DefaultRenderer(Renderable view, GCanvas canvas) {
		this.canvas = canvas;
		this.view = view;
		last = new GCompound();
	}
	
	
	public void setView(Renderable view) {
		this.view = view;
	}
	
	
	public void render() {		
		canvas.remove(last);
		
		GCompound entities = new GCompound();
		for (GObject object : view.toGObjects(canvas.getWidth(), canvas.getHeight())) {
			entities.add(object);
		}
		canvas.add(entities);
		
		last = entities;
	}
}
