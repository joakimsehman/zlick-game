package buffs;

import utilities.SoundHandler;
import animation.AnimationGroup;
import animation.DirectedAnimation;
import entities.Minion;
import game.Model;

public class Polymorph extends Buff{

	private static AnimationGroup polymorphAnimation;
	private static int walkingAnimation;
	private static int standingAnimation;
	
	
	public Polymorph(int duration) {
		super(duration);
		if(polymorphAnimation == null){
			polymorphAnimation = new AnimationGroup();
			polymorphAnimation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("animal.png", 9, 3, 0, 4)));
			walkingAnimation = polymorphAnimation.addNewPartAnimation(0, 3);
			standingAnimation = polymorphAnimation.addNewPartAnimation(1, 1);
		}
	}

	@Override
	public void onApply(Minion minion) {
		minion.setTransformed(polymorphAnimation, getDuration(), walkingAnimation, standingAnimation);
		minion.applyMovementModifyer(0.2f, getDuration());
		minion.setIsAbleToCast(false);
		if(Model.model.isOnScreen(minion.getXPos(), minion.getYPos())){
			SoundHandler.getInstance().sheepSound.play(1.0f, 0.5f);
		}
		
	}

	@Override
	public void onUpdate(int delta, Minion minion) {
	}

	@Override
	public void onRemove(Minion minion) {
		minion.setIsAbleToCast(true);
	}

	@Override
	public int getID() {
		return 0;
	}

}
