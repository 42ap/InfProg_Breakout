package view;

import java.awt.Color;
import java.util.ArrayList;
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
	public ArrayList<GObject> toGObjects(int cvsWidth, int cvsHeight) {
		ArrayList<GObject> result = new ArrayList<GObject>();
		
		
		GRect bg = new GRect(0, 0, cvsWidth, cvsHeight);
		bg.setFilled(true);
		bg.setFillColor(Color.BLACK);
		result.add(bg);

		
		result.add(game.ball.toGObject(cvsWidth, cvsHeight)); 
		result.add(game.paddle.toGObject(cvsWidth, cvsHeight));
		
		for (Brick brick : game.bricks)
			result.add(brick.toGObject(cvsWidth, cvsHeight));
		
		
		return result;
	}



	@Override
	public String title() {
		return "GAME";
	}
	
	
}
