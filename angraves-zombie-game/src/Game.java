/**
 * @author jloew2
 */

import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame {
	
	public static boolean		BACKGROUND_HIDDEN	= false;
	
	public static boolean		debug				= true;
	
	public static int			GAME_WIDTH			= 800, GAME_HEIGHT = 600;
	
	private Drawable			background;
	private Player				player1;
	private ArrayList<Drawable>	drawables;
	private ArrayList<Zombie>	zombies;
	private ArrayList<Bullet>	bullets;
	private int					score				= 0;
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
				player1.setIsMoving(true);
				player1.getLoc().setDirection(270);
			} else if (keyCode == KeyEvent.VK_RIGHT) {
				player1.setIsMoving(true);
				player1.getLoc().setDirection(90);
			} else if (keyCode == KeyEvent.VK_UP) {
				player1.setIsMoving(true);
				player1.getLoc().setDirection(0);
			} else if (keyCode == KeyEvent.VK_DOWN) {
				player1.setIsMoving(true);
				player1.getLoc().setDirection(180);
			} else if (keyCode == KeyEvent.VK_SPACE) {
				player1.setIsShooting(true);
			}// else if
		}// keyPressed
		
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_SPACE) {
				player1.setIsShooting(false);
			} else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_UP
					|| keyCode == KeyEvent.VK_DOWN) {
				player1.setIsMoving(false);
			}
		}// keyReleased
	}// AL class
	
	public Game() {
		// JFrame stuff
		super.addKeyListener(new AL());
		super.setTitle("Angrave vs. Zombies");
		try {
			super.setIconImage(ImageIO.read(getClass().getResourceAsStream("Icon.png")));
		} catch (IOException e) {
			System.out.println("Error: Can't load icon image");
		}
		super.setSize(GAME_WIDTH, GAME_HEIGHT);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setBackground(Color.WHITE);
		super.setVisible(true);
		this.drawables = new ArrayList<Drawable>();
		
		// Add the first player
		this.player1 = new Player(new Location(GAME_WIDTH / 2, GAME_HEIGHT / 2, 0.0));
		super.add(this.player1);
		this.drawables.add(this.player1);
		super.setComponentZOrder(player1, 0);
		
		// Draw the background image
		if (!BACKGROUND_HIDDEN) {
			background = new Background();
			background.setWidth(GAME_WIDTH);
			background.setHeight(GAME_HEIGHT);
			super.add(background);
			super.setComponentZOrder(background, 1);
		}
		
		this.bullets = new ArrayList<>();
		this.zombies = new ArrayList<>();
		
		this.score = 0;
		this.isPaused = false;
		this.shouldDisplayHelp = true;
	}
	
	public static void main(String[] args) throws InterruptedException {
		Game game = new Game();
		Drawable.setGame(game);
		game.setShouldDisplayHelp(false);
		game.setPaused(false);	// TODO: remove this line
		for (int i = 0; i < 3; i++) {
			game.addDrawable(new Zombie(game.player1));
		}
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
				for (int i = 0; i < game.drawables.size(); i++) {
					Drawable dbl = game.drawables.get(i);
					dbl.move();	// Update positions
				}
				// All Drawables that are Actionables should take action
				for (int i = 0; i < game.getDrawables().size(); i++) {
					Drawable d = game.getDrawables().get(i);
					if(d instanceof Actionable)
						((Actionable) d).takeAction();
				}
				// Redraw everything
				switch(Constants.DrawingMethod) {
					case PaintAll: game.paintAll(game.getGraphics()); break;
					case PaintComponents: game.paintComponents(game.getGraphics()); break;
					case Repaint: game.repaint(); break;
					case Update: game.update(game.getGraphics()); break;
					default: System.out.println("Unknown value for DrawingMethod."); break;
				}
				
				Thread.sleep(60);
			}// if/else for paused/help
		}// while
	}// main
	
	public Drawable getPlayer1() {
		return player1;
	}
	
	public ArrayList<Drawable> getDrawables() {
		if (drawables == null)
			drawables = new ArrayList<Drawable>();
		return drawables;
	}
	
	public void addDrawable(Drawable d) {
		if (d == null)
			return;
		super.add(d);
		super.setComponentZOrder(d, 1);
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
		if (this.bullets == null)
			this.bullets = new ArrayList<Bullet>();
		return this.bullets;
	}
	
	public ArrayList<Zombie> getZombies() {
		if (this.zombies == null)
			this.zombies = new ArrayList<Zombie>();
		return this.zombies;
	}
	
}
