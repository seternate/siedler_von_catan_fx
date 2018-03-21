package playing_field;

import figure.Player;
import figure.Port;

public class Crossing {
	
	private Field[] fieldsAround;
	private Crossing[] crossingsAround;
	private Street[] streetsAround;
	private Player player;
	private Port port;
	
	public Crossing() {
		this.setPlayer(null);
		this.setPort(null);
		this.fieldsAround = new Field[3];
		this.crossingsAround = new Crossing[3];
		this.streetsAround = new Street[3];
	}
	
	public Field[] getFieldsAround() {
		return this.fieldsAround;
	}
	
	public Field getFieldAround(int index) {
		return this.getFieldsAround()[index];
	}
	
	void setFieldsAround(Field field) {
		this.fieldsAround = new Field[1];
		this.fieldsAround[0] = field;		
	}
	
	void setFieldsAround(Field field1, Field field2) {
		this.fieldsAround = new Field[2];
		this.fieldsAround[0] = field1;
		this.fieldsAround[1] = field2;		
	}
	
	void setFieldsAround(Field field1, Field field2, Field field3) {
		this.fieldsAround[0] = field1;
		this.fieldsAround[1] = field2;
		this.fieldsAround[2] = field3;
		
	}
	
	public Crossing[] getCrossingsAround() {
		return this.crossingsAround;
	}
	
	public Crossing getCrossingAround(int index) {
		return this.getCrossingsAround()[index];
	}
	
	void setCrossingsAround(Crossing crossing1, Crossing crossing2) {
		this.crossingsAround = new Crossing[2];
		this.crossingsAround[0] = crossing1;
		this.crossingsAround[1] = crossing2;
	}
	
	void setCrossingsAround(Crossing crossing1, Crossing crossing2, Crossing crossing3) {
		this.crossingsAround[0] = crossing1;
		this.crossingsAround[1] = crossing2;
		this.crossingsAround[2] = crossing3;
	}
	
	public Street[] getStreetsAround() {
		return this.streetsAround;
	}
	
	public Street getStreetAround(int index) {
		return this.getStreetsAround()[index];
	}
	
	void setStreetsAround(Street street1, Street street2) {
		this.streetsAround = new Street[2];
		this.streetsAround[0] = street1;
		street1.setCrossing(this);
		this.streetsAround[1] = street2;
		street2.setCrossing(this);
	}
	
	void setStreetsAround(Street street1, Street street2, Street street3) {
		this.streetsAround[0] = street1;
		street1.setCrossing(this);
		this.streetsAround[1] = street2;
		street2.setCrossing(this);
		this.streetsAround[2] = street3;
		street3.setCrossing(this);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public boolean isOccupied() {
		if(this.getPlayer() == null) {
			return false;
		}
		
		return true;
	}
	
	public Port getPort() {
		return this.port;
	}
	
	void setPort(Port port) {
		this.port = port;
	}
	
	public boolean isPort() {
		if(this.getPort()== null) {
			return false;
		}
		
		return true;
	}
	
}
