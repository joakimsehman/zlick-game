package animation;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import utilities.TextureHandler;

public class DirectedAnimation {

	private Image currentImage;
	private Image[][] images;

	private int imageCounter;

	public DirectedAnimation(Image[][] animImages) {
		images = animImages;
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
		if (imageCounter < images[0].length - 1) {
			imageCounter++;
		} else {
			imageCounter = 0;
		}
	}

	protected void draw(Graphics g, float xPos, float yPos) {
		if (currentImage != null) {
			g.drawImage(currentImage, xPos, yPos);
		}
	}

	protected void update(int delta, double directionInDegrees) {
		double percentOfArray = directionInDegrees / 361.0f;

		currentImage = images[((int) (percentOfArray * images.length))][imageCounter];
	}

}
