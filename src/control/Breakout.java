package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import acm.graphics.GCompound;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import model.Game;

@SuppressWarnings("serial")
public class Breakout extends GraphicsProgram {
	static int mouseX;
	final static int xDim = 600, yDim = 400;

	public void run() {
		setSize(xDim, yDim);
		Game game = new Game(xDim, yDim);
		GRect frame = new GRect(xDim, yDim);

		frame.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
			}
		});

		add(frame);
		GCompound state = new GCompound();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int i = 0;
		while (true) {
			if (i % 5 == 0) {
				remove(state);
				state = view.GObjectView.show(game);
				add(state);
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
}