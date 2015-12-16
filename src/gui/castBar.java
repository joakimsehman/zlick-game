package gui;

import game.Model;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import utilities.TextureHandler;

public class CastBar extends GuiEntity {

	private Image castbar;
	private Rectangle castTime;

	public CastBar(int xPos, int yPos) {
		super(xPos, yPos);
		castbar = TextureHandler.getInstance().getImageByName("castbar.png");
		castTime = new Rectangle(xPos + 2, yPos + 3, 45, 4);
	}

	// add check with player isCasting

	@Override
	public void draw(Graphics g) {

		if (Model.model.getMyself().isCasting()) {
			g.drawImage(castbar, getxPos(), getyPos());

			g.setColor(Color.yellow);
			g.fillRect(castTime.getX(), castTime.getY(), castTime.getWidth(),
					castTime.getHeight());
		}
	}

	@Override
	public void update(int delta) {
		if (Model.model.getMyself().isChanneling()) {
			castTime.setWidth(45.0f * (((float) Model.model.getMyself()
					.getCastTimeLeft()) / Model.model.getMyself().getCastTime()));
		} else {
			castTime.setWidth(45.0f - 44.0f * (((float) Model.model.getMyself()
					.getCastTimeLeft()) / Model.model.getMyself().getCastTime()));
		}
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
