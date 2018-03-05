package view;

import java.util.ArrayList;
import java.util.HashMap;

import acm.graphics.GObject;
import acm.graphics.GRect;
import model.Vector;
import view.windowelement.TextBox;
import view.windowelement.WindowElement;

public class MainmenuView implements Renderable {


	HashMap<String, WindowElement> entities;
	
	
	public MainmenuView() {
		entities = new HashMap<String, WindowElement>();
		
		TextBox button = new TextBox(0.2, 0.2, 0.6, 0.6, "");
		entities.put("tb", button);
	}
	
	@Override
	public void update(double mouseX, double mouseY, double runtime) {
		TextBox tb = (TextBox)entities.get("tb");
		tb.text = 3 - (int)(runtime / 1000 / 1000 / 1000) + "";
		entities.put("tb", tb);
	}
	
	
	@Override
	public ArrayList<GObject> toGObjects(double cvsWidth, double cvsHeight) {
		ArrayList<GObject> objects = new ArrayList<GObject>();
		
		for (WindowElement entity : entities.values()) {
			objects.add(entity.toGCompound(new Vector(cvsWidth, cvsHeight)));
		}
		
		return objects;
	}

	@Override
	public String title() {
		return "MAIN";
	}




}
