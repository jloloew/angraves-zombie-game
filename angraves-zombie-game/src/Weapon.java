/**
 * @author jloew2
 */

public class Weapon {
	
	private int			ammo;			// how many more bullets you can shoot
	private double		rateOfFire;	// bullets per second
	private double		bulletDamage;	// damage done by each bullet
	protected Player	player;		// the player holding the weapon
										
	public Weapon() {
		ammo = 500;
		rateOfFire = 2.5;
		bulletDamage = 10.0;
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
