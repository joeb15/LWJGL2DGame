package shaders.uniforms;

import org.lwjgl.opengl.GL20;

public abstract class Uniform {
	private int location;
	protected String name;
	
	protected Uniform(String name){
		this.name=name;
	}
	
	public void storeUniformLocation(int program){
		location = GL20.glGetUniformLocation(program, name);
		if(location == -1){
			System.err.println("Uniform: "+name+" is not found!");
		}
	}
	
	protected int getLocation(){
		return location;
	}
}
