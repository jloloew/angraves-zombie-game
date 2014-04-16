import java.io.File;
import javax.sound.sampled.*;

public class Sound {
	
	public static synchronized void play(final String fileName){ //works for "Sound.play("FILE_NAME.wav");"
		
		new Thread(new Runnable() {
			
			public void run() {
				try{
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName)); 	//.wav files
					clip.open(inputStream);																	//full file path required? or maybe just local?
					clip.start();
				}catch (Exception e) {
					System.out.println("error playing sound: " + e.getMessage()+ " for " + fileName);
				}
			}

		}).start(); //end of thread 
	}
}