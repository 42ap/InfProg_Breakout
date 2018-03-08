package view.renderer;

import view.View;

/**
 * Interface the renderers have to implement
 */
public interface Renderer {
	// The view to be rendered
	View view = null;
	
	// Set new view
	public void setView(View view);
	
	// Render
	public void render();
}
