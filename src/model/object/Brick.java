package model.object;

import java.awt.Color;

import acm.graphics.GRect;
import model.Helper;


/**
 * Class which represents a brick in the game.
 *
 */
public class Brick extends RectangularObject {

	// ***** FIELDS ***** 
	
	public final static byte MAXSTATE = 3;
	public byte state;
	
	
	
	
	/**
	 * Constructor.
	 * @param x The x of the brick
	 * @param y The y of the brick
	 * @param w The width of the brick
	 * @param h The height of the brick
	 */
	public Brick(double x, double y, double w, double h) {
		super(x, y, w, h);
		state = (byte)acm.util.RandomGenerator.getInstance().nextInt(1, MAXSTATE);
	}
	
	
	
	// ***** MAIN METHODS *****


	/**
	 * Collision method, reduces state of brick (slowly breaking).
	 * @param d Ignored.
	 */
	@Override
	public void onCollision(DeflectDirection d) {
		state--;
	}
	
	
    /**
     * Turns this model into a displayable GObject.
     * @param cvsWidth The width of the canvas
     * @param cvsHeight The height of the canvas
     */
	@Override
	public GRect toGObject(double cvsWidth, double cvsHeight) {
		GRect brick = super.toGObject(cvsWidth, cvsHeight);
		brick.setFilled(true);
		brick.setColor(new Color(255, (int)Helper.map(state, 0, MAXSTATE, 0, 220), 0));
		return brick;
	}
}
