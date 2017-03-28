package entities;

import org.joml.Vector2f;

import core.Texture;

public class Entity {

	private Vector2f pos, size;
	private Texture texture;
	
	public Entity(Vector2f pos, Vector2f size, Texture texture){
		this.pos=pos;
		this.size=size;
		this.texture=texture;
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
