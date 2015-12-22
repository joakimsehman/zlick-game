package abilities.transformation;

import abilities.Ability;

public class Bite extends Ability{

	public Bite(String name, int playerCreatedId, int animationNumber) {
		super(name, playerCreatedId);
		
	}
	
	
	@Override
	public void useAbility(int id, float mouseGameX, float mouseGameY, int spellEffectId[]) {
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		
		
		
	}

	@Override
	public int getCost() {
		return 25;
	}

	@Override
	public int getSpellEffectAmount() {
		return 1;
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
		return 3000;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		return true;
	}


	@Override
	public int getId() {
		return 10;
	}

}
