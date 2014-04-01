/**
 * @author jloew2
 */

@SuppressWarnings("serial")
public class Player extends Drawable {
	
	private static final String IMAGE_NAME = "Angrave.png";
	
	/*
	 * From Drawable: Location loc double speed
	 */
	private double	maxSpeed	= 3.0;
	private double	health;
	private Weapon	weapon;
	private boolean	isShooting;
	
	public Player() {
		super(new Location(Game.GAME_WIDTH / 2, Game.GAME_HEIGHT / 2, 0.0), IMAGE_NAME);
		description = "Player";
		speed = 0.0;
		weapon = new Weapon();
		health = 100.0;
		isShooting = false;
	}
	
	public void restoreHealth(double healthRestored) {
		health += healthRestored;
	}
	
	public void dealDamage(double healthLost) {
		health -= healthLost;
	}
	
	public void move(int dx, int dy) {
		loc.move(dx, dy);
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
		if (speed > maxSpeed)
			super.setSpeed(maxSpeed);
		else
			super.setSpeed(speed);
	}
	
	public double getMaxSpeed() {
		return maxSpeed;
	}
	
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
}
