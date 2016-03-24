package com.unlimitedstudios.blocktap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.unlimitedstudios.blocktap.screens.GameOver;
import com.unlimitedstudios.blocktap.screens.GameScreen;
import com.unlimitedstudios.blocktap.screens.StartScreen;

public class BlockTap extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public Camera camera;

    public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";


	
	@Override
	public void create () {
		batch = new SpriteBatch();
        //Font Creation
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("MODENINE.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 8; //size_of_the_font_in_pixel
        parameter.characters = FONT_CHARACTERS;

        font = generator.generateFont(parameter);

        generator.dispose();
        //font = new BitmapFont();

		//font = TrueTypeFontFactory.createBitmapFont(Gdx.files.internal("MODENINE.ttf"), FONT_CHARACTERS, 12.5f, 7.5f, 1.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		float aspectRatio = (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
		camera = new OrthographicCamera(20 *aspectRatio, 12);
		setScreen(new StartScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
        font.dispose();
	}
}
