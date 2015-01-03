package abilities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import entities.Player;
import entities.PolymorphingEffect;
import entities.SpellAreaOfEffect;
import game.Model;
import utilities.TextureHandler;

public class MassPolymorph extends Ability{

	private int duration;
	
	public MassPolymorph(String name, int duration) {
		super(name, 1, TextureHandler.getInstance().getImageByName("massPolymorphIcon.png"));
		this.duration = duration;
	}

	@Override
	public void useAbility(int id, float mouseGameX, float mouseGameY,
			int[] spellEffectId) {
		
		
		PolymorphingEffect spell = new PolymorphingEffect(mouseGameX-100, mouseGameY-94, new Vector2f(0,0), new Circle(mouseGameX-100, mouseGameY-94, 100), TextureHandler.getInstance().getImageByName("spell_circle.png"), duration, false, id, spellEffectId[0]);
	
		Model.model.addActiveSpell(spell);
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	public int getSpellEffectAmount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getCastTime() {
		return 1500;
	}

	@Override
	public boolean isCastableWhileMoving() {
		return false;
	}

	
	
}
