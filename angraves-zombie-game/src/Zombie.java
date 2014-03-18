/**
 * @author jloew2
 */

public class Zombie {
	
	private Location	location;
	private double		direction;
	private double		health;
	
	public Zombie() {
		// Start on a random edge
		if (Math.random() >= 0.5) {
			location.setX((int) (Game.GAME_WIDTH * Math.random()));
			location.setY(Math.random() >= 0.5 ? Game.GAME_HEIGHT : 0);
		} else {
			location.setX(Math.random() >= 0.5 ? Game.GAME_WIDTH : 0);
			location.setY((int) (Game.GAME_HEIGHT * Math.random()));
		}
		direction = location.directionTo(new Location(Game.GAME_WIDTH / 2,
				Game.GAME_HEIGHT / 2));
		health = 30;
	}
	
}
