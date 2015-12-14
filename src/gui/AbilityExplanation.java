package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import utilities.TextureHandler;

/**
 * Created by joakim on 2015-12-14.
 */
public class AbilityExplanation extends GuiEntity {

    private Image background;

    public AbilityExplanation(int xPos, int yPos) {
        super(xPos, yPos);

        background = TextureHandler.getInstance().getImageByName("abilityExplanationBackground.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, getxPos(), getyPos());
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
