import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Spawner {

	public int timer = 0;
	
	public List<RectObj> rectangles = new ArrayList<RectObj>();
	
	public void tick() {
		
	}
	
	public void render(Graphics graphics) { 
		
		for(int i = 0; i < rectangles.size(); i++) {
			RectObj current = rectangles.get(i);
			graphics.setColor(current.color);
			graphics.fillRect(current.x, current.y, current.width, current.height);
		}
		
	}
	
}
