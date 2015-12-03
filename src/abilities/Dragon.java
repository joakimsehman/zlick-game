package abilities;

public class Dragon extends Ability{

	
	public Dragon(String name, int id, int playerCreatedId){
		super(name, id, playerCreatedId);
		
		
		
	}
	
	public void useAbility(int id, float mouseGameX, float mouseGameY, int[] spellEffectId){
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		
		
		
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSpellEffectAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCastTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCastableWhileMoving() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCooldown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		// TODO Auto-generated method stub
		return false;
	}
}
