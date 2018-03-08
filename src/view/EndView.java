package view;

import java.awt.Color;
import java.util.HashMap;

import acm.graphics.GRect;
import acm.graphics.GObject;

public class EndView implements View {

	float offset = 0;
	
	@Override
	public String title() {
		return "END";
	}

	@Override
	public void update(double mouseX, double mouseY, double runtime) {
		offset += 0.2;
	}

	@Override
	public HashMap<String, GObject>  toGObjects(int cvsWidth, int cvsHeight) {	
		HashMap<String, GObject>  list = new HashMap<String, GObject>();
		
		for (int i = 0; i < cvsHeight; i++) {
			GRect bar = new GRect(0, i, cvsWidth, 1);
			bar.setColor(Color.getHSBColor((i + offset) / (float)cvsHeight, 1f, 1f));
			list.put("LINE" + i, bar);
		}
		
	
		return list;
	}
	
}
