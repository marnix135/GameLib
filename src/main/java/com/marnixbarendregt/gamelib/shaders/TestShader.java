package com.marnixbarendregt.gamelib.shaders;

/**
 * Created by marnixbarendregt on 21/09/2017.
 */
public class TestShader extends ShaderProgram {

    public TestShader() {
        super("resources/shaders/test/shader.vert", "resources/shaders/test/shader.frag");

        try {
            createUniform("projectionMatrix");
            createUniform("worldMatrix");
            createUniform("modelViewMatrix");
            createUniform("color");
            createUniform("texture_sampler");
            createUniform("hasTexture");
            createUniform("lightPos");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
