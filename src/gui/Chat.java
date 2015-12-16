package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import utilities.TextureHandler;

import java.util.ArrayList;
import java.util.List;

public class Chat extends GuiEntity{

    private List<String> messages;
    private Image background;

    public Chat(int xPos, int yPos) {
        super(xPos, yPos);
        messages = new ArrayList<String>();
        background = TextureHandler.getInstance().getImageByName("chatArea.png");
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
