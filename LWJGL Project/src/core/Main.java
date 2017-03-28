package core;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import entities.Entity;
import renderers.DefaultRenderer;
import time.Time;
import time.Timer;

public class Main {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Window";
	
	private Window window;
	private Camera cam;
	
	public static void main(String[] args){
		new Main();
	}
	
	public Main(){
		window = new Window(WIDTH, HEIGHT, TITLE);
		
		Texture texture = new Texture("./res/img.png");
		
		DefaultRenderer renderer = new DefaultRenderer();
		
		cam = new Camera(window.getWidth(), window.getHeight());
		
		List<Entity> entities = new ArrayList<Entity>();
		
		entities.add(new Entity(new Vector2f(0,0), new Vector2f(256,256), texture));
		
		glEnable(GL_TEXTURE_2D);
		
		new Timer(()->{tick();}, 1/60D);
		new Timer(()->{System.out.println("TPS:"+tps+", FPS:"+fps);tps=0;fps=0;}, 1);
		//TODO: Change Timer to protected and move creation to the Time class
		
		while(!window.shouldClose()){
			Time.update();

			glClear(GL_COLOR_BUFFER_BIT);
			fps++;
			renderer.render(entities, cam);
			
			window.swapBuffers();
		}
		cleanUp();
	}
	private int tps=0, fps=0;
	private void tick(){
		float camDist = (float) (200*Time.getDelta());
		
		if(window.getKey(GLFW_KEY_W))
			cam.addPos(new Vector3f(0,-camDist,0));
		if(window.getKey(GLFW_KEY_S))
			cam.addPos(new Vector3f(0,camDist,0));
		if(window.getKey(GLFW_KEY_A))
			cam.addPos(new Vector3f(camDist,0,0));
		if(window.getKey(GLFW_KEY_D))
			cam.addPos(new Vector3f(-camDist,0,0));
		tps++;
		glfwPollEvents();
	}
	
	private void cleanUp(){
		glfwTerminate();
	}
	
}