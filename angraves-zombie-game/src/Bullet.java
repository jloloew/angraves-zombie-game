/*
 * @author jloew2
 */

import java.util.ArrayList;


@SuppressWarnings("serial")
public class Bullet extends Drawable {
	
	private static final String	IMAGE_NAME		= "Bullet.png";
	
	protected double			damage;
	private static int			numberOfBullets	= 0;
	private static double		hitRadius		= 3.0;
	
	public Bullet(Location location, double damage) {
		this(location, damage, IMAGE_NAME);
	}// Bullet
	
	public Bullet(Location location, double damage, String imageFileName) {
		super(location, imageFileName);
		super.points = 10;
		this.description = "Bullet";
		this.damage = damage;
		numberOfBullets++;
	}// Bullet
	
	@Override
	public void move() {
		super.move();
		
	}// move
	
	public void takeAction() {
		ArrayList<Zombie> zombies = super.game.getZombies();
		for (int i = 0; i < zombies.size(); i++) {
			Zombie z = zombies.get(i);
			if (super.loc.distanceTo(z.getLoc()) <= hitRadius) {
				z.dealDamage(damage);
				if (z.isDead()) {
					super.game.removeDrawable(z);
				}// if
				super.game.removeDrawable(this);
			}// if
		}// for
	}
	
	public static boolean bulletsDoExist() {
		return numberOfBullets > 0;
	}// bulletsDoExist
	
	@Override
	public void finalize() throws Throwable {
		numberOfBullets--;
		super.finalize();
	}// finalize
	
	public static double getHitRadius() {
		return hitRadius;
	}// getHitRadius
	
	public static void setHitRadius(double hitRadius) {
		Bullet.hitRadius = hitRadius;
	}// setHitRadius
	
}// Bullet class
