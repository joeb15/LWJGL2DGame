#version 120

in vec2 pass_tex;

uniform sampler2D tex;

void main(){
	gl_FragColor = texture2D(tex, pass_tex);
}