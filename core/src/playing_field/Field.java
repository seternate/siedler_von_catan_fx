package playing_field;

import figure.Robber;

public class Field {	

	private Robber robber;
	private int number;
	private RESOURCE resource;
	
	public Field() {
		this.robber = null;
		this.number = 0;
		this.resource = RESOURCE.SAND;
	}
	
	public Field(RESOURCE resource) {
		this.robber = null;
		this.number = 0;
		this.resource = resource;
	}
	
	public RESOURCE getResource() {
		return this.resource;
	}
	
	public boolean isRobber() {
		if(this.robber == null) {
			return false;
		}
		return true;
	}
	
	public void setRobber() {
		this.robber = Robber.initRobber();
		if(Robber.getRobber().getField() == null) {
			Robber.getRobber().setField(this);
			return;
		}
		Robber.getRobber().getField().deleteRobber();
		Robber.getRobber().setField(this);
	}
	
	private void deleteRobber() {
		this.robber = null;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	void setNumber(int number) {
		this.number = number;
	}
	
}

 
