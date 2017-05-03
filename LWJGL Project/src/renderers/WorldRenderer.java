package renderers;

import core.Camera;
import core.Model;
import org.joml.Vector2f;
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
		float[] vertices = new float[]{-.5f,.5f,0,.5f,.5f,0,.5f,-.5f,0,-.5f,-.5f,0};
		float[] texCoords = new float[]{0,0,1,0,1,1,0,1};
		int[] indices = new int[]{0,1,2,2,3,0};

		model = new Model(vertices, texCoords, indices);

		shader = new WorldShader();

		glEnable(GL_TEXTURE_2D);

		tiles = new HashMap<Texture, ArrayList<Vector2f>>();
	}

	private HashMap<Texture, ArrayList<Vector2f>> tiles;
	private Vector4f screenBounds;

	public void render(World world, Camera cam){
		tiles.clear();

		shader.bind();
		shader.projection.loadMat4(cam.getProjection());
		screenBounds = cam.getViewableArea();
		model.bind();

		HashMap<Integer, Chunk> chunks = world.getChunks();
		for(int i:chunks.keySet()){
			Chunk c = chunks.get(i);
			float xPos = i * Tile.TILE_SIZE;
			HashMap<Integer, Integer> tiles = c.getTiles();
			for(int j:tiles.keySet()){
				Texture t = Tile.tiles[tiles.get(j)].getTexture();
				float yPos = j*Tile.TILE_SIZE;
				processTile(xPos, yPos, t);
			}
		}

		Vector2f scale = new Vector2f(Tile.TILE_SIZE,Tile.TILE_SIZE);
		shader.scale.loadVec2(scale);
		for(Texture t:tiles.keySet()){
			t.bind(0);
			for(Vector2f pos:tiles.get(t)) {
				shader.pos.loadVec2(pos);
				model.render();
			}
		}
		model.unbind();
		shader.unbind();
	}

	private void processTile(float xPos, float yPos, Texture t) {
		if(		xPos+Tile.TILE_SIZE/2<screenBounds.x || xPos-Tile.TILE_SIZE/2>screenBounds.z ||
				yPos+Tile.TILE_SIZE/2<screenBounds.w || yPos-Tile.TILE_SIZE/2>screenBounds.y){
			return;//tile is out of frame
		}
		if(!tiles.containsKey(t)){
			tiles.put(t, new ArrayList<Vector2f>());
		}
		tiles.get(t).add(new Vector2f(xPos, yPos));
	}

}
