package view.renderer;

import view.Renderable;

public interface Renderer {
	Renderable view = null;
	
	public void setView(Renderable view);
	public void render();
}
