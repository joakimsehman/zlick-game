package abilities.dragon;

import abilities.Ability;

public class Dragon extends Ability{

	public Dragon(String name, int id, int playerCreatedId) {
		super(name, id, playerCreatedId);
		
		
		
		
		
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
		return 0;
	}

	@Override
	public int getCastTime() {
		return 500;
	}

	@Override
	public boolean isCastableWhileMoving() {
		return true;
	}

	@Override
	public int getCooldown() {
		return 60000;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		return true;
	}

}
