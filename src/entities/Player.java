package entities;

import entities.Player.Clothes;
import entities.Player.Gender;
import entities.Player.Hair;
import entities.Player.Weapon;
import game.Model;
import game.Model.Team;
import gui.HealthBar;

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
	private boolean isChanneling;
	private int castTime;
	private int castTimeLeft;
	private int castingSpellAbilityNumber;
	private float castingSpellXPos;
	private float castingSpellYPos;
	private final Color brown;

	private Gender gender;
	private Clothes clothes;
	private Weapon weapon;
	private Hair hair;

	private AnimationGroup playerAnimation;

	private int playerStillAnimation;
	private int playerMoveAnimation;
	private int playerCastAnimation;
	private int playerAttackAnimation;
	private int playerShootAnimation;

	private HealthBar healthBar;

	private AnimationGroup healAnimation;
	private AnimationGroup fireAnimation;
	private AnimationGroup iceAnimation;
	private AnimationGroup bloodAnimation;

	private int fireAnimationNr;
	private int iceAnimationNr;
	private int bloodAnimationNr;

	public enum EffectAnimation {
		HEAL, FIRE, ICE, BLOOD
	}

	public enum Gender {
		MALE, FEMALE
	};

	public enum Clothes {
		CLOTHES, LETHER, STEEL;
		private static Clothes[] vals = values();

		public Clothes getNext() {
			return vals[(ordinal() + 1) % vals.length];
		}

		public Clothes getPrevious() {
			if (ordinal() - 1 < 0) {
				return vals[vals.length - 1];
			} else {
				return vals[(ordinal() - 1)];
			}
		}
	};

	public enum Weapon {
		BOW, STAFF, SWORD, SHIELD;
		private static Weapon[] vals = values();

		public Weapon getNext() {
			return vals[(ordinal() + 1) % vals.length];
		}

		public Weapon getPrevious() {
			if (ordinal() - 1 < 0) {
				return vals[vals.length - 1];
			} else {
				return vals[(ordinal() - 1)];
			}
		}
	};

	public enum Hair {
		NORMAL, BALD, HOOD;
		private static Hair[] vals = values();

		public Hair getNext() {
			return vals[(ordinal() + 1) % vals.length];
		}

		public Hair getPrevious() {
			if (ordinal() - 1 < 0) {
				return vals[vals.length - 1];
			} else {
				return vals[(ordinal() - 1)];
			}
		}
	};

	public Player(float xPos, float yPos, Vector2f vector, Shape boundingBox,
			int healthPoints, String name, int id) {
		super(xPos, yPos, vector, boundingBox, TextureHandler.getInstance()
				.getImageByName("playerLeft.png"), healthPoints);
		this.id = id;

		gender = Gender.MALE;
		clothes = Clothes.STEEL;
		weapon = Weapon.SWORD;

		playerAnimation = new AnimationGroup();
		playerAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation.getSpritesAlongX("male_steel_armor.png", 0,
						32, 0, 8)));
		playerAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation.getSpritesAlongX("male_head2.png", 0, 32, 0,
						8)));
		playerAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation.getSpritesAlongX("male_greatsword.png", 0,
						32, 0, 8)));

		playerStillAnimation = playerAnimation.addNewPartAnimation(0, 4);
		playerMoveAnimation = playerAnimation.addNewPartAnimation(4, 8);
		playerCastAnimation = playerAnimation.addNewPartAnimation(24, 4);
		playerAttackAnimation = playerAnimation.addNewPartAnimation(12, 4);
		playerShootAnimation = playerAnimation.addNewPartAnimation(28, 4);

		playerAnimation.setImageSwitchSpeed(110);

		healAnimation = new AnimationGroup();
		healAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation
						.getSpritesAlongX("healEffect.png", 0, 6, 0, 1)));
		healAnimation.setImageSwitchSpeed(170);

		bloodAnimation = new AnimationGroup();
		bloodAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation.getSpritesAlongX("sparks.png", 0, 4, 0, 2)));
		bloodAnimation.setImageSwitchSpeed(100);
		bloodAnimationNr = bloodAnimation.addNewPartAnimation(0, 4);

		fireAnimation = new AnimationGroup();
		fireAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation.getSpritesAlongX("sparks.png", 0, 4, 2, 2)));
		fireAnimation.setImageSwitchSpeed(100);
		fireAnimationNr = fireAnimation.addNewPartAnimation(0, 4);

		iceAnimation = new AnimationGroup();
		iceAnimation.addDirectedAnimation(new DirectedAnimation(
				DirectedAnimation.getSpritesAlongX("sparks.png", 0, 4, 4, 2)));
		iceAnimation.setImageSwitchSpeed(100);
		iceAnimationNr = iceAnimation.addNewPartAnimation(0, 4);

		this.name = name;
		energy = 100f;
		abilities = new Ability[4];
		team = Model.Team.GREEN;
		isCasting = false;
		isChanneling = false;
		brown = new Color(150, 75, 0);
		castTimeLeft = 0;
		castingSpellAbilityNumber = -1;

		healthBar = new HealthBar(0, 0, id);

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

		if (isInvisible()
				&& Model.model.getPlayer(Model.model.getID()).getTeam() != this
						.getTeam()) {

		} else {
			healthBar.draw(g);

			if (!isTransformed()) {

				playerAnimation.draw(g, getXPos() - cameraX - 45, getYPos()
						- cameraY - 50);

			} else {
				super.draw(g, cameraX, cameraY);
			}

			if (name != null) {

				g.setColor(getTeamColor());
				g.drawString(name, getXPos() - cameraX, getYPos() - cameraY
						- 25);
				if (Model.model.getID() == id) {
					g.drawString("Energy: " + (int) energy, 800, 50);
					g.drawString("Health: " + getHealthPoints(), 800, 70);
				}
			}
		}

		// g.drawRect(getXPos() - cameraX, getYPos() - cameraY, getBoundingBox()
		// .getWidth(), getBoundingBox().getHeight());
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

	public Gender getGender() {
		return gender;
	}

	public Clothes getClothes() {
		return clothes;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public Hair getHair() {
		return hair;
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
	
	public void update(int delta, ArrayList<Entity> entities, boolean collidesWithTerrain){
		update(delta, entities);
	}

	public void update(int delta, ArrayList<Entity> entities) {
		super.update(delta, entities, true);

		healthBar.update1(delta, getXPos() - 5, getYPos() - 35);
		
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
				playerAnimation.setCurrentAnimation(playerCastAnimation);
			} else {
				if (isMoving()) {
					playerAnimation.setCurrentAnimation(playerMoveAnimation);
				} else {
					playerAnimation.setCurrentAnimation(playerStillAnimation);
				}
			}

			playerAnimation.update(delta, playerDegrees);

		}

		if (isCasting()) {
			if (!isMoving()
					|| isChanneling
					|| getAbility(castingSpellAbilityNumber)
							.isCastableWhileMoving()) {
				if (castTimeLeft > 0) {
					castTimeLeft = castTimeLeft - delta;
				} else {
					isCasting = false;
					if (!isChanneling) {
						useCastedSpell();
					} else {
						isChanneling = false;
					}
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

	public boolean isChanneling() {
		return isChanneling;
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
		if (!isChanneling) {
			if ((!isMoving() || getAbility(abilityNumber)
					.isCastableWhileMoving())
					&& getAbility(abilityNumber).getCost() < this.energy) {

				castTime = this.getAbility(abilityNumber).getCastTime();
				castTimeLeft = castTime;
				isCasting = true;
				if (Model.model.isOnScreen(getXPos(), getYPos())) {
					SoundHandler.getInstance().castingSound.play(1.0f, 0.1f);
				}
				this.castingSpellAbilityNumber = abilityNumber;
				this.castingSpellXPos = mouseGameX;
				this.castingSpellYPos = mouseGameY;
			}
		}
	}

	// for network
	public void setIsCasting(boolean isCasting) {
		if (this.isCasting != isCasting) {
			this.isCasting = isCasting;
			if (isCasting && Model.model.isOnScreen(getXPos(), getYPos())) {
				SoundHandler.getInstance().castingSound.play();
			}
		}
	}

	public void startChanneling(int channelingTime) {
		setIsCasting(true);
		isChanneling = true;
		castTime = channelingTime;
		castTimeLeft = castTime;
	}

	public void abortChanneling() {
		setIsCasting(false);
		isChanneling = false;

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

			if (isMoving) {
				SoundHandler.getInstance().runningSound.loop(1.0f, 0.5f);
			} else {
				SoundHandler.getInstance().runningSound.stop();
			}

			super.setIsMoving(isMoving);
		}
	}

	public boolean isMouseAttackReady() {
		// TODO Auto-generated method stub
		return true;
	}

	public void useMouseAttack(int mouseButton, float mouseGameX,
			float mouseGameY) {
		double angle = this.getAngleToPoint(mouseGameX, mouseGameY);

		//should be correct, but somehow it feels like the player is not always hitting the correct direction
		angle = angle + Math.PI + Math.PI / 8;
		angle = angle / (Math.PI * 2);
		if (angle >= 1) {
			angle = 0;
		}
		if(weapon == Weapon.BOW){
			playerAnimation.playAnimationOnce(playerShootAnimation, angle);
			if(this.getID() == Model.model.getID()){
				setIsAbleToMove(false, 300);
			}
			
		}else{
			playerAnimation.playAnimationOnce(playerAttackAnimation, angle);
			if(this.getID() == Model.model.getID()){
				this.setIsAbleToMove(false, 150);
			}
		}
	}

	public void setCustomization(Gender gender, Clothes clothes, Hair hair,
			Weapon weapon) {
		this.gender = gender;
		this.clothes = clothes;
		this.hair = hair;
		this.weapon = weapon;

		playerAnimation = new AnimationGroup();

		if (gender == Gender.MALE) {
			if (hair == Hair.NORMAL) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX("male_head1.png", 0,
								32, 0, 8)));
			} else if (hair == Hair.BALD) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX("male_head2.png", 0,
								32, 0, 8)));
			} else if (hair == Hair.HOOD) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX("male_head3.png", 0,
								32, 0, 8)));
			}

			if (clothes == Clothes.CLOTHES) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX("male_clothes.png",
								0, 32, 0, 8)));
			} else if (clothes == Clothes.LETHER) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX(
								"male_leather_armor.png", 0, 32, 0, 8)));
			} else if (clothes == Clothes.STEEL) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX(
								"male_steel_armor.png", 0, 32, 0, 8)));

			}
			if (weapon == Weapon.BOW) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX("male_greatbow.png",
								0, 32, 0, 8)));
			} else if (weapon == Weapon.SWORD) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX(
								"male_greatsword.png", 0, 32, 0, 8)));
			} else if (weapon == Weapon.STAFF) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX(
								"male_greatstaff.png", 0, 32, 0, 8)));
			} else if (weapon == Weapon.SHIELD) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX(
								"male_longsword.png", 0, 32, 0, 8)));
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX("male_shield.png",
								0, 32, 0, 8)));
			}

		} else {
			playerAnimation.addDirectedAnimation(new DirectedAnimation(
					DirectedAnimation.getSpritesAlongX("female_head_long.png",
							0, 32, 0, 8)));

			if (clothes == Clothes.CLOTHES) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX(
								"female_clothes.png", 0, 32, 0, 8)));
			} else if (clothes == Clothes.LETHER) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX(
								"female_leather_armor.png", 0, 32, 0, 8)));
			} else if (clothes == Clothes.STEEL) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX(
								"female_steel_armor.png", 0, 32, 0, 8)));

			}
			if (weapon == Weapon.BOW) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX(
								"female_greatbow.png", 0, 32, 0, 8)));
			} else if (weapon == Weapon.SWORD) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX(
								"female_greatsword.png", 0, 32, 0, 8)));
			} else if (weapon == Weapon.STAFF) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX(
								"female_greatstaff.png", 0, 32, 0, 8)));
			} else if (weapon == Weapon.SHIELD) {
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX(
								"female_longsword.png", 0, 32, 0, 8)));
				playerAnimation.addDirectedAnimation(new DirectedAnimation(
						DirectedAnimation.getSpritesAlongX("female_shield.png",
								0, 32, 0, 8)));
			}

		}

		playerStillAnimation = playerAnimation.addNewPartAnimation(0, 4);
		playerMoveAnimation = playerAnimation.addNewPartAnimation(4, 8);
		playerCastAnimation = playerAnimation.addNewPartAnimation(24, 4);
		playerAttackAnimation = playerAnimation.addNewPartAnimation(12, 4);
		playerShootAnimation = playerAnimation.addNewPartAnimation(28, 4);

		playerAnimation.setImageSwitchSpeed(110);

	}

	protected void onDying() {
		if (Model.model.getMyself().getID() == this.getID()) {
			setHealthToMax();
			goToSpawnPoint();
		}
	}

	public void applyDamage(int amount, EffectAnimation ea) {
		super.applyDamage(amount);
		playAnimation(ea);
	}

	protected void playAnimation(EffectAnimation ea) {
		if (ea == EffectAnimation.FIRE) {
			fireAnimation.playAnimationOnce(fireAnimationNr, Math.random());
			Model.model.addTemporaryDecoration(new AttachedAnimatedDecoration(
					this, fireAnimation, -15, -5), 400, true);
		} else if (ea == EffectAnimation.ICE) {
			iceAnimation.playAnimationOnce(iceAnimationNr, Math.random());
			Model.model.addTemporaryDecoration(new AttachedAnimatedDecoration(
					this, iceAnimation, -15, -5), 400, true);
		} else if (ea == EffectAnimation.HEAL) {
			Model.model.addTemporaryDecoration(new AttachedAnimatedDecoration(
					this, healAnimation, -15, -5), 1000, true);
		} else if (ea == EffectAnimation.BLOOD) {
			bloodAnimation.playAnimationOnce(bloodAnimationNr, Math.random());
			Model.model.addTemporaryDecoration(new AttachedAnimatedDecoration(
					this, bloodAnimation, -15, -5), 400, true);
		}
	}

	public void onInvis(boolean isInvisible) {
		if (isInvisible) {
			playerAnimation.setAlpha(0.5f);
		} else {
			playerAnimation.setAlpha(1);
		}
	}

}
