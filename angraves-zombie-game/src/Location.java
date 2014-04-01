/**
 * @author jloew2
 * 
 * A Location has x, y, and direction, in degrees.
 * 
 */

public class Location {
	
	private int	x, y;
	private double direction;	// In degrees, 0 is north
	
	public Location(int x, int y, double d) {
		this.x = x;
		this.y = y;
		this.setDirection(d);
	}
	
	public int getX() {
		return x;
	}
	public int x() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public int y() {
		return y;
	}
	
	public double getDirection() {
		return direction;
	}
	
	public void setX(int x) {
		if (0 <= x && x <= Game.GAME_WIDTH)
			this.x = x;
	}
	
	public void setY(int y) {
		if (0 <= y && y <= Game.GAME_HEIGHT)
			this.y = y;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public void move(int dx, int dy) {
		setX(x + dx);
		setY(y + dy);
	}
	
	public void turn(double d) {
		direction = (direction + d) % 360;
		if(direction < 0)
			direction += 360;
	}
	
	public void turnTo(double d) {
		direction = d % 360;
		if(direction < 0)
			direction += 360;
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
	
	public double degreesTo(Location loc) {
		double direc = directionTo(loc) % 360;
		if(direc < 0)
			direc += 360;
		return direc;
	}
	
}//Location class
