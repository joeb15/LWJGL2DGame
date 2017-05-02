package shaders.uniforms;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;

public class UniformVec3 extends Uniform {

	private Vector3f currentValue;
	private boolean used = false;

	public UniformVec3(String name) {
		super(name);
	}
	
	public void loadVec3(Vector3f value){
		if(!used || !currentValue.equals(value)){
			GL20.glUniform3f(getLocation(), value.x, value.y, value.z);
			used=true;
            currentValue=new Vector3f(value);
		}
	}

}
