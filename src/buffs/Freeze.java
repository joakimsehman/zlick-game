package buffs;

import animation.AnimationGroup;
import animation.DirectedAnimation;
import entities.AnimatedDecoration;
import entities.AttachedAnimatedDecoration;
import entities.Minion;
import game.Model;

public class Freeze extends Buff{

	private AnimationGroup animation;
	
	public Freeze(int duration) {
		super(duration);
		
		animation = new AnimationGroup();
		animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("icespikes.png", 0, 19, 0, 1)));
		
	}

	@Override
	public void onApply(Minion minion) {
		// TODO Auto-generated method stub
		minion.applyMovementModifyer(0, getDuration());
		Model.model.addTemporaryDecoration(new AttachedAnimatedDecoration(minion, animation, -10, 5), 1900);
	}

	@Override
	public void onUpdate(int delta, Minion minion) {
		
	}

	@Override
	public void onRemove(Minion minion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

}
