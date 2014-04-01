/**
 * @author jloew2
 */

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import JavaGame.Game.AL;


@SuppressWarnings("serial")
public class Game extends JFrame {
	
	public static final int		GAME_WIDTH	= 640, GAME_HEIGHT = 480;
	private ArrayList<Drawable>	drawables;
	private Drawable			player1;
	
	int							x, y;
	private Image				dbImage;
	private Graphics			dbg;
	
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == e.VK_LEFT) {
				if (x <= 10) {
					x = 10;
				} else {
					x -= 5;
				}
			}
			if (keyCode == e.VK_RIGHT) {
				if (x >= 750) {
					x = 750;
				} else {
					x += 5;
				}
			}
			if (keyCode == e.VK_UP) {
				if (y <= 30) {
					y = 30;
				} else {
					y -= 5;
				}
			}
			if (keyCode == e.VK_DOWN) {
				if (y >= 370) {
					y = 370;
				} else {
					y += 5;
				}
			}
		}
		
		public void keyReleased(KeyEvent e) {
			
		}
	}
	
	public Game() {
		addKeyListener(new AL());
		this.setTitle("Angrave VS. Zombie");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		setBackground(Color.BLUE);
		
		x = 400;
		y = 300;
		
		this.drawables = new ArrayList<Drawable>();
		this.player1 = new Drawable(GAME_WIDTH / 2, GAME_HEIGHT / 2,
				"Player1.png");
		this.player1.draw(this);	// This line must go AFTER the JFrame is
									// initialized
		this.drawables.add(this.player1);
	}
	
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillOval(x, y, 40, 40);
		repaint();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Player player = new Player();
		ArrayList<Zombie> zombies = new ArrayList<>(50);
		for (int i = 0; i < zombies.size(); i++) {
			zombies.set(i, new Zombie());
		}// for
		boolean gameIsRunning = true;
		while (gameIsRunning) {
			// Update positions
			
			// Update game state
			
			// Draw game
			
			Zen.flipBuffer();
			Thread.sleep(90);
		}// while
	}// main
	
}// Game class
