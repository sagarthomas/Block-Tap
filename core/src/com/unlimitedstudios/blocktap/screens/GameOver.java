package com.unlimitedstudios.blocktap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.utils.TimeUtils;
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
    Texture gameOver, gameScore, respawn;
    private long time = 600000000;
    OrthographicCamera camera;



    public GameOver(final BlockTap game, int score) {
        this.game = game;
        this.score = score;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);

        gameOver = new Texture(Gdx.files.internal("Game Over.png"));
        gameScore = new Texture(Gdx.files.internal("Score.png"));
        respawn = new Texture(Gdx.files.internal("Respawn2.png"));
        game.font.getData().setScale(3, 3);


    }



    Respawn restart = new Respawn();

    private void showRespawn() {

        restart.x = 130;
        restart.y = 250;
        restart.width = 230;
        restart.height = 250;
        restart.setTexture(respawn);
   }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);




        game.batch.begin();

        game.batch.draw(gameOver, 10, 240);
        game.batch.draw(gameScore, 40, 120);
        game.font.draw(game.batch, "" + score, 220, 175);
        showRespawn();
        game.batch.draw(restart.getTexture(), restart.x, restart.y);
        game.batch.end();






        if(Gdx.input.isTouched()){
            Vector3 touchPoint = new Vector3();
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPoint);

                if(restart.contains(touchPoint.x, touchPoint.y))
                    restart.setTouched(true);

        }
        if (restart.isTouched())
        game.setScreen(new GameScreen(game));


    }

    @Override
    public void resize(int width, int height) {

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
