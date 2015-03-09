package buffs;

import entities.Minion;

public class Chill extends Buff{

	public Chill(int duration) {
		super(duration);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onApply(Minion minion) {
		minion.applyMovementModifyer(0.7f, getDuration());
		
	}

	@Override
	public void onUpdate(int delta, Minion minion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRemove(Minion minion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
