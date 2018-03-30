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

import figure.Player;
import playing_field.Field;
import playing_field.MatchField;
import siedlerfx.game.SiedlerGame;
import siedlerfx.game.scenes.PlayerHud;
import siedlerfx.game.scenes.Scene;

public class PlayScreen implements Screen{
	private SiedlerGame game;
	
	private Music gameMusic;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private Scene playerHud;
	
	private Texture woodTexture, woolTexture, grainTexture, clayTexture, oreTexture, sandTexture;
	private int fieldWidth, fieldHeight;
	
	private Player[] players;
	private MatchField matchField;
	
	public PlayScreen(SiedlerGame game) {
		this.game = game;
		camera = new OrthographicCamera();
		viewport = new ScreenViewport(camera);
		camera.position.set(SiedlerGame.V_WIDTH / 2, SiedlerGame.V_HEIGHT / 2, 0);
		
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game.ogg"));
		gameMusic.setLooping(true);
		gameMusic.play();
		
		matchField = MatchField.initMatchField();
		players = Player.initPlayers(MatchField.initMatchField(), new String[] {"Player1", "Player2", "Player3", "Player4"});
		
		playerHud = new PlayerHud(game, new FitViewport(SiedlerGame.V_WIDTH, SiedlerGame.V_HEIGHT, new OrthographicCamera()));
		
		woodTexture = new Texture("fields/wood.png");
		woolTexture = new Texture("fields/wool.png");
		grainTexture = new Texture("fields/grain.png");
		clayTexture = new Texture("fields/clay.png");
		oreTexture = new Texture("fields/ore.png");
		sandTexture = new Texture("fields/desert.png");
		
		fieldWidth = woodTexture.getWidth() / 8;
		fieldHeight = woodTexture.getHeight() / 8;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		for(int i = 0; i < 3; i++)
			game.batch.draw(getFieldTexture(matchField.getField(i)), (SiedlerGame.V_WIDTH - fieldWidth) / 2 + (i-1)*fieldWidth, (SiedlerGame.V_HEIGHT - fieldHeight) / 2 + 2*fieldHeight - 6*fieldHeight/11, fieldWidth, fieldHeight);
		for(int i = 3; i < 7; i++)
			game.batch.draw(getFieldTexture(matchField.getField(i)), SiedlerGame.V_WIDTH / 2 - (i-4)*fieldWidth, (SiedlerGame.V_HEIGHT - fieldHeight) / 2 + fieldHeight - 3*fieldHeight/11, fieldWidth, fieldHeight);
		for(int i = 7; i < 12; i++)
			game.batch.draw(getFieldTexture(matchField.getField(i)), (SiedlerGame.V_WIDTH - fieldWidth) / 2 + (i-9)*fieldWidth, (SiedlerGame.V_HEIGHT - fieldHeight) / 2, fieldWidth, fieldHeight);
		for(int i = 12; i < 16; i++)
			game.batch.draw(getFieldTexture(matchField.getField(i)), SiedlerGame.V_WIDTH / 2 - (i-13)*fieldWidth, (SiedlerGame.V_HEIGHT - fieldHeight) / 2 - fieldHeight + 3*fieldHeight/11, fieldWidth, fieldHeight);
		for(int i = 16; i < 19; i++)
			game.batch.draw(getFieldTexture(matchField.getField(i)), (SiedlerGame.V_WIDTH - fieldWidth) / 2 + (i-17)*fieldWidth, (SiedlerGame.V_HEIGHT - fieldHeight) / 2 - 2*fieldHeight + 6*fieldHeight/11, fieldWidth, fieldHeight);
		game.batch.end();
		
		playerHud.render(delta);
	}
	
	private Texture getFieldTexture(Field field){
		switch(field.getResource()){
			case WOOD: return woodTexture;
			case WOOL: return woolTexture;
			case GRAIN: return grainTexture;
			case CLAY: return clayTexture;
			case ORE: return oreTexture;
			case SAND: return sandTexture;
		}
		return null;
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
		woodTexture.dispose();
		woolTexture.dispose();
		grainTexture.dispose();
		clayTexture.dispose();
		oreTexture.dispose();
		sandTexture.dispose();
	}

}
