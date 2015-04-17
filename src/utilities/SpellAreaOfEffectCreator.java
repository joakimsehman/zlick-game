package utilities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import entities.Arrow;
import entities.BolaEffect;
import entities.FireballEffect;
import entities.IceLanceEffect;
import entities.PolymorphingEffect;
import entities.SoulReleaseEffect;
import entities.SpellAreaOfEffect;
import entities.SwordEffect;
import entities.SwordEffect.WestSword;


public class SpellAreaOfEffectCreator {

	
	/*
	 This method is to help the networking create custom spell area of effects that is not used within an ability(because an ability useAbility(), or update(), will be run on all clients), 
	 the reason behind this method is to have an efficient way to tell one client that an effect has been launched in another client(we send over the id and it will be created, to minimize how much is sent)
	 
	 if you want to be able to create a certain type of spell effect with the method launchCustomAreaOfEffect() in model
	 give it a unique EffectId(ex. 1337 if that is not used by any other ability) that returns by the static method getEffectId() in your spell effect
	 
	 if you want to use launchCustomAreaOfEffect() you should check the comment above that method. (its in model)
	 
	 as of now only the recieveCustomSpellAreaOfEffect() in Model calls this method
	 */
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
			
		} else if (effectId == SoulReleaseEffect.getEffectId()){
			return null; //SHOULD NOT RETURN NULL, null is placeholder so program can run
		}else if(effectId == Arrow.getEffectId()){
			return new Arrow(xPos, yPos, vector, duration, playerUsedId, spellEffectId);
		}
		
		else if(effectId == SwordEffect.WestSword.getEffectId()){
			return new SwordEffect.WestSword(xPos, yPos, vector, duration, playerUsedId, spellEffectId);
		}else if(effectId == SwordEffect.SouthWestSword.getEffectId()){
			return new SwordEffect.SouthWestSword(xPos, yPos, vector, duration, playerUsedId, spellEffectId);
		}else if(effectId == SwordEffect.SouthSword.getEffectId()){
			return new SwordEffect.SouthSword(xPos, yPos, vector, duration, playerUsedId, spellEffectId);
		}else if(effectId == SwordEffect.SouthEastSword.getEffectId()){
			return new SwordEffect.SouthEastSword(xPos, yPos, vector, duration, playerUsedId, spellEffectId);
		}else if(effectId == SwordEffect.EastSword.getEffectId()){
			return new SwordEffect.EastSword(xPos, yPos, vector, duration, playerUsedId, spellEffectId);
		}else if(effectId == SwordEffect.NorthEastSword.getEffectId()){
			return new SwordEffect.NorthEastSword(xPos, yPos, vector, duration, playerUsedId, spellEffectId);
		}else if(effectId == SwordEffect.NorthSword.getEffectId()){
			return new SwordEffect.NorthSword(xPos, yPos, vector, duration, playerUsedId, spellEffectId);
		}else if(effectId == SwordEffect.NorthWestSword.getEffectId()){
			return new SwordEffect.NorthWestSword(xPos, yPos, vector, duration, playerUsedId, spellEffectId);
		}
		
		else{
			return null;
		}
	}

}
