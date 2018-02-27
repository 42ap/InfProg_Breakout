package model;

import java.util.ArrayList;
import model.objects.*;
import acm.graphics.*;


public class Game {
	protected Ball ball;
	protected Rectangle bounds;
	protected Paddle paddle;
	protected ArrayList<Collidable> bricks;

	public Game(double width, double height) {
		ball = new Ball(new Vector(10, 10), 10,  new Vector(0.4, 0.3));
		bounds = new Bounds(0, 0, width, height);
		paddle = new Paddle(0.5 * width, 0.9 * height, 0.2*width, height / 30, 0.5);
		
		bricks = new ArrayList<Collidable>();
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7 - i; j++)
				bricks.add(new Brick(width / 16 * (i + 1.5) + width * j / 8, 25 + i * 25, width / 12, height / 16));
	}

	public void update(int mouseX) {
		paddle.update(mouseX);
		ball.update();
		ArrayList<Collidable> hit = new ArrayList<Collidable>();
		for(Vector point : ball.contactPoints) {
			if(!bounds.contains(point))
				hit.add(bounds);
			if(paddle.contains(point))
				hit.add(paddle);
			for(Collidable obstacle : bricks)
				if(obstacle.contains(point))
					hit.add(obstacle);
			
			if(!hit.isEmpty()) {
				for(Collidable obstacle : hit)
					obstacle.onCollision(point, ball, bricks);
				break;
			}
		}
		
	}

	public ArrayList<GObject> toGObjects() {
		ArrayList<GObject> result = new ArrayList<GObject>();
		result.add(ball.toGObject());
		result.add(paddle.toGObject());
		for (GameObject obstacle : bricks)
			result.add(obstacle.toGObject());
		return result;
	}
	
	public boolean isWon() {
		return bricks.isEmpty();
	}
	
	public boolean isLost() {
		return ball.getCenter().y > bounds.getCenter().y + 9 * bounds.halfheight / 10;
	}

}
