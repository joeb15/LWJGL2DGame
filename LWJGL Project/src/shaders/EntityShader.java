package shaders;

import shaders.uniforms.UniformInt;
import shaders.uniforms.UniformMat4;
import shaders.uniforms.UniformVec2;
import shaders.uniforms.UniformVec3;

import static org.lwjgl.opengl.GL20.glBindAttribLocation;

public class EntityShader extends Shader {

	public UniformInt tex = new UniformInt("tex");
	public UniformMat4 projection = new UniformMat4("projection");
	public UniformVec3 pos = new UniformVec3("pos");
	public UniformVec3 scale = new UniformVec3("scale");

	public EntityShader() {
		super("./shaders/entity.vs","./shaders/entity.fs");
		addUniforms(tex, projection, pos, scale);
		tex.loadInt(0);
	}
	
	public void bindAttribLocations() {
        glBindAttribLocation(program, 0, "position");
        glBindAttribLocation(program, 1, "normal");
		glBindAttribLocation(program, 2, "textureCoords");
	}
	
	

}
