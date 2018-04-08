package siedlerfx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
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

	private OrthographicCamera camera;
	private Viewport viewport;
	
	private Music gameMusic;
	private Texture woodTexture, woolTexture, grainTexture, clayTexture, oreTexture, sandTexture, waterTexture;
	private int fieldWidth, fieldHeight;
	
	private Player[] players;
	private MatchField matchField;
	
	private Scene playerHud;
	
	
	public PlayScreen(SiedlerGame game) {
		this.game = game;
		camera = new OrthographicCamera();
		viewport = new ScreenViewport(camera);
		camera.position.set(SiedlerGame.V_WIDTH / 2, SiedlerGame.V_HEIGHT / 2, 0);
		
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game.ogg"));
		gameMusic.setLooping(true);
		gameMusic.play();
		
		woodTexture = new Texture("fields/wood.png");
		woolTexture = new Texture("fields/wool.png");
		grainTexture = new Texture("fields/grain.png");
		clayTexture = new Texture("fields/clay.png");
		oreTexture = new Texture("fields/ore.png");
		sandTexture = new Texture("fields/desert.png");
		waterTexture = new Texture("fields/water.png");
		fieldWidth = woodTexture.getWidth() / 7;
		fieldHeight = woodTexture.getHeight() / 7;
		
		matchField = MatchField.initMatchField();
		players = Player.initPlayers(MatchField.initMatchField(), new String[] {"Player1", "Player2", "Player3", "Player4"});
		
		playerHud = new PlayerHud(game, new FitViewport(SiedlerGame.V_WIDTH, SiedlerGame.V_HEIGHT, new OrthographicCamera()));

	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	private void handleInput() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		handleInput();
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		//background water
		int waterFieldsWidth = SiedlerGame.V_WIDTH / fieldWidth;
		int waterFieldsHeight = SiedlerGame.V_HEIGHT / fieldHeight;
		Sprite sprite;
		//middle fields water
		for(int i = 0; i <= waterFieldsWidth + 1; i++) {
			sprite = new Sprite(waterTexture);
			sprite.setOrigin(fieldWidth / 2, fieldHeight / 2);
			sprite.setRotation(MathUtils.random(6)*60);
			sprite.setPosition((SiedlerGame.V_WIDTH - fieldWidth) / 2 + (i-(waterFieldsWidth+1)/2)*fieldWidth, (SiedlerGame.V_HEIGHT - fieldHeight) / 2);
			sprite.setSize(fieldWidth, fieldHeight);
			sprite.draw(game.batch);
		}
		//game.batch.draw(waterTexture, (SiedlerGame.V_WIDTH - fieldWidth) / 2 + (i-(waterFieldsWidth+1)/2)*fieldWidth, (SiedlerGame.V_HEIGHT - fieldHeight) / 2, fieldWidth, fieldHeight);
		for(int k = 1; k <= waterFieldsHeight / 2 + 1; k++) {
			if(k%2 == 0) {
				for(int i = 0; i <= waterFieldsWidth + 1; i++)
					game.batch.draw(waterTexture, (SiedlerGame.V_WIDTH - fieldWidth) / 2 + (i-(waterFieldsWidth+1)/2)*fieldWidth, (SiedlerGame.V_HEIGHT - fieldHeight) / 2 + k*fieldHeight - k*4*fieldHeight/15, fieldWidth, fieldHeight);
				for(int i = 0; i <= waterFieldsWidth + 1; i++) 
					game.batch.draw(waterTexture, (SiedlerGame.V_WIDTH - fieldWidth) / 2 + (i-(waterFieldsWidth+1)/2)*fieldWidth, (SiedlerGame.V_HEIGHT - fieldHeight) / 2 - k*fieldHeight + k*4*fieldHeight/15, fieldWidth, fieldHeight);
			}else {
				for(int i = 0; i <= waterFieldsWidth + 1; i++)
					game.batch.draw(waterTexture, SiedlerGame.V_WIDTH / 2 + (i-(waterFieldsWidth+1)/2)*fieldWidth, (SiedlerGame.V_HEIGHT - fieldHeight) / 2 + k*fieldHeight - k*4*fieldHeight/15, fieldWidth, fieldHeight);
				for(int i = 0; i <= waterFieldsWidth + 1; i++)
					game.batch.draw(waterTexture, SiedlerGame.V_WIDTH / 2 + (i-(waterFieldsWidth+1)/2)*fieldWidth, (SiedlerGame.V_HEIGHT - fieldHeight) / 2 - k*fieldHeight + k*4*fieldHeight/15, fieldWidth, fieldHeight);
			}
		}
		//every row of the fields
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
		
		/*sprite = new Sprite(waterTexture);
		sprite.setOrigin(fieldWidth / 2, fieldHeight / 2);
		sprite.setRotation(180);
		sprite.setSize(fieldWidth, fieldHeight);
		sprite.setPosition((SiedlerGame.V_WIDTH - fieldWidth) / 2 , (SiedlerGame.V_HEIGHT - fieldHeight) / 2);
		sprite.draw(game.batch);*/
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
		woodTexture.dispose();
		woolTexture.dispose();
		grainTexture.dispose();
		clayTexture.dispose();
		oreTexture.dispose();
		sandTexture.dispose();
		waterTexture.dispose();
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

}
