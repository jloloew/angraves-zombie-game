/**
 * @author jloew2
 * 
 *         A Location has x, y, and direction, in degrees.
 * 
 */

public class Location {
	
	private double		x, y;
	private double	direction;	// In degrees, 0 is north
								
	public Location(int x, int y, double d) {
		this.x = x;
		this.y = y;
		this.setDirection(d);
	}
	
	public Location(Location loc) {
		this.x = loc.x();
		this.y = loc.y();
		this.direction = loc.getDirection();
	}
	
	public Location() {
		this(0, 0, 0.0);
	}
	
	public int getX() {
		return (int)x;
	}
	
	public double x() {
		return x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public double y() {
		return y;
	}
	
	public double getDirection() {
		return direction;
	}
	
	public void setX(double x) {
		if (0 <= x && x <= Game.GAME_WIDTH)
			this.x = x;
	}
	
	public void setY(double y) {
		if (0 <= y && y <= Game.GAME_HEIGHT)
			this.y = y;
	}
	
	public void setDirection(double direction) {
		this.direction = direction % 360;
	}
	
	public void move(double dx, double dy) {
		setX(x + dx);
		setY(y + dy);
	}
	
	public void move(double distance) {
		moveToward(this.direction, distance);
	}
	
	public void moveToward(Location loc, double distance) {
		double radians = Math.toRadians(directionTo(loc));
		double dx = distance * Math.cos(radians);
		double dy = distance * Math.sin(radians);
		move(dx, dy);
	}
	
	public void moveToward(double d, double distance) {
		double radians = Math.toRadians(d - 90);
		double dx = distance * Math.cos(radians);
		double dy = distance * Math.sin(radians);
		move(dx, dy);
	}
	
	public void turn(double d) {
		direction = (direction + d) % 360;
		if (direction < 0)
			direction += 360;
	}
	
	public void turnTo(double d) {
		direction = d % 360;
		if (direction < 0)
			direction += 360;
	}
	
	public double distanceTo(Location loc) {
		return Math.sqrt((x - loc.getX()) * (x - loc.getX()) + (y - loc.getY())
				* (y - loc.getY()));
	}
	
	public double directionTo(Location loc) {
		// Avoid division by 0
		if (loc.getX() - x <= 1e-12 && loc.getX() - x > 0)
			return loc.getY() - y > 0 ? 180.0 : 0.0;
		double dx = loc.getX() - x;
		double dy = loc.getY() - y;
		double radians = Math.atan(1.0*dy/dx);
		double degrees = Math.toDegrees(radians);
		degrees -= 90;
		if (!(dx < 0 && degrees < 0))
			degrees += 180;
		return degrees;
	}
	
	public double degreesTo(Location loc) {
		double direc = directionTo(loc) % 360;
		if (direc < 0)
			direc += 360;
		return direc;
	}
	
	public boolean isOutOfBounds() {
		return x < 0 || y < 0 || x > Game.GAME_WIDTH || y > Game.GAME_HEIGHT;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Location) {
			Location loc = (Location) obj;
			return loc.x() == this.x
					&& loc.y() == this.y
					&& Math.abs(loc.getDirection() - this.getDirection()) < 1e-9;
		} else
			return false;
	}
	
}// Location class
