package com.vdum.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vdum.game.states.GameStateManager;
import com.vdum.game.states.MainGame;
import com.vdum.game.states.StartMenu;


public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameStateManager gsm;

	@Override
	public void create() {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		gsm.push(new StartMenu(gsm));

	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
}
