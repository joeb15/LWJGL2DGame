package shaders;

import static org.lwjgl.opengl.GL20.*;

public class DefaultShader extends Shader {

	public UniformInt tex = new UniformInt("tex");
	public UniformMat4 projection = new UniformMat4("projection");
	public UniformVec2 pos = new UniformVec2("pos");
	public UniformVec2 scale = new UniformVec2("scale");
	
	public DefaultShader() {
		super("./shaders/default.vs","./shaders/default.fs");	
		addUniforms(tex, projection, pos, scale);
		tex.loadInt(0);
	}
	
	public void bindAttribLocations() {
		glBindAttribLocation(program, 0, "position");
		glBindAttribLocation(program, 1, "textureCoords");
	}
	
	

}
