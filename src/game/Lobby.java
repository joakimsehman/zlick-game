package game;

import gui.PlayerCustomizer;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import abilities.Ability;
import utilities.AbilityCreator;
import utilities.TextureHandler;

public class Lobby implements GameState {

	private final int stateID;
	private int selectedAbility;
	private ArrayList<Image> abilityIcons;
	private PlayerCustomizer playerCustomizer;
	
	private Image background;
	

	public Lobby(int GameState) {

		stateID = GameState;
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
		return false;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int arg0, char arg1) {
		// TODO Auto-generated method stub

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
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		background = TextureHandler.getInstance().getImageByName("background.jpg");
		
		selectedAbility = 1;
		abilityIcons.clear();

		// add all abilityIcon that you should be able to choose from
        //right now they must be in order of id
        abilityIcons.add(TextureHandler.getInstance().getImageByName("fireballIcon.png"));
        abilityIcons.add(TextureHandler.getInstance().getImageByName("massPolymorphIcon.png"));
        abilityIcons.add(TextureHandler.getInstance().getImageByName("bolaIcon.png"));
        abilityIcons.add(TextureHandler.getInstance().getImageByName("teleportIcon.png"));
        abilityIcons.add(TextureHandler.getInstance().getImageByName("icelanceIcon.png"));
        
        playerCustomizer = new PlayerCustomizer(gc.getScreenWidth()/2, gc.getScreenHeight()/2);
        Model.model.addActiveGui(playerCustomizer);
	}

	@Override
	public int getID() {
		return stateID;

	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)throws SlickException{
		abilityIcons = new ArrayList<Image>();
	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		// background
		g.drawImage(background, 0, 0);

		// draw player name
		if (Model.model.getMyself() != null) {
			g.drawString(Model.model.getMyself().getName(), 100, 100);
		}

		// draw your abilities
		if (Model.model.getMyself() != null) {
			for (int i = 0; i < 4; i++) {

				if (Model.model.getMyself().getAbility(i + 1) != null) {
					Model.model.getMyself().getAbility(i + 1).getIcon()
							.draw(300 + 40 * i, 90);
				} else {
					g.drawImage(
							TextureHandler.getInstance().getImageByName(
									"questionmarkIcon.png"), 300 + 40 * i, 90);
				}
			}
		}

		// draw team selected string
		if (Model.model.getMyself() != null) {
			if (Model.model.getMyself().getTeam() == Model.Team.GREEN) {
				g.setColor(Color.green);
				g.drawString("Green Team", 470, 100);
			} else if (Model.model.getMyself().getTeam() == Model.Team.BROWN) {
				g.setColor(new Color(150, 75, 0));
				g.drawString("Brown Team", 470, 100);
			}
		}
		
		//draw other team selected strings
		for(int i = 0; i < Model.model.getOtherPlayers().size(); i++){
			if(Model.Team.GREEN == Model.model.getOtherPlayers().get(i).getTeam()){
				g.setColor(Color.green);
				g.drawString("Green Team", 470, 180 + 40* i);
			}else if(Model.Team.BROWN == Model.model.getOtherPlayers().get(i).getTeam()){
				g.setColor(new Color(150, 75, 0));
				g.drawString("Brown Team", 470, 180 + 40* i);
			}
		}

		// draw abilitySelection box
		g.setColor(Color.blue);
		g.drawRect(299 + (selectedAbility - 1) * 40, 89, 31, 31);

		// draw other player names and question mark boxes :)
		for (int i = 0; i < Model.model.getOtherPlayers().size(); i++) {
			g.drawString(Model.model.getOtherPlayers().get(i).getName(), 100,
					180 + 40 * i);
			for (int j = 0; j < 4; j++) {
				g.drawImage(
						TextureHandler.getInstance().getImageByName(
								"questionmarkIcon.png"), 300 + 40 * j,
						170 + 40 * i);
			}
		}

		// draw team selection buttons
		g.drawImage(
				TextureHandler.getInstance()
						.getImageByName("joinGreenTeam.png"), gc
						.getScreenWidth() / 2 - 250, 20);
		g.drawImage(
				TextureHandler.getInstance()
						.getImageByName("joinBrownTeam.png"), gc
						.getScreenWidth() / 2 + 50, 20);

		// draw all ability choices
		for (int i = 0; i < abilityIcons.size(); i++) {
			abilityIcons
					.get(i).draw(gc.getScreenWidth() / 2 + (i % 10) * 40,
							90 + (i / 10) * 40);
		}
		
		//draw gui..
		for (int i = 0; i < Model.model.getActiveGui().size(); i++) {
			Model.model.getActiveGui().get(i).draw(g);
		}
		

		if (Model.model.isServer()) {
			TextureHandler.getInstance().getImageByName("startGame.png")
					.draw(200, gc.getScreenHeight() - 200);
		}
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

		if (Mouse.isButtonDown(0)) {
			int posX = Mouse.getX();
			int posY = Mouse.getY();

			// ability buttons
			if ((posY > gc.getScreenHeight() - 120)
					&& (posY < gc.getScreenHeight() - 90)) {
				if ((posX > 300) && (posX < 330)) {
					selectedAbility = 1;
				} else if ((posX > 340) && (posX < 370)) {
					selectedAbility = 2;
				} else if ((posX > 380) && (posX < 410)) {
					selectedAbility = 3;
				} else if ((posX > 420) && (posX < 450)) {
					selectedAbility = 4;
				}
			}

			// check if ability selection clicked
			for (int i = 0; i < abilityIcons.size(); i++) {
				if (posX > (gc.getScreenWidth() / 2 + (i % 10) * 40)
						&& posX < (gc.getScreenWidth() / 2 + (i % 10) * 40 + 30)) {
					if (posY < (gc.getScreenHeight() - (90 + (i / 10) * 40))
							&& posY > (gc.getScreenHeight() - (90 + (i / 10) * 40 + 30))) {
						Model.model.getMyself().setAbility(
								AbilityCreator.getInstance().getNewAbility(i, Model.model.getMyself().getID()),
								selectedAbility);
					}
				}

			}

			// check if team selection buttons are pressed
			if ((posY < gc.getScreenHeight() - 20)
					&& posY > gc.getScreenHeight() - 70) {
				if (posX > (gc.getScreenWidth() / 2 - 250)
						&& posX < (gc.getScreenWidth() / 2 - 50)) {
					Model.model.setAndSendTeam(Model.Team.GREEN);
				} else if (posX > (gc.getScreenWidth() / 2 + 50)
						&& posX < (gc.getScreenWidth() / 2 + 250)) {
					Model.model.setAndSendTeam(Model.Team.BROWN);
				}
			}

			// game start button
			if (Model.model.isServer()) {

				if ((posX > 200) && (posX < 400)) {
					if (posY < 200 && posY > 150) {
						Model.model.startGame();
						Model.model.startClients();
						sbg.enterState(1);
					}
				}
			}
		}
		
		Model.model.updateGui(delta);

		if (Model.model.isGaming()) {
			sbg.enterState(1);
			Model.model.setAndSendPlayerCustomization(playerCustomizer.getSelectedGender(), playerCustomizer.getSelectedClothes(), playerCustomizer.getSelectedHair(), playerCustomizer.getSelectedWeapon());
		}

	}

}
