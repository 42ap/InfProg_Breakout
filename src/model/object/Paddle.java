package model.object;

import java.awt.Color;

import acm.graphics.GRect;
import model.Helper;

/**
 * Class which represents the paddle of the game.
 */
public class Paddle extends RectangularObject implements Updateable {
	
	// ***** FIELDS *****
	
	// Speed of the paddle
	private double speed;

	
	/**
	 * Constructor.
	 * @param x The x of the paddle
	 * @param y The y of the paddle
	 * @param w The width of the paddle
	 * @param h The height of the paddle
	 */
	public Paddle(double x, double y, double w, double h) {
		super(x, y, w, h);
		this.speed = 0.0;
	}

	
	
	// ***** MAIN METHODS *****
	
	
	/**
	 * Update method.
	 * @param frameTime The time in ms of the last frame 
	 */
	@Override
	public void update(double frameTime) {
        this.loc.x += speed * frameTime / 100;
        this.loc.x = Helper.clamp(this.loc.x, 0, 1-this.size.x);
    }
	
	
	/**
	 * Update the speed of the paddle.
	 * @param delta The x difference between center of paddle and mouse
	 */
	public void setSpeed(double delta) {
		speed = 0.15 * Math.signum(delta) * Math.sqrt(Math.abs(delta));  
	}
	
    /**
     * Turns this model into a displayable GObject.
     * @param cvsWidth The width of the canvas
     * @param cvsHeight The height of the canvas
     */
	@Override
	public GRect toGObject(double cvsWidth, double cvsHeight) {
		GRect paddle = super.toGObject(cvsWidth, cvsHeight);
		paddle.setFilled(true);
		paddle.setColor(Color.blue);
		return paddle;
	}
}
