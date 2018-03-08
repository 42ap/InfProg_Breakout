package view;


import java.util.HashMap;
import acm.graphics.GObject;

public interface View {
	public String title();
	
	public void update(double mouseX, double mouseY, double runtime);
	
	public HashMap<String, GObject> toGObjects(int cvsWidth, int cvsHeight);
}
