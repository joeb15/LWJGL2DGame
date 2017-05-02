package renderers;

import core.Model;
import core.ModelLoader;
import guis.Gui;
import shaders.GuiShader;

import java.util.List;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

public class GuiRenderer {

	private GuiShader shader;

	private Model model;

	public GuiRenderer(){

		model = ModelLoader.load("./res/plane.obj");
		
		shader = new GuiShader();

		glEnable(GL_TEXTURE_2D);
	}

	public void render(List<Gui> guis){

		shader.bind();
		model.bind();

		for(Gui g:guis){
		    g.getTexture().bind(0);
		    shader.scale.loadVec2(g.getSize());
		    shader.pos.loadVec2(g.getPos());
		    model.render();
        }

		model.unbind();
		shader.unbind();
	}

}
