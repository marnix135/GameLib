#version 330

in vec3 outColor;
in vec2 outTexCoords;
in vec3 outNormals;

uniform sampler2D texture_sampler;
uniform float hasTexture;

out vec4 fragColor;

void main()
{
    if (hasTexture > 0.5) {
        fragColor = texture(texture_sampler, outTexCoords);
    } else {
        fragColor = vec4(outColor, 1.0);
    }


    float diffuseFactor = max(dot(outNormals, vec3(0.5, 0.5, 0.0)), 0.0);
    fragColor = fragColor * vec4(diffuseFactor);

}