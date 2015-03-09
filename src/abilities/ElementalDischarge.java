package abilities;

import org.newdawn.slick.geom.Vector2f;

import entities.ElementalDischargeEffect;
import entities.Player;
import game.Model;

public class ElementalDischarge extends Ability {

	private ElementalDischargeEffect spell;
	private int duration;
	private int cooldown;
	private int casttime;
	private int cost;

	public ElementalDischarge(String name, int id, int playerCreatedId) {
		super(name, 5, playerCreatedId);
		// TODO Auto-generated constructor stub
		duration = 10000;
		casttime = 1000;
		cooldown = 30000;
		cost = 50;
		this.resetCooldown();
	}

	public void useAbility(int id, float mouseGameX, float mouseGameY,
			int[] spellEffectId) {
		int msSinceLastUse = getMsSinceLastUse();
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);
		Player usingPlayer = Model.model.getPlayer(this.getPlayerCreatedId());
		
		
		if (msSinceLastUse > 10000) {
			float angle = (float) Math
					.toDegrees(Math.atan2(mouseGameY - usingPlayer.getYPos(),
							mouseGameX - usingPlayer.getXPos()));

			spell = new ElementalDischargeEffect(usingPlayer.getXPos(),
					usingPlayer.getYPos(), new Vector2f(0, 0), duration,
					getPlayerCreatedId(), spellEffectId[0]);
			spell.setVectorByDegree(50, angle);

			Model.model.addActiveSpell(spell);
			cooldown = 0;
			casttime = 0;
			cost = 0;
		}else{
			spell.onActivate();
			cooldown = 30000;
			casttime = 1000;
			cost = 50;
		}
	}
	
	public void update(int delta){
		super.update(delta);
		if(cooldown == 0 && getMsSinceLastUse() > 10000){
			cooldown = 30000;
			casttime = 1000;
			cost = 50;
		}
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public int getSpellEffectAmount() {
		return 1;
	}

	@Override
	public int getCastTime() {
		// TODO Auto-generated method stub
		return casttime;
	}

	@Override
	public boolean isCastableWhileMoving() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCooldown() {
		// TODO Auto-generated method stub
		return cooldown;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {

		return true;
	}

}
