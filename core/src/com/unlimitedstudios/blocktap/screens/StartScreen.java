package com.unlimitedstudios.blocktap.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.unlimitedstudios.blocktap.BlockTap;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.unlimitedstudios.blocktap.actors.PlayButton;

/**
 * Created by Charles on 03/01/2016.
 */
public class StartScreen implements Screen {

    private BlockTap game;
    Texture blockTapLogo, playButton;
    OrthographicCamera camera;
    Sprite play;


    Viewport viewport;
    Skin skin = new Skin();
    Stage stage;


    //ImageButton play;
    PlayButton playBtn;

    public StartScreen(final BlockTap game) {
        this.game = game;

        float aspectRatio = (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();

        camera = new OrthographicCamera(GameScreen.GAME_WORLD_WIDTH * aspectRatio, GameScreen.GAME_WORLD_HEIGHT);
        //camera.setToOrtho(false, 480, 800);

        viewport = new ExtendViewport(GameScreen.GAME_WORLD_WIDTH * aspectRatio, GameScreen.GAME_WORLD_HEIGHT, camera);

        viewport.apply();

        stage = new Stage(viewport);
        stage.getViewport().apply();

        playBtn = new PlayButton();
        playButton = new Texture(Gdx.files.internal("Play.png"));
        //skin.add("playButton", playButton);
        //play = new ImageButton(skin.getDrawable("playButton"));
        //play.setX(Gdx.graphics.getWidth() / 4);
        //play.setY((Gdx.graphics.getHeight() / 4));



        blockTapLogo = new Texture(Gdx.files.internal("BlockTapLogo4.png"));
        playButton = new Texture(Gdx.files.internal("Play.png"));
        play = new Sprite(playButton);

        playBtn.setSprite(play);
        playBtn.setX(2);
        playBtn.setY(4);
        playBtn.initialize();

        stage.addActor(playBtn);
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

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        game.batch.begin();
        game.batch.draw(blockTapLogo, -2, 6, 16, 18);
        //stage.draw();
        game.batch.end();


        if(playBtn.isTouched()) {
            game.setScreen(new GameScreen(game, false));
        }




//        play.addListener(new InputListener() {
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                game.setScreen(new GameScreen(game, false));
//                return true;
//            }
//        });

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
        blockTapLogo.dispose();
        stage.dispose();
        playButton.dispose();
        skin.dispose();
        //play.remove();


    }

}
