package core;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import shaders.DefaultShader;

public class Main {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Window";
	
	public static void main(String[] args){
		new Main();
	}
	
	public Main(){
		Window window = new Window(WIDTH, HEIGHT, TITLE);
		float[] vertices = new float[]{-.5f,.5f,0,.5f,.5f,0,.5f,-.5f,0,-.5f,-.5f,0};
		float[] texCoords = new float[]{0,0,1,0,1,1,0,1};
		int[] indices = new int[]{0,1,2,2,3,0};

		Model model = new Model(vertices, texCoords, indices);
		Texture texture = new Texture("./res/img.png");
		
		DefaultShader shader = new DefaultShader();
		
		Camera cam = new Camera(window.getWidth(), window.getHeight());
		
		glEnable(GL_TEXTURE_2D);
		
		while(!window.shouldClose()){

			if(window.getKey(GLFW_KEY_W)){
				
			}
			
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);

			shader.bind();
			shader.projection.loadMat4(cam.getProjection());
			texture.bind(0);
			model.render();
			
			window.swapBuffers();
		}
		cleanUp();
	}
	
	private void cleanUp(){
		glfwTerminate();
	}
	
}
