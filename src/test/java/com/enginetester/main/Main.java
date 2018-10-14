package com.enginetester.main;

import com.marnixbarendregt.gamelib.controls.FirstPerson;
import com.marnixbarendregt.gamelib.controls.ThirdPerson;
import com.marnixbarendregt.gamelib.main.*;
import com.marnixbarendregt.gamelib.materials.Material;
import com.marnixbarendregt.gamelib.mesh.Cube;
import com.marnixbarendregt.gamelib.utils.Color;
import com.marnixbarendregt.gamelib.utils.OBJParser;
import org.joml.Vector3f;

import java.util.Random;

/**
 * Created by marnixbarendregt on 18/09/2017.
 */
public class Main extends Game {

    private Window window;
    private Scene scene;
    private Renderer renderer;
    private Camera camera;
    private Entity entity;
    private Entity sun;


    private Main() {
        window = new Window(600, 400, "Test", true);
        window.show();

        super.init(window);
    }

    @Override
    public void init() {
        setClearColor(new Color(0, 0, 0));

        camera = new Camera(window, 50f, 0.1f, 1000.0f);

        scene = new Scene();
        renderer = new Renderer();

        //Terrain terrain = new Terrain(500, 500);
        //scene.add(terrain);
        //System.out.println(terrain.getMesh().hasNormals());
        //System.out.println(terrain.getMesh().getNormals().length);
        //sun = new Entity(Cube.getMesh(), Material.TEST, new Color(1.0f, 0.0f, 0.0f), new Vector3f(250.0f, 100.0f, 250.0f), new Vector3f(0.0f), 1.0f);
        //scene.add(sun);

        useControls(new FirstPerson(camera, window,1.0f, 0.6f, true));
    }

    @Override
    public void update() {}

    @Override
    public void render() { renderer.render(scene, camera); }

    @Override
    public void onClose() {
        scene.cleanUp();
    }

    public static void main(String[] args) {
        new Main();
    }
}
