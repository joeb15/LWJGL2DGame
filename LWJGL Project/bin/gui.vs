#version 120

in vec3 position;
in vec2 textureCoords;

out vec2 pass_tex;

uniform vec2 scale;
uniform vec2 pos;

void main(){
	pass_tex = textureCoords;
	
	vec3 worldCoords = position;
	
	worldCoords.x = worldCoords.x * scale.x;
	worldCoords.x = worldCoords.x + pos.x;
	
	worldCoords.y = worldCoords.y * scale.y;
	worldCoords.y = worldCoords.y + pos.y;
	
	gl_Position = vec4(worldCoords, 1);
}