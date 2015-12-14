package gui;

import game.Model;
import listener.ButtonListener;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import utilities.AbilityCreator;
import utilities.TextureHandler;

/**
 * Created by joakim on 2015-12-14.
 */
public class AbilitySelection extends GuiEntity implements ButtonListener {

    private Image background;
    private Image chosenAbilitySpace, chosenUltimateSpace;
    private Image abilitySpace, ultimateAbilitySpace;
    private ChangableIconButton[] abilityButtons;
    private int selectedAbility;
    private Button[] abilityChoiceButtons;

    public AbilitySelection(int xPos, int yPos) {
        super(xPos, yPos);
        selectedAbility = 0;
        background = TextureHandler.getInstance().getImageByName("abilitySelectionBackground.png");

        chosenAbilitySpace = TextureHandler.getInstance().getImageByName("chosenAbilitySpace.png");
        chosenUltimateSpace = TextureHandler.getInstance().getImageByName("chosenUltimateSpace.png");
        abilitySpace = TextureHandler.getInstance().getImageByName("abilitySpace.png");
        ultimateAbilitySpace = TextureHandler.getInstance().getImageByName("ultimateAbilitySpace.png");

        abilityButtons = new ChangableIconButton[3];
        abilityChoiceButtons = new Button[AbilityCreator.getInstance().getNumberOfAbilities()];

        for(int i = 0; i < 3; i++){
            abilityButtons[i] = new ChangableIconButton(getxPos() + 50 + 94*i, getyPos() + 53, chosenAbilitySpace, 94, 94);
            abilityButtons[i].addButtonListener(this);
        }

        for(int i = 0; i < abilityChoiceButtons.length; i++){
            abilityChoiceButtons[i] = new Button(getxPos()+ 70 + (i % 4) * 60,
                    getyPos() + 175 + (i / 4) * 60, AbilityCreator.getInstance().getSmallSpellIconFromId(i), 56, 56);
            abilityChoiceButtons[i].addButtonListener(this);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, getxPos(), getyPos());
        g.drawImage(chosenUltimateSpace, getxPos()+ 330, getyPos() + 46);
        g.drawImage(abilitySpace, getxPos() + 50, getyPos() + 155);
        g.drawImage(ultimateAbilitySpace, getxPos() + 330, getyPos() + 155);

        //draw selectedAbility box
        g.setColor(Color.white);
        g.drawRect(getxPos() + 55 + 94*selectedAbility, getyPos() + 58, 90, 87);

        for(int i = 0; i < abilityButtons.length; i++){
            abilityButtons[i].draw(g);
        }

        for(int i = 0; i < abilityChoiceButtons.length; i++){
            abilityChoiceButtons[i].draw(g);
        }
    }

    @Override
    protected void update(int delta) {
        for(int i = 0; i < abilityButtons.length; i++){
            abilityButtons[i].update(delta);
        }

        for(int i = 0; i < abilityChoiceButtons.length; i++){
            abilityChoiceButtons[i].update(delta);
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

    @Override
    public void buttonEvent(Button.ButtonEvent b, int buttonId) {
        if(b == Button.ButtonEvent.BUTTON_CLICKED){
            for(int i = 0; i < abilityButtons.length; i++){
                if(buttonId == abilityButtons[i].getId()){
                    selectedAbility = i;
                }
            }

            for(int i = 0; i < abilityChoiceButtons.length; i++){
                if(buttonId == abilityChoiceButtons[i].getId()){
                    Model.model.getMyself().setAbility(
                            AbilityCreator.getInstance().getNewAbility(i, Model.model.getMyself().getID()),
                            selectedAbility + 1);
                    abilityButtons[selectedAbility].setIcon(Model.model.getMyself().getAbility(selectedAbility + 1).getIcon());
                }
            }
        }
    }

    private class ChangableIconButton extends Button{

        private Image icon;

        public ChangableIconButton(int xPos, int yPos, Image image, int width, int height) {
            super(xPos, yPos, image, width, height);
            icon = null;
        }

        public void draw(Graphics g){
            super.draw(g);

            if(icon != null){
                g.drawImage(icon, getxPos() + 11, getyPos() + 8);
            }
        }

        public void setIcon(Image image){
            icon = image;
        }
    }
}
