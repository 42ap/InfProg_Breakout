package view;

import java.awt.Color;
import java.util.TreeMap;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import model.GameModel;

/**
 * View of the game itself.
 */
public class GameView implements View {
	
	// ***** FIELDS *****
	
	private GameModel game;
	
	
	/**
	 * Constructor needs a game model so it can display something.
	 * @param game
	 */
	public GameView(GameModel game) {
		this.game = game;
	}

	
	// ***** MAIN METHODS *****
	
	
	
	/** 
	 * Update method not needed in this view, everything updates in model.
	 * @param runtime Ignored
	 */
	@Override
	public void update(double runtime) { }
	
	
	/**
	 * Converts this view into displayable GObjects.
	 * @param cvsWidth The width of the canvas
	 * @param cvsHeight The height of the canvas
	 */
	@Override
	public TreeMap<String, GObject> toGObjects(int cvsWidth, int cvsHeight) {
		TreeMap<String, GObject>  result = new TreeMap<String, GObject>();
		
		// Background
		GRect bg = new GRect(0, 0, cvsWidth, cvsHeight);
		bg.setFilled(true);
		bg.setFillColor(Color.BLACK);
		result.put("BACKGROUND", bg);

		// Ball and paddle
		result.put("BALL", game.ball.toGObject(cvsWidth, cvsHeight)); 
		result.put("PADDLE", game.paddle.toGObject(cvsWidth, cvsHeight));
		
		// Bricks
		for (int i = 0; i < game.bricks.size(); i++)
			result.put("BRICK" + i, game.bricks.get(i).toGObject(cvsWidth, cvsHeight));
		
		// Bounds
		GRect bounds = new GRect(1, 1, cvsWidth-2, cvsHeight-2);
		bounds.setColor(Color.WHITE);
		result.put("BOUNDS", bounds);
		
		// Level indicator
		GLabel level = new GLabel("" + (game.currLevel+1), 3, 0.05*cvsHeight);
		level.setColor(Color.WHITE);
		result.put("LEVEL", level);
		
		return result;
	}


	/**
	 * Returns the title of this view.
	 */
	@Override
	public String title() {
		return "GAME";
	}
	
	
}
