package com.marnixbarendregt.gamelib.shaders;

public class TerrainShader extends ShaderProgram {

    public TerrainShader() {
        super("resources/shaders/terrain/shader.vert", "resources/shaders/terrain/shader.frag");

        try {
            createUniform("projectionMatrix");
            createUniform("worldMatrix");
            createUniform("modelViewMatrix");
            createUniform("color");
            createUniform("lightPos");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
