package com.unlimitedstudios.blocktap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.unlimitedstudios.blocktap.BlockTap;
import com.unlimitedstudios.blocktap.actors.Block;

/**
 * Created by Sagar on 2015-12-22.
 */
public class GameOver implements Screen{
    private BlockTap game;
    private int score;
    Texture gameOver, gameScore, respawn;
    private long time = 600000000;

    public GameOver(final BlockTap game, int score) {
        this.game = game;
        this.score = score;

        gameOver = new Texture(Gdx.files.internal("Game Over.png"));
        gameScore = new Texture(Gdx.files.internal("Score.png"));
        respawn = new Texture(Gdx.files.internal("Respawn.png"));
        game.font.getData().setScale(3, 3);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.setScreen(new GameScreen(game));
        /*
        game.batch.begin();
        //game.font.draw(game.batch, "Game Over", 240, 780);
        game.batch.draw(gameOver, 10, 240);
        game.batch.draw(gameScore, 40, 120);
        game.font.draw(game.batch, "" + score, 200, 120);
        //game.batch.draw(respawn, 120, 240);
        game.batch.end();
        */



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
