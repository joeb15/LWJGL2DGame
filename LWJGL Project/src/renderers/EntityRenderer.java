package renderers;

import core.Camera;
import core.Model;
import entities.Entity;
import org.joml.Vector2f;
import org.joml.Vector4f;
import shaders.EntityShader;
import textures.Texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

public class EntityRenderer {

	private EntityShader shader;

	private Model model;

	public EntityRenderer(){
		float[] vertices = new float[]{-.5f,.5f,0,.5f,.5f,0,.5f,-.5f,0,-.5f,-.5f,0};
		float[] texCoords = new float[]{0,0,1,0,1,1,0,1};
		int[] indices = new int[]{0,1,2,2,3,0};

		model = new Model(vertices, texCoords, indices);

		shader = new EntityShader();

		glEnable(GL_TEXTURE_2D);

		entitiesHash = new HashMap<Texture, ArrayList<Entity>>();
	}

	private HashMap<Texture, ArrayList<Entity>> entitiesHash;
	private Vector4f screenBounds;

	public void render(List<Entity> entities, Camera cam){
		entitiesHash.clear();
        screenBounds = cam.getViewableArea();

		for(Entity e:entities){
			processEntity(e);
		}

		shader.bind();
		shader.projection.loadMat4(cam.getProjection());
		model.bind();

		for(Texture t:entitiesHash.keySet()){
			t.bind(0);
			for(Entity e:entitiesHash.get(t)) {
				shader.scale.loadVec2(e.getSize());
				shader.pos.loadVec2(e.getPos());
                model.render();
			}
		}

		model.unbind();
		shader.unbind();
	}

	private void processEntity(Entity e){
		Vector2f pos = e.getPos();
		Vector2f size = e.getSize();
		Texture t = e.getTexture();

		if(		pos.x+size.x/2<screenBounds.x || pos.x-size.x/2>screenBounds.z ||
				pos.y+size.y/2<screenBounds.w || pos.y-size.y/2>screenBounds.y){
			return;
		}
		if(!entitiesHash.containsKey(t)){
			entitiesHash.put(t, new ArrayList<Entity>());
		}
		entitiesHash.get(t).add(e);
	}

}
