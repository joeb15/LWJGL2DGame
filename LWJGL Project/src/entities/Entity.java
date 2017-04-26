package entities;

import org.joml.Vector2f;
import textures.Texture;
import textures.Textures;

public class Entity {

	private Vector2f pos, size, vel;
	private Texture texture;

    public Entity(Vector2f pos, Vector2f size, String textureFile){
        this.pos=pos;
        this.size=size;
        vel = new Vector2f();
        this.texture=Textures.getTexture(textureFile);
    }

    public Entity(Vector2f pos, Vector2f size, Texture texture){
        this.pos=pos;
        this.size=size;
        vel = new Vector2f();
        this.texture=texture;
    }
	
	public Vector2f getPos(){
		return pos;
	}
	
	public Vector2f getSize(){
		return size;
	}

	public Vector2f getVel(){
	    return vel;
    }

	public Texture getTexture() {
		return texture;
	}

    public void setPos(float x, float y) {
        pos.x=x;
        pos.y=y;
    }
    public void changePos(float x, float y) {
        pos.x+=x;
        pos.y+=y;
    }
    public void setPos(Vector2f vec) {
        pos.x=vec.x;
        pos.y=vec.y;
    }
    public void changePos(Vector2f vec) {
        pos.x+=vec.x;
        pos.y+=vec.y;
    }
    public void setVel(float x, float y) {
        vel.x=x;
        vel.y=y;
    }
    public void changeVel(float x, float y) {
        vel.x+=x;
        vel.y+=y;
    }
    public void setVel(Vector2f vec) {
        vel.x=vec.x;
        vel.y=vec.y;
    }
    public void changeVel(Vector2f vec) {
        vel.x+=vec.x;
        vel.y+=vec.y;
    }

    public void tick(){
	    pos.add(vel);
    }

    public void onCollide(Entity other) {

    }
}
