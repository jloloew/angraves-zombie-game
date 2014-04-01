/**
 * @author jloew2
 */

public class Zombie extends Drawable {
	
	private double	health;
	private double	attack;
	
	public Zombie() {
		super(0, 0, "Zombie.png");
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
	}
	
	
	
}
