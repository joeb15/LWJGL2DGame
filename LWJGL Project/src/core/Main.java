package core;

import entities.Entity;
import org.joml.Vector2f;
import org.joml.Vector3f;
import renderers.EntityRenderer;
import renderers.WorldRenderer;
import time.Time;
import world.World;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "Cracked Earth";

	private Window window;
	private World world;
    private List<Entity> entities;

	public static void main(String[] args){
		new Main();
	}
	
	public Main(){

		window = new Window(WIDTH, HEIGHT, TITLE);
		WorldRenderer worldRenderer = new WorldRenderer();
		EntityRenderer entityRenderer = new EntityRenderer();

        entities = new ArrayList<Entity>();

        entities.add(new Entity(new Vector2f(0, 0), new Vector2f(256, 256), "./res/img.png"));

		world = World.load("world1.ce");


		Time.addTimer((f)->{
			tick(f);
		}, 1/60D);
		
		Time.addTimer((f)->{
			System.out.println("TPS:"+tps+", FPS:"+fps);
			tps=0;
			fps=0;
		}, 1);

		glEnable(GL_DEPTH_TEST);

		while(!window.shouldClose()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			Time.update();
			
			fps++;

            entityRenderer.render(entities, window.getCamera());
			worldRenderer.render(world, window.getCamera());


			window.swapBuffers();
		}
		cleanUp();
	}
	
	private int tps=0, fps=0;
	
	private void tick(float delta){
		float camDist = 200 * delta;

        entities.get(0).setPos((float)Math.sin(Time.getTotalGameTime())*100, (float)Math.cos(Time.getTotalGameTime())*100);

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
//		world.save("world1.ce");
		glfwTerminate();
	}
	
}
