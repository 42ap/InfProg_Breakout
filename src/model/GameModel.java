package model;

import java.util.ArrayList;

import acm.graphics.*;
import model.object.*;

/**
 * The main class of the model, containing all game elements
 */
public class GameModel {
	
	// ***** FIELDS ****
	public Ball ball;
	public Bounds bounds;
	public Paddle paddle;
	public ArrayList<Brick> bricks;
	
	public boolean isRunning;
	
	
	
	/**
	 * Constructor, initializes the game objects.
	 * @param aspectRatio The aspectratio of the view, to have an reasonable model
	 */
	public GameModel(double aspectRatio) {		
		
		// The model works with a system going from 0 to 1 on the x-axis, 
		// and depending on the aspectratio from 0 to something on the y-axis.
		
		bounds = new Bounds(0, 0, 1, 1/aspectRatio);
		ball = new Ball(new Vector(0.9, 0.9 * bounds.bottom()), 0.01, new Vector(0.03, -0.03));
		paddle = new Paddle(0.5, 0.95 * bounds.bottom(), 0.2, 0.03);
		
		// Default level
		bricks = new ArrayList<Brick>();
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 2; j++)
				bricks.add(new Brick(0.075 + (0.15 * i), 0.05 + (0.08 * j), 0.1, 0.03));
	}
	
	
	/**
	 * Starts the game
	 */
	public void start() {
		isRunning = true;
	}
	
	

	/**
	 * Updates all objects.
	 * @param frameTime The last frame time, so updating is dependent on real time not on fps.
	 * @param mouseX The current mouse state (between 0 and 1)
	 */
	public void update(double frameTime, double mouseX) {	
		// ** Check game status
		isRunning = isRunning && !(isWon() || isLost());
		if (!isRunning)
			return;
		
		
		// ** Check mouse
        if (mouseX > 0 && mouseX < 1) {
        	double delta = (mouseX - paddle.center().x);
        	paddle.setSpeed(delta); 
        }
        
		
		// ** Check collision
		// Ball <-> Bounds
		if (ball.left() <= bounds.left() || ball.right() >= bounds.right()) {
			ball.onCollision(DeflectDirection.LeftRight);
			ball.update(frameTime);
		}
		if (ball.top() <= bounds.top()) {
			ball.onCollision(DeflectDirection.UpDown);
			ball.update(frameTime);
		}
		
		// Ball <-> Paddle
        if (ball.bottom() >= paddle.top()) {
            if ( ball.center().x > paddle.left() && ball.center().x < paddle.right()) {
            	// Basically updating back in time, so that the ball is outside the paddle for sure (as it was one frame ago), and the collision is not triggering every frame
             
                ball.update(-frameTime); 
                ball.householderCollision(paddle.center().x - ball.center().x, paddle.right() - paddle.left());
                ball.update(frameTime);
            }
        }
		
		// Ball <-> Bricks	
		for (int i = bricks.size()-1; i >= 0; i--) {
			Brick b = bricks.get(i);
			
			// Find the closest point to the circle within the rectangle
			Vector closestPt = new Vector(Helper.clamp(ball.center().x, b.left(), b.right()), Helper.clamp(ball.center().y, b.top(), b.bottom()));
			
			// Calculate the distance between the circle's center and this closest point
			double dist = Vector.distSqr(ball.center(), closestPt);
					
			// If the distance is less than the circle's radius, an intersection occurs
			if (dist < (ball.radius() * ball.radius())) {
				DeflectDirection dd = (Helper.isBetween(ball.center().x, b.left(), b.right()) ? DeflectDirection.UpDown : DeflectDirection.LeftRight);
				ball.onCollision(dd);
				ball.update(frameTime);
				b.onCollision(null);
				
				// Remove from array if brick is "broken"
				if (b.state == 0)
					bricks.remove(i);
			}
		}
		
		
		
		// ** Update objects
		paddle.update(frameTime);
		ball.update(frameTime);
	}


	
	
	/**
	 * Checks if game is won.
	 * @return true if no bricks are left
	 */
	private boolean isWon() {
		return bricks.isEmpty();
	}
	
	/**
	 * Checks if game is lost.
	 * @return true if ball touches the ground
	 */
	private boolean isLost() {
		return ball.center().y > paddle.bottom();
	}

}