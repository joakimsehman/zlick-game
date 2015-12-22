package abilities.transformation;

import entities.Player;
import game.Model;
import abilities.Ability;

public class Roar extends Ability{

	private int roarAnimation;
	
	public Roar(String name, int playerCreatedId, int animationNumber) {
		super(name, playerCreatedId);
		
		roarAnimation = animationNumber;
	}
	
	
	@Override
	public void useAbility(int id, float mouseGameX, float mouseGameY, int spellEffectId[]) {
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		
		Player castingPlayer = Model.model.getPlayer(getPlayerCreatedId());
		
		castingPlayer.overrideTransformationAnimation(roarAnimation);
		
		
	}
	
	
	@Override
	public void update(int delta){
		super.update(delta);
	}
	

	@Override
	public int getCost() {
		
		return 15;
	}

	@Override
	public int getSpellEffectAmount() {
		return 0;
	}

	@Override
	public int getCastTime() {
		
		return 500;
	}

	@Override
	public boolean isCastableWhileMoving() {
		return false;
	}

	@Override
	public int getCooldown() {
		return 5000;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		return true;
	}


	@Override
	public int getId() {
		return 9;
	}

}
