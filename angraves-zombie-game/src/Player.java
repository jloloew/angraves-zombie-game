/**
 * @author jloew2
 */

@SuppressWarnings("serial")
public class Player extends Drawable {
	
	// Location loc from Drawable
	private double		health;
	private Weapon		weapon;
	private boolean		isShooting;
	
	public Player() {
		super(Game.GAME_WIDTH / 2, Game.GAME_HEIGHT / 2, "Player.png");
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
}
