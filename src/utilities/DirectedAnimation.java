package utilities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class DirectedAnimation {

	
	private Image currentImage;
	private Image[][] images;
	private String spriteSheetName;
	
	private int imageCounter;
	private int imageSwitchSpeed;
	private int imageDeltaSinceSwitch;

	// remember to run an init method!
	public DirectedAnimation(String spriteSheetName) {
		this.spriteSheetName = spriteSheetName;
		imageSwitchSpeed = 100;
		imageDeltaSinceSwitch = 0;
	}

	public void initSpritesAlongX(int x, int deltaX, int y, int deltaY) {
		if (deltaY < 1 || deltaX < 1) {
			throw new IllegalArgumentException(
					"Animation length or directions cant be less than 1");
		}

		images = new Image[deltaY][deltaX];

		for (int j = y; j < deltaY; j++) {
			for (int i = x; i < deltaX; i++) {
				images[j][i] = TextureHandler.getInstance().getImageFromSpriteSheet(
						x, y, spriteSheetName);
			}
		}
	}

	public void initSpritesAlongY(int x, int deltaX, int y, int deltaY) {
		if (deltaY < 1 || deltaX < 1) {
			throw new IllegalArgumentException(
					"Animation length or directions cant be less than 1");
		}
		
		images = new Image[deltaX][deltaY];
		
		for(int j = x; j < deltaX; j++){
			for(int i = y; i < deltaY; i++){
				images[j][i] = TextureHandler.getInstance().getImageFromSpriteSheet(
						x, y, spriteSheetName);
			}
		}
	}
	
	public void setImageSwitchSpeed(int ms){
		imageSwitchSpeed = ms;
	}

	public void draw(Graphics g, int xPos, int yPos) {
		g.drawImage(currentImage, xPos, yPos);
	}

	public void update(int delta, double directionInDegrees) {
		double percentOfArray = directionInDegrees/361.0f;
		
		imageDeltaSinceSwitch += delta;
		if(imageDeltaSinceSwitch > imageSwitchSpeed){
			imageDeltaSinceSwitch -= imageSwitchSpeed;
			if(imageCounter == images[0].length){
				imageCounter = 0;
			}else{
				imageCounter++;
			}
		}
		
		currentImage = images[(int)percentOfArray*images.length][imageCounter];
	}

}
