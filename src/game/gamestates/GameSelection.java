package game.gamestates;

import database.HostGameManager;
import database.LoginInfo;
import game.Application;
import game.Model;
import gui.Button;
import gui.GamesViewer;
import listener.ButtonListener;
import org.newdawn.slick.*;
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

        
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        super.update(gameContainer, stateBasedGame, i);
    }

    @Override
    public void enter(GameContainer gameContainer, final StateBasedGame stateBasedGame) throws SlickException {
        super.enter(gameContainer, stateBasedGame);

        final GamesViewer gamesViewer = new GamesViewer(160, 210);
        Model.model.addActiveGui(gamesViewer);

        final Button hostGameButton = new Button(637 ,555, TextureHandler.getInstance().getImageByName("hostButton.png"), 200, 50);
        Model.model.addActiveGui(hostGameButton);

        final Button joinGameButton = new Button(343, 555, TextureHandler.getInstance().getImageByName("joinButton.png"), 200, 50);
        Model.model.addActiveGui(joinGameButton);

        ButtonListener buttonListener = new ButtonListener(){

            @Override
            public void buttonEvent(Button.ButtonEvent b, int buttonId) {
                if(b == Button.ButtonEvent.BUTTON_CLICKED){
                    if(buttonId == hostGameButton.getId()){
                        Model.model.setName(LoginInfo.getInstance().getNick());
                        Model.model.createHost();

                        HostGameManager.getInstance().hostNewGame();

                        stateBasedGame.enterState(Application.LOBBY);

                    }else if(buttonId == joinGameButton.getId()){

                        Model.model.setName(LoginInfo.getInstance().getNick());
                        if(Model.model.createClient(gamesViewer.getSelectedGameGlobalIp())){
                        	stateBasedGame.enterState(Application.LOBBY);
                        }else if(Model.model.createClient(gamesViewer.getSelectedGameLocalIp())){
                        	stateBasedGame.enterState(Application.LOBBY);
                        }
                    }
                }
            }
        };

        hostGameButton.addButtonListener(buttonListener);
        joinGameButton.addButtonListener(buttonListener);

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
