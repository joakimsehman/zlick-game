package gui;

import game.Model;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public abstract class GuiEntity {

	private int xPos;
	private int yPos;
    private int id;

    private static int idCounter = 0;
	
	private static GuiEntity selectedElement = null;

	public GuiEntity(int xPos, int yPos){
		this.setxPos(xPos);
		this.setyPos(yPos);

        id = idCounter++;
    }


	public abstract void draw(Graphics g);
	
	protected abstract void update(int delta);

    protected abstract boolean isSelectable();

    public abstract boolean intersects(int x, int y);
	
	public int getxPos() {
		return xPos;
	}


	public void setxPos(int xPos) {
		this.xPos = xPos;
	}


	public int getyPos() {
		return yPos;
	}


	public void setyPos(int yPos) {
		this.yPos = yPos;
	}


    public int getGuiId() {
        return id;
    }

    public boolean isSelected(){
        if(selectedElement == null){
            return false;
        }

        if(this.getGuiId() == selectedElement.getGuiId()){
            return true;
        }else{
            return false;
        }
    }

    public void setSelectedEntity(GuiEntity guiEntity){
        selectedElement = guiEntity;
    }

    public static void updateGUI(int delta){
        ArrayList<GuiEntity> activeGui = Model.getModel().getActiveGui();
        for(int i = 0; i < activeGui.size(); i++){

            GuiEntity crntGui = activeGui.get(i);

            if(Mouse.isButtonDown(0) && crntGui.isSelectable() && crntGui.intersects(Mouse.getX(), Model.model.getScreenHeight() - Mouse.getY())){
                selectedElement = crntGui;
                selectedElement.onSelected();
            }

            crntGui.update(delta);
        }

    }

    public void onSelected(){}

    public static void resetGui(){
        selectedElement = null;
    }

    public static void keyPressed(int keyNr, char c) {
        if(selectedElement != null){
            selectedElement.onSelectedKeyPressed(keyNr, c);
        }
    }

    public static void keyReleased(int keyNr, char c) {
        if(selectedElement != null){
            selectedElement.onSelectedKeyReleased(keyNr, c);
        }
    }

    public void onSelectedKeyPressed(int keyNr, char c){}

    public void onSelectedKeyReleased(int keyNr, char c){}
}
