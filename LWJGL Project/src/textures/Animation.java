package textures;

import org.lwjgl.BufferUtils;
import time.Time;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_FASTEST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL14.GL_GENERATE_MIPMAP_HINT;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

/**
 * Created by joeba on 4/21/2017.
 */
public class Animation extends Texture{

    private int[] ids;
    private int frames;
    private float time;

    public Animation(String fileName, int frames, float time){
        isAnimation=true;
        this.frames=frames;
        this.time=time;
        ids = new int[frames];
        BufferedImage bi;
        try{
            bi = ImageIO.read(new File(fileName));
            width = bi.getWidth()/frames;
            height = bi.getHeight();

            assert(height%frames==0);
            for(int i=0;i<frames;i++) {
                int[] raw = new int[width * height];
                bi.getRGB(width*i, 0, width, height, raw, 0, width);
                ByteBuffer pix = BufferUtils.createByteBuffer(width * height * 4);
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int pixel = raw[y * width + x];
                        pix.put((byte) (pixel >> 16 & 0xff));
                        pix.put((byte) (pixel >> 8 & 0xff));
                        pix.put((byte) (pixel & 0xff));
                        pix.put((byte) (pixel >> 24));
                    }
                }
                pix.flip();

                ids[i] = glGenTextures();
                glBindTexture(GL_TEXTURE_2D, ids[i]);

                glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
                glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pix);
                glHint(GL_GENERATE_MIPMAP_HINT, GL_FASTEST);
                glGenerateMipmap(GL_TEXTURE_2D);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void bind(int sample){
        float time = (float) Time.getTotalGameTime();
        float unitTime = this.time/frames;
        int frame = (int)((time % this.time)/unitTime);
        assert(frame>=0 && frame<frames);
        if(sample>=0 && sample<=31){
            glActiveTexture(GL_TEXTURE0 + sample);
            glBindTexture(GL_TEXTURE_2D, ids[frame]);
        }
    }
}
