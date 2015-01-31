package animation;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class AnimationGroup {
	
	private int imageSwitchSpeed;
	private int imageDeltaSinceSwitch;
	
	private ArrayList<DirectedAnimation> animations;
	
	public AnimationGroup(){
		animations = new ArrayList<DirectedAnimation>();
		imageSwitchSpeed = 100;
	}
	
	public int getImageSwitchSpeed(){
		return imageSwitchSpeed;
	}
	
	public void setImageSwitchSpeed(int ms){
		imageSwitchSpeed = ms;
	}
	
	public void addDirectedAnimation(DirectedAnimation anim){
		animations.add(anim);
	}
	
	public void draw(Graphics g, float xPos, float yPos){
		for(int i = 0; i < animations.size(); i++){
			animations.get(i).draw(g, xPos, yPos);
		}
	}
	
	public void update(int delta, double directionInDegrees){
		directionInDegrees++; // to compensate for wierd angling
		double directionInPercentOfDirections = directionInDegrees / 361.0f;
		update1(delta, directionInPercentOfDirections);
	}
	
	public void update1(int delta, double directionInPercentOfDirections){
		imageDeltaSinceSwitch += delta;
		if(imageDeltaSinceSwitch > imageSwitchSpeed){
			imageDeltaSinceSwitch -= imageSwitchSpeed;
			for(int i = 0; i < animations.size(); i++){
				animations.get(i).switchImage();
			}
		}
		
		for(int i = 0; i < animations.size(); i++){
			animations.get(i).update(delta, directionInPercentOfDirections);
		}
	}
}
