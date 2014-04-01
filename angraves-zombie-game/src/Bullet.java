/*
 * @author jloew2
 */

import java.util.ArrayList;

public class Bullet extends Drawable {
	
	protected double damage;
	private static ArrayList<Zombie> zombies;
	
	public Bullet(Location location, double damage){
		this(location, damage, "Bullet.png");
	}
	
	public Bullet(Location location, double damage, String imageFileName) {
		super(location, imageFileName);
		description = "Bullet";
		this.damage = damage;
	}
	
	@Override
	public void move() {
		
	}
	
}
