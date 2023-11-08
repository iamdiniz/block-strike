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
	
	public Spawner spawner;
	
	public static int barraDeVida = 100;
	public static int score = 0;
	public static int mouseHorizontal;
	public static int mouseVertical;
	public static boolean clicked = false;
	public boolean gameOver = false;
	
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
		BufferStrategy bufferStrategy = this.getBufferStrategy(); // Vamos usar o double buffering
		
		if (bufferStrategy == null) { // Caso não exista
			this.createBufferStrategy(3); // Crie uma
			return;
		}
		
		Graphics gameGraphics = bufferStrategy.getDrawGraphics();
		gameGraphics.setColor(Color.black);
		gameGraphics.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (gameOver == false) {
			gameGraphics.setColor(Color.green);
			gameGraphics.fillRect(Game.WIDTH/2 - 100 - 70, 20, barraDeVida * 3, 20);
			gameGraphics.setColor(Color.white);
			gameGraphics.drawRect(Game.WIDTH/2 - 100 - 70, 20, 300, 20);
			
			spawner.render(gameGraphics);
			
		} else {
			gameGraphics.setColor(Color.red);
			gameGraphics.setFont(new Font("Arial", Font.BOLD, 30));
			gameGraphics.drawString("Game Over!", WIDTH/2 - 100, HEIGHT/2 - 50);
			
			gameGraphics.setColor(Color.blue);
			gameGraphics.drawString("Score: " + score, WIDTH/2 - 80, HEIGHT/2 + 80 - 50);
		}
		
		bufferStrategy.show();
	}
	
	public static void main(String[] args) {
		Game gameBlockStrike = new Game();
		JFrame gameFrame = new JFrame("My game");
		
		gameFrame.add(gameBlockStrike);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.pack();
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		
		new Thread(gameBlockStrike).start();
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
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Game.clicked = true;
		mouseHorizontal = e.getX();
		mouseVertical = e.getY();
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
