/**
 * @author jloew2
 * 
 *         A Location has x, y, and direction, in degrees.
 * 
 */

public class Location {
	
	private double	direction	= 0.0;	// In degrees, 0 is north
	private double	x			= 0, y = 0;
	
	public Location() {
		this(0, 0, 0.0);
	}
	
	public Location(int x, int y, double d) {
		this.x = x;
		this.y = y;
		this.setDirection(d);
	}
	
	public Location(Location loc) {
		if (loc != null) {
			this.x = loc.x();
			this.y = loc.y();
			this.direction = loc.getDirection();
		}
	}
	
	public double degreesTo(Location loc) {
		double direc = directionTo(loc) % 360;
		if (direc < 0)
			direc += 360;
		return direc;
	}
	
	public double directionTo(Location loc) {
		// Avoid division by 0
		if (loc.x() - x <= Constants.Epsilon && loc.x() - x > 0)
			return loc.getY() > this.y ? 180.0 : 0.0;
		double dx = loc.getX() - x;
		double dy = loc.getY() - y;
		double radians = Math.atan(1.0 * dy / dx);
		double degrees = Math.toDegrees(radians);
		degrees -= 90;
		if (!(dx < 0 && degrees < 0))
			degrees += 180;
		return degrees;
	}
	
	public double distanceTo(Location loc) {
		return Math.sqrt((this.x - loc.x()) * (this.x - loc.x()) + (this.y - loc.y()) * (this.y - loc.y()));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Location))
			return false;
		Location loc = (Location) obj;
		return loc.x() == this.x && loc.y() == this.y && Math.abs(loc.getDirection() - this.getDirection()) < 1e-9;
	}
	
	public double getDirection() {
		return direction;
	}
	
	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public boolean isOutOfBounds() {
		return x < 0 || y < 0 || x > Game.GAME_WIDTH || y > Game.GAME_HEIGHT;
	}
	
	public void move(double distance) {
		moveToward(this.direction, distance);
	}
	
	public void move(double dx, double dy) {
		setX(x + dx);
		setY(y + dy);
	}
	
	public void moveToward(double degrees, double distance) {
		double radians = Math.toRadians(degrees - 90);	// Math says 0 is on the X-axis, but North says 0 is on the
														// Y-axis.
		double dx = distance * Math.cos(radians);
		double dy = distance * Math.sin(radians);
		move(dx, dy);
	}
	
	public void moveToward(Location loc, double distance) {
		double radians = Math.toRadians(directionTo(loc));
		double dx = distance * Math.cos(radians);
		double dy = distance * Math.sin(radians);
		move(dx, dy);
	}
	
	public void setDirection(double direction) {
		this.direction = direction % 360;
	}
	
	public void setX(double x) {
		if (0 <= x && x <= Game.GAME_WIDTH)
			this.x = x;
		else if (Constants.Debug)
			System.out.println("Location: setX: value " + x + " is out of range.");
	}
	
	public void setY(double y) {
		if (0 <= y && y <= Game.GAME_HEIGHT)
			this.y = y;
		else if (Constants.Debug)
			System.out.println("Location: setY: value " + y + " is out of range.");
	}
	
	public void turn(double degrees) {
		direction = (direction + degrees) % 360;
		if (direction < 0)
			direction += 360;
	}
	
	public void turnTo(double degrees) {
		direction = degrees % 360;
		if (direction < 0)
			direction += 360;
	}
	
	public double x() {
		return x;
	}
	
	public double y() {
		return y;
	}
	
}
