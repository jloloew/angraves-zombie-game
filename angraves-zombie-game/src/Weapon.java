/**
 * @author jloew2
 */

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Weapon {
	
	private static final String	IMAGE_NAME	= "Weapon.png";
	
	private int					ammo;						// how many more
															// bullets you can
															// shoot
	private double				rateOfFire;				// bullets per
															// second
	private double				bulletDamage;				// damage done by
															// each bullet
	protected Player			player;					// the player
															// holding the
															// weapon
	protected BufferedImage		image;
	
	public Weapon(Player player) {
		ammo = 500;
		rateOfFire = 2.5;
		bulletDamage = 10.0;
		this.player = player;
		// set image
		try {
			image = ImageIO.read(getClass().getResourceAsStream(IMAGE_NAME));
		} catch (IOException e) {
			System.out
					.println("Error in Weapon constructor: can't load image \""
							+ IMAGE_NAME + "\"");
		}
	}
	
	// Return true if we can shoot
	public Bullet shoot() {
		if (ammo > 0) {
			ammo--;
			return new Bullet(player.getLoc(), bulletDamage);
		}
		return null;
	}
	
	public void addAmmo(int howManyMore) {
		ammo += howManyMore;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public double getBulletDamage() {
		return bulletDamage;
	}
	
	public double getRateOfFire() {
		return rateOfFire;
	}
	
	public int getAmmo() {
		return ammo;
	}
	
	public void setBulletDamage(double damage) {
		bulletDamage = damage;
	}
	
	public void setRateOfFire(double bulletsPerSecond) {
		rateOfFire = bulletsPerSecond;
	}
	
	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
}
