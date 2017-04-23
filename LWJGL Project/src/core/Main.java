package core;

import entities.Entity;
import guis.Gui;
import org.joml.Vector2f;
import org.joml.Vector3f;
import renderers.MasterRenderer;
import textures.Animation;
import textures.Animations;
import textures.Texture;
import time.Time;
import world.World;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

public class Main {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "Cracked Earth";

	private Window window;
	private World world;
    private List<Entity> entities;
    private List<Gui> guis;

	public static void main(String[] args){
		new Main();
	}
	
	public Main(){

		window = new Window(WIDTH, HEIGHT, TITLE);

		MasterRenderer renderer = new MasterRenderer();

        entities = new ArrayList<Entity>();
        guis = new ArrayList<Gui>();

//        entities.add(new Entity(new Vector2f(0, 0), new Vector2f(256, 256), Animations.getAnimation("./res/img.png", 1, 1)));
//        guis.add(new Gui("./res/sun.png", new Vector2f(0, .9375f), new Vector2f(2, .125f)));

		world = World.load("world1.ce");


		Time.addTimer((f)->{tick(f);}, 1/60D);
		Time.addTimer((f)->{System.out.println("TPS:"+(tps+(tps=0))+", FPS:"+(fps+(fps=0)));}, 1);

		while(!window.shouldClose()){
			glClear(GL_COLOR_BUFFER_BIT);
			Time.update();
			fps++;

			renderer.render(world, entities, guis, window.getCamera());

			window.swapBuffers();
		}
		cleanUp();
	}
	
	private int tps=0, fps=0;
	
	private void tick(float delta){
		float camDist = 200 * delta;

//        entities.get(0).setPos((float)Math.sin(Time.getTotalGameTime())*100, (float)Math.cos(Time.getTotalGameTime())*100);

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
