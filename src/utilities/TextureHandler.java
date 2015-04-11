package utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

public class TextureHandler {

    private ArrayList<Image> images;
    private ArrayList<SpriteSheet> spriteSheets;
	private static TextureHandler textureHandler;


	private TextureHandler() {
		images = new ArrayList<Image>();
        spriteSheets = new ArrayList<SpriteSheet>();
        
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
			addImage("massPolymorphIcon.png");
			addImage("joinGreenTeam.png");
			addImage("joinBrownTeam.png");
			addImage("spell_circle.png");
			addImage("castbar.png");
			addImage("bolaIcon.png");
			addImage("spell_circle2.png");
			addImage("leftArrow.png");
			addImage("rightArrow.png");
			addImage("male.png");
			addImage("female.png");
			addImage("background.jpg");
			addImage("title.png");
			addImage("teleportIcon.png");
			addImage("icelanceIcon.png");
			addImage("elementalDischargeIcon.png");
			addImage("actionbar.png");
			addImage("cure-3.png");
			addImage("stealth.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

        addSpriteSheet("male_steel_armor.png", 128, 128);
        addSpriteSheet("male_leather_armor.png", 128, 128);
        addSpriteSheet("male_clothes.png", 128, 128);
        
        addSpriteSheet("female_steel_armor.png", 128, 128);
        addSpriteSheet("female_leather_armor.png", 128, 128);
        addSpriteSheet("female_clothes.png", 128, 128);
        
        
        addSpriteSheet("male_head1.png", 128, 128);
        addSpriteSheet("male_head2.png", 128, 128);
        addSpriteSheet("male_head3.png", 128, 128);
        addSpriteSheet("female_head_long.png", 128, 128);
        
        addSpriteSheet("male_greatsword.png", 128, 128);
        addSpriteSheet("male_greatbow.png", 128, 128);
        addSpriteSheet("male_greatstaff.png", 128, 128);
        addSpriteSheet("male_shield.png", 128, 128);
        addSpriteSheet("male_longsword.png", 128, 128);
        
        addSpriteSheet("female_greatsword.png", 128, 128);
        addSpriteSheet("female_greatbow.png", 128, 128);
        addSpriteSheet("female_greatstaff.png", 128, 128);
        addSpriteSheet("female_shield.png", 128, 128);
        addSpriteSheet("female_longsword.png", 128, 128);
        
        
        addSpriteSheet("animal.png", 32,  32);
        addSpriteSheet("projectiles.png", 64, 64);
        addSpriteSheet("fireball.png", 64, 64);
        addSpriteSheet("icelance.png", 64, 64);
        
        addSpriteSheet("teleport_rune.png", 64, 64);
        
        addSpriteSheet("icespikes.png", 64, 64);
        addSpriteSheet("elementaldischarge.png", 32, 32);
        
        addSpriteSheet("healEffect.png", 64, 64);
        addSpriteSheet("sparks.png", 64, 64);
        
        addSpriteSheet("quake_withheal.png", 256, 128);
        

        
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

    private void addSpriteSheet(String filename, int tileWidth, int tileHeight) throws SlickException {
        SpriteSheet sheet = new SpriteSheet("assets/spritesheets/" + filename, tileWidth, tileHeight);
        sheet.setName(filename);
        spriteSheets.add(sheet);

    }

    public SpriteSheet getSpriteSheetByName(String sheetName){
        for(int i = 0; i < spriteSheets.size(); i++){
            if(spriteSheets.get(i).getName() == sheetName){
                return spriteSheets.get(i);
            }
        }
        return null;
    }


    public Image getImageFromSpriteSheet(int tileX, int tileY, String sheetName){
        return getSpriteSheetByName(sheetName).getSprite(tileX, tileY);
    }
    
   
    
    


    //the getSprite method has really bad performance when you are rendering a tiled map. you should use renderInUSe
    //cant :(, you can only use renderInUse if you use one spritesheet at a time it seems


}
