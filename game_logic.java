package game_core;

import java.util.Scanner;

import figure.Player;
import playing_field.MatchField;
import playing_field.RESOURCE;

public class Game {

	public static void main(String[] args) {
		
		beginGame();
		//game();
		
	}
	
	public static void beginGame() {
		Scanner scan = new Scanner(System.in);
		
		MatchField matchField = MatchField.initMatchField();
		System.out.print("Felder:\n\n" + matchField + "\n");
		
		
		
		//Sign player
		System.out.print("Wieviele Mitspieler: ");
		String[] names = new String[scan.nextInt()];
		for(int i = 0; i < names.length; i++) {
			System.out.print("Spieler " + (i+1) + ": ");
			names[i] = scan.next();
		}
		Player[] players = Player.initPlayers(matchField, names);
		
		//First assign
		for(int i = 0; i < players.length; i++) {
			int settlement;
			
			System.out.print("Setzte deine Siedlung " + players[i] + ": ");
			settlement = scan.nextInt();
			while(!players[i].setBeginSettlement(settlement)) {
				System.out.print("Leider nicht möglich! Siedlung: ");
				settlement = scan.nextInt();
			};
			
			System.out.print("Setzte deine Straße " + players[i] + ": ");
			while(!players[i].setBeginStreet(scan.nextInt(), settlement)) {
				System.out.print("Leider nicht möglich! Straße: ");
			};
		}
		
		for(int i = players.length - 1; i >= 0; i--) {
			int settlement;
			
			System.out.print("Setzte deine Siedlung " + players[i] + ": ");
			settlement = scan.nextInt();
			while(!players[i].setBeginSettlement(settlement)) {
				System.out.print("Leider nicht möglich! Siedlung: ");
				settlement = scan.nextInt();
			};
			
			System.out.print("Setzte deine Straße " + players[i] + ": ");
			while(!players[i].setBeginStreet(scan.nextInt(), settlement)) {
				System.out.print("Leider nicht möglich! Straße: ");
			};
		}
		
		for(Player player: players) {
			player.claimBeginResources();
		}
		
		for(Player player: players) {
			System.out.println();
			System.out.println(player + " Resourcen:");
			player.printResources();
		}
		
		System.out.println();
		matchField.printCrossingsAndStreets();
		
		
	}
	
	public static void debugging() {
		//Scanner scan = new Scanner(System.in);
		
		MatchField matchField = MatchField.initMatchField();
		
		
		int wood = 0;
		int wool = 0;
		int grain = 0;
		int clay = 0;
		int ore = 0;
		int sand = 0;
		
		for(int i = 0; i < matchField.getFields().length; i++)
		{
			if(matchField.getFields()[i].getResource() == RESOURCE.WOOD)
				wood++;
			else if(matchField.getFields()[i].getResource() == RESOURCE.WOOL)
				wool++;
			else if(matchField.getFields()[i].getResource() == RESOURCE.GRAIN)
				grain++;
			else if(matchField.getFields()[i].getResource() == RESOURCE.CLAY)
				clay++;
			else if(matchField.getFields()[i].getResource() == RESOURCE.ORE)
				ore++;
			else if(matchField.getFields()[i].getResource() == RESOURCE.SAND)
				sand++;
		}
		
		System.out.println("Anzahl der Felder: ");
		System.out.println("wood: " + wood);
		System.out.println("wool: " + wool);
		System.out.println("grain: " + grain);
		System.out.println("clay: " + clay);
		System.out.println("ore: " + ore);
		System.out.println("sand: " + sand);
		
		System.out.println("\nFelder: ");
		for(int i = 0; i < MatchField.FIELD_NUM; i++) {
			System.out.println("Feld " + (i+1) + ":" + matchField.getFields()[i].getResource() + "   ---   num: " + matchField.getFields()[i].getNumber()
					+ "   ---   robber: " + matchField.getFields()[i].isRobber());
		}
		
		System.out.println();
		for(int i = 0; i < MatchField.CROSSING_NUM; i++) {
			System.out.println("Crossing: " + (i+1) + "   ---   port: " + matchField.getCrossing(i).getPort());
		}
	}

}
