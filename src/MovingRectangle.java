import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class MovingRectangle extends Rectangle {

	public Color color;
	
	public int speed = 0;
	public int rotation = 0;
	
	public MovingRectangle(int horizontal, int vertical, int width, int height) {
		super(horizontal, vertical, width, height);
		color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
		speed = new Random().nextInt(8-6) + 6; // Velocidade do retângulo é dinâmica.
	}
	
	public void update() {
		x+=speed;
		rotation+=2;
		
		if (rotation >= 360) {
			rotation = 0;
		}
	}
	
}
