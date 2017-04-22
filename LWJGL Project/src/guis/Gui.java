package guis;

import org.joml.Vector2f;
import textures.Texture;
import textures.Textures;

/**
 * Created by joeba on 4/21/2017.
 */
public class Gui {

    private Vector2f pos, size;
    private Texture texture;

    public Gui(String texture, Vector2f pos, Vector2f size){
        this.pos=pos;
        this.size=size;
        this.texture = Textures.getTexture(texture);
    }

    public Vector2f getPos() {
        return pos;
    }

    public Vector2f getSize() {
        return size;
    }

    public Texture getTexture() {
        return texture;
    }
}
