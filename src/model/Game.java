package model;

import java.util.ArrayList;

import acm.graphics.*;

public class Game {
	protected Ball ball;
	protected Rectangle bounds;
	protected Paddle paddle;
	protected ArrayList<CollisionObject> obstacles;

	public Game(double width, double height) {
		ball = new Ball(new Vector(10, 10), 10,  new Vector(0.4, 0.3));
		bounds = new Bounds(0, 0, width, height);
		paddle = new Paddle(0.5 * width, 0.9 * height, 0.2*width, height / 30, 0.5);
		obstacles = new ArrayList<CollisionObject>();
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7 - i; j++)
				obstacles.add(new Brick(width / 16 * (i + 1.5) + width * j / 8, 25 + i * 25, width / 12, height / 16));
	}

	public void update(int mouseX) {
		paddle.move(mouseX);
		ball.move();
		ArrayList<CollisionObject> hit = new ArrayList<CollisionObject>();
		for(Vector point : ball.contactPoints) {
			if(!bounds.contains(point))
				hit.add(bounds);
			if(paddle.contains(point))
				hit.add(paddle);
			for(CollisionObject obstacle : obstacles)
				if(obstacle.contains(point))
					hit.add(obstacle);
			if(!hit.isEmpty()) {
				for(CollisionObject obstacle : hit)
					obstacle.collisionEvent(point, ball, obstacles);
				break;
			}
		}
		
	}

	public ArrayList<GObject> toGObjects() {
		ArrayList<GObject> result = new ArrayList<GObject>();
		result.add(ball.toGObject());
		result.add(paddle.toGObject());
		for (GameObject obstacle : obstacles)
			result.add(obstacle.toGObject());
		return result;
	}
	
	public boolean isWon() {
		return obstacles.isEmpty();
	}
	
	public boolean isLost() {
		return ball.center._2 > bounds.center._2 + 9 * bounds.halfheight / 10;
	}

}
