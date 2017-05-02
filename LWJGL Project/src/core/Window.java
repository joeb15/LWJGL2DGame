package core;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Window {
	
	private long window;
	private int width, height;
	private String title;
	private boolean fullscreen;
	private Camera cam;
	
	public Window(String title){
		fullscreen = true;
		this.title=title;
		createWindow();
	}
	
	public Window(int w, int h, String title){
		this.width=w;
		this.height=h;
		this.title=title;
		fullscreen=false;
		createWindow();
	}
	
	public void createWindow(){
		if(glfwInit()==false){
			System.err.println("GLFW Init failed");
			System.exit(1);
		}
		
		GLFWVidMode vm = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		if(fullscreen){
			this.width = vm.width();
			this.height = vm.height();
		}
		
		cam = new Camera(width, height);
		
		if(fullscreen){
			window = glfwCreateWindow(width, height, title, glfwGetPrimaryMonitor(), 0);
		}else{
			window = glfwCreateWindow(width, height, title, 0, 0);
		}
		if(window == 0)
			throw new IllegalStateException("Failed to create window!");
		
		
		
		glfwSetWindowPos(window, vm.width()/2-width/2, vm.height()/2-height/2);
		
		glfwShowWindow(window);
		
		glfwMakeContextCurrent(window);
		
		GL.createCapabilities();
	}
	
	public void setFullscreen(boolean fs){
		this.fullscreen=fs;
		createWindow();
	}
	
	public boolean isFullscreen(){
		return fullscreen;
	}
	
	public void setSize(int w, int h){
		width=w;
		height=h;
		createWindow();
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}

	public boolean getKey(int key) {
		return glfwGetKey(window, key)==GLFW_TRUE;
	}

	public void swapBuffers() {
		glfwSwapBuffers(window);
	}
	
	public Camera getCamera(){
		return cam;
	}
	
}
