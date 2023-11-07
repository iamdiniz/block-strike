import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	
	public boolean gameOver = false;
	
	public Spawner spawner;
	
	public Game() {
		Dimension dimension = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(dimension);
		this.addMouseListener(this);
		
		spawner = new Spawner();
	}
	
	public void update() {
		if (gameOver == false) {
			spawner.update();
			if (barraDeVida <= 0) {
				barraDeVida = 100;
				gameOver = true;
			}
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
		
		if (gameOver == false) {
			graphics.setColor(Color.green);
			graphics.fillRect(Game.WIDTH/2 - 100 - 70, 20, barraDeVida * 3, 20);
			graphics.setColor(Color.white);
			graphics.drawRect(Game.WIDTH/2 - 100 - 70, 20, 300, 20);
			
			spawner.render(graphics);
			
		} else {
			graphics.setColor(Color.red);
			graphics.setFont(new Font("Arial", Font.BOLD, 30));
			graphics.drawString("Game Over!", WIDTH/2 - 100, HEIGHT/2 - 50);
			
			graphics.setColor(Color.blue);
			graphics.drawString("Score: " + this.score, WIDTH/2 - 80, HEIGHT/2 + 80 - 50);
		}
		
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
