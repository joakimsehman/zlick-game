package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import utilities.TextureHandler;

public class MainMenu implements GameState {

	
	private final int stateID;
	private String nameStr;
	private String ipStr;
	private Image background;

	private enum STRINGS {
		ipStr, nameStr
	};

	private STRINGS activeStr;

	public MainMenu(int GAMESTATE) {
		
		stateID = GAMESTATE;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		nameStr = "Enter name here";
		ipStr = "";
		activeStr = STRINGS.ipStr;

		gc.getInput().addKeyListener(this);
		background = TextureHandler.getInstance().getImageByName("420-background.png");
		
	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int keyNr, char c) {
		

		if (activeStr == STRINGS.nameStr) {
			if (keyNr == 14 && !nameStr.isEmpty()) {
				
				nameStr = nameStr.substring(0, nameStr.length()-1);
			} else {
				nameStr = nameStr + c;
			}
		} else if (activeStr == STRINGS.ipStr) {
			if (keyNr == 14 && !ipStr.isEmpty()) {
				
				ipStr = ipStr.substring(0, ipStr.length()-1);
			} else {
				ipStr = ipStr + c;
			}
		}
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownPressed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftPressed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightPressed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpPressed(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpReleased(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(background, 0, 0);
		
		g.drawString("Welcome to The BlazePlaze Game", 200, 100);
		TextureHandler.getInstance().getImageByName("hostGame.png")
				.draw(200, 200);
		TextureHandler.getInstance().getImageByName("joinGame.png")
				.draw(200, 300);
		TextureHandler.getInstance().getImageByName("exitGame.png")
				.draw(200, 400);

		
		//name textfield
		g.setColor(Color.red);
		g.drawString("Enter name here:", 800, 20);
		g.setColor(Color.white);
		g.fillRect(800, 50, 200, 50);
		g.setColor(Color.green);
		g.drawString(nameStr, 800, 70);
		
		//ip textfield
		g.setColor(Color.red);
		g.drawString("Enter IP-adress here:", 800, 270);
		g.setColor(Color.white);
		g.fillRect(800, 300, 200, 50);
		g.setColor(Color.green);
		g.drawString(ipStr, 800, 320);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();

		if (Mouse.isButtonDown(0)) {
			if ((posX > 200) && (posX < 400)) {

				if (posY < gc.getScreenHeight() - 200
						&& posY > gc.getScreenHeight() - 250) {
					// hostGame button
					Model.model.setName(nameStr);
					Model.model.createHost();
					gc.getInput().removeKeyListener(this);
					sbg.enterState(2);

				} else if (posY < gc.getScreenHeight() - 300
						&& posY > gc.getScreenHeight() - 350) {
					 Model.model.setName(nameStr);
					 Model.model.createClient(ipStr);
					 gc.getInput().removeKeyListener(this);
					 sbg.enterState(2);
					// joinGame button

				} else if (posY < gc.getScreenHeight() - 400
						&& posY > gc.getScreenHeight() - 450) {
					// exitGame button
					System.exit(0);
				}
			} else if (posX > 800 && posX < 1000) {
				if (posY < gc.getScreenHeight() - 50
						&& posY > gc.getScreenHeight() - 100) {
					//name Textfield
					activeStr = STRINGS.nameStr;
				}else if(posY < gc.getScreenHeight() - 300 && posY > gc.getScreenHeight() - 350){
					//ip textfield
					activeStr = STRINGS.ipStr;
				}
			}
		}

	}

}
