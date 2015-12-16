package gui;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

import utilities.TextureHandler;

/**
 * Created by joakim on 2015-12-14.
 */
public class AbilityExplanation extends GuiEntity {

	private Image background;
	private AbilitySelection abilitySelection;
	private static boolean antiAlias = true;
	private TrueTypeFont descriptionFont;
	private TrueTypeFont titleFont;

	public AbilityExplanation(int xPos, int yPos,
			AbilitySelection abilitySelection) {
		super(xPos, yPos);

		this.abilitySelection = abilitySelection;
		background = TextureHandler.getInstance().getImageByName(
				"abilityExplanationBackground.png");

		Font awtDescriptionFont = new Font("Felix Titling Regular", Font.BOLD, 12);
		Font awtTitleFont = new Font("Felix Titling Regular", Font.BOLD, 15);
		descriptionFont = new TrueTypeFont(awtDescriptionFont, antiAlias);
		titleFont = new TrueTypeFont(awtTitleFont, antiAlias);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(background, getxPos(), getyPos());
		g.setColor(Color.black);

		String description = abilitySelection.getHoveringAbilityDescription();
		String[] lines = description.split("\n");
		
		g.fillRect(getxPos()+ 236, getyPos()+ 50, 371, 177);
		
		if(lines.length > 0){
			titleFont.drawString(getxPos() + 240, getyPos() + 50, lines[0], Color.white);
		}
		int textOffset = 54;
		for (int i = 1; i < lines.length; i++) {
			descriptionFont.drawString(getxPos() + 240, getyPos() + (textOffset += descriptionFont.getHeight()),
					lines[i],
					Color.white);
		}
		g.setColor(Color.white);
		g.drawString("Tip: instant spells can\nbe casted while casting", getxPos() + 15, getyPos() + 115);
	}

	@Override
	protected void update(int delta) {

	}

	@Override
	protected boolean isSelectable() {
		return false;
	}

	@Override
	public boolean intersects(int x, int y) {
		return false;
	}
}
