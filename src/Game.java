import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, MouseListener {

	public static final int WIDTH = 640;
	
	public static final int HEIGHT = 480;
	
	public static int barraDeVida = 100;
	
	public static int score = 0;
	
	public static int mx;
	
	public static int my;
	
	public static boolean clicked = false;
	
	public Spawner spawner;
	
	public Game() {
		Dimension dimension = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(dimension);
		this.addMouseListener(this);
		
		spawner = new Spawner();
	}
	
	public void update() {
		spawner.update();
		if (barraDeVida <= 0) {
			// Aqui seria o Game Over
			barraDeVida = 100;
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy(); // Vamos usar o double buffering
		
		if (bs == null) { // Caso não exista
			this.createBufferStrategy(3); // Crie uma
			return;
		}
		
		Graphics graphics = bs.getDrawGraphics();
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		/*
		graphics.setColor(Color.white);
		graphics.setFont(new Font("Arial", Font.BOLD, 23));
		
		graphics.drawString("Pontos: " + contador, WIDTH/2 - 60, 30);
		
		*/
		
		graphics.setColor(Color.green);
		graphics.fillRect(Game.WIDTH/2 - 100 - 70, 20, barraDeVida*3, 20);
		graphics.setColor(Color.white);
		graphics.drawRect(Game.WIDTH/2 - 100 - 70, 20, 300, 20);
		
		spawner.render(graphics);
		
		bs.show();
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame jframe = new JFrame("My game");
		jframe.add(game);
		jframe.setLocationRelativeTo(null);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jframe.setVisible(true);
		
		new Thread(game).start();
		
	}
	
	@Override
	public void run() {
		
		while(true) {
			update();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Game.clicked = true;
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
