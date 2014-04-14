/**
 * @author jloew2
 */

@SuppressWarnings("serial")
public class Player extends Drawable {
	
	private static final String	IMAGE_NAME	= "Angrave.png";
	private static final int IMAGE_HEIGHT = 60;
	private static final int IMAGE_WIDTH = 45;
	
	/*
	 * From Drawable: Location loc double speed
	 */
	private double				health;
	private Weapon				weapon;
	private boolean				isShooting;
	
	public Player() {
		this(new Location(Game.GAME_WIDTH / 2, Game.GAME_HEIGHT / 2, 0.0));
	}
	
	public Player(Location location) {
		super(location, IMAGE_NAME);
		super.setWidth(5000);
		super.setHeight(5000);
		description = "Player";
		speed = 10.0;
		health = 100.0;
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
	
	// @Override
	// public void draw(JFrame frame) {
	// super.draw(frame);
	// Graphics g = image.getGraphics();
	// g.drawImage(image, loc.x(), loc.y(), frame);
	// }
	
//	@Override
//	protected void paintComponent(Graphics g) {
//		g.drawImage(weapon.getImage(), loc.x() + 3, loc.y() + 3, loc.x() + 3
//				+ width, loc.y() + 3 + height, 0, 0, weapon.getImage()
//				.getWidth(), weapon.getImage().getHeight(), game);
//	}
	
//	public void move(int dx, int dy) {
//		if(isMoving)
//			super.loc.move(dx, dy);
//	}
	
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
}
