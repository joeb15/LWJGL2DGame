#version 120

in vec2 pass_tex;

uniform sampler2D tex;

void main(){
    vec4 color = texture2D(tex, pass_tex);
    float gray = (color.r+color.g+color.b)/3;
	gl_FragColor = color;
}