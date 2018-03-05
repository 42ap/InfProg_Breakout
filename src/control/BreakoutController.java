package control;
 
import java.util.ArrayList;

import acm.program.GraphicsProgram;
import model.GameModel;
import model.Helper;
import view.GameView;
import view.MainmenuView;
import view.Renderable;
import view.renderer.DefaultRenderer;
import view.renderer.LighthouseRenderer;
import view.renderer.Renderer;
 
 
@SuppressWarnings("serial")
public class BreakoutController extends GraphicsProgram {
   
	private static final int HEIGHT = 280;
	private static final int FPS = 60;
	private static final double ASPECTRATIO = 2;
	
	private static double GAMESTART;
	
    private GameModel game;
    private Renderable view;
    private ArrayList<Renderer> renderer;
   
    
   
    public void init() {
        // Initialize fields
        setSize((int)(HEIGHT*ASPECTRATIO), HEIGHT);
        setName("BREAKOUT - Marek Hummel / Alexander Pluska");
        
        game = new GameModel(ASPECTRATIO);
        view = new MainmenuView();
        
        renderer = new ArrayList<Renderer>();
        renderer.add(new DefaultRenderer(view, this.getGCanvas()));
        renderer.add(new LighthouseRenderer(null));
    }
   
   
   
    public void run() {
    	// Stall until window is ready
        while (!isStarted()) { }
        GAMESTART = System.nanoTime();
       
        // Game Loop
        double maxFrameTime = 1000.0 / FPS;
        double lastFrameTime = 0;
        while (true)
        {        	
            // Update and render
            double start = System.nanoTime();
            update(lastFrameTime);        
            for (Renderer r : renderer)
            	r.render();
           
           
            // Check aspect ratio
            if (this.getWidth() != this.getHeight() / ASPECTRATIO )
                this.setSize(this.getWidth(), (int)(this.getWidth()/ASPECTRATIO));
           
           
            // Pause so we don't exceed max FPS
            double elapsed = System.nanoTime() - start;
            double pauseTime = maxFrameTime - elapsed/1000/1000;
            pause(Helper.clamp(pauseTime,  0,  Double.MAX_VALUE));
           
            // Save last time to have a game speed independent of fps
            lastFrameTime = (System.nanoTime() - start) / 1000 / 1000;
        }
    }
 
   
   
    private void update(double lastFrameTime) {
    	// change view and start game (after 4 secs)
    	if (view.title().equals("MAIN") && System.nanoTime() - GAMESTART > 3_000_000_000L) {
    		view = new GameView(game);
            for (Renderer r : renderer)
            	r.setView(view);
            game.start();
    	}
    	
    	// Update game and view
        double mouseX = java.awt.MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX();
        double mouseY = java.awt.MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY(); 
        
        game.update(lastFrameTime, mouseX / this.getWidth());
        view.update(mouseX, mouseY, System.nanoTime() - GAMESTART);
    }
   
}