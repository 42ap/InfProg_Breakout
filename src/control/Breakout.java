package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import model.Game;
import view.DefaultRenderer;
import view.Renderer;

@SuppressWarnings("serial")
public class Breakout extends GraphicsProgram {
	static int mouseX;
	private final static int xDim = 600, yDim = 400;
	static boolean isRunning;

	public void run() {
		setSize(xDim, yDim);
		Game game = new Game(xDim, yDim);
		Renderer renderer = new DefaultRenderer(this);
		
		
		GRect frame = new GRect(xDim, yDim);
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
			}
		});
		add(frame);
		
		
		
	/*	isRunning = true;
		ArrayList<Double> frameTimes = new ArrayList<Double>();
		while (isRunning)
		{
		  update(game);
		  render(renderer, game, frame);
		}*/

		
		

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		while (true) {
			if (i % 5 == 0) {
				renderer.render(game);
				if(game.isWon() || game.isLost())
					break;
			}
			game.update(mouseX);
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
			i = i % 5;
		}
		
		
		
		

	}
	
	
	
	private void update(Game game) {
		game.update(mouseX);
		isRunning = !(game.isWon() || game.isLost());
	}
	
	
	
	private void render(Renderer renderer, Game game, GObject frame) {
		renderer.render(game);
	}
	
}