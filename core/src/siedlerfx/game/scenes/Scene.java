package siedlerfx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import siedlerfx.game.SiedlerGame;

public abstract class Scene {
	public Stage stage;
	
	SiedlerGame game;
	Viewport viewport;
	
	
	public Scene() {
		
	}
	
	public Scene(final SiedlerGame game, Viewport viewport) {
		this.game = game;
		this.viewport = viewport;
		stage = new Stage(viewport, game.batch);
		Gdx.input.setInputProcessor(stage);
	}
	
	public abstract void render(float dt);
	
	public abstract void resize(int width, int height);
	
	public abstract void dispose();
	
}
