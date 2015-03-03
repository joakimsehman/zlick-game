package utilities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import entities.BolaEffect;
import entities.FireballEffect;
import entities.IceLanceEffect;
import entities.PolymorphingEffect;
import entities.SpellAreaOfEffect;

public class SpellAreaOfEffectCreator {

	public static SpellAreaOfEffect getNewEffect(int effectId, float xPos,
			float yPos, Vector2f vector, int duration, int playerUsedId,
			int spellEffectId) {
		if (effectId == PolymorphingEffect.getEffectId()) {
			return new PolymorphingEffect(xPos, yPos, vector, duration,
					playerUsedId, spellEffectId);
		} else if (effectId == BolaEffect.getEffectId()) {
			return new BolaEffect(xPos, yPos, vector, duration, playerUsedId,
					spellEffectId);
		} else if (effectId == FireballEffect.getEffectId()) {
			return new FireballEffect(xPos, yPos, vector, duration,
					playerUsedId, spellEffectId);
		} else if (effectId == IceLanceEffect.getEffectId()){
			return new IceLanceEffect(xPos, yPos, vector, duration,
					playerUsedId, spellEffectId);
		} else{
			return null;
		}
	}

}
