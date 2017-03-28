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
	
}
