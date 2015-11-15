package com.unlimitedstudios.blocktap.stages;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Sagar on 2015-11-07.
 */
public class GameStage extends Stage{
    private static final int VIEWPORT_WIDTH = 13;
    private static final int VIEWPORT_HEIGHT = 20;



    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    //private Box2DDebugRenderer renderer

    public GameStage() {
        setUpCamera();
    }

    private void setUpCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        //fixed timestep
       // accumulator += delta;
    }

    @Override
    public void draw() {
        super.draw();
    }


}

