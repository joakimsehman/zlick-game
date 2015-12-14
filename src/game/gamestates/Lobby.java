package game.gamestates;

import game.Application;
import game.Model;
import gui.*;
import listener.ButtonListener;
import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import utilities.TextureHandler;

/**
 * Created by joakim on 2015-12-14.
 */
public class Lobby implements GameState{

    private int stateId;
    private Image background;
    private PlayerCustomizer playerCustomizer;
    private Button startButton;


    public Lobby(int gameState){
        this.stateId = gameState;
    }

    public int getID() {
        return stateId;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(background, 0, 0);

        for (int i = 0; i < Model.model.getActiveGui().size(); i++) {
            Model.model.getActiveGui().get(i).draw(graphics);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        Model.model.updateGui(delta);

        if (Model.model.isGaming()) {
            stateBasedGame.enterState(Application.GAME);
            Model.model.setAndSendPlayerCustomization(playerCustomizer.getSelectedGender(), playerCustomizer.getSelectedClothes(), playerCustomizer.getSelectedHair(), playerCustomizer.getSelectedWeapon());
        }
    }

    @Override
    public void enter(GameContainer gameContainer, final StateBasedGame stateBasedGame) throws SlickException {
        background = TextureHandler.getInstance().getImageByName("lobbybackground.png");

        Chat chat = new Chat(25, 450);
        Model.model.addActiveGui(chat);

        TeamSelection teamSelection = new TeamSelection(25, 45);
        Model.model.addActiveGui(teamSelection);

        playerCustomizer = new PlayerCustomizer(505, 45);
        Model.model.addActiveGui(playerCustomizer);

        AbilitySelection abilitySelection = new AbilitySelection(788, 45);
        Model.model.addActiveGui(abilitySelection);

        AbilityExplanation abilityExplanation = new AbilityExplanation(650,450);
        Model.model.addActiveGui(abilityExplanation);

        startButton = new Button(670, 500, TextureHandler.getInstance().getImageByName("startGame.png"), 215, 70);
        Model.model.addActiveGui(startButton);

        startButton.addButtonListener(new ButtonListener() {
            @Override
            public void buttonEvent(Button.ButtonEvent b, int buttonId) {
                if(b == Button.ButtonEvent.BUTTON_CLICKED){
                    if(buttonId == startButton.getId()){
                        if(Model.model.isServer()){
                            Model.model.startGame();
                            Model.model.startClients();
                            stateBasedGame.enterState(Application.GAME);
                        }
                    }
                }
            }
        });

    }

    @Override
    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void controllerLeftPressed(int i) {

    }

    @Override
    public void controllerLeftReleased(int i) {

    }

    @Override
    public void controllerRightPressed(int i) {

    }

    @Override
    public void controllerRightReleased(int i) {

    }

    @Override
    public void controllerUpPressed(int i) {

    }

    @Override
    public void controllerUpReleased(int i) {

    }

    @Override
    public void controllerDownPressed(int i) {

    }

    @Override
    public void controllerDownReleased(int i) {

    }

    @Override
    public void controllerButtonPressed(int i, int i2) {

    }

    @Override
    public void controllerButtonReleased(int i, int i2) {

    }

    @Override
    public void keyPressed(int i, char c) {

    }

    @Override
    public void keyReleased(int i, char c) {

    }

    @Override
    public void mouseWheelMoved(int i) {

    }

    @Override
    public void mouseClicked(int i, int i2, int i3, int i4) {

    }

    @Override
    public void mousePressed(int i, int i2, int i3) {

    }

    @Override
    public void mouseReleased(int i, int i2, int i3) {

    }

    @Override
    public void mouseMoved(int i, int i2, int i3, int i4) {

    }

    @Override
    public void mouseDragged(int i, int i2, int i3, int i4) {

    }

    @Override
    public void setInput(Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return false;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }

}
