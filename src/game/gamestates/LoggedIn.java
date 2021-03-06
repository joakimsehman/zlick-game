package game.gamestates;

import game.Application;
import game.Model;
import gui.Button;
import listener.ButtonListener;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import utilities.TextureHandler;

/**
 * Created by joakim on 2015-12-08.
 */
public abstract class LoggedIn implements GameState {


    private final int stateID;
    private Image loggedInWindow;
    private Image background;

    public LoggedIn(int gameState){
        stateID = gameState;
    }

    @Override
    public int getID() {
        return stateID;
    }


    @Override
    public void enter(GameContainer gameContainer, final StateBasedGame stateBasedGame) throws SlickException {

    	
    	loggedInWindow = TextureHandler.getInstance().getImageByName("loggedInWindow.png");
    	background = TextureHandler.getInstance().getImageByName("loggedInBackground.png");
    	
        final Button gamesButton = new Button(218, 95, TextureHandler.getInstance().getImageByName("gamesButton.png"), 285, 90);
        final Button profileButton = new Button(505, 95, TextureHandler.getInstance().getImageByName("profileButton.png"), 285, 90);
        final Button abilitiesButton = new Button(791, 95, TextureHandler.getInstance().getImageByName("abilitiesButton.png"), 285, 90);

        Model.model.addActiveGui(gamesButton);
        Model.model.addActiveGui(profileButton);
        Model.model.addActiveGui(abilitiesButton);

        ButtonListener buttonListener = new ButtonListener() {

            @Override
            public void buttonEvent(Button.ButtonEvent b, int buttonId) {
                if (b == Button.ButtonEvent.BUTTON_CLICKED) {
                    if (buttonId == gamesButton.getId()) {
                        if(stateID != Application.GAMESELECTION){
                            stateBasedGame.enterState(Application.GAMESELECTION);
                        }
                    } else if (buttonId == profileButton.getId()) {
                        if(stateID != Application.PROFILE){
                            stateBasedGame.enterState(Application.PROFILE);
                        }
                    } else if (buttonId == abilitiesButton.getId()) {

                    }

                } else if (b == Button.ButtonEvent.BUTTON_PRESSED) {

                }
            }
        };

        gamesButton.addButtonListener(buttonListener);
        profileButton.addButtonListener(buttonListener);
        abilitiesButton.addButtonListener(buttonListener);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

    	graphics.drawImage(background, 0, 0);
    	
    	graphics.drawImage(loggedInWindow, 60, 60);
    	
        for (int i = 0; i < Model.model.getActiveGui().size(); i++) {
            Model.model.getActiveGui().get(i).draw(graphics);
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        Model.model.updateGui(delta);
    }

    @Override
    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Model.model.clearGui();
    }
}