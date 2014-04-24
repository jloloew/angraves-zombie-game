/**
 * @author jloew2
 */

@SuppressWarnings("serial")
public class Zombie extends Drawable implements Actionable {
	
	private static final String	IMAGE_NAME		= Constants.Zombie_right_image_name;
	private static final String	IMAGE_NAME2		= Constants.Zombie_left_image_name;
	private static int			zombiesAlive	= 0;
	
	/*
	 * Inherits: Location loc double speed
	 */
	private Drawable			target;
	private double				health;
	private double				attack;
	
	public Zombie(Drawable target) {
		super(new Location(), IMAGE_NAME);
		imageWidth = 40;
		imageHeight = 60;
		super.isMoving = true;
		super.description = "Zombie";
		super.points = 50;
		this.target = target;
		// Start on a random edge
		if (Math.random() >= 0.5) {
			loc.setX((int) (Game.GAME_WIDTH * Math.random()));
			loc.setY(Math.random() >= 0.5 ? Game.GAME_HEIGHT - imageHeight : 22);
		} else {
			loc.setX(Math.random() >= 0.5 ? Game.GAME_WIDTH - imageWidth : 0);
			loc.setY((int) (Game.GAME_HEIGHT * Math.random()));
		}
		super.setWidth(5000);
		super.setHeight(5000);
		health = 30;
		attack = 1.0;
		speed = 3.0;
		zombiesAlive++;
		
		
	}
	
	@Override
	public void move() {
		if (isMoving) {
			loc.setDirection(loc.directionTo(target.getLoc()));
			super.move();
			if ( target.getLoc().getX() - loc.getX() < 0 ) {
				this.setImage(IMAGE_NAME2);
			}
			else {
				setImage(IMAGE_NAME);
			}
		}
		
	}
	
	public void takeAction() {
		if (this.isDead()){
			Sound.play("src/death.wav");
			visible=false;
			game.removeDrawable(this);
			
			
		}
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
