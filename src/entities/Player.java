package entities;

import game.Model;
import game.Model.Team;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import abilities.Ability;
import abilities.Fireball;
import utilities.TextureHandler;

public class Player extends Minion {

	private int id;
	private String name;
	private float energy;
	private Ability[] abilities;
	private Team team;

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
		southWestHead= new Image[32];
		
		westWeapon = new Image[32];
		northWestWeapon = new Image[32];
		northWeapon = new Image[32];
		northEastWeapon = new Image[32];
		eastWeapon = new Image[32];
		southEastWeapon = new Image[32];
		southWeapon = new Image[32];
		southWestWeapon = new Image[32];
		

		this.name = name;
		energy = 100f;
		abilities = new Ability[4];
		team = Model.Team.GREEN;
		
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
		
//		switch(getDirection()){
//		case WEST:
//			g.drawImage(westBody[0], getXPos() - cameraX-40, getYPos() - cameraY-50);
//			break;
//		case NORTHWEST:
//			g.drawImage(northWestBody[0], getXPos() - cameraX-40, getYPos() - cameraY-50);
//			break;
//		case NORTH:
//			g.drawImage(northBody[0], getXPos() - cameraX-40, getYPos() - cameraY-50);
//			break;
//		case NORTHEAST:
//			g.drawImage(northEastBody[0], getXPos() - cameraX-40, getYPos() - cameraY-50);
//			break;
//		case EAST:
//			g.drawImage(eastBody[0], getXPos() - cameraX-40, getYPos() - cameraY-50);
//			break;
//		case SOUTHEAST:
//			g.drawImage(southEastBody[0], getXPos() - cameraX-40, getYPos() - cameraY-50);
//			break;
//		case SOUTH:
//			g.drawImage(southBody[0], getXPos() - cameraX-40, getYPos() - cameraY-50);
//			break;
//		case SOUTHWEST:
//			g.drawImage(southWestBody[0], getXPos() - cameraX-40, getYPos() - cameraY-50);
//			break;
//		default:
//			break;
//			
//		}
		
		
		g.drawImage(currentBody, getXPos() - cameraX-40, getYPos()-cameraY-50);
		g.drawImage(currentHead, getXPos() - cameraX-40, getYPos()-cameraY-50);
		g.drawImage(currentWeapon, getXPos() - cameraX-40, getYPos()-cameraY-50);
		
		
		
		
		if (name != null) {

			g.drawString(name, getXPos() - cameraX, getYPos() - cameraY - 20);
			if (Model.model.getID() == id) {
				g.drawString("Energy: " + (int) energy, 800, 50);
				g.drawString("Health: " + getHealthPoints(), 800, 70);
			}
		}

//		g.drawRect(getXPos() - cameraX, getYPos() - cameraY, getBoundingBox()
//				.getWidth(), getBoundingBox().getHeight());
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
		if(tileTimeCounter > 100){
			tileTimeCounter = 0;
			tileCounter++;
		}
		
		if(isMoving()){
			currentBody = getMovingDirectionBodySprites()[4+tileCounter%8];
			currentHead = getMovingDirectionHeadSprites()[4+tileCounter%8];
			currentWeapon = getMovingDirectionWeaponSprites()[4+tileCounter%8];
		}else{
			currentBody = getMovingDirectionBodySprites()[tileCounter%4];
			currentHead = getMovingDirectionHeadSprites()[tileCounter%4];
			currentWeapon = getMovingDirectionWeaponSprites()[tileCounter%4];
		}
	}
	
	private Image[] getMovingDirectionWeaponSprites() {
		switch(getDirection()){
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

	private Image[] getMovingDirectionBodySprites(){
		switch(getDirection()){
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
	
	private Image[] getMovingDirectionHeadSprites(){
		switch(getDirection()){
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

	private void loadImages() {

		for (int x = 0; x < 32; x++) {	
			int y = 0;
			westBody[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "steel_armor.png");
			westHead[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "male_head2.png");
			westWeapon[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			northWestBody[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "steel_armor.png");
			northWestHead[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "male_head2.png");
			northWestWeapon[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			northBody[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "steel_armor.png");
			northHead[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "male_head2.png");
			northWeapon[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			northEastBody[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "steel_armor.png");
			northEastHead[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "male_head2.png");
			northEastWeapon[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			eastBody[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "steel_armor.png");
			eastHead[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "male_head2.png");
			eastWeapon[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			southEastBody[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "steel_armor.png");
			southEastHead[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "male_head2.png");
			southEastWeapon[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			southBody[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "steel_armor.png");
			southHead[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "male_head2.png");
			southWeapon[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "greatsword.png");
			y++;
			southWestBody[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "steel_armor.png");
			southWestHead[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "male_head2.png");
			southWestWeapon[x] = TextureHandler.getInstance().getImageFromSpriteSheet(x, y, "greatsword.png");
		}

	}
}
