/**
 * @author jloew2
 */

@SuppressWarnings("serial")
public class Zombie extends Drawable {
	
	private static final String	IMAGE_NAME		= "Zombie.png";
	private static final int IMAGE_HEIGHT = 60;
	private static final int IMAGE_WIDTH = 40;
	
	private static int			zombiesAlive	= 0;
	
	/*
	 * Inherits: Location loc double speed
	 */
	private Drawable			target;
	private double				health;
	private double				attack;
	
	public Zombie(Drawable target) {
		super(new Location(), IMAGE_NAME);
		super.isMoving = true;
		super.description = "Zombie";
		super.points = 50;
		this.target = target;
		// Start on a random edge
		if (Math.random() >= 0.5) {
			loc.setX((int) (Game.GAME_WIDTH * Math.random()));
			loc.setY(Math.random() >= 0.5 ? Game.GAME_HEIGHT - IMAGE_HEIGHT : 22);
		} else {
			loc.setX(Math.random() >= 0.5 ? Game.GAME_WIDTH - IMAGE_WIDTH : 0);
			loc.setY((int) (Game.GAME_HEIGHT * Math.random()));
		}
		super.setWidth(5000);
		super.setHeight(5000);
		health = 30;
		attack = 1.0;
		speed = 2.0;
		zombiesAlive++;
	}
	
	@Override
	public void move() {
		if(isMoving){
			loc.setDirection(loc.directionTo(target.getLoc()));
			loc.move(speed);
		}
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
