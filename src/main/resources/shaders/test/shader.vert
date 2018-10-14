#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texCoords;
layout (location = 2) in vec3 normals;

uniform mat4 projectionMatrix;
uniform mat4 worldMatrix;
uniform mat4 modelViewMatrix;
uniform vec3 color;
uniform vec3 lightPos;

out vec3 outColor;
out vec2 outTexCoords;
out float diff;

void main()
{
	gl_Position = projectionMatrix * worldMatrix * modelViewMatrix * vec4(position, 1.0);

	outColor = color;
	outTexCoords = texCoords;

	vec3 norm = normalize(normals);
	vec3 lightDir = normalize(lightPos - position);
	diff = max(dot(norm, lightDir), 0.2);
}