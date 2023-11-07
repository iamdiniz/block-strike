import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawner {

	public int timer = 0;
	
	public List<RectObj> rectangles = new ArrayList<RectObj>();
	
	public void update() {
		timer++;
		if (timer % 60 == 0) {
			rectangles.add(new RectObj(0, new Random().nextInt(480-40), 40, 40));
		}
		
		for (int i = 0; i < rectangles.size(); i++) {
			RectObj current = rectangles.get(i);
			
			rectangles.get(i).update();
			
			if (current.x > Game.WIDTH) {
				rectangles.remove(current); // remove os retangulo que saiu da tela.
				Game.barraDeVida--; // Nesse caso, tu perde vida.
			}
		}
		
	}
	
	public void render(Graphics graphics) { 
		
		for(int i = 0; i < rectangles.size(); i++) {
			RectObj current = rectangles.get(i);
			graphics.setColor(current.color);
			graphics.fillRect(current.x, current.y, current.width, current.height);
		}
		
	}
	
}
