package game.gamestates;

import game.Application;
import game.Model;
import gui.Button;
import gui.Button.ButtonEvent;
import gui.PlayerCustomizer;

import java.util.ArrayList;

import listener.ButtonListener;

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
import abilities.AbilityInfo;
import utilities.SoundHandler;
import utilities.TextureHandler;

public class OldLobby implements GameState, ButtonListener{

	private final int stateID;
	private int selectedAbility;
	private PlayerCustomizer playerCustomizer;
	
	private Button[] abilityButtons;
	private int[] abilityChoiceButtonId;
	
	private Image background;
	

	public OldLobby(int gameState) {

		stateID = gameState;
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
		
		abilityButtons = new Button[4];
		abilityChoiceButtonId = new int[AbilityInfo.getInstance().getNumberOfAbilities()];
		
		for(int i = 0; i < 4; i++){
			abilityButtons[i] = new Button(300 + 85 * i, 90, TextureHandler.getInstance().getImageByName("questionmarkIcon.png"), 81, 81);
			Model.model.addActiveGui(abilityButtons[i]);
			abilityButtons[i].addButtonListener(this);
		}
		
		for(int i = 0; i < abilityChoiceButtonId.length; i++){
//			// draw all ability choices
			Button button = new Button(gc.getScreenWidth() / 2 + (i % 5) * 85,
					90 + (i / 5) * 85, AbilityInfo.getInstance().getSpellIconFromId(i), 81, 81);
			Model.model.addActiveGui(button);
			abilityChoiceButtonId[i] = button.getId();
			button.addButtonListener(this);
		}
		

        
        playerCustomizer = new PlayerCustomizer(gc.getScreenWidth()/2, gc.getScreenHeight()/2);
        Model.model.addActiveGui(playerCustomizer);
	}

	@Override
	public int getID() {
		return stateID;

	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)throws SlickException{
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

		// draw team selected string
		if (Model.model.getMyself() != null) {
			if (Model.model.getMyself().getTeam() == Model.Team.GREEN) {
				g.setColor(Color.green);
				g.drawString("Green Team", 670, 100);
			} else if (Model.model.getMyself().getTeam() == Model.Team.BROWN) {
				g.setColor(new Color(150, 75, 0));
				g.drawString("Brown Team", 670, 100);
			}
		}
		
		//draw other team selected strings
		for(int i = 0; i < Model.model.getOtherPlayers().size(); i++){
			if(Model.Team.GREEN == Model.model.getOtherPlayers().get(i).getTeam()){
				g.setColor(Color.green);
				g.drawString("Green Team", 670, 225 + 40* i);
			}else if(Model.Team.BROWN == Model.model.getOtherPlayers().get(i).getTeam()){
				g.setColor(new Color(150, 75, 0));
				g.drawString("Brown Team", 670, 225 + 40* i);
			}
		}

		// draw abilitySelection box
		g.setColor(Color.blue);
		g.drawRect(299 + (selectedAbility - 1) * 85, 89, 82, 82);

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

		// draw team selection buttons TODO: change these to buttons
		g.drawImage(
				TextureHandler.getInstance()
						.getImageByName("joinGreenTeam.png"), gc
						.getScreenWidth() / 2 - 250, 20);
		g.drawImage(
				TextureHandler.getInstance()
						.getImageByName("joinBrownTeam.png"), gc
						.getScreenWidth() / 2 + 50, 20);


		
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
						sbg.enterState(Application.GAME);
					}
				}
			}
		}
		
		Model.model.updateGui(delta);

		if (Model.model.isGaming()) {
			sbg.enterState(Application.GAME);
			Model.model.setAndSendPlayerCustomization(playerCustomizer.getSelectedGender(), playerCustomizer.getSelectedClothes(), playerCustomizer.getSelectedHair(), playerCustomizer.getSelectedWeapon());
        }

	}

	@Override
	public void buttonEvent(ButtonEvent b, int buttonId) {
		if(b == ButtonEvent.BUTTON_CLICKED){
			for(int i = 0; i < abilityButtons.length; i++){
				if(buttonId == abilityButtons[i].getId()){
					selectedAbility = i+1;
				}
			}
			
			for(int i = 0; i < abilityChoiceButtonId.length; i++){
				if(buttonId == abilityChoiceButtonId[i]){
					Model.model.getMyself().setAbility(
							AbilityInfo.getInstance().getNewAbility(i, Model.model.getMyself().getID()),
							selectedAbility);
					abilityButtons[selectedAbility-1].setImage(Model.model.getMyself().getAbility(selectedAbility).getIcon());
				}
			}
		}
		
	}

}
