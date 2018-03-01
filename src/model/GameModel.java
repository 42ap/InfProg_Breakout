package model;

import java.util.ArrayList;
import model.objects.*;
import acm.graphics.*;


public class GameModel {
	
	protected Ball ball;
	protected Bounds bounds;
	protected Paddle paddle;
	protected ArrayList<Brick> bricks;
	
	public boolean isRunning;
	private double aspectRatio;
	
	
	
	public GameModel(double aspectRatio) {
		this.aspectRatio = aspectRatio;
		
		bounds = new Bounds(0, 0, 1, 1/aspectRatio);
		ball = new Ball(new Vector(0.9, 0.9 * bounds.size.y), 0.01, new Vector(0.03, -0.03));
		paddle = new Paddle(0.5, 0.95 * bounds.size.y, 0.2, 0.03);
		
		bricks = new ArrayList<Brick>();
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 1; j++)
				bricks.add(new Brick(0.075 + (0.15 * i), 0.05 + (0.08 * j), 0.1, 0.03));
		
		isRunning = true;
	}
	
	
	

	public void update(double frameTime, double mouseX) {
		// ** Check game status
		isRunning = !(isWon() || isLost());
		
		// ** Check mouse
        if (mouseX > 0 && mouseX < 1) {
        	double delta = (mouseX - paddle.getCenterX());
        	paddle.speed = 3 * ball.velocity.abs() * Math.signum(delta) * Math.sqrt(Math.abs(delta));  
        }
        
		
		// ** Check collision
		// Ball <-> Bounds
		if (ball.center.x - ball.radius <= bounds.loc.x || ball.center.x + ball.radius >= bounds.size.x) {
			ball.onCollision(DeflectDirection.LeftRight);
			ball.update(frameTime);
		}
		if (ball.center.y - ball.radius <= bounds.loc.y) {
			ball.onCollision(DeflectDirection.UpDown);
			ball.update(frameTime);
		}
		
		// Ball <-> Paddle
        if (ball.center.y + ball.radius >= paddle.top()) {
            if ( ball.center.x > paddle.loc.x && ball.center.x < paddle.right()) {
                double rel = (paddle.getCenterX() - ball.center.x) / (2*paddle.size.x);
                Vector v = new Vector(rel, 1 + rel * -ball.velocity.x / ball.velocity.y);
                v.mult(1 / v.abs());
                ball.update(-frameTime);
                ball.householderCollision(v);
                ball.update(frameTime);
            }
        }
		
		// Ball <-> Bricks	
		for (int i = bricks.size()-1; i >= 0; i--) {
			Brick b = bricks.get(i);
			
			// Find the closest point to the circle within the rectangle
			Vector closestPt = new Vector(Helper.clamp(ball.center.x, b.left(), b.right()), Helper.clamp(ball.center.y, b.top(), b.bottom()));
			// Calculate the distance between the circle's center and this closest point
			double dist = Vector.distSqr(ball.center, closestPt);
			
			
			// If the distance is less than the circle's radius, an intersection occurs
			if (dist < (ball.radius * ball.radius)) {
				DeflectDirection dd = (Helper.isBetween(ball.center.x, b.left(), b.right()) ? DeflectDirection.UpDown : DeflectDirection.LeftRight);
				ball.onCollision(dd);
				ball.update(frameTime);
				bricks.remove(i);
			}
		}
		
		
		
		// Update objects
		paddle.update(frameTime);
		ball.update(frameTime);
	}

	public ArrayList<GObject> toGObjects(double cvsWidth, double cvsHeight) {
		ArrayList<GObject> result = new ArrayList<GObject>();
		
		result.add(ball.toGObject(cvsWidth, cvsHeight, aspectRatio)); 
		result.add(paddle.toGObject(cvsWidth, cvsHeight, aspectRatio));
		
		for (Brick brick : bricks)
			result.add(brick.toGObject(cvsWidth, cvsHeight, aspectRatio));
		
		
		GRect bounds = new GRect(1, 1, cvsWidth-2, cvsHeight-2);
		//result.add(bounds);
		
		return result;
	}
	
	
	
	

	
	
	
	private boolean isWon() {
		return bricks.isEmpty();
	}
	
	private boolean isLost() {
		return ball.center.y > paddle.bottom();
	}

}