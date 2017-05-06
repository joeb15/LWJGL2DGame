#version 120

in vec3 position;
in vec2 textureCoords;

out vec2 pass_tex;

uniform vec2 scale;
uniform vec2 pos;
uniform vec2 screen;

void main(){
	pass_tex = textureCoords;

	vec2 worldCoords = position.xy + vec2(.5);
    worldCoords = worldCoords * scale + pos - screen/2;
    worldCoords = worldCoords*2/screen;
	gl_Position = vec4(worldCoords, 0, 1);
}