package com.unlimitedstudios.blocktap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;
import com.unlimitedstudios.blocktap.BlockTap;
import com.unlimitedstudios.blocktap.actors.Block;
import com.unlimitedstudios.blocktap.actors.PlayButton;
import com.unlimitedstudios.blocktap.actors.Respawn;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Charles on 03/01/2016.
 */
public class StartScreen implements Screen {

    private BlockTap game;
    Texture blockTapLogo, playButton;
    OrthographicCamera camera;

    Skin skin = new Skin();
    Stage stage = new Stage();

    ImageButton play;

    public StartScreen(final BlockTap game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        playButton = new Texture(Gdx.files.internal("Play.png"));
        skin.add("playButton", playButton);
        play = new ImageButton(skin.getDrawable("playButton"));
        play.setX(Gdx.graphics.getWidth() / 4);
        play.setY((Gdx.graphics.getHeight() / 4));

        stage.addActor(play);
        blockTapLogo = new Texture(Gdx.files.internal("BlockTapLogo4.png"));
        playButton = new Texture(Gdx.files.internal("Play.png"));
        game.font.getData().setScale(3, 3);
        Gdx.input.setInputProcessor(stage);



    }












    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(blockTapLogo, 10, 400);
        game.batch.end();
        stage.draw();



        play.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {


                game.setScreen(new GameScreen(game));
                return true;

            }

        });

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
        blockTapLogo.dispose();
        stage.dispose();
        playButton.dispose();
        skin.dispose();
        play.remove();


    }

}
