package entities;

import org.joml.Vector2f;
import org.joml.Vector3f;
import textures.Texture;
import textures.Textures;

public class Entity {

	private Vector3f pos, size, vel, rot;
	private Texture texture;

    public Entity(Vector3f pos, Vector3f size, String textureFile){
        this.pos=pos;
        this.size=size;
        vel = new Vector3f();
        rot = new Vector3f();
        this.texture=Textures.getTexture(textureFile);
    }

    public Entity(Vector3f pos, Vector3f size, Texture texture){
        this.pos=pos;
        this.size=size;
        vel = new Vector3f();
        rot = new Vector3f();
        this.texture=texture;
    }
	
	public Vector3f getPos(){
		return pos;
	}
	
	public Vector3f getSize(){
		return size;
	}

    public Vector3f getVel(){
        return vel;
    }

    public Vector3f getRot(){
        return rot;
    }

	public Texture getTexture() {
		return texture;
	}

    public void setPos(float x, float y, float z) {
        pos.x=x;
        pos.y=y;
        pos.z=z;
    }
    public void changePos(float x, float y, float z) {
        pos.x+=x;
        pos.y+=y;
        pos.z+=z;
    }
    public void setPos(Vector3f vec) {
        pos.x=vec.x;
        pos.y=vec.y;
        pos.z=vec.z;
    }
    public void changePos(Vector3f vec) {
        pos.x+=vec.x;
        pos.y+=vec.y;
        pos.z+=vec.z;
    }
    public void setRot(float x, float y, float z) {
        rot.x=x;
        rot.y=y;
        rot.z=z;
    }
    public void changeRot(float x, float y, float z) {
        rot.x+=x;
        rot.y+=y;
        rot.z+=z;
    }
    public void setRot(Vector3f vec) {
        rot.x=vec.x;
        rot.y=vec.y;
        rot.z=vec.z;
    }
    public void changeRot(Vector3f vec) {
        rot.x+=vec.x;
        rot.y+=vec.y;
        rot.z+=vec.z;
    }
    public void setVel(float x, float y, float z) {
        vel.x=x;
        vel.y=y;
        vel.z=z;
    }
    public void changeVel(float x, float y, float z) {
        vel.x+=x;
        vel.y+=y;
        vel.z+=z;
    }
    public void setVel(Vector3f vec) {
        vel.x=vec.x;
        vel.y=vec.y;
        vel.z=vec.z;
    }
    public void changeVel(Vector3f vec) {
        vel.x+=vec.x;
        vel.y+=vec.y;
        vel.z+=vec.z;
    }

    public void tick(){
	    pos.add(vel);
    }

    public void onCollide(Entity other) {

    }
}
