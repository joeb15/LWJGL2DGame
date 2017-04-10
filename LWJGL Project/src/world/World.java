package world;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by joeba on 4/7/2017.
 */
public class World {

    private HashMap<Integer, Chunk> chunks = new HashMap<Integer, Chunk>();

    protected World(){};

    public void save(String worldName){
        StringBuffer worldSave = new StringBuffer();
        for(Integer i:chunks.keySet()){
            Chunk c = chunks.get(i);
            worldSave.append(i+":"+c.getRange()+"\n");
            worldSave.append(c.getSaveString());
        }
    }

    public static World load(String worldName){
        World world = new World();
        StringBuilder worldSave = new StringBuilder();
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(worldName));
            while((line=br.readLine())!=null){
                worldSave.append(line+'\n');
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String worldText = worldSave.toString();
        String[] lines = worldText.split("\n");
        assert(lines.length%2==0);
        for(int i=0;i<lines.length;i+=2){
            String info = lines[i];
            String chunkData = lines[i+1];
            String[] rawInfo = info.split(":");
            int chunkX = Integer.getInteger(rawInfo[0]);
            int lower = Integer.getInteger(rawInfo[1]);
            int upper = Integer.getInteger(rawInfo[2]);
            String[] chunkRaw = chunkData.split(" ");
            int pointer = lower;
            Chunk c = new Chunk();
            for(int j=0;j<chunkRaw.length;j++){
                c.add(pointer++, Integer.getInteger(chunkRaw[j]));
            }
        }
        System.err.println("CORRUPTED WORLD");
        return null;
    }

}
