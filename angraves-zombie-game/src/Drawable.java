/*
 * @author jloew2
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public abstract class Drawable extends JComponent {
	
	protected static Game		game;
	
	private static final String	IMAGE_NAME	= "Drawable.png";
	
	protected Location			loc;
	protected int				imageWidth, imageHeight;
	protected double			speed		= 0.0;
	protected int				points		= 0;
	protected String			description;
	protected BufferedImage		image;
	protected int				width, height;
	protected boolean			isMoving;
	
	public Drawable(Location location) {
		this(location, IMAGE_NAME);
	}
	
	public Drawable(Location location, String imageFileName) {
		this.loc = location;
		if (this.loc == null)
			this.loc = new Location(0, 0, 0.0);
		this.imageWidth = 30;
		this.imageHeight = 30;
		this.description = "Drawable";
		this.image = null;
		this.isMoving = false;
		setImage(imageFileName);
		setWidth(300);
		setHeight(300);
	}
	
	public Drawable(int x, int y) {
		this(new Location(x, y, 0.0));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.drawImage(image, loc.x(), loc.y(), null);
		g.drawImage(image, loc.getX(), loc.getY(), game);
		// if (image != null) {
		// g.drawImage(image, loc.x(), loc.y(), loc.x() + width, loc.y()
		// + height, 0, 0, image.getWidth(), image.getHeight(), game);
		// }
	}
	
	// public void draw(JFrame frame) {
	// if (image == null)
	// System.out.println("Error in Drawable draw: image is null");
	// else {
	// Graphics g = image.getGraphics();
	// g.drawImage(image, loc.x(), loc.y(), frame);
	// }
	// }
	
	public void move() {
		if (isMoving)
			loc.move(speed);
	}
	
	public void setImage(String fileName) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(fileName));
		} catch (IOException e) {
			System.out.println("Error in Drawable: can't load image \"" + fileName + "\"");
		}
	}
	
	public int getX() {
		return loc.getX();
	}
	
	public int getY() {
		return loc.getY();
	}
	
	public void setX(int x) {
		loc.setX(x);
	}
	
	public void setY(int y) {
		loc.setY(y);
	}
	
	public Location getLoc() {
		return loc;
	}
	
	public void setLoc(Location l) {
		this.loc = l;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String s) {
		description = s;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
		super.setSize(width, height);
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
		super.setSize(width, height);
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public static Game getGame() {
		return game;
	}
	
	public static void setGame(Game g) {
		game = g;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public boolean getIsMoving() {
		return isMoving;
	}
	
	public void setIsMoving(boolean b) {
		isMoving = b;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Drawable))
			return false;
		else {
			Drawable d = (Drawable) obj;
			boolean result = true;
			if (!loc.equals(d.getLoc()))
				result = false;
			else if (!(Math.abs(speed - d.getSpeed()) < 1e-9))
				result = false;
			else if (points != d.getPoints())
				result = false;
			else if (!description.equals(d.getDescription()))
				result = false;
			// else if (!image.equals(d.getImage()))
			// result = false;
			else if (width != d.getWidth() || height != d.getHeight())
				result = false;
			return result;
		}
	}
	
	@Override
	public void finalize() throws Throwable {
		game.awardPoints(points);
		super.finalize();
	}
	
}
