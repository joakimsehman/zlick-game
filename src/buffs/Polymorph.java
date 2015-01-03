package buffs;

import entities.Minion;

public class Polymorph extends Buff{

	public Polymorph(int duration) {
		super(duration);
	}

	@Override
	public void onApply(Minion minion) {
		minion.setPolymorphed(true, getDuration());
		System.out.println("polymorph applied");
	}

	@Override
	public void onUpdate(int delta, Minion minion) {
	}

	@Override
	public void onRemove(Minion minion) {
		minion.setPolymorphed(false, 0);
		System.out.println("polymorph removed");
		
	}

	@Override
	public int getID() {
		return 0;
	}

}
