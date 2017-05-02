package renderers;

import core.Camera;
import core.Model;
import core.ModelLoader;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import shaders.WorldShader;
import textures.Texture;
import world.Chunk;
import world.Tile;
import world.World;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

public class WorldRenderer {

	private WorldShader shader;

	private Model model;
	
	public WorldRenderer(){
        model = ModelLoader.load("./res/cube.obj");

        shader = new WorldShader();

		glEnable(GL_TEXTURE_2D);

		tiles = new HashMap<Texture, ArrayList<Vector3f>>();
	}

	private HashMap<Texture, ArrayList<Vector3f>> tiles;

	public void render(World world, Camera cam){
		tiles.clear();

		shader.bind();
		shader.projection.loadMat4(cam.getProjection());
		model.bind();

		HashMap<Integer, Chunk> chunks = world.getChunks();
		for(int i:chunks.keySet()){
			Chunk c = chunks.get(i);
			float xPos = i * Tile.TILE_SIZE;
			HashMap<Integer, Integer> tiles = c.getTiles();
			for(int j:tiles.keySet()){
				Texture t = Tile.tiles[tiles.get(j)].getTexture();
				float yPos = j*Tile.TILE_SIZE;
				//TODO: redo the world class to use 3d
				float zPos = 0;
				processTile(xPos, yPos, zPos, t);
			}
		}

		Vector3f scale = new Vector3f(Tile.TILE_SIZE,Tile.TILE_SIZE,Tile.TILE_SIZE);
		shader.scale.loadVec3(scale);
		for(Texture t:tiles.keySet()){
			t.bind(0);
			for(Vector3f pos:tiles.get(t)) {
				shader.pos.loadVec3(pos);
				model.render();
			}
		}
		model.unbind();
		shader.unbind();
	}

	private void processTile(float xPos, float yPos, float zPos, Texture t) {
//        TODO: optimize rendering if off screen
//      if(		xPos+Tile.TILE_SIZE/2<screenBounds.x || xPos-Tile.TILE_SIZE/2>screenBounds.z ||
//				yPos+Tile.TILE_SIZE/2<screenBounds.w || yPos-Tile.TILE_SIZE/2>screenBounds.y){
//			return;//tile is out of frame
//		}
		if(!tiles.containsKey(t)){
			tiles.put(t, new ArrayList<Vector3f>());
		}
		tiles.get(t).add(new Vector3f(xPos, yPos, zPos));
	}

}
