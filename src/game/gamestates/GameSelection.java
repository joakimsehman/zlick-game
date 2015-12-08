package game.gamestates;

import database.LoginInfo;
import game.Application;
import game.Model;
import gui.Button;
import listener.ButtonListener;
import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import utilities.TextureHandler;

/**
 * Created by joakim on 2015-12-08.
 */
public class GameSelection extends LoggedIn {


    public GameSelection(int gameState){
        super(gameState);
    }


    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        super.render(gameContainer, stateBasedGame, graphics);

        graphics.setColor(Color.cyan);
        graphics.fillRect(100, 220, 1100, 450);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        super.update(gameContainer, stateBasedGame, i);
    }

    @Override
    public void enter(GameContainer gameContainer, final StateBasedGame stateBasedGame) throws SlickException {
        super.enter(gameContainer, stateBasedGame);

        final Button hostGameButton = new Button(1000 ,700, TextureHandler.getInstance().getImageByName("hostGame.png"), 200, 50);
        Model.model.addActiveGui(hostGameButton);

        ButtonListener buttonListener = new ButtonListener(){

            @Override
            public void buttonEvent(Button.ButtonEvent b, int buttonId) {
                if(b == Button.ButtonEvent.BUTTON_CLICKED){
                    if(buttonId == hostGameButton.getId()){
                        Model.model.setName(LoginInfo.getInstance().getNick());
                        Model.model.createHost();

                        stateBasedGame.enterState(Application.LOBBY);
                    }
                }
            }
        };

        hostGameButton.addButtonListener(buttonListener);

    }

    @Override
    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        super.leave(gameContainer, stateBasedGame);
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
