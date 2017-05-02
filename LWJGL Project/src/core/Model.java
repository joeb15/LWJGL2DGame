package core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Model {
	
	private int drawCount, vertID, texID, normID, indID;

    public Model(float[] vertices, float[] texCoords, float[] normals, int[] indices){
        drawCount = indices.length;

        vertID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertID);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_STATIC_DRAW);

        normID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, normID);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(normals), GL_STATIC_DRAW);

        texID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, texID);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(texCoords), GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);

        indID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, createBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public Model(float[] vertices, float[] texCoords, float[] normals){
        drawCount = vertices.length/3;

        int[] indices = new int[drawCount];
        for(int i=0;i<drawCount;i++)
            indices[i]=i;

        vertID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertID);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_STATIC_DRAW);

        normID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, normID);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(normals), GL_STATIC_DRAW);

        texID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, texID);
        glBufferData(GL_ARRAY_BUFFER, createBuffer(texCoords), GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);

        indID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, createBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
	
	public void bind(){
		glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glBindBuffer(GL_ARRAY_BUFFER, vertID);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, normID);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);

		glBindBuffer(GL_ARRAY_BUFFER, texID);
		glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indID);
	}
	
	public void render(){		
		glDrawElements(GL_TRIANGLES, drawCount, GL_UNSIGNED_INT, 0);		
	}
	
	public void unbind(){
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
	}
	
	private FloatBuffer createBuffer(float[] data){
		FloatBuffer buf = BufferUtils.createFloatBuffer(data.length);
		for(float f:data){
			buf.put(f);
		}
		buf.flip();
		return buf;
	}
	
	private IntBuffer createBuffer(int[] data){
		IntBuffer buf = BufferUtils.createIntBuffer(data.length);
		for(int i:data){
			buf.put(i);
		}
		buf.flip();
		return buf;
	}
	
}
