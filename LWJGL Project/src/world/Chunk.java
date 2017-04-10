package world;

import java.util.HashMap;

/**
 * Created by joeba on 4/7/2017.
 */
public class Chunk {

    private static final int LOWER_DEFAULT = -64;
    private static final int UPPER_DEFAULT = 128;

    public HashMap<Integer, Integer> getTiles() {
        return tiles;
    }

    private HashMap<Integer, Integer> tiles;

    private int lower = LOWER_DEFAULT;
    private int upper = UPPER_DEFAULT;

    protected Chunk(){
        tiles = new HashMap<Integer, Integer>();
    }

    public String getSaveString() {
        StringBuilder sb = new StringBuilder();

        for(int i=lower; i<=upper; i++){
            sb.append(tiles.get(i)+" ");
        }

        return sb.toString().trim();
    }

    public String getRange() {
        return lower+":"+upper;
    }

    public void add(int i, Integer integer) {
        tiles.put(i, integer);
    }

    public void addTile(int height, int id) {
        tiles.put(height, id);
    }

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }
}
