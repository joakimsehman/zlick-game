package gui;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;

/**
 * Created by joakim on 2015-12-08.
 */
public class TextField extends GuiEntity {

    private int width, height;
    private String text;
    private boolean textHidden;
    private int backSpaceCounter;
    private Color backgroundColor, textColor;
    private Image backgroundImage;
    private boolean hasBackgroundImage;

    public TextField(int xPos, int yPos, int width, int height) {
        super(xPos, yPos);

        this.width = width;
        this.height = height;

        text = "";
        backSpaceCounter = 0;

        backgroundColor = Color.white;
        textColor = Color.black;

        hasBackgroundImage = false;

        textHidden = false;
    }

    public void setBackgroundImage(Image image){
        backgroundImage = image;
        hasBackgroundImage = true;
    }

    @Override
    public void draw(Graphics g) {
        if(!hasBackgroundImage) {
            g.setColor(backgroundColor);
            g.fillRect(getxPos(), getyPos(), width, height);
        }else{
            g.drawImage(backgroundImage, getxPos(), getyPos());
        }
        g.setColor(textColor);
        if(!textHidden) {
            g.drawString(text, getxPos(), getyPos() + height / 3);
        }else{
            StringBuffer outputBuffer = new StringBuffer(text.length());
            for (int i = 0; i < text.length(); i++){
                outputBuffer.append("*");
            }
            g.drawString(outputBuffer.toString(), getxPos(), getyPos() + height / 3);
        }
    }

    @Override
    public void update(int delta) {

        if(isSelected()) {
            if (Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
                backSpaceCounter += delta;
                if (backSpaceCounter > 100) {
                    if (text.length() > 0) {
                        text = text.substring(0, text.length() - 1);
                    }
                    backSpaceCounter -= 100;
                }
            } else {
                backSpaceCounter = 0;
            }
        }
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setBackgroundColor(Color color){
        backgroundColor = color;
    }

    public void setTextColor(Color color){
        textColor = color;
    }

    public void setTextHidden(boolean textHidden){
        this.textHidden = textHidden;
    }

    @Override
    protected boolean isSelectable() {
        return true;
    }

    @Override
    public void onSelected(){
    }

    @Override
    public boolean intersects(int x, int y) {

        if(x > this.getxPos() && y > this.getyPos() && x <= this.getxPos() + width && y <= this.getyPos() + height){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onSelectedKeyPressed(int keyNr, char c){
        if(keyNr == 14 && text.length() > 0) {
            text = text.substring(0, text.length() -1);
        }else if(keyNr == 42){
        }else{
            text = text + c;
        }


    }

    @Override
    public void onSelectedKeyReleased(int keyNr, char c){

    }
}
