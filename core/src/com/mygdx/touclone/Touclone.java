package com.mygdx.touclone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Touclone extends Game {

	// Master Game Class - contains all resources

	Sprite player;

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont font;
	int w, h;

	class TouchInfo{
		public float touchX = 0;
		public float touchY = 0;
		public boolean touched = false;
	}

	TouchInfo finger;

	@Override
	public void create () {

		player = new Sprite(new Texture("player.png"));
		player.setX(Gdx.graphics.getWidth() * .5f);
		player.setY(Gdx.graphics.getHeight() * .1f);

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont(Gdx.files.internal("vcr-80b.fnt"));
		font.setColor(Color.WHITE);
		finger = new TouchInfo();
		setScreen(new TitleScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		font.dispose();
	}


}
