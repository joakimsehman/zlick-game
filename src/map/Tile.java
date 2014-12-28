package map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Tile {

	public static final int HEIGHT = 32;
	public static final int WIDTH = 64;

    public static Tile blank = new BlankTile();
    private static class BlankTile extends Tile{
        public boolean isSolid(){
            return false;
        }
    }

    public static Tile grass = new GrassTile();
    private static class GrassTile extends Tile{
        public boolean isSolid(){
            return false;
        }
    };

    public static Tile water = new WaterTile();
    private static class WaterTile extends Tile{
        public boolean isSolid(){
            return true;
        }
    }

    public static Tile rock = new RockTile();
    public static class RockTile extends Tile{
        public boolean isSolid(){
            return true;
        }
    }

	public Tile(){
		
	}

    public abstract boolean isSolid();

}
