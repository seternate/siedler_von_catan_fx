package siedlerfx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import siedlerfx.game.SiedlerGame;
import siedlerfx.game.screens.PlayScreen;

public class StartMenu extends Scene{
	private Skin skin;
	private TextureAtlas buttonAtlas;
	private TextButton playBtn;
	
	public StartMenu(final SiedlerGame game, Viewport viewport) {
		super(game, viewport);
		
		buttonAtlas = new TextureAtlas(Gdx.files.internal("orange/skin/uiskin.atlas"));
		skin = new Skin(Gdx.files.internal("orange/skin/uiskin.json"), buttonAtlas);
		playBtn = new TextButton("Play", skin);
		playBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getScreen().dispose();
				game.setScreen(new PlayScreen(game));
			}
			
		});
		
		Table table = new Table();
		table.setFillParent(true);
		
		table.add(playBtn);
		
		stage.addActor(table);
	}
	
	public void dispose() {
		stage.dispose();
		skin.dispose();
		buttonAtlas.dispose();
	}

	@Override
	public void render(float dt) {
		super.game.batch.setProjectionMatrix(super.stage.getCamera().combined);
		super.stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		super.stage.getViewport().update(width, height);
		
	}
}
