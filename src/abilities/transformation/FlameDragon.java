package abilities.transformation;

import abilities.Ability;
import animation.AnimationGroup;
import animation.DirectedAnimation;

public class FlameDragon extends TransformationAbility{

	private AnimationGroup dragonAnimation;
	
	private int hoverAnimation;
	private int flyAnimation;
	private int stingAnimation;
	private int breatheAnimation;
	private int ramAnimation;
	private int hitAnimation;
	private int deathAnimation;
	
	private Ability[] dragonAbilities;
	
	public FlameDragon(String name, int playerCreatedId) {
		super(name,  playerCreatedId);
		
		
		dragonAnimation = new AnimationGroup();
		dragonAnimation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("wyvern_fire.png", 0, 56, 0, 8)));
		dragonAnimation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("wyvern_shadow.png", 0, 56, 0, 8)));
		
		hoverAnimation = dragonAnimation.addNewPartAnimation(0, 8);
		flyAnimation = dragonAnimation.addNewPartAnimation(8, 8);
		stingAnimation = dragonAnimation.addNewPartAnimation(16, 8);
		breatheAnimation = dragonAnimation.addNewPartAnimation(24, 8);
		ramAnimation = dragonAnimation.addNewPartAnimation(32, 8);
		hitAnimation = dragonAnimation.addNewPartAnimation(40, 8);
		deathAnimation = dragonAnimation.addNewPartAnimation(48, 8);
		
		dragonAbilities = new Ability[4];
		
		dragonAbilities[0] = new Roar("Roar", playerCreatedId, breatheAnimation);
		dragonAbilities[1] = new Bite("Bite", playerCreatedId, hitAnimation);
		dragonAbilities[2] = new Dash("Dash", playerCreatedId, ramAnimation);
		dragonAbilities[3] = new Spit("Spit", playerCreatedId, breatheAnimation);
		
	}

	@Override
	public Ability[] getTransformedAbilities() {
		return dragonAbilities;
	}

	@Override
	public int getId() {
		return 8;
	}

	@Override
	public int getDuration() {
		
		return 10000;
	}

	@Override
	public AnimationGroup getTransformAnimation() {
		
		return dragonAnimation;
	}

	@Override
	public int getWalkingAnimation() {
		return flyAnimation;
	}

	@Override
	public int getStandingAnimation() {
		return hoverAnimation;
	}

	@Override
	public int getCost() {
		return 50;
	}

	@Override
	public int getCastTime() {
		return 500;
	}

	@Override
	public boolean isCastableWhileMoving() {
		return false;
	}

	@Override
	public int getCooldown() {
		return 60000;
	}

	@Override
	public boolean isCastable(int id, float mouseGameX, float mouseGameY) {
		return true;
	}

	
}
