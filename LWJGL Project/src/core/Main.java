package core;

import entities.Entity;
import org.joml.Vector2f;
import org.joml.Vector3f;
import renderers.DefaultRenderer;
import time.Time;
import world.World;
import world.WorldGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Main {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Window";
	
	private Window window;
	
	public static void main(String[] args){
		new Main();
	}
	
	public Main(){
		window = new Window(WIDTH, HEIGHT, TITLE);
		
		DefaultRenderer renderer = new DefaultRenderer();

		List<Entity> entities = new ArrayList<Entity>();

		entities.add(new Entity(new Vector2f(0, 0), new Vector2f(256, 256), "./res/img.png"));

		World world = WorldGenerator.generateWorld();

		Time.addTimer((f)->{
			tick(f);
		}, 1/60D);
		
		Time.addTimer((f)->{
			System.out.println("TPS:"+tps+", FPS:"+fps);
			tps=0;
			fps=0;
		}, 1);
		
		while(!window.shouldClose()){
			Time.update();
			
			fps++;
			renderer.render(world, window.getCamera());
			
			window.swapBuffers();
		}
		cleanUp();
	}
	
	private int tps=0, fps=0;
	
	private void tick(float delta){
		float camDist = 200 * delta;
		
		if(window.getKey(GLFW_KEY_W))
			window.getCamera().addPos(new Vector3f(0,-camDist,0));
		if(window.getKey(GLFW_KEY_S))
			window.getCamera().addPos(new Vector3f(0,camDist,0));
		if(window.getKey(GLFW_KEY_A))
			window.getCamera().addPos(new Vector3f(camDist,0,0));
		if(window.getKey(GLFW_KEY_D))
			window.getCamera().addPos(new Vector3f(-camDist,0,0));
		tps++;
		glfwPollEvents();
	}
	
	private void cleanUp(){
		glfwTerminate();
	}
	
}
