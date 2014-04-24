
public abstract class Constants {
	// File names
	public static final String Background_image_name = "Background.png";
	public static final String Bullet_image_name = "Bullet.png";
	public static final String Player_image_name = "Angrave.png";
	public static final String Weapon_image_name = "Weapon.png";
	public static final String Zombie_right_image_name = "ZombieRight.png";
	public static final String Zombie_left_image_name = "ZombieLeft.png";
	
	public static final String Bullet_fired_sound_name = "src/Laser.wav";
	
	
	// Other stuff
	public static final boolean Debug = true;
	public enum DrawingMethods {
		PaintAll,
		PaintComponents,
		Repaint,
		Update
	}
	public static final DrawingMethods DrawingMethod = DrawingMethods.PaintComponents;
	
}
