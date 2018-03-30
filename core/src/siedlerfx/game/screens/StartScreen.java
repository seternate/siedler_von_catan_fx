package siedlerfx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import siedlerfx.game.SiedlerGame;
import siedlerfx.game.scenes.Scene;
import siedlerfx.game.scenes.StartMenu;

public class StartScreen implements Screen{
	private final SiedlerGame game;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private Music menuMusic;	
	private Texture background;
	
	private Scene startMenu;
	
	public StartScreen(SiedlerGame game) {
		this.game = game;
		camera = new OrthographicCamera();
		viewport = new FitViewport(SiedlerGame.V_WIDTH, SiedlerGame.V_HEIGHT, camera);
		camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
		
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menu.ogg"));
		menuMusic.setLooping(true);
		menuMusic.play();
		
		background = new Texture("menu_screen.jpg");
		
		startMenu = new StartMenu(game, new FitViewport(SiedlerGame.V_WIDTH, SiedlerGame.V_HEIGHT, new OrthographicCamera()));
	}
	
	@Override
	public void show() {
		
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
		game.batch.end();
		
		startMenu.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		startMenu.resize(width, height);
		viewport.update(width, height);
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
		background.dispose();
		startMenu.dispose();
		menuMusic.stop();
		menuMusic.dispose();
	}

}
