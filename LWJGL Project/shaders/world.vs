#version 120

in vec3 position;
in vec2 textureCoords;

out vec2 pass_tex;

uniform mat4 projection;

uniform vec3 scale;
uniform vec3 pos;

void main(){
	pass_tex = textureCoords;
	
	vec3 worldCoords = position;

	worldCoords = worldCoords * scale;
	worldCoords = worldCoords + pos;

	gl_Position = projection * vec4(worldCoords, 1);
}