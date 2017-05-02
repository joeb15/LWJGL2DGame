package shaders;

import shaders.uniforms.UniformInt;
import shaders.uniforms.UniformMat4;
import shaders.uniforms.UniformVec2;
import shaders.uniforms.UniformVec3;

import static org.lwjgl.opengl.GL20.glBindAttribLocation;

public class WorldShader extends Shader {

	public UniformInt tex = new UniformInt("tex");
	public UniformMat4 projection = new UniformMat4("projection");
	public UniformVec3 pos = new UniformVec3("pos");
	public UniformVec3 scale = new UniformVec3("scale");
	
	public WorldShader() {
		super("./shaders/world.vs","./shaders/world.fs");
		addUniforms(tex, projection, pos, scale);
		tex.loadInt(0);
	}
	
	public void bindAttribLocations() {
        glBindAttribLocation(program, 0, "position");
        glBindAttribLocation(program, 1, "normals");
		glBindAttribLocation(program, 2, "textureCoords");
	}
	
	

}
