package game.gamestates;

import game.Model;
import map.Level;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import utilities.SoundHandler;
import utilities.TextureHandler;
import entities.Entity;
import gui.AbilityBar;
import gui.CastBar;

public class Game implements GameState {

	private final int stateID;

	public Game(int GAME) {

		stateID = GAME;
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
	public void mousePressed(int button, int x, int y) {
		System.out.println("button: " + button);
		
		if (button == 0) {
			Model.model.useMouseAttack(button, x, y);
		}else if(button == 1){
			Model.model.clickToMove();
		}
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
		switch (keyNr) {
		case 2:
			// 1 pressed
			Model.model.useAbility(1, Mouse.getX(), Model.model.getScreenHeight() - Mouse.getY());
			break;
		case 3:
			// 2 pressed
			Model.model.useAbility(2, Mouse.getX(), Model.model.getScreenHeight() - Mouse.getY());
			break;
		case 4:
			// 3 pressed
			Model.model.useAbility(3, Mouse.getX(), Model.model.getScreenHeight() - Mouse.getY());
			break;
		case 5:
			// 4 pressed
			Model.model.useAbility(4, Mouse.getX(), Model.model.getScreenHeight() - Mouse.getY());
			break;
		case 57:
			// Space pressed
			Model.model.useAbility(5, Mouse.getX(), Model.model.getScreenHeight() - Mouse.getY());
			break;
		}

	}

	@Override
	public void keyReleased(int keyNr, char c) {
		
		if(keyNr == 17 || (keyNr > 29 && keyNr < 33)){
			Model.model.onMovementKeyRelease();
		}
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
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub

		Model.model.initLevel(new Level());

		
		
		Model.model.setFullScreen();
		//Model.model.setDisplayMode(1920, 1080, false);

		Model.model.clearGui();
		Model.model.addActiveGui(new CastBar(gc.getScreenWidth() / 2, gc.getScreenHeight() / 2 - 10));
		Model.model.addActiveGui(new AbilityBar(gc.getScreenWidth() / 2 - 206, gc.getScreenHeight() - 137));

		gc.getInput().addKeyListener(this);

		SoundHandler.getInstance().menuMusic.stop();

	}

	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame arg1) throws SlickException {

	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, StateBasedGame app, Graphics g) throws SlickException {

		float cameraX = Model.model.getCameraX();
		float cameraY = Model.model.getCameraY();

		Model.model.getLevel().render((int) -cameraX, (int) -cameraY);

		for (int i = 0; i < Model.model.getTemporaryDecorations().size(); i++) {
			Model.model.getTemporaryDecorations().get(i).draw(g, cameraX, cameraY);
		}

		for (int i = 0; i < Model.model.getActiveSpells().size(); i++) {
			Model.model.getActiveSpells().get(i).draw(g, cameraX, cameraY);
		}

		Model.model.getMyself().draw(g, cameraX, cameraY);
		for (Entity e : Model.model.getTerrain()) {
			e.draw(g, cameraX, cameraY);
		}
		for (int i = 0; i < Model.model.getTemporaryMinions().size(); i++) {
			Model.model.getTemporaryMinions().get(i).draw(g, cameraX, cameraY);
		}
		for (int i = 0; i < Model.model.getOtherPlayers().size(); i++) {
			Model.model.getOtherPlayers().get(i).draw(g, cameraX, cameraY);
		}

		for (int i = 0; i < Model.model.getTemporaryAbovePlayerDecorations().size(); i++) {
			Model.model.getTemporaryAbovePlayerDecorations().get(i).draw(g, cameraX, cameraY);
		}

		Model.model.getLevel().renderDecorations((int) -cameraX, (int) -cameraY);

		// shows coordinates and stuff
		//		g.setColor(Color.blue);
		//		g.drawString("cameraX:" + cameraX + "  cameraY:" + cameraY
		//				+ "   playerX:" + Model.model.getMyself().getXPos()
		//				+ "   playerY:" + Model.model.getMyself().getYPos(), 100, 100);

		// draw gui
		for (int i = 0; i < Model.model.getActiveGui().size(); i++) {
			Model.model.getActiveGui().get(i).draw(g);
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame app, int delta) throws SlickException {

		
		
		boolean leftKeyPressed = false;
		boolean rightKeyPressed = false;
		boolean upKeyPressed = false;
		boolean downKeyPressed = false;

		if (gc.getInput().isKeyDown(17)) {
			upKeyPressed = true;
		}
		if (gc.getInput().isKeyDown(31)) {
			downKeyPressed = true;
		}
		if (gc.getInput().isKeyDown(30)) {
			leftKeyPressed = true;
		}
		if (gc.getInput().isKeyDown(32)) {
			rightKeyPressed = true;
		}

		Model.model.handlePlayerMovement(upKeyPressed, downKeyPressed, leftKeyPressed, rightKeyPressed);

		Model.model.getMyself().update(delta, Model.model.getTerrain());

		for (int i = 0; i < Model.model.getOtherPlayers().size(); i++) {
			Model.model.getOtherPlayers().get(i).update(delta, null);
		}

		Model.model.updateTemporaryMinions(delta);

		// calculating camera position
		float cameraX = Model.model.getMyself().getXPos() - gc.getScreenWidth() / 2;
		float cameraY = Model.model.getMyself().getYPos() - gc.getScreenHeight() / 2;

		Model.model.setCamera(cameraX, cameraY);

		Model.model.updateMouseGameXY(Mouse.getX(), gc.getScreenHeight() - Mouse.getY());

		Model.model.updateSpells(delta);

		Model.model.updateTemporaryDecorations(delta);

		Model.model.checkForExpiredSpells();

		Model.model.updateGui(delta);

	}

}
