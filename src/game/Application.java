package game;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import entities.Entity;
import entities.Terrain;
import utilities.TextureHandler;

public class Application extends StateBasedGame{

	//game state identifiers
	public static final int MAINMENU = 0;
	public static final int GAME = 1;
	public static final int LOBBY = 2;  //IMPLEMENT
	
	
	public Application(String gamename) throws SlickException {
		super(gamename);
		Model.model = new Model();
//		this.addState(new MainMenu(MAINMENU));
//		this.addState(new Game(GAME));
//		this.addState(new Lobby(LOBBY));
	}
	
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
			TextureHandler.getInstance().loadTextures();
			Model.model.initModel();
//			this.getState(MAINMENU).init(gc, this);
//			this.getState(GAME).init(gc, this);
//			this.getState(LOBBY).init(gc, this);
			this.addState(new MainMenu(MAINMENU));
			this.addState(new Game(GAME));
			this.addState(new Lobby(LOBBY));
	}
	
	
	
	
	public void keyPressed(int key, char c){
		
	}
	
	public void keyReleased(int key, char c){
		
	}
	
	

}
