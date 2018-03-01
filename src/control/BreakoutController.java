package control;
 
import acm.program.GraphicsProgram;
import model.GameModel;
import view.DefaultRenderer;
import view.LighthouseRenderer;
import view.Renderer;
 
 
@SuppressWarnings("serial")
public class BreakoutController extends GraphicsProgram {
   
    private GameModel game;
    private Renderer grenderer;
    private Renderer lrenderer;
   
    private double mouseX;
    private double aspectRatio = 2;
   
    public void init() {
        // Init
        setSize((int)(280*aspectRatio), 280);
        game = new GameModel(aspectRatio);
        grenderer = new DefaultRenderer(this.getGCanvas());
        lrenderer = new LighthouseRenderer();
    }
   
   
   
    public void run() {
        // Stall until window is ready
        while (!isStarted()) { }
       
       
        // Game Loop
        double maxFrameTime = 1000.0 / 15;
        double lastFrameTime = 0;
        while (game.isRunning)
        {
            // Update and render
            double start = System.nanoTime();
            update(lastFrameTime);
            grenderer.render(game);
            lrenderer.render(game);
           
           
            // Check aspect ratio
            if (this.getWidth() != this.getHeight() / aspectRatio )
                this.setSize(this.getWidth(), (int)(this.getWidth()/aspectRatio));
           
           
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