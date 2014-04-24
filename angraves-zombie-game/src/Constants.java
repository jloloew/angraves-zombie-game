public abstract class Constants {
	// Background
	public static final String			Background_image_name	= "Background.png";
	
	// Bullet
	public static final String			Bullet_image_name		= "Bullet.png";
	public static final String			Bullet_fired_sound_name	= "src/Laser.wav";
	public static final int				Bullet_width			= 15;
	public static final int				Bullet_height			= 15;
	public static final int				Bullet_points			= 10;
	public static final double			Bullet_speed			= 10.0;
	public static final double			Bullet_damage			= 10.0;
	public static final double			Bullet_hit_radius		= 3.0;
	
	// Player
	public static final String			Player_image_name		= "Angrave_Headless.png";
	
	// Weapon
	public static final String			Weapon_image_name		= "Weapon.png";
	
	// Zombie
	public static final double			Zombie_damage_radius	= 5.0;
	public static final String			Zombie_image_name_right	= "ZombieRight.png";
	public static final String			Zombie_image_name_left	= "ZombieLeft.png";
	
	// Game
	public static final int				Game_width				= 800;
	public static final int				Game_height				= 600;
	
	// Other stuff
	public static final boolean			Debug					= true;
	public static final DrawingMethods	DrawingMethod			= DrawingMethods.PaintComponents;
	
	public enum DrawingMethods {
		PaintAll, PaintComponents, Repaint, Update
	}
	
}
