import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawner {

	public List<MovingRectangle> movingRectangles = new ArrayList<MovingRectangle>();
	public List<RectangleParticles> rectanglesParticles = new ArrayList<RectangleParticles>();
	
	public int timer = 0;
	
	public void update() {
		timer++;
		if (timer % 20 == 0) {
			movingRectangles.add(new MovingRectangle(0, new Random().nextInt(480-40), 40, 40));
		}
		
		for (int i = 0; i < movingRectangles.size(); i++) {
			MovingRectangle current = movingRectangles.get(i);
			
			movingRectangles.get(i).update();
			
			if (current.x > Game.WIDTH) {
				movingRectangles.remove(current); // remove os retangulo que saiu da tela.
				Game.barraDeVida-=3; // Nesse caso, tu perde vida.
			}
			
			if (Game.clicked) {
				
				if (Game.mouseHorizontal >= current.x && Game.mouseHorizontal < current.x + current.width) {
					
					if (Game.mouseVertical >= current.y && Game.mouseVertical < current.y + current.height) {
						movingRectangles.remove(current);
						Game.score++;
						Game.clicked = false;
						
						for (int n = 0; n < 50; n++) {
							rectanglesParticles.add(new RectangleParticles(current.x, current.y, 8, 8, current.color));
						}
					}
				}
			}
			
		}
		
		for (int i = 0; i < rectanglesParticles.size(); i++) {
			rectanglesParticles.get(i).update();
			
			RectangleParticles particle = rectanglesParticles.get(i);
			if (particle.timer >= 60) {
				rectanglesParticles.remove(particle);
			}
		}
		
	}
	
	public void render(Graphics gameGraphics) { 
		
		for(int i = 0; i < movingRectangles.size(); i++) {
			MovingRectangle current = movingRectangles.get(i);
			Graphics2D rotatingGraphics = (Graphics2D) gameGraphics;
			rotatingGraphics.rotate(Math.toRadians(current.rotation), current.x + current.width/2,
					current.y + current.height/2);
			rotatingGraphics.setColor(current.color);
			rotatingGraphics.fillRect(current.x, current.y, current.width, current.height);
			rotatingGraphics.rotate(Math.toRadians(-current.rotation), current.x + current.width/2,
					current.y + current.height/2);
		}
		for (int i = 0; i < rectanglesParticles.size(); i++) {
			rectanglesParticles.get(i).render(gameGraphics);
		}
		
	}
	
}
