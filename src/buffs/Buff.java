package buffs;

import entities.Minion;
import entities.Player;

public abstract class Buff {
	
	public abstract void onApply(Minion minion);
	public abstract void update(int delta, Minion minion);
	public abstract void onRemove(Minion minion);
}
