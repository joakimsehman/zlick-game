package gui;

import game.Model;
import listener.ButtonListener;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import utilities.TextureHandler;

/**
 * Created by joakim on 2015-12-14.
 */
public class TeamSelection extends GuiEntity implements ButtonListener {

    private Image background;
    private Button joinGreen, joinBrown;

    public TeamSelection(int xPos, int yPos) {
        super(xPos, yPos);
        background = TextureHandler.getInstance().getImageByName("teamselectionbackground.png");

        joinGreen = new Button(getxPos() + 40, getyPos() + 40, TextureHandler.getInstance().getImageByName("joinTeam.png"), 190, 50);
        joinBrown = new Button(getxPos() + 220, getyPos() + 40, TextureHandler.getInstance().getImageByName("joinTeam.png"), 190, 50);

        joinGreen.addButtonListener(this);
        joinBrown.addButtonListener(this);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, getxPos(), getyPos());

        int greenPlayerCount = 0;
        int brownPlayerCount = 0;

        g.setColor(Color.black);
        if(Model.Team.GREEN == Model.model.getMyself().getTeam()){
            g.drawString(Model.model.getMyself().getName(), getxPos() + 60, getyPos() + 95 + 20*greenPlayerCount);

            greenPlayerCount++;
        }else{
            g.drawString(Model.model.getMyself().getName(), getxPos() + 230, getyPos() + 95 + 20*brownPlayerCount);

            brownPlayerCount++;
        }

        joinGreen.draw(g);
        joinBrown.draw(g);

        for(int i = 0; i < Model.model.getOtherPlayers().size(); i++){
            if(Model.Team.GREEN == Model.model.getOtherPlayers().get(i).getTeam()){
                g.drawString(Model.model.getOtherPlayers().get(i).getName(), getxPos()+ 60, getyPos() + 80 + 20*greenPlayerCount);

                greenPlayerCount++;
            }else{
                g.drawString(Model.model.getOtherPlayers().get(i).getName(), getxPos() + 60, getyPos() + 80 + 20*brownPlayerCount);

                brownPlayerCount++;
            }
        }
    }

    @Override
    protected void update(int delta) {
        joinBrown.update(delta);
        joinGreen.update(delta);
    }

    @Override
    protected boolean isSelectable() {
        return false;
    }

    @Override
    public boolean intersects(int x, int y) {
        return false;
    }

    @Override
    public void buttonEvent(Button.ButtonEvent b, int buttonId) {
        if(b == Button.ButtonEvent.BUTTON_CLICKED){
            if(buttonId == joinGreen.getId()){
                Model.model.setAndSendTeam(Model.Team.GREEN);
            }else if(buttonId == joinBrown.getId()){
                Model.model.setAndSendTeam(Model.Team.BROWN);
            }
        }
    }
}
