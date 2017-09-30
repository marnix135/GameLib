package com.marnixbarendregt.gamelib.main;

import com.marnixbarendregt.gamelib.mesh.Mesh;
import com.marnixbarendregt.gamelib.utils.Noise;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by marnixbarendregt on 27/09/2017.
 */
public class Terrain {

    private int width;
    private int depth;
    private int indicesSize;
    private Mesh mesh;
    private Matrix4f modelViewMatrix;


    public Terrain(int width, int depth) {
        this.width = width;
        this.depth = depth;
        this.modelViewMatrix = new Matrix4f().identity();

        float[][] heights = new float[width][depth];

        float[] vertices = new float[width * depth * 3];
        float[] normals = new float[width * depth * 3];
        int[] indices = new int[6 * (width - 1) * (depth - 1)];

        this.indicesSize = indices.length;

        Noise noise = new Noise();


        int pointer = 0;

        for (int z = 0; z < depth; z++) {
            for (int x = 0; x < width; x++) {
                int index = x + z * width;

                // heights
                float y = noise.generateTerrainNoise((float) x * 0.1f, (float) z * 0.1f, 70.0f, 3f, 0.3f);
                heights[x][z] = y;

                // Vertices
                vertices[3 * index] = (float) x;
                vertices[3 * index + 1] = y;
                vertices[3 * index + 2] = (float) z;


                // Indices
                if (z < (depth - 1) && x < (width - 1)) {
                    int topLeft = x + z * width;
                    int topRight = (x + 1) + z * width;
                    int bottomLeft = x + (z + 1) * width;
                    int bottomRight = (x + 1) + (z + 1) * width;

                    indices[pointer++] = topLeft;
                    indices[pointer++] = bottomLeft;
                    indices[pointer++] = topRight;
                    indices[pointer++] = topRight;
                    indices[pointer++] = bottomLeft;
                    indices[pointer++] = bottomRight;


                    if (z > 1 && x > 1) {
                        // Normals

                        Vector3f normal = calculateNormal(x, z, heights);
                        normals[3 * index] = normal.x;
                        normals[3 * index + 1] = normal.y;
                        normals[3 * index + 2] = normal.z;
                    }
                }
            }
        }

        this.mesh = new Mesh(vertices, indices, new float[0], normals);
    }


    public void render() {
        glBindVertexArray(mesh.getVaoId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glDrawElements(GL_TRIANGLES, indicesSize, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
    }

    public Matrix4f getModelViewMatrix() {
        modelViewMatrix.identity();
        modelViewMatrix.translate(new Vector3f(0.0f, 0.0f, 0.0f));
        modelViewMatrix.rotate((float) Math.toRadians(0.0f), new Vector3f(1, 0, 0));
        modelViewMatrix.rotate((float) Math.toRadians(0.0f), new Vector3f(0, 1, 0));
        modelViewMatrix.rotate((float) Math.toRadians(0.0f), new Vector3f(0, 0, 1));
        modelViewMatrix.scale(1.0f);

        return modelViewMatrix;
    }

    private Vector3f calculateNormal(int x, int z, float[][] heights) {
        float leftHeight = 0;
        float rightHeight = 0;
        float topHeight = 0;
        float bottomHeight = 0;

        if (x > 0 && z > 0 && x < heights.length && z < heights[0].length) {
            leftHeight = heights[x - 1][z];
            rightHeight = heights[x + 1][z];
            topHeight = heights[x][z - 1];
            bottomHeight = heights[x][z + 1];
        }

        return new Vector3f(leftHeight - rightHeight, 2f, topHeight - bottomHeight).normalize();
    }

    public Mesh getMesh() {
        return mesh;
    }
}
