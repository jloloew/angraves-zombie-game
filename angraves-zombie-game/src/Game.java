/**
 * @author jloew2
 */

import java.util.ArrayList;


public class Game {
	
	public static final int	GAME_WIDTH	= 640, GAME_HEIGHT = 480;
	
	public static void main(String[] args) {
		Player player = new Player();
		ArrayList<Zombie> zombies = new ArrayList<>(50);
		for (int i = 0; i < zombies.size(); i++) {
			zombies.set(i, new Zombie());
		}// for
		boolean gameIsRunning = true;
		while (gameIsRunning) {
			
		}// while
	}// main
}// Game class
