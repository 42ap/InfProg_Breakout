package control;

import acm.program.GraphicsProgram;
import model.GameModel;
import view.DefaultRenderer;
import view.Renderer;


@SuppressWarnings("serial")
public class BreakoutController extends GraphicsProgram {
	
	private GameModel game;
	private Renderer renderer;
	
	private double mouseX;
	
	
	public void run() {
		// Init
		setSize(600, 600);
		game = new GameModel();
		renderer = new DefaultRenderer(this);
		
		
		
		// Game Loop
		double maxFrameTime = 1000.0 / 60;
		double lastFrameTime = 0;
		while (game.isRunning)
		{
			// Update and render
			double start = System.nanoTime();
			update(lastFrameTime);
			renderer.render(game);
			
			
			
			// Pause so we don't exceed max FPS
			double elapsed = System.nanoTime() - start;
			double pauseTime = maxFrameTime - elapsed/1000/1000;
			pause(pauseTime > 0 ? pauseTime : 0);
			
			// Save last time to have game speed independent of fps
			lastFrameTime = (System.nanoTime() - start) / 1000 / 1000;
		}
	}

	
	
	private void update(double lastFrameTime) {	
		mouseX = java.awt.MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX();
		game.update(lastFrameTime, mouseX / this.getWidth());
	}
	
}