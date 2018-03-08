package model.object;

import java.awt.Color;

import acm.graphics.GOval;
import model.Vector;

/**
 * Class which represents the ball in the game.
 */
public class Ball implements GameObject, Updateable {
	
	
	// ***** FIELDS *****
	
	private Vector center;
	private double radius;
	private Vector velocity; // velocity per 100ms

	
	
	
	/**
	 * Constructor with initial and immutable properties.
	 * @param center The center of the ball
	 * @param radius The radius of the ball
	 * @param velocity The initial velocity of the ball
	 */
	public Ball(Vector center, double radius, Vector velocity) {
		this.center = center;
		this.radius = radius;
		this.velocity = velocity;
	}



	
	
	// ***** MAIN METHODS *****
	
	
	
	/**
	 * Update method.
	 * @param frameTime The time in ms of the last frame
	 */
	@Override
	public void update(double frameTime) {
		center.add(Vector.times(velocity, frameTime / 100));
	}

	
	/**
	 * The collision method, inverts velocity based on direction.
	 * @param d The direction of the deflection
	 */
	@Override
	public void onCollision(DeflectDirection d) {
		switch (d) {
		case UpDown:
			velocity.y *= -1;
			break;
		case LeftRight:
			velocity.x *= -1;
			break;
		default:
			break;
		}			
	}
	
	
	/**
	 * Householder Collision, which is used for the paddle/ball interaction, 
	 * to achieve different deflection angles based on the collision point.
	 * @param deltaX The x component of the distance between paddle center and ball center 
	 * @param paddleWidth The width of the paddle
	 */
    public void householderCollision(double deltaX, double paddleWidth) {
        double rel = deltaX / (2*paddleWidth);
        Vector v = new Vector(rel, 1 + rel * -velocity.x / velocity.y);
        v.mult(1 / v.abs());
        velocity.add(Vector.times(v, -2 * (v.x * velocity.x + v.y * velocity.y)));
    }
	
	
    /**
     * Turns this model into a displayable GObject.
     * @param cvsWidth The width of the canvas
     * @param cvsHeight The height of the canvas
     */
	@Override
	public GOval toGObject(double cvsWidth, double cvsHeight) {
		double aspectRatio = cvsWidth / cvsHeight;
		GOval ball = new GOval((center.x - radius) * cvsWidth, (center.y - radius) * cvsHeight * aspectRatio, 2 * radius * cvsWidth, 2 * radius * cvsHeight * aspectRatio);
		ball.setFilled(true);
		ball.setColor(new Color(245, 245, 245));
		return ball;
	}


	
	
	
	
	
	// **** GETTER ****
	
	
	

	/**
	 * Returns the x of the left bound of this ball.
	 */
	@Override
	public double left() {
		return center.x - radius;
	}

	/**
	 * Returns the x of the right bound of this ball.
	 */
	@Override
	public double right() {
		return center.x -+radius;
	}

	/**
	 * Returns the y of the top bound of this ball.
	 */
	@Override
	public double top() {
		return center.y - radius;
	}

	/**
	 * Returns the y of the bottom bound of this ball.
	 */
	@Override
	public double bottom() {
		return center.y + radius;
	}

	/**
	 * Returns the center of this ball.
	 */
	@Override
	public Vector center() {
		return center;
	}
	
	/**
	 * Returns the radius of this ball.
	 */
	public double radius() {
		return radius;
	}



}
