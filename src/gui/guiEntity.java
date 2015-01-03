package gui;

import org.newdawn.slick.Graphics;

public abstract class GuiEntity {

	private int xPos;
	private int yPos;
	
	
	public GuiEntity(int xPos, int yPos){
		this.setxPos(xPos);
		this.setyPos(yPos);
		
	}


	public abstract void draw(Graphics g);
	
	public abstract void update(int delta);
	
	public int getxPos() {
		return xPos;
	}


	public void setxPos(int xPos) {
		this.xPos = xPos;
	}


	public int getyPos() {
		return yPos;
	}


	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
}
