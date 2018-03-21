package figure;

import playing_field.Field;

public class Robber {
	
	private static Robber robber;
	
	public static Robber initRobber() {
		if(Robber.robber == null) {
			Robber.robber = new Robber();
		}
		
		return Robber.robber;
	}
	
	public static Robber getRobber() {
		return Robber.robber;
	}
	
	
	
	private Field field;
	
	private Robber() {
		this.field = null;
	}
	
	private Robber(Field field) {
		this.field = field;
	}
	
	public Field getField() {
		return this.field;
	}
	
	public boolean setField(Field field) {
		if(this.getField() == field) {
			return false;
		}
		
		this.field = field;
		return true;
	}
	
}
