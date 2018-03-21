package playing_field;

import java.util.List;

import figure.Player;

public class Street{
	
	private Crossing[] crossingsAround;
	private Street[] streetsAround;
	private Player player;
	
	public Street() {
		this.setPlayer(null);
		this.crossingsAround = new Crossing[2];
		this.streetsAround = new Street[4];
	}
	
	boolean setCrossing(Crossing crossing) {
		for(int i = 0; i < this.getCrossingsAround().length; i++) {
			if(this.getCrossingAround(i) == null) {
				this.crossingsAround[i] = crossing;
				return true;
			}
		}
		
		return false;
	}
	
	void setCrossings(Crossing crossing1, Crossing crossing2) {
		this.crossingsAround[0] = crossing1;
		this.crossingsAround[1] = crossing2;
	}
	
	public Crossing[] getCrossingsAround() {
		return this.crossingsAround;
	}
	
	public Crossing getCrossingAround(int index) {
		return this.getCrossingsAround()[index];
	}

	void setStreetsAround(List<Street> streets) {
		if(streets.size() != 4) {
			this.streetsAround = new Street[streets.size()];
		}
		for(int i = 0; i < streets.size(); i++) {
			this.streetsAround[i] = streets.get(i);
		}
	}
		
	public Street[] getStreetsAround() {
		return this.streetsAround;
	}
	
	public Street getStreetAround(int index) {
		return this.getStreetsAround()[index];
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public boolean isOccupied() {
		if(this.getPlayer() == null) {
			return false;
		}
		
		return true;
	}
	
	public Crossing getSameCrossing(Street street) {
		for(int i = 0; i < this.getCrossingsAround().length; i++) {
			for(int k = 0; k < street.getCrossingsAround().length; k++) {
				if(this.getCrossingAround(i) == this.getCrossingAround(k)) {
					return this.getCrossingAround(i);
				}
			}
		}
		
		return null;
	}
	
}
