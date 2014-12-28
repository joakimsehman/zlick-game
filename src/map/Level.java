package map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level {

	private int width = 150, height = 150;
	
	private Tile[][] tileProperties = new Tile[width][height];

    private int background;
    private int stuff;
	
	public TiledMap map = null;
	
	public Level(){
		String path = "assets/tileMaps/nufan.tmx";

        try {
            map = new TiledMap(path);
        } catch (SlickException e) {
            System.out.println("could not load map");
            e.printStackTrace();
        }

        width = map.getWidth();
        height = map.getHeight();

        background = map.getLayerIndex("background");
        stuff = map.getLayerIndex("solids");

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                tileProperties[x][y] = Tile.blank;
            }
        }

        addTiles();
    }

    private void addTiles(){
        for(int x = 0; x < tileProperties.length; x++){
            for(int y = 0; y < tileProperties[0].length; y++){
                int tileId = map.getTileId(x, y, background);
                if(tileId == 1 || tileId == 2){
                    tileProperties[x][y] = Tile.grass;

                }else if(tileId == 86){
                    tileProperties[x][y] = Tile.water;
                }
                tileId = map.getTileId(x, y, stuff);
                if(tileId == 55){
                    tileProperties[x][y] = Tile.rock;
                }
            }
        }
    }


    //seems to work.. for once something works....
    public Tile getTileAtPos(float x, float y){
        int mapX = (int)(x / 64 + y / 32);
        int mapY = (int)(y / 32 - x / 64);

        return tileProperties[mapX][mapY];

    }

    public void render(int x, int y){
        //map.render(x, y,10, 10, 10, 20, background, false); //FUCKING SHITCUNT sx sy DONT DO FUCKING SHIT WTF??? 10 fucking hours for nothing.. TODO: RENDER ONLY MAP THATS ON SCREEN....
        //map.render(x, y, (-x)/64, (-y)/32, 50, 50);
        map.render(x, y, background);
        map.render(x, y, stuff);
    }
	
}
