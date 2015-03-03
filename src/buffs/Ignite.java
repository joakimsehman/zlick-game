package buffs;

import entities.Minion;

public class Ignite extends Buff{

	private int dmgCounter;
	
	public Ignite(int duration) {
		super(duration);
		// TODO Auto-generated constructor stub
		dmgCounter = 0;
	}

	@Override
	public void onApply(Minion minion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(int delta, Minion minion) {
		// TODO Auto-generated method stub
		dmgCounter += delta;
		if(dmgCounter > 100){
			dmgCounter -=100;
			minion.applyDamageModifyer(1);
		}
	}

	@Override
	public void onRemove(Minion minion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
