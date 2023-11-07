import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Particle extends Rectangle {

	public Color color;
	
	public int speed = 0;
	
	public int rotation = 0;
	
	public double xa;
	
	public double ya;
	
	public double dx;
	
	public double dy;
	
	public Particle(int x, int y, int width, int height,Color color) {
		super(x, y, width, height);
		xa = x;
		ya = y;
		this.color = color;
		
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();
		
		speed = 8;
	}
	
	public void update() {
		xa+= dx * speed;
		ya+= dy * speed;
		
	}
	
	public void render(Graphics graphics) {
		graphics.setColor(this.color);
		graphics.fillRect((int)xa, (int)ya, width, height);
	}
	
}
