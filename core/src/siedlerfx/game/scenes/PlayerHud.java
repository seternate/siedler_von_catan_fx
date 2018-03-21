package siedlerfx.game.scenes;

import com.badlogic.gdx.utils.viewport.Viewport;

import figure.Player;
import playing_field.MatchField;
import siedlerfx.game.SiedlerGame;

public class PlayerHud extends Scene{
	
	public Player[] player;
	
	public PlayerHud(final SiedlerGame game, Viewport viewport) {
		super(game, viewport);
		
		this.player = Player.initPlayers(MatchField.initMatchField(), new String[]{"Player1", "Player2"});
	}

	@Override
	public void render(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
