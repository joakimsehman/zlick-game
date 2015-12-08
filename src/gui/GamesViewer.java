package gui;

import org.newdawn.slick.Graphics;

/**
 * Created by joakim on 2015-12-08.
 */
public class GamesViewer extends GuiEntity {

    public GamesViewer(int xPos, int yPos) {
        super(xPos, yPos);
    }

    @Override
    public void draw(Graphics g) {

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
