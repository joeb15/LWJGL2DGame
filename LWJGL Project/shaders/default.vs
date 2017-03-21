#version 120

in vec3 position;
in vec2 textureCoords;

out vec2 pass_tex;

uniform mat4 projection;

void main(){
	pass_tex = textureCoords;
	gl_Position = projection * vec4(position, 1);
}