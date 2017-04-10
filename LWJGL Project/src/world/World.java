package world;

import java.io.*;
import java.util.HashMap;

/**
 * Created by joeba on 4/7/2017.
 */
public class World {

    public HashMap<Integer, Chunk> getChunks() {
        return chunks;
    }

    private HashMap<Integer, Chunk> chunks;


    protected final long seed;

    protected World(long seed){
        this.seed=seed;
        chunks = new HashMap<Integer, Chunk>();
    };



    public void save(String worldName){
        StringBuffer worldSave = new StringBuffer();
        worldSave.append(seed+"\n");
        for(Integer i:chunks.keySet()){
            Chunk c = chunks.get(i);
            worldSave.append(i+":"+c.getRange()+"\n");
            worldSave.append(c.getSaveString()+"\n");
        }
        try {
            FileWriter fw = new FileWriter("./saves/"+worldName);
            fw.write(worldSave.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static World load(String worldName){
        StringBuilder worldSave = new StringBuilder();
        World world = null;

        try {
            File file = new File("./saves/"+worldName);
            //TODO: handle missing/corrupt worlds
            if(!file.exists()) {
                return WorldGenerator.generateWorld();
            }
            String line;
            BufferedReader br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            Long seed = Long.parseLong(line);
            world = new World(seed);
            while((line=br.readLine())!=null){
                worldSave.append(line+'\n');
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("CORRUPTED WORLD");
            System.exit(1);
        }

        String worldText = worldSave.toString();
        String[] lines = worldText.split("\n");

        assert(lines.length%2==0);

        for(int i=0;i<lines.length-1;i+=2){
            String info = lines[i];
            String chunkData = lines[i+1];
            String[] rawInfo = info.split(":");
            int chunkX = Integer.parseInt(rawInfo[0]);
            int lower = Integer.parseInt(rawInfo[1]);
            int upper = Integer.parseInt(rawInfo[2]);
            String[] chunkRaw = chunkData.split(" ");
            int pointer = lower;
            Chunk c = new Chunk();
            for(int j=0;j<chunkRaw.length;j++){
                c.add(pointer++, Integer.parseInt(chunkRaw[j]));
            }
            assert(pointer-1==upper);
            world.addChunk(chunkX, c);
        }

        return world;
    }

    protected void addChunk(int i, Chunk c) {
        chunks.put(i, c);
    }

}
