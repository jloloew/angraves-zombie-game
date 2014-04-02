/**
 * @author jloew2
 */

public class Stopwatch {
	
	@SuppressWarnings("unused")
	private long	startTime, stopTime, elapsed;
	private boolean	isRunning;
	
	public Stopwatch() {
		startTime = 0;
		stopTime = 0;
		elapsed = 0;
		isRunning = false;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void start() {
		isRunning = true;
		startTime = System.currentTimeMillis();
	}
	
	public void stop() {
		stopTime = System.currentTimeMillis();
		isRunning = false;
		elapsed = stopTime - startTime;
	}
	
	public void reset() {
		
	}
	
}
