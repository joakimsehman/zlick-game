package game;

import game.gamestates.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import utilities.SoundHandler;
import utilities.TextureHandler;

public class Application extends StateBasedGame{

	//game state identifiers
    public static final int LOGINSCREEN = 0;
	public static final int MAINMENU = 1;
	public static final int GAME = 2;
	public static final int OLDLOBBY = 3;
	public static final int PROFILE = 4;
    public static final int GAMESELECTION = 5;
    public static final int LOBBY = 6;
    //IMPLEMENT
	
	
	public Application(String gamename) throws SlickException {
		super(gamename);
		Model.model = new Model();
//		this.addState(new MainMenu(MAINMENU));
//		this.addState(new Game(GAME));
//		this.addState(new Lobby(OLDLOBBY));
	}
	
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
			TextureHandler.getInstance().loadTextures();
			SoundHandler.getInstance().loadSounds();
			Model.model.initModel();
//			this.getState(MAINMENU).init(gc, this);
//			this.getState(GAME).init(gc, this);
//			this.getState(OLDLOBBY).init(gc, this);
            this.addState(new LoginScreen(LOGINSCREEN));
            this.addState(new Profile(PROFILE));
			this.addState(new MainMenu(MAINMENU));
			this.addState(new Game(GAME));
			//this.addState(new OldLobby(OLDLOBBY));
            this.addState(new Lobby(LOBBY));
            this.addState(new GameSelection(GAMESELECTION));
	}
	
	public void keyPressed(int key, char c){
		
	}
	
	public void keyReleased(int key, char c){
		
	}
	
	

}
