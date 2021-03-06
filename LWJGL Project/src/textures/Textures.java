package textures;

import java.util.HashMap;

public class Textures {

	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	public static Texture getTexture(String fileName){
		if(!textures.containsKey(fileName))
			textures.put(fileName, new Texture(fileName));
		return textures.get(fileName);
	}
	
}
