package abilities.transformation;

import utilities.Updatable;
import entities.Player;
import game.Model;
import abilities.Ability;
import animation.AnimationGroup;
import animation.DirectedAnimation;

public abstract class TransformationAbility extends Ability{
	
	private Ability[] abilityStorage;
	private boolean inUse;
	
	private int durationLeft;
	
	public TransformationAbility(String name, int playerCreatedId) {
		super(name, playerCreatedId);
		
		inUse = false;
		durationLeft = 0;
		
		abilityStorage = new Ability[4];
	}
	
	
	@Override
	public void useAbility(int id, float mouseGameX, float mouseGameY, int spellEffectId[]) {
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		
		inUse = true;
		durationLeft = getDuration()-50;
		
		Player castingPlayer = Model.model.getPlayer(getPlayerCreatedId());
		
		castingPlayer.setTransformed(getTransformAnimation(), getDuration(), getWalkingAnimation(), getStandingAnimation());
		
		for(int i = 0; i < abilityStorage.length; i++){
			abilityStorage[i] = castingPlayer.getAbility(i+1);
			if(abilityStorage[i] != null){
				Model.model.addUpdateHook(abilityStorage[i]);
			}
		}
		
		for(int i = 0; i < getTransformedAbilities().length; i++){
			castingPlayer.setAbility(getTransformedAbilities()[i], i+1);
		}
		
	}
	
	public abstract Ability[] getTransformedAbilities();
	
	public abstract int getDuration();
	
	public abstract AnimationGroup getTransformAnimation();
	
	public abstract int getWalkingAnimation();
	
	public abstract int getStandingAnimation();
	
	
	
	@Override
	public int getSpellEffectAmount() {
		return 0;
	}
	
	public void update(int delta){
		super.update(delta);
		
		if(inUse){
			durationLeft -= delta;
			
			
			if(durationLeft < 0){
				
				Player castingPlayer = Model.model.getPlayer(getPlayerCreatedId());
				castingPlayer.abortChanneling();
				for(int i = 0; i < abilityStorage.length; i++){
					
					castingPlayer.setAbility(abilityStorage[i], i+1);
					
					if(abilityStorage[i] != null){
						Model.model.removeUpdateHook(abilityStorage[i]);
					}
				}
				
				inUse = false;
			}
		}
	}

	

}
