/*
 * @author jloew2
 */

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Bullet extends Drawable implements Actionable {
	
	private static final String	IMAGE_NAME		= Constants.Bullet_image_name;
	
	protected double			damage			= Constants.Bullet_damage;
	private static double		hitRadius		= Constants.Bullet_hit_radius;
	private static int			numberOfBullets	= 0;
	
	public Bullet(Location location, double damage) {
		this(location, damage, IMAGE_NAME);
	}
	
	public Bullet(Location location, double damage, String imageFileName) {
		super(location, imageFileName);
		imageWidth = Constants.Bullet_width;
		imageHeight = Constants.Bullet_height;
		super.setWidth(5000);
		super.setHeight(5000);
		super.points = Constants.Bullet_points;
		super.speed = Constants.Bullet_speed;
		this.description = "Bullet";
		this.damage = damage;
		numberOfBullets++;
		Sound.play(Constants.Bullet_fired_sound_name);
	}
	
	public void takeAction() {
		ArrayList<Zombie> zombies = game.getZombies();
		for (int i = 0; i < zombies.size(); i++) {
			Zombie z = zombies.get(i);
			if (loc.distanceTo(z.getLoc()) <= hitRadius) {
				z.dealDamage(damage);
				game.removeDrawable(this);
			}
		}
	}
	
	public static boolean bulletsDoExist() {
		return numberOfBullets > 0;
	}
	
	@Override
	public void finalize() throws Throwable {
		numberOfBullets--;
		super.finalize();
	}
	
	public static double getHitRadius() {
		return hitRadius;
	}
	
	public static void setHitRadius(double hitRadius) {
		Bullet.hitRadius = hitRadius;
	}
	
}
