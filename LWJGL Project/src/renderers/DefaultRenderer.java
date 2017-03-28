package renderers;

import java.util.List;

import core.Camera;
import core.Model;
import entities.Entity;
import shaders.DefaultShader;

public class DefaultRenderer {

	private DefaultShader shader;
	private Model model;
	
	public DefaultRenderer(){
		float[] vertices = new float[]{-.5f,.5f,0,.5f,.5f,0,.5f,-.5f,0,-.5f,-.5f,0};
		float[] texCoords = new float[]{0,0,1,0,1,1,0,1};
		int[] indices = new int[]{0,1,2,2,3,0};

		model = new Model(vertices, texCoords, indices);
		
		shader = new DefaultShader();
	}
	
	public void render(List<Entity> entities, Camera cam){
		shader.bind();
		shader.projection.loadMat4(cam.getProjection());
		float depth=0;
		model.bind();
		for(Entity e:entities){
			shader.pos.loadVec2(e.getPos());
			shader.scale.loadVec2(e.getSize());
			shader.depth.loadFloat(depth++);
			e.getTexture().bind(0);
			model.render();
		}
		model.unbind();
		shader.unbind();
	}
	
}