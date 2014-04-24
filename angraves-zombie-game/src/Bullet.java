/*
 * @author jloew2
 */

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Bullet extends Drawable implements Actionable {
	
	private static final String	IMAGE_NAME		= Constants.Bullet_image_name;
	private static final String	SOUND_NAME		= Constants.Bullet_fired_sound_name;
	
	protected double			damage;
	private static int			numberOfBullets	= 0;
	private static double		hitRadius		= 3.0;
	
	public Bullet(Location location, double damage) {
		this(location, damage, IMAGE_NAME);
	}// Bullet
	
	public Bullet(Location location, double damage, String imageFileName) {
		super(location, imageFileName);
		imageWidth = Constants.Bullet_width;
		imageHeight = Constants.Bullet_height;
		this.points = Constants.Bullet_points;
		this.description = "Bullet";
		this.damage = damage;
		numberOfBullets++;
		Sound.play(SOUND_NAME);
	}// Bullet
	
	@Override
	public void move() {
		super.move();
		
	}// move
	
	public void takeAction() {
		ArrayList<Zombie> zombies = game.getZombies();
		for (int i = 0; i < zombies.size(); i++) {
			Zombie z = zombies.get(i);
			if (loc.distanceTo(z.getLoc()) <= hitRadius) {
				z.dealDamage(damage);
				if (z.isDead()) {
					game.removeDrawable(z);
				}
				game.removeDrawable(this);
			}
		}
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
