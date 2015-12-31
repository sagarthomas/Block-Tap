package com.unlimitedstudios.blocktap.screens;

import com.badlogic.gdx.Screen;
import com.unlimitedstudios.blocktap.BlockTap;
import com.unlimitedstudios.blocktap.actors.Block;

/**
 * Created by Sagar on 2015-12-22.
 */
public class GameOver implements Screen{
    private BlockTap game;

    public GameOver(final BlockTap game) {
        this.game = game;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.font.draw(game.batch, "Game Over", 240, 780);
        game.batch.end();

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
