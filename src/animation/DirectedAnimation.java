package animation;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import utilities.TextureHandler;

public class DirectedAnimation {

	private Image currentImage;
	private Image[][] images;

	private int animationLength;
	private int currentAnimXStart;
	private int currentAnimCurrentX;

	private int imageCounter;

	private boolean oneTimeAnimPlaying;
	private int oneTimeAnimXStart;
	private int oneTimeAnimDeltaX;
	private int oneTimeAnimDirectionSpriteNr;

	public DirectedAnimation(Image[][] animImages) {
		images = animImages;
		animationLength = images[0].length;
		currentAnimXStart = 0;
		currentAnimCurrentX = 0;
		oneTimeAnimPlaying = false;
	}

	public static Image[][] getSpritesAlongX(String spriteSheetName, int x,
			int deltaX, int y, int deltaY) {
		if (deltaY < 1 || deltaX < 1) {
			throw new IllegalArgumentException(
					"Animation length or directions cant be less than 1");
		}

		Image[][] images = new Image[deltaY][deltaX];

		for (int dy = 0; dy < deltaY; dy++) {
			for (int dx = 0; dx < deltaX; dx++) {
				images[dy][dx] = TextureHandler.getInstance()
						.getImageFromSpriteSheet(x + dx, y + dy,
								spriteSheetName);
			}
		}

		return images;
	}

	public static Image[][] getSpritesAlongY(String spriteSheetName, int x,
			int deltaX, int y, int deltaY) {
		if (deltaY < 1 || deltaX < 1) {
			throw new IllegalArgumentException(
					"Animation length or directions cant be less than 1");
		}

		Image[][] images = new Image[deltaX][deltaY];

		for (int dx = 0; dx < deltaX; dx++) {
			for (int dy = 0; dy < deltaY; dy++) {
				images[dx][dy] = TextureHandler.getInstance()
						.getImageFromSpriteSheet(x + dx, y + dy,
								spriteSheetName);
			}
		}

		return images;
	}

	protected void switchImage() {
		if (!oneTimeAnimPlaying) {
			if (imageCounter < animationLength - 1) {
				imageCounter++;
			} else {
				imageCounter = 0;
			}
			currentAnimCurrentX = currentAnimXStart + imageCounter;
		}else{
			if(imageCounter < oneTimeAnimDeltaX -1){
				imageCounter++;
			}else{
				imageCounter = 0;
				oneTimeAnimPlaying = false;
			}
			currentAnimCurrentX = oneTimeAnimXStart + imageCounter;
		}
	}

	protected void draw(Graphics g, float xPos, float yPos) {
		if (currentImage != null) {
			g.drawImage(currentImage, xPos, yPos);
		}
	}

	protected void update(int delta, double directionInPercentOfDirections) {

		int directionSpriteNr;
		if(oneTimeAnimPlaying && oneTimeAnimDirectionSpriteNr != -1){
			directionSpriteNr = oneTimeAnimDirectionSpriteNr;
		}else{
			directionSpriteNr = (int) (directionInPercentOfDirections * images.length);
		}
		currentImage = images[directionSpriteNr][currentAnimCurrentX];
		
	}

	public void setCurrentAnimation(int currentAnimX, int deltaX) {
		animationLength = deltaX;
		this.currentAnimXStart = currentAnimX;
		currentAnimCurrentX = currentAnimXStart;
	}

	//pass -1 into directionInPercentOfDirections to make the animation direction change dynamically
	public void playAnimationOnce(int AnimX, int deltaX, double directionInPercentOfDirections) {
		oneTimeAnimPlaying = true;
		oneTimeAnimXStart = AnimX;
		oneTimeAnimDeltaX = deltaX;
		if(directionInPercentOfDirections != -1){
			oneTimeAnimDirectionSpriteNr = (int) (directionInPercentOfDirections * images.length);
		}else{
			oneTimeAnimDirectionSpriteNr = -1;
		}
		imageCounter = 0;
	}

}
