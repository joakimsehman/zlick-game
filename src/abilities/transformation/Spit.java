package abilities.transformation;

import abilities.Ability;

public class Spit extends Ability{

	public Spit(String name, int playerCreatedId, int animationNumber) {
		super(name, playerCreatedId);
		
	}
	
	
	@Override
	public void useAbility(int id, float mouseGameX, float mouseGameY, int spellEffectId[]) {
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		
		
		
	}
	

	@Override
	public int getCost() {
		return 50;
	}

	@Override
	public int getSpellEffectAmount() {
		return 1;
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
		return 30000;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		return true;
	}


	@Override
	public int getId() {
		return 12;
	}

}
