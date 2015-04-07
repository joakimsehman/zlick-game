package abilities;

import animation.AnimationGroup;
import animation.DirectedAnimation;
import entities.AttachedAnimatedDecoration;
import entities.Player;
import game.Model;

public class Healcharge extends Ability {

	private int duration;
	private int cost;
	private int cooldown;
	private int tickCounter;
	private boolean isActive;
	private float preTeleX;
	private float preTeleY;

	private AnimationGroup healAoeEffect;
	private int healAnimation;

	public Healcharge(String name, int playerCreatedId) {
		super(name, 6, playerCreatedId);
		duration = 3000;
		cooldown = 10000;
		cost = 30;
		isActive = false;
		healAoeEffect = new AnimationGroup();
		healAoeEffect.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation.getSpritesAlongX("quake_withheal.png", 0, 6,
						0, 1)));
		healAnimation = healAoeEffect.addNewPartAnimation(0, 6);
		this.resetCooldown();
	}

	public void useAbility(int id, float mouseGameX, float mouseGameY,
			int spellEffectId[]) {

		int msSinceLastUse = getMsSinceLastUse();
		super.useAbility(id, mouseGameX, mouseGameY, spellEffectId);

		if (msSinceLastUse > duration) {
			tickCounter = 0;
			isActive = true;

			preTeleX = Model.model.getPlayer(getPlayerCreatedId()).getXPos();
			preTeleY = Model.model.getPlayer(getPlayerCreatedId()).getYPos();
			Model.model.getPlayer(this.getPlayerCreatedId()).setPos(
					mouseGameX - 25, mouseGameY - 25);

			Model.model.getPlayer(getPlayerCreatedId()).startChanneling(duration);
			
			if (Model.model.getMyself().getID() == this.getPlayerCreatedId()) {
				Model.model.getMyself().setIsAbleToMove(false);
			}

			cooldown = 0;
			cost = 0;
		} else if (isActive) {
			Model.model.getPlayer(getPlayerCreatedId()).setPos(preTeleX,
					preTeleY);
			cooldown = 10000;
			cost = 30;
			isActive = false;
			
			Model.model.getPlayer(getPlayerCreatedId()).abortChanneling();
			
			if (Model.model.getMyself().getID() == this.getPlayerCreatedId()) {
				Model.model.getMyself().setIsAbleToMove(true);
			}
		}

	}

	public void update(int delta) {
		super.update(delta);
		if (isActive) {
			if (getMsSinceLastUse()+100 > tickCounter * 1000) {
				tickCounter++;
				int healAmount = 5 - tickCounter + tickCounter * tickCounter;
				Player playerCreated = Model.model.getPlayer(this.getPlayerCreatedId());
				healAoeEffect.playAnimationOnce(healAnimation, 0);
				Model.model.addTemporaryDecoration(
						new AttachedAnimatedDecoration(playerCreated,
								healAoeEffect, -110, -40), 600, false);
				if (500 > Model.model.getMyself().getDistanceTo(
						playerCreated)) {
					Model.model.getMyself().applyDamage(healAmount,
							Player.EffectAnimation.HEAL);
				}
				for (int i = 0; i < Model.model.getOtherPlayers().size(); i++) {
					Player otherPlayer = Model.model.getOtherPlayers().get(i);
					if(otherPlayer.getTeam() == playerCreated.getTeam())
					otherPlayer.applyDamage(healAmount,
									Player.EffectAnimation.HEAL);
					
				}

				if (tickCounter == 4) {
					cooldown = 10000;
					cost = 30;
					isActive = false;
					
					
					if (Model.model.getMyself().getID() == this.getPlayerCreatedId()) {
						Model.model.getMyself().setIsAbleToMove(true);
					}
				}
			}
		}
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return cost;
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
		return true;
	}

	@Override
	public int getCooldown() {
		// TODO Auto-generated method stub
		return cooldown;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		if (isActive) {
			return true;
		}
		if (((Math.pow((Model.model.getPlayer(this.getPlayerCreatedId())
				.getXPos() - mouseGameX - 25), 2)
				+ Math.pow((Model.model.getPlayer(this.getPlayerCreatedId())
						.getYPos() - mouseGameY - 25), 2) < Math.pow(500, 2)))) {
			if (Model.model.isNonSolidNonNullPosition(mouseGameX - 25,
					mouseGameY - 25)
					&& Model.model.isNonSolidNonNullPosition(mouseGameX - 25,
							mouseGameY + 25)
					&& Model.model.isNonSolidNonNullPosition(mouseGameX + 25,
							mouseGameY - 25)
					&& Model.model.isNonSolidNonNullPosition(mouseGameX + 25,
							mouseGameY + 25)) {
				return true;
			}
		}
		return false;
	}

}
