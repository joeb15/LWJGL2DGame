package world;

import java.util.Random;

/**
 * Created by joeba on 4/8/2017.
 */
public class WorldGenerator {
    private static final int RADIUS_TO_GENERATE = 100;

    public static World generateWorld() {
        return generateWorld(new Random().nextLong());
    }

    public static World generateWorld(long seed) {
        World world = new World(seed);
        for(int xPos=-RADIUS_TO_GENERATE;xPos<RADIUS_TO_GENERATE;xPos++){
            Chunk chunk = generateChunk(seed, xPos);
            world.addChunk(xPos, chunk);
        }
        return world;
    }

    public static Chunk generateChunk(long seed, int xPos) {
        Chunk c = new Chunk();
        for(int height=c.getLower();height<=c.getUpper();height++){
            if(Math.abs(xPos)-3<Math.log(height) && height>=0)
                c.addTile(height, Tile.TestTile2.id);
            else
                c.addTile(height, Tile.TestTile.id);
        }
        return c;
    }

}
