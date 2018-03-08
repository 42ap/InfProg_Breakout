package view;


import java.awt.Font;
import java.util.HashMap;

import acm.graphics.GLabel;
import acm.graphics.GObject;




public class StartView implements View {


	int countdown = 3;
	
	public StartView() {
		
	}
	
	@Override
	public void update(double mouseX, double mouseY, double runtime) {
		countdown = 3 - (int)(runtime / 1000 / 1000 / 1000);
	}
	
	
	@Override
	public HashMap<String, GObject> toGObjects(int cvsWidth, int cvsHeight) {
		HashMap<String, GObject> objects = new HashMap<String, GObject>();
		
		
		GLabel label = new GLabel("" + countdown, cvsWidth / 2, cvsHeight / 2);
		label.setFont(new Font("Calibri", 0, 30));
		objects.put("Label", label);
		
		return objects;
	}

	@Override
	public String title() {
		return "MAIN";
	}




}
