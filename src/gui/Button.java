package gui;

import game.Model;
import listener.ButtonListener;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Button extends GuiEntity {

	public enum ButtonEvent {
		BUTTON_CLICKED, BUTTON_PRESSED
	};

	private static int buttonIdCounter = 0;

	private int id;

	private Image button;
	private int height;
	private int width;
	private boolean buttonPressed;
	private ButtonListener buttonListener;

	public Button(int xPos, int yPos, Image image, int width, int height) {
		super(xPos, yPos);
		buttonPressed = false;

		button = image;
		this.width = width;
		this.height = height;
		id = buttonIdCounter++;
	}

	public void addButtonListener(ButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	public void draw(Graphics g) {
		g.drawImage(button, getxPos(), getyPos());
	}

	@Override
	public void update(int delta) {

		float actualY = Model.model.getScreenHeight() - Mouse.getY();
		if (!Mouse.isButtonDown(0) && buttonPressed	&& (Mouse.getX() > getxPos() && Mouse.getX() <= getxPos() + width && actualY > getyPos() && actualY <= getyPos() + height)) {
			if (buttonListener != null) {
				buttonListener.buttonEvent(ButtonEvent.BUTTON_CLICKED, id);
			}
		}

		if ((Mouse.isButtonDown(0) && Mouse.getX() > getxPos() && Mouse.getX() <= getxPos() + width	&& actualY > getyPos() && actualY <= getyPos() + height)) {
			buttonPressed = true;
			if (buttonListener != null) {
				buttonListener.buttonEvent(ButtonEvent.BUTTON_PRESSED, id);
			}
		} else {
			buttonPressed = false;
		}

	}
	
	public boolean getButtonPressed(){
		return buttonPressed;
	}

	public int getId() {
		return id;
	}

}
