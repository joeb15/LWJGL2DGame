package world;

import java.util.HashMap;

/**
 * Created by joeba on 4/7/2017.
 */
public class Chunk {

    private static final int LOWER_DEFAULT = -64;
    private static final int UPPER_DEFAULT = 128;

    private HashMap<Integer, Integer> tiles = new HashMap<Integer, Integer>();

    private int lower = LOWER_DEFAULT;
    private int upper = UPPER_DEFAULT;

    public Chunk(){
        for(int i=lower;i<=upper;i++){
            tiles.put(i, i%2);
        }
    }

    public String getSaveString() {
        StringBuilder sb = new StringBuilder();
        for(Integer i:tiles.keySet()){
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
}
