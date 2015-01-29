package gui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;

public class Button extends GuiEntity{

	private Mouse mouse;
	
	public Button(int xPos, int yPos, Mouse mouse) {
		super(xPos, yPos);
		mouse = mouse;
	}

	@Override
	public void draw(Graphics g) {
		
		
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

}
