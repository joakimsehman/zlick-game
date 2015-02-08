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

	public DirectedAnimation(Image[][] animImages) {
		images = animImages;
		animationLength = images[0].length;
		currentAnimXStart = 0;
		currentAnimCurrentX = 0;
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
						.getImageFromSpriteSheet(x+dx, y+dy, spriteSheetName);
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
						.getImageFromSpriteSheet(x+dx, y+dy, spriteSheetName);
			}
		}

		return images;
	}
	
	

	protected void switchImage() {
		if (imageCounter < animationLength -1) {
			imageCounter++;
		} else {
			imageCounter = 0;
		}
		currentAnimCurrentX = currentAnimXStart + imageCounter;
	}

	protected void draw(Graphics g, float xPos, float yPos) {
		if (currentImage != null) {
			g.drawImage(currentImage, xPos, yPos);
		}
	}

	protected void update(int delta, double directionInPercentOfDirections) {

		currentImage = images[((int) (directionInPercentOfDirections * images.length))][currentAnimCurrentX];
	}

	public void setCurrentAnimation(int currentAnimX, int deltaX) {
		animationLength = deltaX;
		this.currentAnimXStart = currentAnimX;
		currentAnimCurrentX = currentAnimXStart;
	}

}
