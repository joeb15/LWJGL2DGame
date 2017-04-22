package entities;

import org.joml.Vector2f;
import textures.Texture;
import textures.Textures;

public class Entity {

	private Vector2f pos, size;
	private Texture texture;
	
	public Entity(Vector2f pos, Vector2f size, String textureFile){
		this.pos=pos;
		this.size=size;
		this.texture=Textures.getTexture(textureFile);
	}
	
	public Vector2f getPos(){
		return pos;
	}
	
	public Vector2f getSize(){
		return size;
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
}
