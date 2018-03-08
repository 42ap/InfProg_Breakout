package view;

import java.util.ArrayList;
import acm.graphics.GLabel;
import acm.graphics.GObject;




public class MenuView implements View {


	int countdown = 3;
	
	public MenuView() {
		
	}
	
	@Override
	public void update(double mouseX, double mouseY, double runtime) {
		countdown = 3 - (int)(runtime / 1000 / 1000 / 1000);
	}
	
	
	@Override
	public ArrayList<GObject> toGObjects(int cvsWidth, int cvsHeight) {
		ArrayList<GObject> objects = new ArrayList<GObject>();
		
		
		GLabel label = new GLabel("" + countdown, cvsWidth / 2, cvsHeight / 2);
		objects.add(label);
		
		return objects;
	}

	@Override
	public String title() {
		return "MAIN";
	}




}
