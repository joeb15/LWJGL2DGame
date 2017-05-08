package core;

import entities.Entity;
import guis.Gui;
import guis.TestGui;
import org.joml.Vector2f;
import renderers.MasterRenderer;
import textures.Animations;
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
    private List<Gui> guis;

	public static void main(String[] args){
		new Main();
	}

	public Main(){

		window = new Window(WIDTH, HEIGHT, TITLE);

		MasterRenderer renderer = new MasterRenderer();

        entities = new ArrayList<Entity>();
        guis = new ArrayList<Gui>();

        entities.add(new Entity(new Vector2f(0, 0), new Vector2f(256, 256), Animations.getAnimation("./res/img.png", 1, 1)));
        guis.add(new TestGui());

        window.getCamera().setPos(entities.get(0).getPos());

		world = World.load("world1.ce");


		Time.addTimer((f)->{tick(f);}, 1/60D);
		Time.addTimer((f)->{System.out.println("TPS:"+(tps+(tps=0))+", FPS:"+(fps+(fps=0)));}, 1);

		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		while(!window.shouldClose()){
			glClear(GL_COLOR_BUFFER_BIT);
			Time.update();
			fps++;

			renderer.render(world, entities, guis, window);

			window.swapBuffers();
		}
		cleanUp();
	}

	private int tps=0, fps=0;

	private void tick(float delta){
		float speed = 200;
        Entity main = entities.get(0);

		main.setVel(20, (float)Math.sin(Time.getTotalGameTime()/5f)*50);

		if(window.getKey(GLFW_KEY_W))
			main.changeVel(0,speed);
		if(window.getKey(GLFW_KEY_S))
            main.changeVel(0,-speed);
		if(window.getKey(GLFW_KEY_A))
            main.changeVel(-speed,0);
		if(window.getKey(GLFW_KEY_D))
            main.changeVel(speed,0);

		tps++;
        for(Entity e:entities)
            e.tick(delta);
        for(Gui g:guis)
            g.preClick(window.getCursorPos(), window.isCursorClicked());
		glfwPollEvents();
	}
	
	private void cleanUp(){
		world.save("world1.ce");
		glfwTerminate();
	}
	
}
