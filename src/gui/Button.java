package gui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Button extends GuiEntity{

	private Mouse mouse;
	private Image button;
	private int height;
	private int width;
	private boolean buttonClicked;
	private boolean buttonPressed;
	
	
	public Button(int xPos, int yPos, Mouse mouse, Image image, int width, int height) {
		super(xPos, yPos);
		buttonClicked = false;
		buttonPressed = false;
		
		button = image;
		this.mouse = mouse;
		this.width = width;
		this.height = height;
	}

	
	public void draw(Graphics g) {
		g.drawImage(button, getxPos(), getyPos());	
	}

	@Override
	public void update(int delta) {
		
		if(!mouse.isButtonDown(0) && buttonPressed && (mouse.getX() > getxPos() && mouse.getX() <= getxPos() + width && mouse.getY() > getyPos() && mouse.getY() <= getyPos() + height)){
			buttonClicked = true;
		}
		
		if(mouse.isButtonDown(0) && (mouse.getX() > getxPos() && mouse.getX() <= getxPos() + width && mouse.getY() > getyPos() && mouse.getY() <= getyPos() + height)){
			buttonPressed = true;
		}else{
			buttonPressed = false;
		}
			
	}

}
