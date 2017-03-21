package shaders;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class Shader {

	protected int program;
	private int vs;
	private int fs;
	
	public Shader(String vert, String frag){
		program = glCreateProgram();

		vs = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vs, readFile(vert));
		glCompileShader(vs);
		if(glGetShaderi(vs, GL_COMPILE_STATUS) != 1){
			System.err.println(glGetShaderInfoLog(vs));
			System.exit(1);
		}
		
		fs = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fs, readFile(frag));
		glCompileShader(fs);
		if(glGetShaderi(fs, GL_COMPILE_STATUS) != 1){
			System.err.println(glGetShaderInfoLog(fs));
			System.exit(1);
		}

		glAttachShader(program, vs);
		glAttachShader(program, fs);
		
		bindAttribLocations();
		
		glLinkProgram(program);
		if(glGetProgrami(program, GL_LINK_STATUS) != 1){
			System.err.println(glGetProgramInfoLog(program));
			System.exit(1);
		}
		
		glValidateProgram(program);
		if(glGetProgrami(program, GL_VALIDATE_STATUS) != 1){
			System.err.println(glGetProgramInfoLog(program));
			System.exit(1);
		}
	}
	
	public void addUniforms(Uniform... uniforms){
		for(Uniform u:uniforms)
			u.storeUniformLocation(program);
	}
	
	public abstract void bindAttribLocations();
	
	public void bind(){
		glUseProgram(program);
	}
	
	private String readFile(String fileName){
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			String s;
			while((s=br.readLine())!=null){
				sb.append(s);
				sb.append('\n');
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Unable to read: "+fileName);
			System.exit(1);
		}
		return null;
	}
	
}
