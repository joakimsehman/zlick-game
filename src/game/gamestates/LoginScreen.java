package game.gamestates;

import database.LoginInfo;
import game.Application;
import game.Model;
import gui.Button;
import gui.GuiEntity;
import gui.TextField;
import listener.ButtonListener;
import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import database.DatabaseConfiguration;
import utilities.SoundHandler;
import utilities.TextureHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by joakim on 2015-12-08.
 */
public class LoginScreen implements GameState {

    private final int stateID;
    private String message;
    private Image background;

    public LoginScreen(final int gamestate){
        stateID = gamestate;
        message = "";
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

        graphics.drawImage(background, 0, 0);

        for (int i = 0; i < Model.model.getActiveGui().size(); i++) {
            Model.model.getActiveGui().get(i).draw(graphics);
        }
        graphics.setColor(Color.white);
        graphics.drawString("Username:", 488, 210);
        graphics.drawString("Password:", 488, 300);
        graphics.setColor(Color.red);
        graphics.drawString(message, 488, 400);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        Model.model.updateGui(delta);
    }

    @Override
    public void enter(GameContainer gameContainer, final StateBasedGame stateBasedGame) throws SlickException {


        final TextField userName = new TextField(488,240, 300, 50);
        userName.setBackgroundImage(TextureHandler.getInstance().getImageByName("textfield.png"));
        Model.model.addActiveGui(userName);



        final TextField password = new TextField(488, 330, 300, 50);
        password.setBackgroundImage(TextureHandler.getInstance().getImageByName("textfield.png"));
        password.setTextHidden(true);
        Model.model.addActiveGui(password);

        final Button loginButton = new Button(485, 450, TextureHandler.getInstance().getImageByName("signin.png"), 310, 52);
        Model.model.addActiveGui(loginButton);


        ButtonListener buttonListener = new ButtonListener() {
            @Override
            public void buttonEvent(Button.ButtonEvent b, int buttonId) {
                if(b == Button.ButtonEvent.BUTTON_CLICKED) {
                    if (buttonId == loginButton.getId()){

                    	if(LoginInfo.getInstance().logIn(userName.getText(), password.getText())){
                    		message = "login successfull";
                    		SoundHandler.getInstance().loginMusic.stop();
                            stateBasedGame.enterState(Application.PROFILE);
                    	}else{
                    		message = "login failed";
                    	}
                        
                    	                        
                    }
                }
            }
        };

        loginButton.addButtonListener(buttonListener);

        SoundHandler.getInstance().loginMusic.loop();

        background = TextureHandler.getInstance().getImageByName("loginbackground.png");

    }

    @Override
    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Model.model.clearGui();
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
