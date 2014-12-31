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

public class Player extends Minion{

    private int id;
    private String name;
    private Image leftImage;
    private Image rightImage;
    private Image currentImage;
    private float energy;
    private Ability[] abilities;
    private Team team;
    private boolean isMoving;



    private enum Gender{MALE, FEMALE};
    private enum Clothes{CLOTHES, LETHER, STEEL};
    private enum Weapon{BOW, STAFF, SWORD};
	
	
	public Player(float xPos, float yPos, Vector2f vector, Shape boundingBox, int healthPoints, String name, int id) {
		super(xPos, yPos, vector, boundingBox, TextureHandler.getInstance().getImageByName("playerLeft.png"), healthPoints);
		this.id = id;
		leftImage = TextureHandler.getInstance().getImageByName("playerLeft.png");
		rightImage = TextureHandler.getInstance().getImageByName("playerRight.png");
		currentImage = rightImage;
		this.name = name;
		energy = 100f;
		abilities = new Ability[4];
		team = Model.Team.GREEN;
		
	}
	
	public Player(float xPos, float yPos, String name, int id){
		this(xPos, yPos, new Vector2f(0, 0), new Rectangle(50f, 50f, 50, 50), 100, name, id);
	}

	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public void draw(Graphics g, float cameraX, float cameraY) {
		if (getImage() != null) {
			if(this.getVectorX() > 0){
				currentImage = rightImage;
			}else if(this.getVectorX() < 0){
				currentImage = leftImage;
			}
			g.drawImage(currentImage, getXPos() - cameraX, getYPos() - cameraY);
		}
		//System.out.println("" + getName());
		if(name != null){
			
			g.drawString(name, getXPos() - cameraX, getYPos() - cameraY - 20);
			if(Model.model.getID() == id){
				g.drawString("Energy: " + (int)energy, 800, 50);
				g.drawString("Health: " + getHealthPoints(), 800, 70);
			}
		}

        g.drawRect(getXPos() - cameraX, getYPos() - cameraY, getBoundingBox().getWidth(), getBoundingBox().getHeight());
	}
	
	public String getName(){
		return name;
		
	}

	public boolean reduceEnergy(int amount) {
		if(energy - amount < 0){
			return false;
		}else{
			energy = energy - amount;
			return true;
		}
		
		
	}
	
	
	public void update(int delta, ArrayList<Entity> entities){
		super.update(delta, entities, true);

        if(energy < 100){
			energy = energy + ((float)delta)/100;
		}
	}

	public Ability getAbility(int abilityNumber) {
		
		return abilities[abilityNumber-1];
	}

	public void setAbility(Ability ability, int i) {
		
		if(i < 5 && i > 0){
			abilities[i-1] = ability;
		}
		
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public Team getTeam(){
		return team;
	}
}
