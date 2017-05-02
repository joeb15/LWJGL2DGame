#version 120

in vec2 pass_tex;

uniform sampler2D tex;

void main(){
    vec4 color = texture2D(tex, pass_tex);
	gl_FragColor = vec4(.5);
}