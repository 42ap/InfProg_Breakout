package control;

import java.util.ArrayList;
import java.util.HashMap;

import acm.program.GraphicsProgram;
import model.*;
import view.*;
import view.renderer.*;



/**
 * Main entry point of this program. Class which watches the model and view and
 * controls communication between them.
 */
@SuppressWarnings("serial")
public class BreakoutController extends GraphicsProgram {

	// ***** STATIC FIELDS ****

	private static final int HEIGHT = 280;
	private static final int FPS = 60;
	private static final double ASPECTRATIO = 2;
	private static final boolean STREAM_TO_LIGHTHOUSE = false;

	
	private static double GAMESTART;

	
	
	
	
	// ***** FIELDS *****

	private GameModel game;
	private View view;
	private ArrayList<Renderer> renderer;

	
	
	
	
	/**
	 * Initalizes the program, i.d. setting the size and initializing the
	 * game/view/renderer
	 */
	public void init() {
		setSize((int) (HEIGHT * ASPECTRATIO), HEIGHT);
		setTitle("BREAKOUT - Marek Hummel / Alexander Pluska");

		game = new GameModel(ASPECTRATIO);
        view = new GameView(game);
        game.start();
        
        renderer = new ArrayList<Renderer>();
        renderer.add(new DefaultRenderer(view, this.getGCanvas()));
		if (STREAM_TO_LIGHTHOUSE)
			renderer.add(new LighthouseRenderer(view));
	}
	
	
	
	

	/**
	 * Run method, runs for as long as program is active.
	 */
	public void run() {
		// Stall until window is ready
		while (!isStarted()) {
		}
		GAMESTART = System.nanoTime();

		// Game Loop
		double maxFrameTime = 1000.0 / FPS;
		double lastFrameTime = 0;
		while (true) {
			// Update and render
			double start = System.nanoTime();
			update(lastFrameTime);
			for (Renderer r : renderer)
				r.render();

			// Check aspect ratio
			if (this.getWidth() != this.getHeight() / ASPECTRATIO)
				this.setSize(this.getWidth(), (int) (this.getWidth() / ASPECTRATIO));

			// Pause so we don't exceed max FPS
			double elapsed = System.nanoTime() - start;
			double pauseTime = maxFrameTime - elapsed / 1000 / 1000;
			pause(Helper.clamp(pauseTime, 0, Double.MAX_VALUE));

			// Save last time to have a game speed independent of fps
			lastFrameTime = (System.nanoTime() - start) / 1000 / 1000;
		}
	}

	
	
	
	
	/**
	 * Out sourced update part of the game loop, updates the view object and the
	 * game and view itself
	 * 
	 * @param lastFrameTime
	 */
	private void update(double lastFrameTime) {
		// Change view if necessary
		updateViewObject();

		// Update game and view
		double mouseX = java.awt.MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX();
		double mouseY = java.awt.MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY();

		game.update(lastFrameTime, mouseX / this.getWidth());
		view.update(mouseX, mouseY, System.nanoTime() - GAMESTART);
	}

	
	
	
	
	/**
	 * Updates the view object, meaning changing it if necessary.
	 */
	private void updateViewObject() {
		// change view and start game (after 3 secs)
		if (view.title().equals("MAIN") && System.nanoTime() - GAMESTART > 3_000_000_000L) {
			view = new GameView(game);
			for (Renderer r : renderer)
				r.setView(view);
			game.start();
		}

		// change view if game is won
		if (view.title().equals("GAME") && !game.isRunning) {
			view = new EndView();
			for (Renderer r : renderer)
				r.setView(view);
		}
	}

}