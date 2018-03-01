package model.objects;

import java.awt.Color;

import acm.graphics.GRect;
import model.Helper;
import model.Vector;

public class Paddle extends RectangularObject implements Updateable {
	public double speed;

	public Paddle(double x, double y, double w, double h) {
		super(x, y, w, h);
		this.speed = 0.0;
	}

	@Override
	public void update(double frameTime) {
        this.loc.x += speed * frameTime / 100;
        this.loc.x = Helper.clamp(this.loc.x, 0, 1-this.size.x) ;
    }
	
	
	@Override
	public GRect toGObject(double cvsWidth, double cvsHeight, double aspectRatio) {
		GRect paddle = super.toGObject(cvsWidth, cvsHeight, aspectRatio);
		paddle.setFilled(true);
		paddle.setColor(Color.blue);
		return paddle;
	}
}
