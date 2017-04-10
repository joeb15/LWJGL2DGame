package renderers;

import core.Camera;
import core.Model;
import shaders.DefaultShader;
import world.World;

import static org.lwjgl.opengl.GL11.*;

public class DefaultRenderer {

	private DefaultShader shader;
	private Model model;
	
	public DefaultRenderer(){
		float[] vertices = new float[]{-.5f,.5f,0,.5f,.5f,0,.5f,-.5f,0,-.5f,-.5f,0};
		float[] texCoords = new float[]{0,0,1,0,1,1,0,1};
		int[] indices = new int[]{0,1,2,2,3,0};

		model = new Model(vertices, texCoords, indices);
		
		shader = new DefaultShader();

		glEnable(GL_TEXTURE_2D);
		
	}
	
	public void render(World world, Camera cam){
		glClear(GL_COLOR_BUFFER_BIT);

		shader.bind();
		shader.projection.loadMat4(cam.getProjection());

		model.bind();

//		for(Entity e:entities){
//			shader.pos.loadVec2(e.getPos());
//			shader.scale.loadVec2(e.getSize());
//			e.getTexture().bind(0);
//			model.render();
//		}
		model.unbind();
		shader.unbind();
	}
	
}
