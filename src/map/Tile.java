package map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {

	public static final int HEIGHT = 32;
	public static final int WIDTH = 64;
	
	private static BufferedImage tiles;
	
	public Tile(){
		
		
		
			try {
				Tile.tiles = ImageIO.read(new File("assets/tileExamples/iso-64x64-outside.png"));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
	}
	
}
