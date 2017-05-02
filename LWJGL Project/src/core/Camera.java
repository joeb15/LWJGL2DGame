package core;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Camera {

	private Vector3f position;
	private Matrix4f projection;
	private int w, h;
	public Camera(int w, int h){
		this.w=w;
		this.h=h;
		position = new Vector3f(0,0,0);
		projection = new Matrix4f().ortho2D(-w/2, w/2, -h/2, h/2);
	}

	public Vector4f getViewableArea(){
		return new Vector4f(-w/2-position.x, h/2-position.y, w/2-position.x, -h/2-position.y);

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
