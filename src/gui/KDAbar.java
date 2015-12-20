package gui;

import game.Model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import utilities.TextureHandler;

public class KDAbar extends GuiEntity{
	
	private int minutes, seconds;
	
	private int height, width;
	
	private Image background;
	
	public KDAbar(int xPos, int yPos) {
		super(xPos, yPos);
		
		minutes = 0;
		seconds = 0;
		
		height = 121;
		width = 381;
		
		background = TextureHandler.getInstance().getImageByName("kda.png");
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(background, getxPos(), getyPos());
		
		g.setColor(Color.white);
		g.drawString("" + Model.model.getStatistics().getPlayerKillsById(Model.model.getMyself().getID()), getxPos() + 70, getyPos() + 35);
		g.drawString("" + Model.model.getStatistics().getPlayerDeathCountById(Model.model.getMyself().getID()), getxPos() + 180, getyPos() + 35);
		g.drawString(minutes + ":" + seconds, getxPos() + 250, getyPos() + 35);
	}

	@Override
	protected void update(int delta) {
		
		minutes = Model.model.getStatistics().getGameTime() / 60000;
		seconds = (Model.model.getStatistics().getGameTime() % 60000) / 1000;
		
		
	}

	@Override
	protected boolean isSelectable() {
		return false;
	}

	@Override
	public boolean intersects(int x, int y) {
		return false;
	}

	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
