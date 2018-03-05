package model.object;

import java.awt.Color;

import acm.graphics.GRect;
import model.Helper;

public class Brick extends RectangularObject {

	public final static byte MAXSTATE = 4;
	public byte state;
	
	public Brick(double x, double y, double w, double h) {
		super(x, y, w, h);
		state = MAXSTATE;
	}


	@Override
	public void onCollision(DeflectDirection d) {
		state--;
	}
	
	
	@Override
	public GRect toGObject(double cvsWidth, double cvsHeight) {
		GRect brick = super.toGObject(cvsWidth, cvsHeight);
		brick.setFilled(true);
		brick.setColor(new Color(255, (int)Helper.map(state, 0, MAXSTATE, 0, 220), 0));
		return brick;
	}
}
