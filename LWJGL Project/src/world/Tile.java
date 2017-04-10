package world;

import textures.Texture;
import textures.Textures;

/**
 * Created by joeba on 4/7/2017.
 */
public class Tile {
    public static final int TILE_SIZE = 32;
    public static Tile[] tiles = new Tile[4096];

    public static final Tile TestTile = new Tile(0, "./res/img.png");
    public static final Tile TestTile2 = new Tile(1, "./res/sun.png");

    private byte id;
    private String texture;

    public Tile(int id, String texture){
        this.id=(byte)id;
        this.texture=texture;
        if(tiles[id]!=null){
            System.err.println("ID "+id+" is already registered!");
        }
        tiles[id] = this;
    }

    public byte getId(){
        return id;
    }

    public Texture getTexture() {
        return Textures.getTexture(texture);
    }
}
