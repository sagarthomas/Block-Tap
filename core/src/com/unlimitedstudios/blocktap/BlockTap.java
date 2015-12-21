package com.unlimitedstudios.blocktap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.unlimitedstudios.blocktap.screens.GameScreen;

public class BlockTap extends Game {

	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		setScreen(new GameScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
	}
}
