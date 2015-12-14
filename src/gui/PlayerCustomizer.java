package gui;

import entities.Player.Clothes;
import entities.Player.Gender;
import entities.Player.Hair;
import entities.Player.Weapon;
import gui.Button.ButtonEvent;
import listener.ButtonListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animation.AnimationGroup;
import animation.DirectedAnimation;
import org.newdawn.slick.Image;
import utilities.TextureHandler;

public class PlayerCustomizer extends GuiEntity implements ButtonListener{

	private AnimationGroup animation;

    private Image background;
	
	private Button leftHeadButton;
	private Button rightHeadButton;
	private Button leftBodyButton;
	private Button rightBodyButton;
	private Button leftWeaponButton;
	private Button rightWeaponButton;
	private Button setFemaleButton;
	private Button setMaleButton;
	
	private Gender gender;
	private Clothes clothes;
	private Weapon weapon;
	private Hair hair;
	
	public PlayerCustomizer(int xPos, int yPos) {
		super(xPos, yPos);
		
		leftHeadButton = new Button(xPos, yPos, TextureHandler.getInstance().getImageByName("leftArrow.png"), 50, 50);
		leftBodyButton = new Button(xPos, yPos+50, TextureHandler.getInstance().getImageByName("leftArrow.png"), 50, 50);
		leftWeaponButton = new Button(xPos, yPos+100, TextureHandler.getInstance().getImageByName("leftArrow.png"), 50, 50);
		
		rightHeadButton = new Button(xPos + 128 + 50, yPos, TextureHandler.getInstance().getImageByName("rightArrow.png"), 50, 50);
		rightBodyButton = new Button(xPos + 128+ 50, yPos+50, TextureHandler.getInstance().getImageByName("rightArrow.png"), 50, 50);
		rightWeaponButton = new Button(xPos + 128+ 50, yPos + 100, TextureHandler.getInstance().getImageByName("rightArrow.png"), 50, 50);
		
		setFemaleButton = new Button(xPos + 64, yPos + 150, TextureHandler.getInstance().getImageByName("female.png"), 50, 50);
		setMaleButton = new Button(xPos + 114, yPos + 150, TextureHandler.getInstance().getImageByName("male.png"), 50, 50);
		
		leftHeadButton.addButtonListener(this);
		leftBodyButton.addButtonListener(this);
		leftWeaponButton.addButtonListener(this);
		rightHeadButton.addButtonListener(this);
		rightBodyButton.addButtonListener(this);
		rightWeaponButton.addButtonListener(this);
		setFemaleButton.addButtonListener(this);
		setMaleButton.addButtonListener(this);
		
		gender = Gender.MALE;
		clothes = Clothes.STEEL;
		weapon = Weapon.SWORD;
		hair = Hair.NORMAL;

        background = TextureHandler.getInstance().getImageByName("playerCustomizerBackground.png");
		
		reinitializeAnimation();
	}
	
	private void reinitializeAnimation(){
		animation = new AnimationGroup();
		
		
		
		if(gender == Gender.MALE){
			if(hair == Hair.NORMAL){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("male_head1.png", 0, 32, 0, 8)));
			}else if(hair == Hair.BALD){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("male_head2.png", 0, 32, 0, 8)));
			}else if(hair == Hair.HOOD){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("male_head3.png", 0, 32, 0, 8)));
			}
			
			if(clothes == Clothes.CLOTHES){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("male_clothes.png", 0, 32, 0, 8)));
			}else if(clothes == Clothes.LETHER){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("male_leather_armor.png", 0, 32, 0, 8)));
			}else if(clothes == Clothes.STEEL){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("male_steel_armor.png", 0, 32, 0, 8)));
				
			}
			if(weapon == Weapon.BOW){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("male_greatbow.png", 0, 32, 0, 8)));
			}else if(weapon == Weapon.SWORD){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("male_greatsword.png", 0, 32, 0, 8)));
			}else if(weapon == Weapon.STAFF){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("male_greatstaff.png", 0, 32, 0, 8)));
			}else if(weapon == Weapon.SHIELD){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("male_longsword.png", 0, 32, 0, 8)));
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("male_shield.png", 0, 32, 0, 8)));
			}
			
		}else{
			animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("female_head_long.png", 0, 32, 0, 8)));
			
			if(clothes == Clothes.CLOTHES){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("female_clothes.png", 0, 32, 0, 8)));
			}else if(clothes == Clothes.LETHER){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("female_leather_armor.png", 0, 32, 0, 8)));
			}else if(clothes == Clothes.STEEL){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("female_steel_armor.png", 0, 32, 0, 8)));
				
			}
			if(weapon == Weapon.BOW){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("female_greatbow.png", 0, 32, 0, 8)));
			}else if(weapon == Weapon.SWORD){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("female_greatsword.png", 0, 32, 0, 8)));
			}else if(weapon == Weapon.STAFF){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("female_greatstaff.png", 0, 32, 0, 8)));
			}else if(weapon == Weapon.SHIELD){
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("female_longsword.png", 0, 32, 0, 8)));
				animation.addDirectedAnimation(new DirectedAnimation(DirectedAnimation.getSpritesAlongX("female_shield.png", 0, 32, 0, 8)));
			}
			
		}
		
		
		
	}

	@Override
	public void draw(Graphics g) {
        g.drawImage(background, getxPos(), getyPos());

		leftHeadButton.draw(g);
		rightHeadButton.draw(g);
		leftBodyButton.draw(g);
		rightBodyButton.draw(g);
		leftWeaponButton.draw(g);
		rightWeaponButton.draw(g);
		setFemaleButton.draw(g);
		setMaleButton.draw(g);
		g.setColor(Color.white);
		g.fillRect(getxPos()+50, getyPos(), 128, 150);
		animation.draw(g, getxPos()+50, getyPos()+25);
		
	}

	@Override
	public void update(int delta) {
		leftHeadButton.update(delta);
		rightHeadButton.update(delta);
		leftBodyButton.update(delta);
		rightBodyButton.update(delta);
		leftWeaponButton.update(delta);
		rightWeaponButton.update(delta);
		setFemaleButton.update(delta);
		setMaleButton.update(delta);
		animation.update(delta, 0);
	}

    @Override
    protected boolean isSelectable() {
        return false;
    }

    @Override
    public boolean intersects(int x, int y) {
        return false;
    }

    @Override
	public void buttonEvent(ButtonEvent b, int buttonId) {
		if(b == ButtonEvent.BUTTON_CLICKED){
			if(buttonId == leftHeadButton.getId()){
				hair = hair.getPrevious();
			}else if(buttonId == rightHeadButton.getId()){
				hair = hair.getNext();
			}else if(buttonId == leftBodyButton.getId()){
				clothes = clothes.getPrevious();
			}else if(buttonId == rightBodyButton.getId()){
				clothes = clothes.getNext();
			}else if(buttonId == leftWeaponButton.getId()){
				weapon = weapon.getPrevious();
			}else if(buttonId == rightWeaponButton.getId()){
				weapon = weapon.getNext();
			}else if(buttonId == setFemaleButton.getId()){
				gender = Gender.FEMALE;
			}else if(buttonId == setMaleButton.getId()){
				gender = Gender.MALE;
			}
			reinitializeAnimation();
		}
	}
	
	public Gender getSelectedGender(){
		return gender;
	}
	
	public Clothes getSelectedClothes(){
		return clothes;
	}
	
	public Weapon getSelectedWeapon(){
		return weapon;
	}
	
	public Hair getSelectedHair(){
		return hair;
	}

}
