/**
 * @author jloew2
 */

@SuppressWarnings("serial")
public class Player extends Drawable implements Actionable {
	
	private static final String	IMAGE_NAME	= Constants.Player_image_name;
	
	private double				health		= Constants.Player_default_health;
	private Weapon				weapon;
	private boolean				isShooting;
	
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
	
	public void restoreHealth(double healthRestored) {
		health += healthRestored;
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
	
	public void setHealth(double d) {
		health = d;
	}
	
	public void setWeapon(Weapon w) {
		weapon = w;
	}
	
	public void setIsShooting(boolean b) {
		isShooting = b;
	}
	
	public void setSpeed(double speed) {
		super.setSpeed(speed);
	}
	
	public void takeAction() {
		if (isShooting)
			game.addDrawable(weapon.shoot());
	}
}
