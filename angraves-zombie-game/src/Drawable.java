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
	
	private static final String IMAGE_NAME = "Drawable.png";
	
	protected Location		loc;
	protected double		speed	= 0.0;
	protected String		description;
	protected BufferedImage	image;
	
	public Drawable(Location location) {
		this(location, IMAGE_NAME);
	}
	
	public Drawable(Location location, String imageFileName) {
		this.loc = location;
		if(this.loc == null)
			this.loc = new Location(0, 0, 0.0);
		this.description = "Drawable";
		image = null;
		setImage(imageFileName);
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
			image = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			System.out.println("Error in Drawable: can't load image");
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
	
}
