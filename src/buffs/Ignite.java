package buffs;

import entities.Minion;

public class Ignite extends Buff{

	private int dmgCounter;
	
	public Ignite(int duration) {
		super(duration);
		
		dmgCounter = 0;
	}

	@Override
	public void onApply(Minion minion) {
		
	}

	@Override
	public void onUpdate(int delta, Minion minion) {
		
		dmgCounter += delta;
		if(dmgCounter > 1000){
			dmgCounter -=1000;
			minion.applyDamage(-1);
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
