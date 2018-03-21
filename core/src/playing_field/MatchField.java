package playing_field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import figure.Port;

public class MatchField {
	
	private static MatchField matchField;
	
	public static final int FIELD_NUM = 19;
	public static final int DESERT_FIELD = 9;
	public static final int CROSSING_NUM = 54;
	public static final int STREET_NUM = 72;
	public static final int PORT_NUM = 9;
	
	public static MatchField initMatchField() {
		if(MatchField.matchField == null) {
			MatchField.matchField = new MatchField();
		}
		
		return MatchField.matchField;
	}
	
	
	
	private Field[] fields;
	private Crossing[] crossings;
	private Street[] streets;
	
	/*
	 * Standard-Constructor to Initialize the whole match field
	 */
	private MatchField() {		
		this.fields = new Field[MatchField.FIELD_NUM];
		this.crossings = new Crossing[MatchField.CROSSING_NUM];
		this.streets = new Street[MatchField.STREET_NUM];
		
		this.initFields();
		this.initCrossings();
		this.initStreets();
		
		this.connectAll();
	}
	
	/*
	 * Initialize all Fields with resources and numbers
	 */
	private void initFields() {
		this.initResource();
		this.initNumbers();		
	}
	
	/*
	 * Initialize all Resources randomly with the right amount from 'RESOURCE.java' over all Fields.
	 * Middle Field is Desert with Robber at the beginning of the match.
	 */
	private void initResource() {
		final int[] RESOURCE_NUM = {4, 4, 4, 3, 3};
		final RESOURCE[] RESOURCES = RESOURCE.values();
		
		//Random-Generator
		Random random = new Random();
		int rng;
		
		//Middle Field with Desert and Robber
		fields[DESERT_FIELD] = new Field(RESOURCE.SAND);
		fields[DESERT_FIELD].setRobber();
		
		
		//Iterate over all Fields and assign the Resources randomly
		for(int k = 0; k < RESOURCES.length - 1; k++) {
			for(int i = 0; i < RESOURCE_NUM[k]; i++) {
				//Get random number between 0 and FIELD_NUM-1, until a Field has no assigned Resource
				do {
					rng = random.nextInt(FIELD_NUM);
				}while(fields[rng] != null);
				//Assign Resource if the field has no Resource assigned
				fields[rng] = new Field(RESOURCES[k]);
			}
		}
		
		
	}
	
	/*
	 * Initialize the numbers you can dice randomly over the fields
	 */
	private void initNumbers() {
		//Random-Generator
		Random random = new Random();
		int rng;
		
		//All available numbers to be all over the Fields
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.addAll(Arrays.asList(2 , 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12));
		
		//Iterate over all Fields and assign a number from 'numbers'
		for(Field field : fields) {
			//Get a number from 'numbers'
			rng = random.nextInt(numbers.size());
			
			if(field.getResource() != RESOURCE.SAND) {
				//Assign number to field, if its not the Middle Field 
				//And delete assigned number from the 'number'-List
				field.setNumber(numbers.get(rng));
				numbers.remove(rng);
			}
		}
	}
	
	/*
	 * Initialize all empty Crossings and set all ports to the field
	 */
	private void initCrossings() {
		Port[] ports = new Port[MatchField.PORT_NUM];
		
		//Iterate over all crossings and initialize them
		for(int i = 0; i < MatchField.CROSSING_NUM; i++) {
			this.getCrossings()[i] = new Crossing();
		}
		
		//Initialize all ports with following pattern: even - 2:1 (randomly from each resource one port), odd - 3:1
		for(int i = 0; i < ports.length; i++) {
			if(i%2 == 0) {
				ports[i] = new Port(RESOURCE.values()[i/2]);
			}
			else {
				ports[i] = new Port();
			}
		}	
		
		//Assign the ports to the fields
		this.getCrossing(0).setPort(ports[0]);
		this.getCrossing(1).setPort(ports[0]);
		
		this.getCrossing(3).setPort(ports[1]);
		this.getCrossing(4).setPort(ports[1]);
		
		this.getCrossing(14).setPort(ports[2]);
		this.getCrossing(15).setPort(ports[2]);
		
		this.getCrossing(26).setPort(ports[3]);
		this.getCrossing(37).setPort(ports[3]);
		
		this.getCrossing(45).setPort(ports[4]);
		this.getCrossing(53).setPort(ports[4]);
		
		this.getCrossing(50).setPort(ports[5]);
		this.getCrossing(51).setPort(ports[5]);
		
		this.getCrossing(47).setPort(ports[6]);
		this.getCrossing(48).setPort(ports[6]);
		
		this.getCrossing(27).setPort(ports[7]);
		this.getCrossing(28).setPort(ports[7]);
		
		this.getCrossing(7).setPort(ports[8]);
		this.getCrossing(17).setPort(ports[8]);
	}

	/*
	 * Initialize all empty Streets
	 */
	private void initStreets() {
		//Iterate over all streets and initialize them
		for(int i = 0; i < MatchField.STREET_NUM; i++) {
			this.getStreets()[i] = new Street();
		}
	}
	
	/*
	 * Connect all Crossings, Streets and Fields together
	 */
	private void connectAll() {
		//Connect all surrounding crossings to the crossings
		this.getCrossing(0).setCrossingsAround(this.getCrossing(1), this.getCrossing(8));
		this.getCrossing(1).setCrossingsAround(this.getCrossing(0), this.getCrossing(2));
		this.getCrossing(2).setCrossingsAround(this.getCrossing(1), this.getCrossing(3), this.getCrossing(10));
		this.getCrossing(3).setCrossingsAround(this.getCrossing(2), this.getCrossing(4));
		this.getCrossing(4).setCrossingsAround(this.getCrossing(3), this.getCrossing(5), this.getCrossing(12));
		this.getCrossing(5).setCrossingsAround(this.getCrossing(4), this.getCrossing(6));
		this.getCrossing(6).setCrossingsAround(this.getCrossing(5), this.getCrossing(14));
		this.getCrossing(7).setCrossingsAround(this.getCrossing(8), this.getCrossing(17));
		this.getCrossing(8).setCrossingsAround(this.getCrossing(0), this.getCrossing(7), this.getCrossing(9));
		this.getCrossing(9).setCrossingsAround(this.getCrossing(8), this.getCrossing(10), this.getCrossing(19));
		this.getCrossing(10).setCrossingsAround(this.getCrossing(2), this.getCrossing(9), this.getCrossing(11));
		this.getCrossing(11).setCrossingsAround(this.getCrossing(10), this.getCrossing(12), this.getCrossing(21));
		this.getCrossing(12).setCrossingsAround(this.getCrossing(4), this.getCrossing(11), this.getCrossing(13));
		this.getCrossing(13).setCrossingsAround(this.getCrossing(12), this.getCrossing(14), this.getCrossing(23));
		this.getCrossing(14).setCrossingsAround(this.getCrossing(6), this.getCrossing(13), this.getCrossing(15));
		this.getCrossing(15).setCrossingsAround(this.getCrossing(14), this.getCrossing(25));
		this.getCrossing(16).setCrossingsAround(this.getCrossing(17), this.getCrossing(27));
		this.getCrossing(17).setCrossingsAround(this.getCrossing(7), this.getCrossing(16), this.getCrossing(18));
		this.getCrossing(18).setCrossingsAround(this.getCrossing(17), this.getCrossing(19), this.getCrossing(29));
		this.getCrossing(19).setCrossingsAround(this.getCrossing(9), this.getCrossing(18), this.getCrossing(20));
		this.getCrossing(20).setCrossingsAround(this.getCrossing(19), this.getCrossing(21), this.getCrossing(31));
		this.getCrossing(21).setCrossingsAround(this.getCrossing(11), this.getCrossing(20), this.getCrossing(22));
		this.getCrossing(22).setCrossingsAround(this.getCrossing(21), this.getCrossing(23), this.getCrossing(33));
		this.getCrossing(23).setCrossingsAround(this.getCrossing(13), this.getCrossing(22), this.getCrossing(24));
		this.getCrossing(24).setCrossingsAround(this.getCrossing(23), this.getCrossing(25), this.getCrossing(35));
		this.getCrossing(25).setCrossingsAround(this.getCrossing(15), this.getCrossing(24), this.getCrossing(26));
		this.getCrossing(26).setCrossingsAround(this.getCrossing(25), this.getCrossing(37));
		this.getCrossing(27).setCrossingsAround(this.getCrossing(16), this.getCrossing(28));
		this.getCrossing(28).setCrossingsAround(this.getCrossing(27), this.getCrossing(29), this.getCrossing(38));
		this.getCrossing(29).setCrossingsAround(this.getCrossing(18), this.getCrossing(28), this.getCrossing(30));
		this.getCrossing(30).setCrossingsAround(this.getCrossing(29), this.getCrossing(31), this.getCrossing(40));
		this.getCrossing(31).setCrossingsAround(this.getCrossing(20), this.getCrossing(30), this.getCrossing(32));
		this.getCrossing(32).setCrossingsAround(this.getCrossing(31), this.getCrossing(33), this.getCrossing(42));
		this.getCrossing(33).setCrossingsAround(this.getCrossing(22), this.getCrossing(32), this.getCrossing(34));
		this.getCrossing(34).setCrossingsAround(this.getCrossing(33), this.getCrossing(35), this.getCrossing(44));
		this.getCrossing(35).setCrossingsAround(this.getCrossing(24), this.getCrossing(34), this.getCrossing(36));
		this.getCrossing(36).setCrossingsAround(this.getCrossing(35), this.getCrossing(37), this.getCrossing(46));
		this.getCrossing(37).setCrossingsAround(this.getCrossing(26), this.getCrossing(36));
		this.getCrossing(38).setCrossingsAround(this.getCrossing(28), this.getCrossing(39));
		this.getCrossing(39).setCrossingsAround(this.getCrossing(38), this.getCrossing(40), this.getCrossing(47));
		this.getCrossing(40).setCrossingsAround(this.getCrossing(30), this.getCrossing(39), this.getCrossing(41));
		this.getCrossing(41).setCrossingsAround(this.getCrossing(40), this.getCrossing(42), this.getCrossing(49));
		this.getCrossing(42).setCrossingsAround(this.getCrossing(32), this.getCrossing(41), this.getCrossing(43));
		this.getCrossing(43).setCrossingsAround(this.getCrossing(42), this.getCrossing(44), this.getCrossing(51));
		this.getCrossing(44).setCrossingsAround(this.getCrossing(34), this.getCrossing(43), this.getCrossing(45));
		this.getCrossing(45).setCrossingsAround(this.getCrossing(44), this.getCrossing(46), this.getCrossing(53));
		this.getCrossing(46).setCrossingsAround(this.getCrossing(36), this.getCrossing(45));
		this.getCrossing(47).setCrossingsAround(this.getCrossing(39), this.getCrossing(48));
		this.getCrossing(48).setCrossingsAround(this.getCrossing(47), this.getCrossing(49));
		this.getCrossing(49).setCrossingsAround(this.getCrossing(48), this.getCrossing(50));
		this.getCrossing(50).setCrossingsAround(this.getCrossing(49), this.getCrossing(51));
		this.getCrossing(51).setCrossingsAround(this.getCrossing(50), this.getCrossing(52));
		this.getCrossing(52).setCrossingsAround(this.getCrossing(51), this.getCrossing(53));
		this.getCrossing(53).setCrossingsAround(this.getCrossing(45), this.getCrossing(52));
		
		//Connect all surrounding fields to the crossings
		this.getCrossing(0).setFieldsAround(this.getField(0));
		this.getCrossing(1).setFieldsAround(this.getField(0));
		this.getCrossing(2).setFieldsAround(this.getField(0), this.getField(1));
		this.getCrossing(3).setFieldsAround(this.getField(1));
		this.getCrossing(4).setFieldsAround(this.getField(1), this.getField(2));
		this.getCrossing(5).setFieldsAround(this.getField(2));
		this.getCrossing(6).setFieldsAround(this.getField(2));
		this.getCrossing(7).setFieldsAround(this.getField(3));
		this.getCrossing(8).setFieldsAround(this.getField(0), this.getField(3));
		this.getCrossing(9).setFieldsAround(this.getField(0), this.getField(3), this.getField(4));
		this.getCrossing(10).setFieldsAround(this.getField(0), this.getField(1), this.getField(4));
		this.getCrossing(11).setFieldsAround(this.getField(1), this.getField(4), this.getField(5));
		this.getCrossing(12).setFieldsAround(this.getField(1), this.getField(2), this.getField(5));
		this.getCrossing(13).setFieldsAround(this.getField(2), this.getField(5), this.getField(6));
		this.getCrossing(14).setFieldsAround(this.getField(2), this.getField(6));
		this.getCrossing(15).setFieldsAround(this.getField(6));
		this.getCrossing(16).setFieldsAround(this.getField(7));
		this.getCrossing(17).setFieldsAround(this.getField(3), this.getField(7));
		this.getCrossing(18).setFieldsAround(this.getField(3), this.getField(7), this.getField(8));
		this.getCrossing(19).setFieldsAround(this.getField(3), this.getField(4), this.getField(8));
		this.getCrossing(20).setFieldsAround(this.getField(4), this.getField(8), this.getField(9));
		this.getCrossing(21).setFieldsAround(this.getField(4), this.getField(5), this.getField(9));
		this.getCrossing(22).setFieldsAround(this.getField(5), this.getField(9), this.getField(10));
		this.getCrossing(23).setFieldsAround(this.getField(5), this.getField(6), this.getField(10));
		this.getCrossing(24).setFieldsAround(this.getField(6), this.getField(10), this.getField(11));
		this.getCrossing(25).setFieldsAround(this.getField(6), this.getField(11));
		this.getCrossing(26).setFieldsAround(this.getField(11));
		this.getCrossing(27).setFieldsAround(this.getField(7));
		this.getCrossing(28).setFieldsAround(this.getField(7), this.getField(12));
		this.getCrossing(29).setFieldsAround(this.getField(7), this.getField(8), this.getField(12));
		this.getCrossing(30).setFieldsAround(this.getField(8), this.getField(12), this.getField(13));
		this.getCrossing(31).setFieldsAround(this.getField(8), this.getField(9), this.getField(13));
		this.getCrossing(32).setFieldsAround(this.getField(9), this.getField(13), this.getField(14));
		this.getCrossing(33).setFieldsAround(this.getField(9), this.getField(10), this.getField(14));
		this.getCrossing(34).setFieldsAround(this.getField(10), this.getField(14), this.getField(15));
		this.getCrossing(35).setFieldsAround(this.getField(10), this.getField(11), this.getField(15));
		this.getCrossing(36).setFieldsAround(this.getField(11), this.getField(15));
		this.getCrossing(37).setFieldsAround(this.getField(11));
		this.getCrossing(38).setFieldsAround(this.getField(12));
		this.getCrossing(39).setFieldsAround(this.getField(12), this.getField(16));
		this.getCrossing(40).setFieldsAround(this.getField(12), this.getField(13), this.getField(16));
		this.getCrossing(41).setFieldsAround(this.getField(13), this.getField(16), this.getField(17));
		this.getCrossing(42).setFieldsAround(this.getField(13), this.getField(14), this.getField(17));
		this.getCrossing(43).setFieldsAround(this.getField(14), this.getField(17), this.getField(18));
		this.getCrossing(44).setFieldsAround(this.getField(14), this.getField(15), this.getField(18));
		this.getCrossing(45).setFieldsAround(this.getField(15), this.getField(18));
		this.getCrossing(46).setFieldsAround(this.getField(15));
		this.getCrossing(47).setFieldsAround(this.getField(16));
		this.getCrossing(48).setFieldsAround(this.getField(16));
		this.getCrossing(49).setFieldsAround(this.getField(16), this.getField(17));
		this.getCrossing(50).setFieldsAround(this.getField(17));
		this.getCrossing(51).setFieldsAround(this.getField(17), this.getField(18));
		this.getCrossing(52).setFieldsAround(this.getField(18));
		this.getCrossing(53).setFieldsAround(this.getField(18));
		
		//Connect all surrounding streets to the crossings and otherwise
		this.getCrossing(0).setStreetsAround(this.getStreet(0), this.getStreet(6));
		this.getCrossing(1).setStreetsAround(this.getStreet(0), this.getStreet(1));
		this.getCrossing(2).setStreetsAround(this.getStreet(1), this.getStreet(2), this.getStreet(7));
		this.getCrossing(3).setStreetsAround(this.getStreet(2), this.getStreet(3));
		this.getCrossing(4).setStreetsAround(this.getStreet(3), this.getStreet(4), this.getStreet(8));
		this.getCrossing(5).setStreetsAround(this.getStreet(4), this.getStreet(5));
		this.getCrossing(6).setStreetsAround(this.getStreet(5), this.getStreet(9));
		this.getCrossing(7).setStreetsAround(this.getStreet(10), this.getStreet(18));
		this.getCrossing(8).setStreetsAround(this.getStreet(6), this.getStreet(10), this.getStreet(11));
		this.getCrossing(9).setStreetsAround(this.getStreet(11), this.getStreet(12), this.getStreet(19));
		this.getCrossing(10).setStreetsAround(this.getStreet(7), this.getStreet(12), this.getStreet(13));
		this.getCrossing(11).setStreetsAround(this.getStreet(13), this.getStreet(14), this.getStreet(20));
		this.getCrossing(12).setStreetsAround(this.getStreet(8), this.getStreet(14), this.getStreet(15));
		this.getCrossing(13).setStreetsAround(this.getStreet(15), this.getStreet(16), this.getStreet(21));
		this.getCrossing(14).setStreetsAround(this.getStreet(9), this.getStreet(16), this.getStreet(17));
		this.getCrossing(15).setStreetsAround(this.getStreet(17), this.getStreet(22));
		this.getCrossing(16).setStreetsAround(this.getStreet(23), this.getStreet(33));
		this.getCrossing(17).setStreetsAround(this.getStreet(18), this.getStreet(23), this.getStreet(24));
		this.getCrossing(18).setStreetsAround(this.getStreet(24), this.getStreet(25), this.getStreet(34));
		this.getCrossing(19).setStreetsAround(this.getStreet(25), this.getStreet(26), this.getStreet(19));
		this.getCrossing(20).setStreetsAround(this.getStreet(26), this.getStreet(27), this.getStreet(35));
		this.getCrossing(21).setStreetsAround(this.getStreet(20), this.getStreet(27), this.getStreet(28));
		this.getCrossing(22).setStreetsAround(this.getStreet(28), this.getStreet(29), this.getStreet(36));
		this.getCrossing(23).setStreetsAround(this.getStreet(21), this.getStreet(29), this.getStreet(30));
		this.getCrossing(24).setStreetsAround(this.getStreet(30), this.getStreet(31), this.getStreet(37));
		this.getCrossing(25).setStreetsAround(this.getStreet(22), this.getStreet(31), this.getStreet(32));
		this.getCrossing(26).setStreetsAround(this.getStreet(32), this.getStreet(38));
		this.getCrossing(27).setStreetsAround(this.getStreet(33), this.getStreet(39));
		this.getCrossing(28).setStreetsAround(this.getStreet(39), this.getStreet(40), this.getStreet(49));
		this.getCrossing(29).setStreetsAround(this.getStreet(34), this.getStreet(40), this.getStreet(41));
		this.getCrossing(30).setStreetsAround(this.getStreet(41), this.getStreet(42), this.getStreet(50));
		this.getCrossing(31).setStreetsAround(this.getStreet(35), this.getStreet(42), this.getStreet(43));
		this.getCrossing(32).setStreetsAround(this.getStreet(43), this.getStreet(44), this.getStreet(51));
		this.getCrossing(33).setStreetsAround(this.getStreet(36), this.getStreet(44), this.getStreet(45));
		this.getCrossing(34).setStreetsAround(this.getStreet(45), this.getStreet(46), this.getStreet(52));
		this.getCrossing(35).setStreetsAround(this.getStreet(37), this.getStreet(46), this.getStreet(47));
		this.getCrossing(36).setStreetsAround(this.getStreet(47), this.getStreet(48), this.getStreet(53));
		this.getCrossing(37).setStreetsAround(this.getStreet(38), this.getStreet(48));
		this.getCrossing(38).setStreetsAround(this.getStreet(49), this.getStreet(54));
		this.getCrossing(39).setStreetsAround(this.getStreet(54), this.getStreet(55), this.getStreet(62));
		this.getCrossing(40).setStreetsAround(this.getStreet(50), this.getStreet(55), this.getStreet(56));
		this.getCrossing(41).setStreetsAround(this.getStreet(56), this.getStreet(57), this.getStreet(63));
		this.getCrossing(42).setStreetsAround(this.getStreet(51), this.getStreet(57), this.getStreet(58));
		this.getCrossing(43).setStreetsAround(this.getStreet(58), this.getStreet(59), this.getStreet(64));
		this.getCrossing(44).setStreetsAround(this.getStreet(52), this.getStreet(59), this.getStreet(60));
		this.getCrossing(45).setStreetsAround(this.getStreet(60), this.getStreet(61), this.getStreet(65));
		this.getCrossing(46).setStreetsAround(this.getStreet(53), this.getStreet(61));
		this.getCrossing(47).setStreetsAround(this.getStreet(62), this.getStreet(66));
		this.getCrossing(48).setStreetsAround(this.getStreet(66), this.getStreet(67));
		this.getCrossing(49).setStreetsAround(this.getStreet(63), this.getStreet(67), this.getStreet(68));
		this.getCrossing(50).setStreetsAround(this.getStreet(68), this.getStreet(69));
		this.getCrossing(51).setStreetsAround(this.getStreet(64), this.getStreet(69), this.getStreet(70));
		this.getCrossing(52).setStreetsAround(this.getStreet(70), this.getStreet(71));
		this.getCrossing(53).setStreetsAround(this.getStreet(65), this.getStreet(71));
		
		//Connect all surrounding streets to the streets
		//Iterate over all streets and add the streets from the surrounding crossings, which aren't the current street
		for(int i = 0; i < MatchField.STREET_NUM; i++) {
			Street street = this.getStreet(i);
			Crossing[] crossingsAround = street.getCrossingsAround();
			List <Street> streets = new ArrayList<Street>();
			
			for(Crossing crossing : crossingsAround) {
				Street[] streetsAround = crossing.getStreetsAround();
				
				for(Street streetAround : streetsAround) {
					if(streetAround != street) {
						streets.add(streetAround);
					}
				}
			}
			
			street.setStreetsAround(streets);
		}
		
	}
	
	
	public Field[] getFields() {
		return this.fields;
	}
	
	public Field getField(int index) {
		return this.getFields()[index];
	}
	
	public Crossing[] getCrossings() {
		return this.crossings;
	}
	
	public Crossing getCrossing(int index){
		return this.getCrossings()[index];
	}
	
	public Street[] getStreets() {
		return this.streets;
	}
	
	public Street getStreet(int index) {
		return this.getStreets()[index];
	}
	
	public String toString() {
		String output = new String();
		for(int i = 0; i < MatchField.FIELD_NUM; i++) {
			output += "Feld " + i + ": " + this.getField(i).getResource(); 
			if(this.getField(i).isRobber()) {
				output += "  --  Robber: " + true;
			}
			output += "\n";
		}
		
		
		
		return output;
	}
	
	public void printCrossingsAndStreets() {
		String output = new String();
		
		for(int i = 0; i < MatchField.CROSSING_NUM; i++) {
			if(this.getCrossing(i).isOccupied()) {
				output += "Kreuzung " + i + ": " + this.getCrossing(i).getPlayer() + "\n";
			}
		}
		
		output += "\n";
		for(int i = 0; i < MatchField.STREET_NUM; i++) {
			if(this.getStreet(i).isOccupied()) {
				output += "Straße " + i + ": " + this.getStreet(i).getPlayer() + "\n";
			}
		}
		
		System.out.print("\n" + output + "\n");
	}
	
}
