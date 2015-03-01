package map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level {

	private int width = 100, height = 100;
	
	private Tile[][] tileProperties = new Tile[width][height];

    private int background;
    private int stuff;
    private int decorations;
    private int trees;
	
	public TiledMap map = null;
	
	public Level(){
		String path = "assets/tileMaps/nufan2.tmx";

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
        decorations = map.getLayerIndex("decorations");
        trees = map.getLayerIndex("trees");

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

                //check first map layer for some stuff(ex. grass, water)
                if(tileId == 1 || tileId == 2){
                	
                    tileProperties[x][y] = Tile.grass;

                }else if(tileId == 86 || tileId == 85){
                    tileProperties[x][y] = Tile.water;
                }
                
                tileId = map.getTileId(x, y, trees);
                
                if(tileId == 159 || tileId == 156 || tileId == 154 || tileId == 153 || tileId == 152 || tileId == 151 ||tileId == 134 || tileId == 133){
                	tileProperties[x][y] = Tile.treeTrunk;
                }

                //check second map layer for some stuff(ex. rocks)
                tileId = map.getTileId(x, y, stuff);
                if(tileId == 55 || tileId == 56){
                    tileProperties[x][y] = Tile.rock;
                }
            }
        }
    }


    //seems to work.. for once something works....
    public Tile getTileAtPos(float x, float y){
        x = x - 25;


        int mapX = (int)(x / 64 + y / 32);
        int mapY = (int)(y / 32 - x / 64);

        if(mapX < 0 || mapX > tileProperties.length || mapY < 0 || mapY > tileProperties[0].length){
        	return null;
        }
        return tileProperties[mapX][mapY];

    }

    public void render(int x, int y){
        //map.render(x, y,10, 10, 10, 20, background, false); //FUCKING SHITCUNT sx sy DONT DO FUCKING SHIT WTF??? 10 fucking hours for nothing.. TODO: RENDER ONLY MAP THATS ON SCREEN....
        //map.render(x, y, (-x)/64, (-y)/32, 50, 50);
        map.render(x, y, background);
        map.render(x, y, decorations);
        map.render(x, y, stuff);
        
    }
    
    public void renderDecorations(int x, int y){
    	map.render(x, y, trees);
    }
	
}
