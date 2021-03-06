/**
 * @author jloew2
 */

@SuppressWarnings("serial")
public class Zombie extends Drawable implements Actionable {
	
	private static final String	IMAGE_NAME		= Constants.Zombie_image_name_right;
	private static final String	IMAGE_NAME2		= Constants.Zombie_image_name_left;
	private static int			zombiesAlive	= 0;
	
	private double				attack;
	private double				health;
	private Drawable			target;
	
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
	
	public void dealDamage(double damageDone) {
		this.health -= damageDone;
		if (isDead()) {
			game.remove(this);
		}
	}
	
	@Override
	public void finalize() throws Throwable {
		zombiesAlive--;
		super.finalize();
	}
	
	public double getAttack() {
		return attack;
	}
	
	public double getHealth() {
		return health;
	}
	
	public Drawable getTarget() {
		return target;
	}
	
	public boolean isDead() {
		return health <= 0;
	}
	
	@Override
	public void move() {
		if (isMoving) {
			loc.setDirection(loc.directionTo(target.getLoc()));
			super.move();
			if (target.getLoc().getX() < this.loc.getX()) {
				this.setImage(IMAGE_NAME2);
			} else {
				setImage(IMAGE_NAME);
			}
		}
	}
	
	public void setAttack(double attack) {
		this.attack = attack;
	}
	
	public void setHealth(double health) {
		this.health = health;
	}
	
	public void setTarget(Drawable target) {
		this.target = target;
	}
	
	public void takeAction() {
		if (this.loc.distanceTo(target.getLoc()) <= Constants.Zombie_damage_radius) {
			// TODO: this
		} else if (this.isDead()) {
			Sound.play(Constants.Zombie_death_sound_name);
			visible = false;
			game.removeDrawable(this);
			
		}
	}
	
	public static int getZombiesAlive() {
		return zombiesAlive;
	}
	
}
