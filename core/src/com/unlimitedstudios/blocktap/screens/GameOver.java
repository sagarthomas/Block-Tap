package com.unlimitedstudios.blocktap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.unlimitedstudios.blocktap.BlockTap;
import com.unlimitedstudios.blocktap.actors.Block;
import com.unlimitedstudios.blocktap.actors.Respawn;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Sagar on 2015-12-22.
 */
public class GameOver implements Screen{
    private BlockTap game;
    private int score;
    private long time = 600000000;
    private boolean restartTouched = false;
    private double rateW, rateH;
    private float scoreX, scoreY;
    Texture gameOver, gameScore, respawn;

    OrthographicCamera camera;
    OrthographicCamera scoreCam;
    Viewport viewport;



    public GameOver(final BlockTap game, int score) {
        this.game = game;
        this.score = score;

        float aspectRatio = (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        camera = new OrthographicCamera(GameScreen.GAME_WORLD_WIDTH * aspectRatio, GameScreen.GAME_WORLD_HEIGHT);
        scoreCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        rateW = scoreCam.viewportWidth/camera.viewportWidth;
        rateH = scoreCam.viewportHeight/camera.viewportHeight;


        //camera = new OrthographicCamera(100 * aspectRatio, 200);

        viewport = new ExtendViewport(GameScreen.GAME_WORLD_WIDTH * aspectRatio, GameScreen.GAME_WORLD_HEIGHT, camera);
        //viewport = new ExtendViewport(200 * aspectRatio, 100, camera);

        viewport.apply();

        gameOver = new Texture(Gdx.files.internal("Game Over.png"));
        gameScore = new Texture(Gdx.files.internal("Score.png"));
        respawn = new Texture(Gdx.files.internal("Respawn.png"));

        game.font.setColor(Color.BLACK);
        //game.font.getData().setScale(1, 1);
        game.font.getData().setScale(0.2f, 0.2f);
    }

    Respawn restart = new Respawn();

    private void showRespawn() {

        restart.x = 3f;
        restart.y = 7;
        restart.width = 5;
        restart.height = 5;
        /*
        restart.x = 130;
        restart.y = 250;
        restart.width = 230;
        restart.height = 250;
        */
        restart.setTexture(respawn);
   }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();
        camera.setToOrtho(false, 12, 20);


        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());

        scoreX = (float)(scoreCam.position.x - (camera.position.x - 2)*rateW);
        scoreY = (float)(scoreCam.position.y - (camera.position.y - 2) * rateH);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        //game.batch.draw(gameOver, 5, 10);

        game.batch.draw(gameScore, 2, 0, 4, 6);
        //game.font.draw(game.batch, "" + score, 220, 175);
        game.font.draw(game.batch, "" + score, 7 ,3.6f);
        showRespawn();
        game.batch.draw(restart.getTexture(), restart.x, restart.y, 6, 6);
        game.batch.draw(gameOver, 0, 8, 12 , 15);
        game.batch.end();


//        Matrix4 uiMatrix = camera.combined.cpy();
//        uiMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//
//        game.batch.setProjectionMatrix(normalProjection);
//        game.batch.begin();
//        game.font.draw(game.batch, "" + score, 0, 0);
//        game.batch.end();

        //Gdx.app.log("Score x", "" + scoreX);
        //Gdx.app.log("Score y", "" + scoreY);




        if(Gdx.input.isTouched()){
            Vector3 touchPoint = new Vector3();
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPoint);

                if(restart.contains(touchPoint.x, touchPoint.y))
                    restart.setTouched(true);

        }
        if (restart.isTouched()) {
            restartTouched = true;
            game.setScreen(new GameScreen(game, restartTouched));
        }



    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(GameScreen.GAME_WORLD_WIDTH/2, GameScreen.GAME_WORLD_HEIGHT/2,0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
