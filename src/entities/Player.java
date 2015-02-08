package entities;

import game.Model;
import game.Model.Team;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import abilities.Ability;
import animation.AnimationGroup;
import animation.DirectedAnimation;
import utilities.SoundHandler;
import utilities.TextureHandler;

public class Player extends Minion {

	private int id;
	private String name;
	private float energy;
	private Ability[] abilities;
	private Team team;
	private boolean isCasting;
	private int castTime;
	private int castTimeLeft;
	private int castingSpellAbilityNumber;
	private float castingSpellXPos;
	private float castingSpellYPos;
	private final Color brown;

	private Gender gender;
	private Clothes clothes;
	private Weapon weapon;

	private AnimationGroup playerStillAnimation;
	private AnimationGroup playerMovingAnimation;
	private AnimationGroup playerCastingAnimation;
	private AnimationGroup currentPlayerAnimation;

	private enum Gender {
		MALE, FEMALE
	};

	private enum Clothes {
		CLOTHES, LETHER, STEEL
	};

	private enum Weapon {
		BOW, STAFF, SWORD
	};

	public Player(float xPos, float yPos, Vector2f vector, Shape boundingBox,
			int healthPoints, String name, int id) {
		super(xPos, yPos, vector, boundingBox, TextureHandler.getInstance()
				.getImageByName("playerLeft.png"), healthPoints);
		this.id = id;

		gender = Gender.MALE;
		clothes = Clothes.STEEL;
		weapon = Weapon.SWORD;

		playerStillAnimation = new AnimationGroup();
		playerStillAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation.getSpritesAlongX("steel_armor.png", 0, 4, 0,
						8)));
		playerStillAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation
						.getSpritesAlongX("male_head2.png", 0, 4, 0, 8)));
		playerStillAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation
						.getSpritesAlongX("greatsword.png", 0, 4, 0, 8)));

		playerMovingAnimation = new AnimationGroup();
		playerMovingAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation.getSpritesAlongX("steel_armor.png", 4, 8, 0,
						8)));
		playerMovingAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation
						.getSpritesAlongX("male_head2.png", 4, 8, 0, 8)));
		playerMovingAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation
						.getSpritesAlongX("greatsword.png", 4, 8, 0, 8)));

		playerCastingAnimation = new AnimationGroup();
		playerCastingAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation.getSpritesAlongX("steel_armor.png", 24, 4, 0,
						8)));
		playerCastingAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation.getSpritesAlongX("male_head2.png", 24, 4, 0,
						8)));
		playerCastingAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation.getSpritesAlongX("greatsword.png", 24, 4, 0,
						8)));

		playerMovingAnimation.setImageSwitchSpeed(110);

		currentPlayerAnimation = playerStillAnimation;

		this.name = name;
		energy = 100f;
		abilities = new Ability[4];
		team = Model.Team.GREEN;
		isCasting = false;
		brown = new Color(150, 75, 0);
		castTimeLeft = 0;
		castingSpellAbilityNumber = -1;

	}

	public Player(float xPos, float yPos, String name, int id) {
		this(xPos, yPos, new Vector2f(0, 0), new Rectangle(50f, 50f, 50, 50),
				100, name, id);
	}

	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

	public void draw(Graphics g, float cameraX, float cameraY) {
		
		if (!isTransformed()) {

			currentPlayerAnimation.draw(g, getXPos() - cameraX - 45,
					getYPos() - cameraY - 50);

		} else {
			super.draw(g, cameraX, cameraY);
		}

		if (name != null) {

			g.setColor(getTeamColor());
			g.drawString(name, getXPos() - cameraX, getYPos() - cameraY - 25);
			if (Model.model.getID() == id) {
				g.drawString("Energy: " + (int) energy, 800, 50);
				g.drawString("Health: " + getHealthPoints(), 800, 70);
			}
		}

//		 g.drawRect(getXPos() - cameraX, getYPos() - cameraY, getBoundingBox()
//		.getWidth(), getBoundingBox().getHeight());
	}

	private Color getTeamColor() {
		if (team == Team.GREEN) {
			return Color.green;
		} else {
			return brown;
		}
	}

	public String getName() {
		return name;

	}

	public boolean reduceEnergy(int amount) {
		if (energy - amount < 0) {
			return false;
		} else {
			energy = energy - amount;
			return true;
		}
	}

	public float getEnergy() {
		return energy;
	}

	public void update(int delta, ArrayList<Entity> entities) {
		super.update(delta, entities, true);

		if (energy < 100) {
			energy = energy + ((float) delta) / 100;
		}
		
		if (!isTransformed()) {
			double playerDegrees = 0;
			switch (getDirection()) {
			case WEST:
				playerDegrees = 0;
				break;
			case NORTHWEST:
				playerDegrees = 45;
				break;
			case NORTH:
				playerDegrees = 90;
				break;
			case NORTHEAST:
				playerDegrees = 135;
				break;
			case EAST:
				playerDegrees = 180;
				break;
			case SOUTHEAST:
				playerDegrees = 225;
				break;
			case SOUTH:
				playerDegrees = 270;
				break;
			case SOUTHWEST:
				playerDegrees = 315;
				break;
			}

			

			if (isCasting() && !isMoving()) {
				currentPlayerAnimation = playerCastingAnimation;
			} else {
				if (isMoving()) {
					currentPlayerAnimation = playerMovingAnimation;
				} else {
					currentPlayerAnimation = playerStillAnimation;
				}
			}

			currentPlayerAnimation.update(delta, playerDegrees);

		}

		if (isCasting()) {
			if (!isMoving()
					|| getAbility(castingSpellAbilityNumber)
							.isCastableWhileMoving()) {
				if (castTimeLeft > 0) {
					castTimeLeft = castTimeLeft - delta;
				} else {
					isCasting = false;
					useCastedSpell();
				}
			} else {
				isCasting = false;
			}
		}

		for (int i = 0; i < abilities.length; i++) {
			if (abilities[i] != null) {
				abilities[i].update(delta);
			}
		}
	}

	public Ability getAbility(int abilityNumber) {
		return abilities[abilityNumber - 1];
	}

	public void setAbility(Ability ability, int i) {

		if (i < 5 && i > 0) {
			abilities[i - 1] = ability;
		}

	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Team getTeam() {
		return team;
	}

	public boolean isCasting() {
		return isCasting;
	}

	// // tobe removed when sheep animation is replaced with Animation
	// private void loadImages() {
	//
	// for (int i = 0; i < southSheep.length; i++) {
	// southSheep[i] = TextureHandler.getInstance()
	// .getImageFromSpriteSheet(9 + i, 0, "animal.png");
	// }
	// for (int i = 0; i < westSheep.length; i++) {
	// westSheep[i] = TextureHandler.getInstance()
	// .getImageFromSpriteSheet(9 + i, 1, "animal.png");
	// }
	// for (int i = 0; i < eastSheep.length; i++) {
	// eastSheep[i] = TextureHandler.getInstance()
	// .getImageFromSpriteSheet(9 + i, 2, "animal.png");
	// }
	// for (int i = 0; i < northSheep.length; i++) {
	// northSheep[i] = TextureHandler.getInstance()
	// .getImageFromSpriteSheet(9 + i, 3, "animal.png");
	// }
	//
	// }

	public void startCastedAbility(int abilityNumber, float mouseGameX,
			float mouseGameY) {
		if ((!isMoving() || getAbility(abilityNumber).isCastableWhileMoving())
				&& getAbility(abilityNumber).getCost() < this.energy) {

			castTime = this.getAbility(abilityNumber).getCastTime();
			castTimeLeft = castTime;
			isCasting = true;
			this.castingSpellAbilityNumber = abilityNumber;
			this.castingSpellXPos = mouseGameX;
			this.castingSpellYPos = mouseGameY;
		}
	}

	// for network
	public void setIsCasting(boolean isCasting) {
		this.isCasting = isCasting;
	}

	private void useCastedSpell() {
		if (this == Model.model.getMyself()) {
			if (this.getAbility(castingSpellAbilityNumber)
					.isCastableWhileMoving()) {
				Model.model.finishCastingAbility(castingSpellAbilityNumber);
			} else {
				Model.model.finishCastingAbility(castingSpellAbilityNumber,
						castingSpellXPos, castingSpellYPos);
			}
		}
	}

	public int getCastTimeLeft() {
		return castTimeLeft;
	}

	public int getCastTime() {
		return castTime;
	}

	protected void setIsMoving(boolean isMoving) {
		if (isMoving != isMoving()
				&& this.getID() == Model.model.getMyself().getID()) {

			SoundHandler.getInstance().runningSound.loop(1.0f, 0.1f);
		} else {
			SoundHandler.getInstance().runningSound.stop();
		}

		super.setIsMoving(isMoving);
	}

}
