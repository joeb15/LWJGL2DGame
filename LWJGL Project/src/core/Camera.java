package core;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

	private Vector3f position;
	private Matrix4f projection;
	
	public Camera(int w, int h){
		position = new Vector3f(0,0,0);
		projection = new Matrix4f().ortho2D(-w/2, w/2, -h/2, h/2);
	}
	
	public void setPos(Vector3f pos){
		this.position=pos;
	}
	
	public void addPos(Vector3f pos){
		this.position.add(pos);
	}
	
	public Vector3f getPos(){
		return position;
	}
	
	public Matrix4f getProjection(){
		Matrix4f target = new Matrix4f();
		Matrix4f pos = new Matrix4f().setTranslation(position);
		
		projection.mul(pos, target);
		return target;
	}
	
}