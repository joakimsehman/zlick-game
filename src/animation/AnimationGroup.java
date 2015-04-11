package animation;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class AnimationGroup {

	private int imageSwitchSpeed;
	private int imageDeltaSinceSwitch;
	private int currentAnimation;

	private ArrayList<DirectedAnimation> animations;

	private ArrayList<Integer> animationPartX;
	private ArrayList<Integer> animationPartDeltaX;

	public AnimationGroup() {
		animations = new ArrayList<DirectedAnimation>();
		imageSwitchSpeed = 100;
		animationPartX = new ArrayList<Integer>();
		animationPartDeltaX = new ArrayList<Integer>();
		currentAnimation = -1;
	}

	public int getImageSwitchSpeed() {
		return imageSwitchSpeed;
	}

	public void setImageSwitchSpeed(int ms) {
		imageSwitchSpeed = ms;
	}

	public void addDirectedAnimation(DirectedAnimation anim) {
		animations.add(anim);
	}

	public int addNewPartAnimation(int x, int deltaX) {
		animationPartX.add(x);
		animationPartDeltaX.add(deltaX);

		return animationPartX.size() - 1;
	}

	public void draw(Graphics g, float xPos, float yPos) {
		for (int i = 0; i < animations.size(); i++) {
			animations.get(i).draw(g, xPos, yPos);
		}
	}

	//this will probably also start the anim over, over and over again, if used in the update loop consecutively.. not anymoore :)
	public void setCurrentAnimation(int animation) {
		if (currentAnimation != animation) {
			currentAnimation = animation;
			for (int i = 0; i < animations.size(); i++) {
				animations.get(i).setCurrentAnimation(
						animationPartX.get(animation),
						animationPartDeltaX.get(animation));
			}
		}
	}

	//pass -1 into directionInPercentOfDirections to make the animation direction change dynamically
	public void playAnimationOnce(int animation, double directionInPercentOfDirections) {
		for (int i = 0; i < animations.size(); i++) {
			animations.get(i).playAnimationOnce(animationPartX.get(animation),
					animationPartDeltaX.get(animation), directionInPercentOfDirections);
		}
	}

	public void update(int delta, double directionInDegrees) {
		directionInDegrees++; // to compensate for wierd angling
		double directionInPercentOfDirections = directionInDegrees / 361.0f;
		update1(delta, directionInPercentOfDirections);
	}

	public void update1(int delta, double directionInPercentOfDirections) {
		imageDeltaSinceSwitch += delta;
		if (imageDeltaSinceSwitch > imageSwitchSpeed) {
			imageDeltaSinceSwitch -= imageSwitchSpeed;
			for (int i = 0; i < animations.size(); i++) {
				animations.get(i).switchImage();
			}
		}

		for (int i = 0; i < animations.size(); i++) {
			animations.get(i).update(delta, directionInPercentOfDirections);
		}
	}
	
	public void update2(int delta, double directionInRadians){
		directionInRadians += Math.PI + Math.PI / 8;
		double directionInPercentOfDirections = directionInRadians / (Math.PI * 2);
		if(directionInPercentOfDirections >= 1){
			directionInPercentOfDirections = 0;
		}
		update1(delta, directionInPercentOfDirections);
	}
	
	public void setAlpha(float alpha){
		for(int i = 0; i < animations.size(); i++){
			animations.get(i).setAlpha(alpha);
		}
	}
}
