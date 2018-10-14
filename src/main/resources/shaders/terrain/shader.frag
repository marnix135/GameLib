#version 330

in vec3 outColor;
in vec2 outTexCoords;
in float diff;

out vec4 fragColor;

void main()
{
    fragColor = vec4(outColor, 1.0) * diff;
}