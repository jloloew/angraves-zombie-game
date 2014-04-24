/**
 * @author jloew2
 */

@SuppressWarnings("serial")
public class Weapon extends Drawable {
	
	private static final String	IMAGE_NAME		= Constants.Weapon_image_name;
	
	private int					ammo			= 500;							// how many more bullets you can shoot
	private double				rateOfFire		= 2.5;							// bullets per second
	private double				bulletDamage	= 10;							// damage done by each bullet
	protected Player			player;										// the player holding the weapon
																				
	public Weapon(Player player) {
		super(player.getLoc(), IMAGE_NAME);
		loc = player.getLoc();
		this.player = player;
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
	
	// public BufferedImage getImage() {
	// return image;
	// }
	
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
