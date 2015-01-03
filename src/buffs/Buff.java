package buffs;

import entities.Minion;
import entities.Player;

public abstract class Buff {
	
	private int duration;
	private int durationLeft;
	
	public Buff(int duration){
		this.duration = duration;
		durationLeft = duration;
	}
	
	public abstract void onApply(Minion minion);
	public final void update(int delta, Minion minion){
		durationLeft = durationLeft - delta;
		onUpdate(delta, minion);
	}
	public abstract void onUpdate(int delta, Minion minion);
	public abstract void onRemove(Minion minion);
	public abstract int getID();
	
	public int getDuration(){
		return duration;
	}
	
	public int getDurationLeft(){
		return durationLeft;
	}
}
