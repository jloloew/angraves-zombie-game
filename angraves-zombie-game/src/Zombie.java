import javax.swing.JFrame;


/**
 * @author jloew2
 */

@SuppressWarnings("serial")
public class Zombie extends Drawable {
	
	/*
	 * Inherits: Location loc double speed
	 */
	private Drawable	target;
	private double		health;
	private double		attack;
	
	public Zombie(Drawable target) {
		super(0, 0, "Zombie.png");
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
	}
	
	@Override
	public void move() {
		loc.setDirection(loc.directionTo(target.getLoc()));
		loc.move(speed);
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
	
	public double getAttack() {
		return attack;
	}
	
	public void setAttack(double attack) {
		this.attack = attack;
	}
	
}
