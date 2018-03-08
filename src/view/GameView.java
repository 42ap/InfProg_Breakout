package view;

import java.awt.Color;
import java.util.HashMap;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import model.GameModel;
import model.object.Brick;

public class GameView implements View {
	
	private GameModel game;
	
	
	public GameView(GameModel game) {
		this.game = game;
	}

	
	
	@Override
	public void update(double mouseX, double mouseY, double runtime) { }
	
	
	@Override
	public HashMap<String, GObject> toGObjects(int cvsWidth, int cvsHeight) {
		HashMap<String, GObject>  result = new HashMap<String, GObject>();
		
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
		//result.put("BOUNDS", bounds);
		
		// Level indicator
		GLabel level = new GLabel("" + game.currLevel, 0, 0.05*cvsHeight);
		level.setColor(Color.WHITE);
		result.put("LEVEL", level);
		
		return result;
	}



	@Override
	public String title() {
		return "GAME";
	}
	
	
}
