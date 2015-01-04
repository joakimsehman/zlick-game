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

	private Image[] westBody;
	private Image[] northWestBody;
	private Image[] northBody;
	private Image[] northEastBody;
	private Image[] eastBody;
	private Image[] southEastBody;
	private Image[] southBody;
	private Image[] southWestBody;

	private Image[] westHead;
	private Image[] northWestHead;
	private Image[] northHead;
	private Image[] northEastHead;
	private Image[] eastHead;
	private Image[] southEastHead;
	private Image[] southHead;
	private Image[] southWestHead;

	private Image[] westWeapon;
	private Image[] northWestWeapon;
	private Image[] northWeapon;
	private Image[] northEastWeapon;
	private Image[] eastWeapon;
	private Image[] southEastWeapon;
	private Image[] southWeapon;
	private Image[] southWestWeapon;

	private Image[] northSheep;
	private Image[] westSheep;
	private Image[] eastSheep;
	private Image[] southSheep;

	private Image currentBody;
	private Image currentHead;
	private Image currentWeapon;

	private int tileTimeCounter;
	private int tileCounter;

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

		westBody = new Image[32];
		northWestBody = new Image[32];
		northBody = new Image[32];
		northEastBody = new Image[32];
		eastBody = new Image[32];
		southEastBody = new Image[32];
		southBody = new Image[32];
		southWestBody = new Image[32];

		westHead = new Image[32];
		northWestHead = new Image[32];
		northHead = new Image[32];
		northEastHead = new Image[32];
		eastHead = new Image[32];
		southEastHead = new Image[32];
		southHead = new Image[32];
		southWestHead = new Image[32];

		westWeapon = new Image[32];
		northWestWeapon = new Image[32];
		northWeapon = new Image[32];
		northEastWeapon = new Image[32];
		eastWeapon = new Image[32];
		southEastWeapon = new Image[32];
		southWeapon = new Image[32];
		southWestWeapon = new Image[32];

		northSheep = new Image[3];
		westSheep = new Image[3];
		eastSheep = new Image[3];
		southSheep = new Image[3];

		this.name = name;
		energy = 100f;
		abilities = new Ability[4];
		team = Model.Team.GREEN;
		isCasting = false;
		brown = new Color(150, 75, 0);
		castTimeLeft = 0;
		castingSpellAbilityNumber = -1;

		loadImages();
		tileCounter = 0;

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

		// switch(getDirection()){
		// case WEST:
		// g.drawImage(westBody[0], getXPos() - cameraX-40, getYPos() -
		// cameraY-50);
		// break;
		// case NORTHWEST:
		// g.drawImage(northWestBody[0], getXPos() - cameraX-40, getYPos() -
		// cameraY-50);
		// break;
		// case NORTH:
		// g.drawImage(northBody[0], getXPos() - cameraX-40, getYPos() -
		// cameraY-50);
		// break;
		// case NORTHEAST:
		// g.drawImage(northEastBody[0], getXPos() - cameraX-40, getYPos() -
		// cameraY-50);
		// break;
		// case EAST:
		// g.drawImage(eastBody[0], getXPos() - cameraX-40, getYPos() -
		// cameraY-50);
		// break;
		// case SOUTHEAST:
		// g.drawImage(southEastBody[0], getXPos() - cameraX-40, getYPos() -
		// cameraY-50);
		// break;
		// case SOUTH:
		// g.drawImage(southBody[0], getXPos() - cameraX-40, getYPos() -
		// cameraY-50);
		// break;
		// case SOUTHWEST:
		// g.drawImage(southWestBody[0], getXPos() - cameraX-40, getYPos() -
		// cameraY-50);
		// break;
		// default:
		// break;
		//
		// }

		if (!isPolymorphed()) {
			g.drawImage(currentBody, getXPos() - cameraX - 40, getYPos()
					- cameraY - 50);
			g.drawImage(currentHead, getXPos() - cameraX - 40, getYPos()
					- cameraY - 50);
			g.drawImage(currentWeapon, getXPos() - cameraX - 40, getYPos()
					- cameraY - 50);
		} else {
			g.drawImage(currentBody, getXPos() - cameraX, getYPos() - cameraY);
		}

		if (name != null) {
			
			g.setColor(getTeamColor());
			g.drawString(name, getXPos() - cameraX, getYPos() - cameraY - 25);
			if (Model.model.getID() == id) {
				g.drawString("Energy: " + (int) energy, 800, 50);
				g.drawString("Health: " + getHealthPoints(), 800, 70);
			}
		}

		// g.drawRect(getXPos() - cameraX, getYPos() - cameraY, getBoundingBox()
		// .getWidth(), getBoundingBox().getHeight());
	}

	private Color getTeamColor() {
		if(team == Team.GREEN){
			return Color.green;
		}else{
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

	public void update(int delta, ArrayList<Entity> entities) {
		super.update(delta, entities, true);

		if (energy < 100) {
			energy = energy + ((float) delta) / 100;
		}

		tileTimeCounter = tileTimeCounter + delta;
		if (tileTimeCounter > 100) {
			tileTimeCounter = 0;
			tileCounter++;
		}

		
		if(isPolymorphed()){
			if(isMoving()){
				currentBody = getMovingDirectionSheep()[tileCounter % 3];
			}else{
				currentBody = getMovingDirectionSheep()[1];
			}
		}else if(isCasting() && !isMoving()){
			currentBody = getMovingDirectionBodySprites()[24+tileCounter % 4];
			currentHead = getMovingDirectionHeadSprites()[24+tileCounter % 4];
			currentWeapon = getMovingDirectionWeaponSprites()[24 + tileCounter % 4];
		}else{
			if (isMoving()) {
				currentBody = getMovingDirectionBodySprites()[4 + tileCounter % 8];
				currentHead = getMovingDirectionHeadSprites()[4 + tileCounter % 8];
				currentWeapon = getMovingDirectionWeaponSprites()[4 + tileCounter % 8];
			} else {
				currentBody = getMovingDirectionBodySprites()[tileCounter % 4];
				currentHead = getMovingDirectionHeadSprites()[tileCounter % 4];
				currentWeapon = getMovingDirectionWeaponSprites()[tileCounter % 4];
			}
		}
		
		if(isCasting()){
			if(!isMoving() || getAbility(castingSpellAbilityNumber).isCastableWhileMoving()){
				if(castTimeLeft > 0){
					castTimeLeft = castTimeLeft - delta;
				}else{
					isCasting = false;
					useCastedSpell();
				}
			}else{
				isCasting = false;
			}
		}
	}

	

	private Image[] getMovingDirectionWeaponSprites() {
		switch (getDirection()) {
		case WEST:
			return westWeapon;
		case NORTHWEST:
			return northWestWeapon;
		case NORTH:
			return northWeapon;
		case NORTHEAST:
			return northEastWeapon;
		case EAST:
			return eastWeapon;
		case SOUTHEAST:
			return southEastWeapon;
		case SOUTH:
			return southWeapon;
		case SOUTHWEST:
			return southWestWeapon;
		default:
			return null;
		}
	}

	private Image[] getMovingDirectionBodySprites() {
		switch (getDirection()) {
		case WEST:
			return westBody;
		case NORTHWEST:
			return northWestBody;
		case NORTH:
			return northBody;
		case NORTHEAST:
			return northEastBody;
		case EAST:
			return eastBody;
		case SOUTHEAST:
			return southEastBody;
		case SOUTH:
			return southBody;
		case SOUTHWEST:
			return southWestBody;
		default:
			return null;
		}
	}

	private Image[] getMovingDirectionHeadSprites() {
		switch (getDirection()) {
		case WEST:
			return westHead;
		case NORTHWEST:
			return northWestHead;
		case NORTH:
			return northHead;
		case NORTHEAST:
			return northEastHead;
		case EAST:
			return eastHead;
		case SOUTHEAST:
			return southEastHead;
		case SOUTH:
			return southHead;
		case SOUTHWEST:
			return southWestHead;
		default:
			return null;
		}
	}
	
	private Image[] getMovingDirectionSheep(){
		switch(getDirection()){
		case WEST:
		case NORTHWEST:
			return westSheep;
		case NORTH:
		case NORTHEAST:
			return northSheep;
		case EAST:
		case SOUTHEAST:
			return eastSheep;
		case SOUTH:
		case SOUTHWEST:
			return southSheep;
		default:
			return null;
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
	
	public boolean isCasting(){
		return isCasting;
	}

	private void loadImages() {

		for (int x = 0; x < 32; x++) {
			int y = 0;
			westBody[x] = TextureHandler.getInstance().getImageFromSpriteSheet(
					x, y, "steel_armor.png");
			westHead[x] = TextureHandler.getInstance().getImageFromSpriteSheet(
					x, y, "male_head2.png");
			westWeapon[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			northWestBody[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "steel_armor.png");
			northWestHead[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "male_head2.png");
			northWestWeapon[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			northBody[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "steel_armor.png");
			northHead[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "male_head2.png");
			northWeapon[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			northEastBody[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "steel_armor.png");
			northEastHead[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "male_head2.png");
			northEastWeapon[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			eastBody[x] = TextureHandler.getInstance().getImageFromSpriteSheet(
					x, y, "steel_armor.png");
			eastHead[x] = TextureHandler.getInstance().getImageFromSpriteSheet(
					x, y, "male_head2.png");
			eastWeapon[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			southEastBody[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "steel_armor.png");
			southEastHead[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "male_head2.png");
			southEastWeapon[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			southBody[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "steel_armor.png");
			southHead[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "male_head2.png");
			southWeapon[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			southWestBody[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "steel_armor.png");
			southWestHead[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "male_head2.png");
			southWestWeapon[x] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(x, y, "greatsword.png");
		}

		for (int i = 0; i < southSheep.length; i++) {
			southSheep[i] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(9 + i, 0, "animal.png");
		}
		for (int i = 0; i < westSheep.length; i++) {
			westSheep[i] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(9 + i, 1, "animal.png");
		}
		for (int i = 0; i < eastSheep.length; i++) {
			eastSheep[i] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(9 + i, 2, "animal.png");
		}
		for (int i = 0; i < northSheep.length; i++) {
			northSheep[i] = TextureHandler.getInstance()
					.getImageFromSpriteSheet(9 + i, 3, "animal.png");
		}

	}

	public void startCastedAbility(int abilityNumber, float mouseGameX,
			float mouseGameY) {
		if((!isMoving() || getAbility(abilityNumber).isCastableWhileMoving()) && getAbility(abilityNumber).getCost() < this.energy){
			
			castTime = this.getAbility(abilityNumber).getCastTime();
			castTimeLeft = castTime;
			isCasting = true;
			this.castingSpellAbilityNumber = abilityNumber;
			this.castingSpellXPos = mouseGameX;
			this.castingSpellYPos = mouseGameY;
		}
	}
	
	//for network 
	public void setIsCasting(boolean isCasting){
		this.isCasting = isCasting;
	}
	
	private void useCastedSpell() {
		Model.model.finishCastingAbility(castingSpellAbilityNumber, castingSpellXPos, castingSpellYPos);
	}
	
	public int getCastTimeLeft(){
		return castTimeLeft;
	}
	
	public int getCastTime(){
		return castTime;
	}
	
	public int getTileCounter(){
		return tileCounter;
		
	}
	
	
}
