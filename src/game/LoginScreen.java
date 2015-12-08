package game;

import gui.Button;
import gui.GuiEntity;
import gui.TextField;
import listener.ButtonListener;
import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import utilities.TextureHandler;

/**
 * Created by joakim on 2015-12-08.
 */
public class LoginScreen implements GameState {

    private final int stateID;

    public LoginScreen(final int gamestate){
        stateID = gamestate;
    }


    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.getInput().addKeyListener(this);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        graphics.setBackground(Color.blue);

        for (int i = 0; i < Model.model.getActiveGui().size(); i++) {
            Model.model.getActiveGui().get(i).draw(graphics);
        }

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        Model.model.updateGui(delta);
    }

    @Override
    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {


        TextField userName = new TextField(100,100, 300, 50);
        Model.model.addActiveGui(userName);

        TextField password = new TextField(100, 300, 300, 50);
        password.setTextHidden(true);
        Model.model.addActiveGui(password);

        final Button loginButton = new Button(100, 500, TextureHandler.getInstance().getImageByName("login_knapp.png"), 300, 150);
        Model.model.addActiveGui(loginButton);

        ButtonListener buttonListener = new ButtonListener() {
            @Override
            public void buttonEvent(Button.ButtonEvent b, int buttonId) {
                if(b == Button.ButtonEvent.BUTTON_CLICKED) {
                    if (buttonId == loginButton.getId()){

                        //TODO: login implement
                    }
                }
            }
        };

        loginButton.addButtonListener(buttonListener);

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
        GuiEntity.keyPressed(i, c);
    }

    @Override
    public void keyReleased(int i, char c) {
        GuiEntity.keyReleased(i, c);
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
        return true;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }
}
