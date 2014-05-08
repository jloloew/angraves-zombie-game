public abstract class Constants {

	// Other stuff
	public static final boolean			Debug					= true;
	public static final DrawingMethods	DrawingMethod			= DrawingMethods.PaintComponents;
	public static final double			Epsilon					= 1.0e-9;
	
	// Background
	public static final String			Background_image_name	= "Background.png";
	public static final int				Background_z_order			= 4;
	
	// Bullet
	public static final String			Bullet_image_name		= "Bullet.png";
	public static final String			Bullet_fired_sound_name	= "src/Laser.wav";
	public static final int				Bullet_width			= 15;
	public static final int				Bullet_height			= 15;
	public static final int				Bullet_points			= 10;
	public static final double			Bullet_speed			= 20.0;
	public static final double			Bullet_damage			= 10.0;
	public static final double			Bullet_hit_radius		= 3.0;
	public static final int				Bullet_z_order			= 0;
	
	// Player
	public static final String			Player_image_name		= "Angrave_Headless.png";
	public static final int				Player_width			= 45;
	public static final int				Player_height			= 60;
	public static final double			Player_speed			= 10.0;
	public static final double			Player_default_health	= 100.0;
	public static final int				Player_z_order			= 3;
	
	// Weapon
	public static final String			Weapon_image_name		= "Weapon.png";
	public static final int				Weapon_z_order			= 2;
	
	// Zombie
	public static final double			Zombie_damage_radius	= 5.0;
	public static final String			Zombie_death_sound_name	= "src/Death.wav";
	public static final String			Zombie_image_name_right	= "ZombieRight.png";
	public static final String			Zombie_image_name_left	= "ZombieLeft.png";
	public static final int				Zombie_z_order			= 1;
	
	// Game
	public static final int				Game_width				= 800;
	public static final int				Game_height				= 600;
	
	public enum DrawingMethods {
		PaintAll, PaintComponents, Repaint, Update
	}
	
}
