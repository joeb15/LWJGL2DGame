package textures;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL14.GL_GENERATE_MIPMAP_HINT;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Texture {

    protected boolean isAnimation;
    protected int id, width, height;

    protected Texture(){}

	public Texture(String fileName){
	    isAnimation=false;
		BufferedImage bi;
		try{
			bi = ImageIO.read(new File(fileName));
			width = bi.getWidth();
			height = bi.getHeight();
			int[] raw = new int[width*height];
			bi.getRGB(0, 0, width, height, raw, 0, width);
			ByteBuffer pix = BufferUtils.createByteBuffer(width*height*4);
			for(int y=0;y<height;y++){
				for(int x=0;x<width;x++){
					int pixel = raw[y*width + x];
					pix.put((byte)(pixel >> 16 & 0xff));
					pix.put((byte)(pixel >> 8 & 0xff));
					pix.put((byte)(pixel & 0xff));
					pix.put((byte)(pixel >> 24));
				}
			}
			pix.flip();
			
			id = glGenTextures();
			glBindTexture(GL_TEXTURE_2D, id);
			
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pix);
			glHint(GL_GENERATE_MIPMAP_HINT, GL_FASTEST);
			glGenerateMipmap(GL_TEXTURE_2D);
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void bind(int sample){
		if(sample>=0 && sample<=31){
			glActiveTexture(GL_TEXTURE0 + sample);
			glBindTexture(GL_TEXTURE_2D, id);
		}
	}
	
}
