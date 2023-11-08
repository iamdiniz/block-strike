import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class RectangleParticles extends Rectangle {

	public Color color;
	
	public int speed = 0;
	public int rotation = 0;
	public int timer = 0;
	public double positionHorizontal;
	public double positionVertical;
	public double directionHorizontal;
	public double directionVertical;
	
	public RectangleParticles(int horizontal, int vertical, int width, int height,Color color) {
		super(horizontal, vertical, width, height);
		positionHorizontal = horizontal;
		positionVertical = vertical;
		this.color = color;
		
		directionHorizontal = new Random().nextGaussian();
		directionVertical = new Random().nextGaussian();
		
		speed = 8;
	}
	
	public void update() {
		positionHorizontal+= directionHorizontal * speed;
		positionVertical+= directionVertical * speed;
		timer++;
	}
	
	public void render(Graphics graphics) {
		graphics.setColor(this.color);
		graphics.fillRect((int)positionHorizontal, (int)positionVertical, width, height);
	}
	
}
