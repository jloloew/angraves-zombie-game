/**
 * @author jloew2
 */

public class Location {
	
	private int	x, y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		if (0 <= x && x <= Game.GAME_WIDTH)
			this.x = x;
	}
	
	public void setY(int y) {
		if (0 <= y && y <= Game.GAME_HEIGHT)
			this.y = y;
	}
	
	public void move(int dx, int dy) {
		setX(x + dx);
		setY(y + dy);
	}
	
	public double distanceTo(Location loc) {
		return Math.sqrt((x - loc.getX()) * (x - loc.getX()) + (y - loc.getY())
				* (y - loc.getY()));
	}
	
	public double directionTo(Location loc) {
		// Avoid division by 0
		if (loc.getX() - x == 0)
			return loc.getY() - y > 0 ? 180.0 : 0.0;
		double radians = Math.atan((y - loc.getY()) / (loc.getX() - x));
		double degrees = Math.toDegrees(radians);
		if (loc.getX() - x < 0 || degrees < 0)
			degrees += 180;
		return degrees;
	}
}
