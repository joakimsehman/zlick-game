package map;

import org.newdawn.slick.tiled.TiledMap;

public class Level {

	private int width = 100, height = 100;
	
	private Tile[][] background = new Tile[width][height];
	
	public TiledMap map = null;
	
	public Level(){
		String path = "assets/tileMaps/test1.tmx";
		
		
	}
	
}
