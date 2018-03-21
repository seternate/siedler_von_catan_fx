package siedlerfx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import siedlerfx.game.SiedlerGame;
import siedlerfx.game.scenes.PlayerHud;
import siedlerfx.game.scenes.Scene;

public class PlayScreen implements Screen{
	private SiedlerGame game;
	
	private Music gameMusic;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private Scene playerHud;
	
	private Texture woodField, woolField, grainField, clayField, oreField, sandField;
	
	public PlayScreen(SiedlerGame game) {
		this.game = game;
		camera = new OrthographicCamera();
		viewport = new ScreenViewport(camera);
		camera.position.set(SiedlerGame.V_WIDTH / 2, SiedlerGame.V_HEIGHT / 2, 0);
		
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game.ogg"));
		gameMusic.setLooping(true);
		//gameMusic.play();
		
		playerHud = new PlayerHud(game, new FitViewport(SiedlerGame.V_WIDTH, SiedlerGame.V_HEIGHT, new OrthographicCamera()));
		
		woodField = new Texture("fields/wood.png");
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(woodField,0,0);
		game.batch.end();
		
		playerHud.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		playerHud.resize(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		playerHud.dispose();
		gameMusic.dispose();
		woodField.dispose();
		woolField.dispose();
		grainField.dispose();
		clayField.dispose();
		oreField.dispose();
		sandField.dispose();
	}

}
