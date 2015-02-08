package gui;

import game.Model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class HealthBar extends GuiEntity {

	private int playerId;
	private Rectangle health;
	private boolean isOnScreen;

	public HealthBar(int xPos, int yPos, int playerId) {
		super(xPos, yPos);
		this.playerId = playerId;
		isOnScreen = false;
		health = new Rectangle(xPos, yPos, 60.0f, 7.0f);
	}

	@Override
	public void draw(Graphics g) {
		if (isOnScreen) {
			g.setColor(Color.black);
			g.drawRect(health.getX()-1, health.getY(), health.getWidth()+2, health.getHeight()+2);
			g.setColor(Color.green);
			g.fillRect(health.getX(), health.getY()+1, health.getWidth(),
					health.getHeight());
		}
	}

	@Override
	public void update(int delta) {
		health.setWidth(60.0f * (((float) Model.model.getPlayer(playerId)
				.getHealthPoints())) / 100);
	}

	public void update1(int delta, float posX, float posY) {
		health.setX(posX - Model.model.getCameraX());
		health.setY(posY - Model.model.getCameraY());
		if (Model.model.isOnScreen(posX, posY)) {
			isOnScreen = true;
		}else{
			isOnScreen = false;
		}
		update(delta);
	}

}
