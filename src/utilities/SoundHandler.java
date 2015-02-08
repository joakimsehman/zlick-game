package utilities;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundHandler {

	public Sound runningSound;
	public Sound fireballSound;
	private static SoundHandler soundHandler;
	
	private SoundHandler(){
	}
	
	
	public static SoundHandler getInstance(){
		if(soundHandler == null){
			soundHandler = new SoundHandler();
		}
		return soundHandler;
	}
	
	
	public void loadSounds() throws SlickException{
		runningSound = new Sound("assets/sound/running1.wav");
		fireballSound = new Sound("assets/sound/fireball.wav");
	}
	
}
