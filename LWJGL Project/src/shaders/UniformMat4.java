package shaders;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

public class UniformMat4 extends Uniform {

	private Matrix4f currentValue;
	private boolean used = false;
	
	public UniformMat4(String name) {
		super(name);
	}
	
	public void loadMat4(Matrix4f value){
		if(!used || currentValue!=value){
			FloatBuffer fb = BufferUtils.createFloatBuffer(16);
			value.get(fb);
			GL20.glUniformMatrix4fv(getLocation(), false, fb);
			used=true;
			currentValue=value;
		}
	}

}
