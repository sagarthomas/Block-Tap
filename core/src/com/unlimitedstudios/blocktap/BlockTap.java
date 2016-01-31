package com.unlimitedstudios.blocktap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unlimitedstudios.blocktap.screens.GameScreen;
import com.unlimitedstudios.blocktap.screens.StartScreen;

public class BlockTap extends Game {

	public SpriteBatch batch;
	public BitmapFont font;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
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
