package com.unlimitedstudios.blocktap;

import com.badlogic.gdx.Game;
import com.unlimitedstudios.blocktap.screens.GameScreen;

public class BlockTap extends Game {

	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

}
