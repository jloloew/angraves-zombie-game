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
	
	protected Location		loc;
	private String			description;
	private BufferedImage	image;
	
	public Drawable(int x, int y, String imageFileName) {
		this.loc = new Location(x, y, 0.0);
		this.description = "DefaultType";
		image = null;
		setImage(imageFileName);
	}
	
	public void draw(JFrame frame) {
		if (image == null)
			System.out.println("Error in Drawable: image is null");
		else {
			Graphics g = image.getGraphics();
			g.drawImage(image, loc.x(), loc.y(), frame);
		}
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
	
}
