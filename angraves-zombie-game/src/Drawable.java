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
	
	private static final String	IMAGE_NAME	= "Drawable.png";
	
	protected static Game		game;
	
	protected String			description;
	protected BufferedImage		image;
	protected int				imageWidth, imageHeight;
	protected boolean			isMoving;
	protected Location			loc;
	protected int				points		= 0;
	protected double			speed		= 0.0;
	protected boolean			visible		= true;
	protected int				width, height;
	
	public Drawable(int x, int y) {
		this(new Location(x, y, 0.0));
	}
	
	public Drawable(Location location) {
		this(location, IMAGE_NAME);
	}
	
	public Drawable(Location location, String imageFileName) {
		this.loc = new Location(location);
		this.imageWidth = 30;
		this.imageHeight = 30;
		this.description = "Drawable";
		this.isMoving = false;
		this.image = null;
		setImage(imageFileName);
		setWidth(300);
		setHeight(300);
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
	
	public String getDescription() {
		return description;
	}
	
	public int getHeight() {
		return height;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public boolean getIsMoving() {
		return isMoving;
	}
	
	public Location getLoc() {
		return loc;
	}
	
	public int getPoints() {
		return points;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getX() {
		return loc.getX();
	}
	
	public int getY() {
		return loc.getY();
	}
	
	public void move() {
		if (isMoving)
			loc.move(speed);
	}
	
	public void setDescription(String s) {
		description = s;
	}
	
	public void setHeight(int height) {
		this.height = height;
		super.setSize(this.width, this.height);
	}
	
	public void setImage(String fileName) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(fileName));
		} catch (IOException e) {
			System.out.println("Error in Drawable: can't load image \"" + fileName + "\"");
		}
	}
	
	public void setIsMoving(boolean b) {
		isMoving = b;
	}
	
	public void setLoc(Location l) {
		this.loc = l;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setWidth(int width) {
		this.width = width;
		super.setSize(this.width, this.height);
	}
	
	public void setX(int x) {
		loc.setX(x);
	}
	
	public void setY(int y) {
		loc.setY(y);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (visible) {
			super.paintComponent(g);
			g.drawImage(image, loc.getX(), loc.getY(), game);
		}
	}
	
	public static Game getGame() {
		return game;
	}
	
	public static void setGame(Game g) {
		game = g;
	}
	
}
