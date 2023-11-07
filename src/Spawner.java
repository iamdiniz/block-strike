import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawner {

	public int timer = 0;
	
	public List<RectObj> rectangles = new ArrayList<RectObj>();
	
	public List<Particle> particles = new ArrayList<Particle>();
	
	public void update() {
		timer++;
		if (timer % 20 == 0) {
			rectangles.add(new RectObj(0, new Random().nextInt(480-40), 40, 40));
		}
		
		for (int i = 0; i < rectangles.size(); i++) {
			RectObj current = rectangles.get(i);
			
			rectangles.get(i).update();
			
			if (current.x > Game.WIDTH) {
				rectangles.remove(current); // remove os retangulo que saiu da tela.
				Game.barraDeVida--; // Nesse caso, tu perde vida.
			}
			
			if (Game.clicked) {
				
				if (Game.mx >= current.x && Game.mx < current.x + current.width) {
					
					if (Game.my >= current.y && Game.my < current.y + current.height) {
						rectangles.remove(current);
						Game.score++;
						Game.clicked = false;
						
						for (int n = 0; n < 50; n++) {
							particles.add(new Particle(current.x, current.y, 8, 8, current.color));
						}
					}
				}
			}
			
		}
		
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
			
			Particle particle = particles.get(i);
			if (particle.timer >= 60) {
				particles.remove(particle);
			}
		}
		
	}
	
	public void render(Graphics graphics) { 
		
		for(int i = 0; i < rectangles.size(); i++) {
			RectObj current = rectangles.get(i);
			Graphics2D g2 = (Graphics2D) graphics;
			g2.rotate(Math.toRadians(current.rotation), current.x + current.width/2,
					current.y + current.height/2);
			g2.setColor(current.color);
			g2.fillRect(current.x, current.y, current.width, current.height);
			g2.rotate(Math.toRadians(-current.rotation), current.x + current.width/2,
					current.y + current.height/2);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(graphics);
		}
		
	}
	
}
