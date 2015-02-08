package animation;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class AnimationGroup {
	
	private int imageSwitchSpeed;
	private int imageDeltaSinceSwitch;
	
	
	private ArrayList<DirectedAnimation> animations;
	
	private ArrayList<Integer> animationPartX;
	private ArrayList<Integer> animationPartDeltaX;
	
	public AnimationGroup(){
		animations = new ArrayList<DirectedAnimation>();
		imageSwitchSpeed = 100;
		animationPartX = new ArrayList<Integer>();
		animationPartDeltaX = new ArrayList<Integer>();
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
	
	public int addNewPartAnimation(int x, int deltaX){
		animationPartX.add(x);
		animationPartDeltaX.add(deltaX);
		
		
		return animationPartX.size() - 1;
	}
	
	public void draw(Graphics g, float xPos, float yPos){
		for(int i = 0; i < animations.size(); i++){
			animations.get(i).draw(g, xPos, yPos);
		}
	}
	
	
	//this will probably also start the anim over, over and over again, if used in the update loop consecutively 
	public void setCurrentAnimation(int animation){
		for(int i = 0; i < animations.size(); i++){
			animations.get(i).setCurrentAnimation(animationPartX.get(animation), animationPartDeltaX.get(animation));
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
