package gui;

import java.util.ArrayList;
import java.util.List;

import game.Model;
import listener.ButtonListener;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import abilities.Ability.AbilityCategory;
import abilities.Ability.AbilityType;
import abilities.AbilityInfo;
import utilities.TextureHandler;

/**
 * Created by joakim on 2015-12-14.
 */
public class AbilitySelection extends GuiEntity implements ButtonListener {

	private Image background;
	private Image chosenAbilitySpace, chosenUltimateSpace;
	private Image abilitySpace, ultimateAbilitySpace;
	private ChangableIconButton[] abilityButtons;
	private int selectedAbility;
	private Button[] abilityChoiceButtons;

	private int currentHoveringAbility;

	public AbilitySelection(int xPos, int yPos) {
		super(xPos, yPos);
		selectedAbility = 0;
		currentHoveringAbility = -1;
		background = TextureHandler.getInstance().getImageByName("abilitySelectionBackground.png");

		chosenAbilitySpace = TextureHandler.getInstance().getImageByName("chosenAbilitySpace.png");
		chosenUltimateSpace = TextureHandler.getInstance().getImageByName("chosenUltimateSpace.png");
		abilitySpace = TextureHandler.getInstance().getImageByName("abilitySpace.png");
		ultimateAbilitySpace = TextureHandler.getInstance().getImageByName("ultimateAbilitySpace.png");

		abilityButtons = new ChangableIconButton[3];
		abilityChoiceButtons = new Button[AbilityInfo.getInstance().getNumberOfLobbyAbilities()];

		for (int i = 0; i < 3; i++) {
			abilityButtons[i] = new ChangableIconButton(getxPos() + 50 + 94 * i, getyPos() + 53, chosenAbilitySpace, 94, 94);
			abilityButtons[i].addButtonListener(this);
		}

		int abilityCount = 0;
		int ultimateCount = 0;
		for (int i = 0; i < AbilityInfo.getInstance().getNumberOfLobbyAbilities(); i++) {

			Button button;
			if (AbilityInfo.getInstance().getAbilityCategory(i) == AbilityCategory.NORMAL) {
				button = new Button(getxPos() + 70 + (abilityCount % 4) * 60, getyPos() + 175 + (abilityCount / 4) * 60, AbilityInfo.getInstance()
						.getSmallSpellIconFromId(i), 56, 56);
				abilityCount++;
				button.addButtonListener(this);
				abilityChoiceButtons[i] = button;
			} else if(AbilityInfo.getInstance().getAbilityCategory(i) == AbilityCategory.ULTIMATE){
				button = new Button(getxPos() + 355, getyPos() + 175 + (ultimateCount * 60), AbilityInfo.getInstance().getSmallSpellIconFromId(i), 56, 56);
				ultimateCount++;
				button.addButtonListener(this);
				abilityChoiceButtons[i] = button;
			}
			
			//			}else{
			//				
			//				Button button = new Button(getxPos() + 270, getyPos() + 175 + (ultimateCount * 60), AbilityInfo.getInstance().getSmallSpellIconFromId(i), 56, 56);
			//				
			//				button.addButtonListener(this);
			//				ultimateChoiceButtons.add(button);
			//				ultimateCount++;
			//			}

		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(background, getxPos(), getyPos());
		g.drawImage(chosenUltimateSpace, getxPos() + 330, getyPos() + 46);
		g.drawImage(abilitySpace, getxPos() + 50, getyPos() + 155);
		g.drawImage(ultimateAbilitySpace, getxPos() + 330, getyPos() + 155);

		//draw selectedAbility box
		g.setColor(Color.white);
		g.drawRect(getxPos() + 55 + 94 * selectedAbility, getyPos() + 58, 90, 87);
		
		if(Model.model.getMyself().getAbility(4) != null){
			g.drawImage(Model.model.getMyself().getAbility(4).getIcon(), getxPos() + 342, getyPos() + 56);
		}
		

		for (int i = 0; i < abilityButtons.length; i++) {
			abilityButtons[i].draw(g);
		}

		for (int i = 0; i < abilityChoiceButtons.length; i++) {
			abilityChoiceButtons[i].draw(g);
		}
	}

	@Override
	protected void update(int delta) {
		for (int i = 0; i < abilityButtons.length; i++) {
			abilityButtons[i].update(delta);
		}

		currentHoveringAbility = -1;
		for (int i = 0; i < abilityChoiceButtons.length; i++) {
			abilityChoiceButtons[i].update(delta);
			if (abilityChoiceButtons[i].intersects(Mouse.getX(), Model.model.getScreenHeight() - Mouse.getY())) {
				currentHoveringAbility = i;
			}
		}

	}

	@Override
	protected boolean isSelectable() {
		return false;
	}

	@Override
	public boolean intersects(int x, int y) {
		return false;
	}

	@Override
	public void buttonEvent(Button.ButtonEvent b, int buttonId) {
		if (b == Button.ButtonEvent.BUTTON_CLICKED) {
			for (int i = 0; i < abilityButtons.length; i++) {
				if (buttonId == abilityButtons[i].getId()) {
					selectedAbility = i;
				}
			}

			for (int i = 0; i < abilityChoiceButtons.length; i++) {
				if (buttonId == abilityChoiceButtons[i].getId()) {

					if (AbilityInfo.getInstance().getAbilityCategory(i) == AbilityCategory.NORMAL) {
						
						Model.model.getMyself().setAbility(AbilityInfo.getInstance().getNewAbility(i, Model.model.getMyself().getID()), selectedAbility + 1);
						abilityButtons[selectedAbility].setIcon(Model.model.getMyself().getAbility(selectedAbility + 1).getIcon());
						
					} else if(AbilityInfo.getInstance().getAbilityCategory(i) == AbilityCategory.ULTIMATE){
						Model.model.getMyself().setAbility(AbilityInfo.getInstance().getNewAbility(i, Model.model.getMyself().getID()), 4);
					}

				}
			}
		}
	}

	public String getHoveringAbilityDescription() {
		if (currentHoveringAbility != -1) {
			return AbilityInfo.getInstance().getDescriptionFromId(currentHoveringAbility);
		} else {
			return "";
		}
	}

	private class ChangableIconButton extends Button {

		private Image icon;

		public ChangableIconButton(int xPos, int yPos, Image image, int width, int height) {
			super(xPos, yPos, image, width, height);
			icon = null;
		}

		public void draw(Graphics g) {
			super.draw(g);

			if (icon != null) {
				g.drawImage(icon, getxPos() + 11, getyPos() + 8);
			}
		}

		public void setIcon(Image image) {
			icon = image;
		}
	}

}
