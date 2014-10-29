package entities;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import utilities.TextureHandler;

public class Player extends Minion{
	
	private int id;
	private String name;
	private Image leftImage; 
	private Image rightImage;
	private Image currentImage;
	private float energy;
	
	
	public Player(float xPos, float yPos, Vector2f vector, Shape boundingBox, int healthPoints, String name, int id) {
		super(xPos, yPos, vector, boundingBox, TextureHandler.getInstance().getImageByName("playerLeft.png"), healthPoints);
		this.id = id;
		leftImage = TextureHandler.getInstance().getImageByName("playerLeft.png");
		rightImage = TextureHandler.getInstance().getImageByName("playerRight.png");
		currentImage = rightImage;
		this.name = name;
		energy = 100f;
		
	}
	
	public Player(float xPos, float yPos, String name, int id){
		super(xPos, yPos, new Vector2f(0, 0), new Rectangle(50f, 50f, 50, 50), TextureHandler.getInstance().getImageByName("playerRight.png"), 100);
		this.id = id;
		leftImage = TextureHandler.getInstance().getImageByName("playerLeft.png");
		rightImage = TextureHandler.getInstance().getImageByName("playerRight.png");
		currentImage = rightImage;
		this.name = name;
		energy = 100f;
		
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
			g.drawString("Energy: " + (int)energy, 800, 50);
		}
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
		super.update(delta, entities);
		if(energy < 100){
			energy = energy + ((float)delta)/100;
		}
	}
	
}
