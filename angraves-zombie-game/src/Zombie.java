/**
 * @author jloew2
 */

@SuppressWarnings("serial")
public class Zombie extends Drawable {
	
	private static final String	IMAGE_NAME		= "Zombie.png";
	private static int			zombiesAlive	= 0;
	
	/*
	 * Inherits: Location loc double speed
	 */
	private Drawable			target;
	private double				health;
	private double				attack;
	
	public Zombie(Drawable target) {
		super(new Location(), IMAGE_NAME);
		super.description = "Zombie";
		super.points = 50;
		this.target = target;
		// Start on a random edge
		if (Math.random() >= 0.5) {
			loc.setX((int) (Game.GAME_WIDTH * Math.random()));
			loc.setY(Math.random() >= 0.5 ? Game.GAME_HEIGHT : 0);
		} else {
			loc.setX(Math.random() >= 0.5 ? Game.GAME_WIDTH : 0);
			loc.setY((int) (Game.GAME_HEIGHT * Math.random()));
		}
		health = 30;
		attack = 1.0;
		speed = 1.0;
		zombiesAlive++;
	}
	
	@Override
	public void move() {
		loc.setDirection(loc.directionTo(target.getLoc()));
		loc.move(speed);
	}
	
	public void takeAction() {
		
	}
	
	public boolean isDead() {
		return health <= 0;
	}
	
	public Drawable getTarget() {
		return target;
	}
	
	public void setTarget(Drawable target) {
		this.target = target;
	}
	
	public double getHealth() {
		return health;
	}
	
	public void setHealth(double health) {
		this.health = health;
	}
	
	public void dealDamage(double damageDone) {
		this.health -= damageDone;
	}
	
	public double getAttack() {
		return attack;
	}
	
	public void setAttack(double attack) {
		this.attack = attack;
	}
	
	public static int getZombiesAlive() {
		return zombiesAlive;
	}
	
	@Override
	public void finalize() throws Throwable {
		zombiesAlive--;
		super.finalize();
	}
	
}
