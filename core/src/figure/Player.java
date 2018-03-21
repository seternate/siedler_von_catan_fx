package figure;

import java.util.ArrayList;
import java.util.List;
import playing_field.Crossing;
import playing_field.Field;
import playing_field.MatchField;
import playing_field.RESOURCE;
import playing_field.Street;

public class Player {
	
	private static Player[] player;
	private static MatchField matchField;
	
	public static Player[] initPlayers(MatchField matchField, String[] name) {
		if(Player.player == null) {
			Player.matchField = matchField;
			Player.player = new Player[name.length];
			
			for(int i = 0; i < name.length; i++) {
				Player.player[i] = new Player(name[i]);
			}
		}
		
		return Player.player;
	}
	
	public static Player[] getPlayer() {
		return Player.player;
	}
	
	
	
	private int wood, wool, grain, clay, ore;
	private String name;
	private List<Crossing> settlements;
	private List<Crossing> cities;
	private List<Street> streets;
	
	private Player() {
		this.settlements = new ArrayList<Crossing>();
		this.cities = new ArrayList<Crossing>();
		this.streets = new ArrayList<Street>();
		
		this.wood = 0;
		this.wool = 0;
		this.grain = 0;
		this.clay = 0;
		this.ore = 0;
	}
	
	private Player(String name) {
		this();
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.getName();
	}
	
	public int getWood() {
		return this.wood;
	}
	
	public int getWool() {
		return this.wool;
	}
	
	public int getGrain() {
		return this.grain;
	}
	
	public int getClay() {
		return this.clay;
	}
	
	public int getOre() {
		return this.ore;
	}
	
	public boolean setBeginSettlement(int index) {
		if(this.settlements.size() > 2) {
			return false;
		}
		
		Crossing crossing = Player.matchField.getCrossing(index);
		if(crossing.isOccupied()) {
			return false;
		}
		
		Crossing[] crossingsAround = crossing.getCrossingsAround();	
		for(Crossing crossingAround : crossingsAround) {
			if(crossingAround.isOccupied()) {
				return false;
			}
		}
					
		crossing.setPlayer(this);
		this.settlements.add(crossing);
		return true;
	}
	
	public boolean setSettlement(int index) {
		if(this.settlements.size() > 5) {
			return false;
		}
		
		if(this.getWood() < 1 || this.getWool() < 1 || this.getGrain() < 1 || this.getClay() < 1) {
			return false;
		}
		
		Crossing crossing = Player.matchField.getCrossing(index);
		if(crossing.isOccupied()) {
			return false;
		}
		
		Crossing[] crossingsAround = crossing.getCrossingsAround();	
		for(Crossing crossingAround : crossingsAround) {
			if(crossingAround.isOccupied()) {
				return false;
			}
		}
					
		Street[] streetsAround = crossing.getStreetsAround();
		for(Street streetAround : streetsAround) {
			if(streetAround.getPlayer() == this) {
				crossing.setPlayer(this);
				this.settlements.add(crossing);
				return true;
			}
		}
		
		return false;
	}

	public boolean setBeginStreet(int indexStreet, int indexSettlement) {
		if(this.streets.size() > 2) {
			return false;
		}
		
		Street street = Player.matchField.getStreet(indexStreet);
		if(street.isOccupied()) {
			return false;
		}
	
		Crossing crossing = Player.matchField.getCrossing(indexSettlement);
		for(Crossing crossingAround : street.getCrossingsAround()) {
			if(crossingAround == crossing && crossingAround.getPlayer() == this) {
				street.setPlayer(this);
				this.streets.add(street);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean setStreet(int index) {
		if(this.streets.size() > 15) {
			return false;
		}
		
		if(this.getWood() < 1 || this.getClay() < 1) {
			return false;
		}
		
		Street street = Player.matchField.getStreet(index);
		if(street.isOccupied()) {
			return false;
		}
		
		Street[] streetsAround = street.getStreetsAround();
		List<Street> ownStreets = new ArrayList<Street>();
		for(Street streetAround : streetsAround) {
			if(streetAround.getPlayer() == this) {
				ownStreets.add(streetAround);
			}
		}
		if(ownStreets.size() == 0) {
			return false;
		}
		
		for(Street ownStreet : ownStreets) {
			if(!street.getSameCrossing(ownStreet).isOccupied() || street.getSameCrossing(ownStreet).getPlayer() == this) {
				street.setPlayer(this);
				this.streets.add(street);
				return true;
			}
		}
		return false;
	}
	
	public boolean claimBeginResources() {
		if(this.settlements.size() != 2 && this.cities.size() != 0 && this.streets.size() != 2) {
			return false;
		}
		
		Crossing settlement = this.settlements.get(1);
		for(Field fieldAround : settlement.getFieldsAround()) {
			RESOURCE resource = fieldAround.getResource();
			
			if(resource == RESOURCE.WOOD)
				this.wood++;
			else if(resource == RESOURCE.WOOL)
				this.wool++;
			else if(resource == RESOURCE.GRAIN)
				this.grain++;
			else if(resource == RESOURCE.CLAY)
				this.clay++;
			else if(resource == RESOURCE.ORE)
				this.ore++;
		}
		return true;
	}

	public void printResources() {
		String output = new String();
		output += "Holz: " + this.wood + "\n";
		output += "Wolle: " + this.wool + "\n";
		output += "Weizen: " + this.grain + "\n";
		output += "Lehm: " + this.clay + "\n";
		output += "Eisen: " + this.ore + "\n";
		System.out.print(output);
	}
	
}
