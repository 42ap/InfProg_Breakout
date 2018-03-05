package view.windowelement;


import java.awt.Color;
import java.awt.Font;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import model.Vector;

public class TextBox implements WindowElement {
	
	public double x, y;
	public double width, height;
	public String text;
	
	
	
	public TextBox(double x, double y, double w, double h, String text) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.text = text;
	}
	
	
	
	public GCompound toGCompound(Vector scale) {
		GCompound comp = new GCompound();
		
		GRect frame = new GRect(this.x * scale.x, this.y * scale.y, this.width * scale.x, this.height * scale.y);
		frame.setFilled(true);
		frame.setColor(Color.ORANGE);
		comp.add(frame);
		
		GLabel text = new GLabel(this.text, (this.x + this.width/2) * scale.x, (this.y + this.height/2) * scale.y);
		text.setFont(new Font("Calibri", Font.BOLD, 50));
		comp.add(text);
		
		return comp;
	}
	
}
