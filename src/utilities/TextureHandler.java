package utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

public class TextureHandler {

	private ArrayList<Image> images;
	private static TextureHandler textureHandler;

	private TextureHandler() {
		images = new ArrayList<Image>();
	}

	public static TextureHandler getInstance() {
		if (textureHandler == null) {
			textureHandler = new TextureHandler();
		}
		return textureHandler;
	}

	public Image getImageByName(String name) {
		for (Image e : images)
			if (e.getName().equals(name))
				return e;
		return null;
	}

	public void loadTextures() throws SlickException {
		try {
			addImage("red.png");
			addImage("hostGame.png");
			addImage("joinGame.png");
			addImage("exitGame.png");
			addImage("startGame.png");
			addImage("playerRight.png");
			addImage("playerLeft.png");
			addImage("fireball.png");
			addImage("questionmarkIcon.png");
			addImage("fireballIcon.png");
			addImage("joinGreenTeam.png");
			addImage("joinBrownTeam.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addImage(String str) throws SlickException, IOException {
		BufferedImage bufferedImage = ImageIO.read(new File("assets/" + str));
		Texture texture = BufferedImageUtil.getTexture("", bufferedImage);
		Image image = new Image(texture.getImageWidth(),
				texture.getImageHeight());
		image.setTexture(texture);
		image.setName(str);
		images.add(image);
	}

}
