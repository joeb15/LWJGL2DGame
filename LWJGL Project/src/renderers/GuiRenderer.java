package renderers;

import core.Model;
import core.Window;
import guis.Gui;
import org.joml.Vector2f;
import shaders.GuiShader;

import java.util.List;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

public class GuiRenderer {

	private GuiShader shader;

	private Model model;

	public GuiRenderer(){
		float[] vertices = new float[]{-.5f,.5f,0,.5f,.5f,0,.5f,-.5f,0,-.5f,-.5f,0};
		float[] texCoords = new float[]{0,0,1,0,1,1,0,1};
		int[] indices = new int[]{0,1,2,2,3,0};

		model = new Model(vertices, texCoords, indices);
		
		shader = new GuiShader();

		glEnable(GL_TEXTURE_2D);
	}

	public void render(List<Gui> guis, Window w){

		shader.bind();
		model.bind();
		shader.screen.loadVec2(new Vector2f(w.getWidth(), w.getHeight()));
		for(Gui g:guis){
		    g.getTexture().bind(0);
		    shader.pos.loadVec2(g.getPos());
		    shader.scale.loadVec2(g.getSize());
		    model.render();
        }

		model.unbind();
		shader.unbind();
	}

}
