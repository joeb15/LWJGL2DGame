#version 120

in vec3 position;
in vec2 textureCoords;

out vec2 pass_tex;

uniform mat4 projection;

uniform vec2 scale;
uniform vec2 pos;
uniform float depth;

void main(){
	pass_tex = textureCoords;

	vec3 worldCoords = position * scale + pos;

	gl_Position = projection * vec4(worldCoords, 1);
}