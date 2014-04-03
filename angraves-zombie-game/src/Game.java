/**
 * @author jloew2
 */

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Game extends JFrame {
	
	public static int			GAME_WIDTH	= 800, GAME_HEIGHT = 600;
	
	private Drawable			background;
	private Player				player1;
	private ArrayList<Drawable>	drawables;
	private ArrayList<Zombie>	zombies;
	private ArrayList<Bullet>	bullets;
	private int					score		= 0;
	private boolean				isPaused;
	private boolean				shouldDisplayHelp;
	
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (shouldDisplayHelp()) {
				setShouldDisplayHelp(false);
			} else if (isPaused()) {
				if (keyCode == KeyEvent.VK_P) {
					setPaused(false);
				}// nested if
			} else if (keyCode == KeyEvent.VK_H) {
				setShouldDisplayHelp(true);
			} else if (keyCode == KeyEvent.VK_P) {
				setPaused(true);
			} else if (keyCode == KeyEvent.VK_LEFT) {
				player1.getLoc().setDirection(270);
			} else if (keyCode == KeyEvent.VK_RIGHT) {
				player1.getLoc().setDirection(90);
			} else if (keyCode == KeyEvent.VK_UP) {
				player1.getLoc().setDirection(0);
			} else if (keyCode == KeyEvent.VK_DOWN) {
				player1.getLoc().setDirection(180);
			} else if (keyCode == KeyEvent.VK_SPACE) {
				player1.setIsShooting(true);
			}// else if
		}// keyPressed
		
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_SPACE) {
				player1.setIsShooting(false);
			}// if
		}// keyReleased
	}// AL class
	
	public Game() {
		// JFrame stuff
		super.addKeyListener(new AL());
		super.setTitle("Angrave vs. Zombies");
		try {
			super.setIconImage(ImageIO.read(getClass().getResourceAsStream("Icon.png")));
//			super.setIconImage(ImageIO.read(new File("Icon.png")));
		} catch (IOException e) {
			System.out.println("Error: Can't load icon image");
		}
		super.setSize(GAME_WIDTH, GAME_HEIGHT);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setBackground(Color.WHITE);
		super.setVisible(true);
		// Draw the background image
		background = new Drawable(new Location(0, 0, 0.0), "Background.png");
		background.setWidth(GAME_WIDTH);
		background.setHeight(GAME_HEIGHT);
//		background.getImage().getGraphics().drawImage(background.getImage(), 0, 0, this);
//		Graphics g = background.getGraphics();
		super.add(background);
//		super.setComponentZOrder(background, 100);
		super.paintComponents(getGraphics());
		
		this.drawables = new ArrayList<Drawable>();
		this.player1 = new Player(new Location(GAME_WIDTH / 2, GAME_HEIGHT / 2,
				0.0));
//		this.player1.draw(this);	// This line must go AFTER the JFrame is
									// initialized
		super.add(this.player1);
		super.repaint();
		this.drawables.add(this.player1);
		
		this.bullets = new ArrayList<>(0);
		this.zombies = new ArrayList<>(3);
		
		this.score = 0;
		this.isPaused = false;
		this.shouldDisplayHelp = true;
	}
	
	public void paint(Graphics g) {
		// dbImage = createImage(getWidth(), getHeight());
		// dbg = dbImage.getGraphics();
		// paintComponent(dbg);
		// g.drawImage(dbImage, 0, 0, this);
		
	}
	
	public void paintComponent(Graphics g) {
		//
		// g.setColor(Color.GREEN);
		// g.fillOval(x, y, 40, 40);
		// repaint();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Game game = new Game();
		game.setShouldDisplayHelp(false);
		game.setPaused(false);	//TODO: remove this line
//		ArrayList<Zombie> zombies = new ArrayList<>();
		for (int i = 0; i < game.zombies.size(); i++) {
//			game.zombies.set(i, new Zombie(game.getPlayer1()));
			game.addDrawable(new Zombie(game.player1));
		}// for
		boolean gameIsRunning = true;
		while (gameIsRunning) {
			
			// Check if the help screen should be displayed
			if (game.shouldDisplayHelp()) {
				game.setPaused(true);
				// TODO: this
			}
			// Check if the game is paused
			else if (game.isPaused()) {
				// TODO: this
			}
			// Continue with the normal run loop
			else {
				// Move all Drawables
				for (int i = 0; i < game.getDrawables().size(); i++) {
					Drawable dbl = game.getDrawables().get(i);
					// Location oldLoc = new Location(dbl.getLoc());
					dbl.move();	// Update positions
					// if (!dbl.getLoc().equals(oldLoc)) { // redraw only if
					// necessary
					// dbl.update(game.getGraphics());
					// }
					// dbl.draw(game);
				}// for
					// All Bullets and Zombies should take action
				for (int i = 0; i < game.getBullets().size(); i++) {
					game.getBullets().get(i).takeAction();
				}
				for (int i = 0; i < game.getZombies().size(); i++) {
					game.getZombies().get(i).takeAction();
				}
				// Redraw everything
				// for(int i=0; i<game.getDrawables().size(); i++){
				// Drawable dbl = game.getDrawables().get(i);
				//
				// }
//				game.repaint();
//				game.update(game.getGraphics());
				game.paintAll(game.getGraphics());
//				game.paintComponents(game.getGraphics());
				
				Thread.sleep(60);
			}// if/else for paused/help
		}// while
	}// main
	
	public Drawable getPlayer1() {
		return player1;
	}
	
	public ArrayList<Drawable> getDrawables() {
		return drawables;
	}
	
	public void addDrawable(Drawable d) {
		if (d == null)
			return;
		super.add(d);
		super.paintComponents(getGraphics());
		drawables.add(d);
		if (d instanceof Zombie)
			zombies.add((Zombie) d);
		if (d instanceof Bullet)
			bullets.add((Bullet) d);
	}
	
	public void removeDrawable(Drawable d) {
		if (d == null)
			return;
		super.remove(d);
		if (d instanceof Zombie)
			zombies.remove((Zombie) d);
		if (d instanceof Bullet)
			bullets.remove((Bullet) d);
		drawables.remove(d);
		awardPoints(d.getPoints());	// get points for killing stuff
		d = null;
	}
	
	public void awardPoints(int points) {
		this.score += points;
	}
	
	public void setSize(int width, int height) {
		GAME_WIDTH = width;
		GAME_HEIGHT = height;
		super.setSize(GAME_WIDTH, GAME_HEIGHT);
	}
	
	public boolean isPaused() {
		return isPaused;
	}
	
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean shouldDisplayHelp() {
		return shouldDisplayHelp;
	}
	
	public void setShouldDisplayHelp(boolean shouldDisplayHelp) {
		this.shouldDisplayHelp = shouldDisplayHelp;
	}
	
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	public ArrayList<Zombie> getZombies() {
		return zombies;
	}
	
}// Game class
