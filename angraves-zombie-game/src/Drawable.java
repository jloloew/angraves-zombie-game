/*
 * @author jloew2
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Drawable extends JComponent {
	
	protected Game				game;
	
	private static final String	IMAGE_NAME	= "Drawable.png";
	
	protected Location			loc;
	protected double			speed		= 0.0;
	protected int				points		= 0;
	protected String			description;
	protected BufferedImage		image;
	protected int				width, height;
	
	public Drawable(Location location) {
		this(location, IMAGE_NAME);
	}
	
	public Drawable(Location location, String imageFileName) {
		this.loc = location;
		if (this.loc == null)
			this.loc = new Location(0, 0, 0.0);
		this.description = "Drawable";
		this.image = null;
		setImage(imageFileName);
		setWidth(30);
		setHeight(30);
	}
	
	public Drawable(int x, int y) {
		this(new Location(x, y, 0.0));
	}
	
	public void draw(JFrame frame) {
		if (image == null)
			System.out.println("Error in Drawable: image is null");
		else {
			Graphics g = image.getGraphics();
			g.drawImage(image, loc.x(), loc.y(), frame);
		}
	}
	
	public void move() {
		loc.move(speed);
	}
	
	public void setImage(String fileName) {
		try {
//			File f = new File(fileName);
			image = ImageIO.read(getClass().getResourceAsStream(fileName));
//			image = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			System.out.println("Error in Drawable: can't load image \""
					+ fileName + "\"");
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
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game g) {
		game = g;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.drawImage(image, loc.x(), loc.y(), null);
		g.drawImage(image, loc.x(), loc.y(), loc.x() + width, loc.y() + height,
				0, 0, image.getWidth(), image.getHeight(), game);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof Drawable) {
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
//			else if (!image.equals(d.getImage()))
//				result = false;
			else if (width != d.getWidth() || height != d.getHeight())
				result = false;
			return result;
		} else
			return false;
	}// equals
	
	@Override
	public void finalize() throws Throwable {
		game.awardPoints(points);
		super.finalize();
	}// finalize
	
}// Drawable class
