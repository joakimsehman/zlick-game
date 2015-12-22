package abilities.transformation;

import abilities.Ability;

public class Dash extends Ability{

	public Dash(String name, int duration, int playerCreatedId) {
		super(name, playerCreatedId);
		
		
	}
	
	
	@Override
	public void useAbility(int id, float mouseGameX, float mouseGameY, int spellEffectId[]) {
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		
		
		
	}

	@Override
	public int getCost() {
		return 30;
	}

	@Override
	public int getSpellEffectAmount() {
		return 0;
	}

	@Override
	public int getCastTime() {
		return 0;
	}

	@Override
	public boolean isCastableWhileMoving() {
		return true;
	}

	@Override
	public int getCooldown() {
		return 6000;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		return true;
	}


	@Override
	public int getId() {
		return 11;
	}

}
