package gui;

import game.Model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import utilities.TextureHandler;

public class AbilityBar extends GuiEntity {

	private Image actionBar;

	public AbilityBar(int xPos, int yPos) {
		super(xPos, yPos);
		actionBar = TextureHandler.getInstance()
				.getImageByName("actionbar.png");
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(actionBar, getxPos(), getyPos());
		
		for (int i = 0; i < 4; i++) {
			if (Model.model.getMyself().getAbility(i + 1) != null) {
				g.drawImage(
						Model.model.getMyself().getAbility(i + 1).getIcon(),
						getxPos() + 34 + 87 * i, getyPos() + 34);
				if(Model.model.getMyself().getAbility(i+1).getCooldown() > Model.model.getMyself().getAbility(i+1).getMsSinceLastUse()){
					g.setColor(Color.white);
					g.drawString("" + (Model.model.getMyself().getAbility(i+1).getCooldown() - Model.model.getMyself().getAbility(i+1).getMsSinceLastUse()), getxPos() + 36 + 87 * i, getyPos() + 36);
				}
				g.setColor(Color.yellow);
				g.drawString("" + (Model.model.getMyself().getAbility(i+1).getCost()), getxPos() + 36 + 87 * i, getyPos() + 95);
			}
		}
		
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub

	}

}
