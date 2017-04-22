package shaders.uniforms;

import org.joml.Vector2f;
import org.lwjgl.opengl.GL20;

public class UniformVec2 extends Uniform {

	private Vector2f currentValue;
	private boolean used = false;
	
	public UniformVec2(String name) {
		super(name);
	}
	
	public void loadVec2(Vector2f value){
		if(!used || !currentValue.equals(value)){
			GL20.glUniform2f(getLocation(), value.x, value.y);
			used=true;
            currentValue=new Vector2f(value);
		}
	}

}
