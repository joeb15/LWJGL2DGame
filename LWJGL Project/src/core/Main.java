package core;

import entities.Entity;
import guis.Gui;
import org.joml.Vector2f;
import org.joml.Vector3f;
import renderers.MasterRenderer;
import textures.Animations;
import textures.Texture;
import textures.Textures;
import time.Time;
import world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

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
        Random r = new Random();
        Texture img = Textures.getTexture("./res/img.png");
        entities.add(new Entity(new Vector3f(0,0,10), new Vector3f(32,32,32), img));
//        guis.add(new Gui("./res/sun.png", new Vector2f(0, .9375f), new Vector2f(2, .125f)));

        //window.getCamera().setPos(entities.get(0).getPos());

		world = World.load("world1.ce");


		Time.addTimer((f)->{tick(f);}, 1/60D);
		Time.addTimer((f)->{System.out.println("TPS:"+(tps+(tps=0))+", FPS:"+(fps+(fps=0)));}, 1);

		glEnable(GL_DEPTH_TEST);

		while(!window.shouldClose()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
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

		Entity main = entities.get(0);
		window.getCamera().addRot(new Vector3f(delta*20, 0, 0));
//		main.setPos((float)Time.getTotalGameTime()*10, (float)Math.sin(Time.getTotalGameTime()/5f)*50);

//		if(window.getKey(GLFW_KEY_W))
//			main.changePos(0,camDist);
//		if(window.getKey(GLFW_KEY_S))
//            main.changePos(0,-camDist);
//		if(window.getKey(GLFW_KEY_A))
//            main.changePos(-camDist,0);
//		if(window.getKey(GLFW_KEY_D))
//            main.changePos(camDist,0);

		tps++;
        for(Entity e:entities)
            e.tick();
		glfwPollEvents();
	}
	
	private void cleanUp(){
//		world.save("world1.ce");
		glfwTerminate();
	}
	
}
