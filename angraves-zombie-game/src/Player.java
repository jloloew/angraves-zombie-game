/**
 * @author jloew2
 */

public class Player {
	
	private Location	location;
	private double		direction;	// in degrees, 0 is north
	private double		health;
	private Weapon		weapon;
	private boolean		isShooting;
	
	public Player() {
		location = new Location(Game.GAME_WIDTH / 2, Game.GAME_HEIGHT / 2);
		direction = 0.0;
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
		location.move(dx, dy);
	}
	
	public Location getLocation() {
		return location;
	}
	
	public double getDirection() {
		return direction;
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
	
	public void setLocation(Location loc) {
		location = loc;
	}
	
	public void setDirection(double d) {
		direction = d % 360;
		if (direction < 0)
			direction += 360;
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
}
