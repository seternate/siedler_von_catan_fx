package siedlerfx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import siedlerfx.game.screens.StartScreen;

public class SiedlerGame extends Game {
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;
	
	public SpriteBatch batch;
	public AssetManager assets;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		assets = new AssetManager();
		
		setScreen(new StartScreen(this));
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		assets.dispose();
	}
	
	
}
