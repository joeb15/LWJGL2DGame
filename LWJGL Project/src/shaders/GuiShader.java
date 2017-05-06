package shaders;

import shaders.uniforms.UniformInt;
import shaders.uniforms.UniformVec2;

import static org.lwjgl.opengl.GL20.glBindAttribLocation;

public class GuiShader extends Shader {

	public UniformInt tex = new UniformInt("tex");
    public UniformVec2 pos = new UniformVec2("pos");
    public UniformVec2 scale = new UniformVec2("scale");
    public UniformVec2 screen = new UniformVec2("screen");

	public GuiShader() {
		super("./shaders/gui.vs","./shaders/gui.fs");
		addUniforms(tex, pos, scale, screen);
		tex.loadInt(0);
	}
	
	public void bindAttribLocations() {
		glBindAttribLocation(program, 0, "position");
		glBindAttribLocation(program, 1, "textureCoords");
	}
	
	

}
