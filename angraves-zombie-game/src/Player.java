import java.util.ArrayList;

/**
 * @author jloew2
 */

@SuppressWarnings("serial")
public class Player extends Drawable implements Actionable {
	
	private static final String	IMAGE_NAME	= Constants.Player_image_name;
	
	private double				health		= Constants.Player_default_health;
	private boolean				isShooting;
	private Weapon				weapon;
	
	public Player() {
		this(new Location(Constants.Game_width / 2, Constants.Game_height / 2, 0.0));
	}
	
	public Player(Location location) {
		super(location, IMAGE_NAME);
		super.setWidth(4000);
		super.setHeight(5000);
		imageWidth = Constants.Player_width;
		imageHeight = Constants.Player_height;
		description = "Player";
		speed = Constants.Player_speed;
		health = Constants.Player_default_health;
		super.isMoving = false;
		isShooting = false;
		weapon = new Weapon(this);
	}
	
	public void dealDamage(double healthLost) {
		health -= healthLost;
	}
	
	public double getHealth() {
		return health;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	
	public boolean isShooting() {
		return isShooting;
	}
	
	public void restoreHealth(double healthRestored) {
		health += healthRestored;
	}
	
	public void setHealth(double d) {
		health = d;
	}
	
	public void setIsShooting(boolean b) {
		isShooting = b;
	}
	
	public void setSpeed(double speed) {
		super.setSpeed(speed);
	}
	
	public void setWeapon(Weapon w) {
		weapon = w;
	}
	
	public boolean isDead() {
		return health <= 0;
	}
	
	public void takeAction() {
		if (isShooting)
			game.addDrawable(weapon.shoot());
		ArrayList<Zombie> zombies = game.getZombies();
		for (int i = 0; i < zombies.size(); i++) {
			Zombie z = zombies.get(i);
			if (loc.distanceTo(z.getLoc()) <= Constants.Zombie_damage_radius) {
				dealDamage(5.0);
			} else if (this.isDead()) {
				Sound.play(Constants.Zombie_death_sound_name);
				visible = false;
				game.removeDrawable(this);
				
			}
		}
	}
}
