package com.mygdx.game;


import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Wolf3 extends Game {
	SpriteBatch batch;
	BitmapFont font;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(0 ,0 ,0 ,1);
		this.setScreen( new GameStart(this));

	}

	@Override
	public void render () {
		super.render();
	}

	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		font.dispose();

	}
}
