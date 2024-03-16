
package game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.NPCs.enemies.Dog;
import game.NPCs.enemies.GiantDog;
import game.NPCs.trader.FingerReaderEnia;
import game.NPCs.trader.Melenia;
import game.Utils.CategoriseMap;
import game.Utils.FancyMessage;
import game.combattypes.*;
import game.gamegrounds.generalground.Cliff;
import game.gamegrounds.generalground.Dirt;
import game.gamegrounds.generalground.Floor;
import game.gamegrounds.generalground.Wall;
import game.gamegrounds.habitats.*;
import game.gamegrounds.sitesofgrace.TheFirstStep;
import game.NPCs.trader.MerchantKale;
import game.gamegrounds.specialground.GoldenFogDoor;
import game.gamegrounds.specialground.SummonSign;
import game.gameitems.Apple;
import game.gameitems.GoldenRunes;
import game.gameweapons.Grossmesser;
import game.gameitems.RemembranceOfTheGrafted;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new SummonSign(),new Barrack(), new Cage(), new Dirt(), new Wall(), new Floor(),new Graveyard(),new GustOfWind(),new PuddleOfWater(), new TheFirstStep(),new Cliff());

		List<String> limGraveArray = Arrays.asList(
				"......................#.............#..........................+++.........",
				"......................#.............#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"......................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"...........................................................................",
				"...........................=...............................................",
				"........++++......................###___###................................",
				"........+++++++...................____U___#................................",
				"..........+++.....................#________................................",
				"............+++...................#_______#................................",
				".............+....................###___###................................",
				"............++......................#___#..................................",
				"..............+...................=........................................",
				"..............++.................................................=.........",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++...........................+++............#....____....",
				"_____________#.....++++..........................+..............__.....#...",
				"_____________#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");

		List<String> stormveilArray = Arrays.asList(
										"...........................................................................",
										"..................<...............<........................................",
										"...........................................................................",
										"##############################################...##########################",
										"............................#................#.......B..............B......",
										".....B...............B......#................#.............................",
										"...............................<.........<.................................",
										".....B...............B......#................#.......B..............B......",
										"............................#................#.............................",
										"#####################..#############...############.####..#########...#####",
										"...............#++++++++++++#................#++++++++++++#................",
										"...............#++++++++++++...<.........<...#++++++++++++#................",
										"...............#++++++++++++..................++++++++++++#................",
										"...............#++++++++++++#................#++++++++++++#................",
										"#####...##########.....#############...#############..#############...#####",
										".._______........................B......B........................B.....B...",
										"_____..._..____....&&........<..............<..............................",
										".........____......&&......................................................",
										"...._______..................<..............<....................<.....<...",
										"#####....##...###..#####...##########___###############......##.....####...",
										"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
										"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
										"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
										"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");

		List<String> roundTableArray = Arrays.asList(
										"##################",
										"#________________#",
										"#________________#",
										"#________________#",
										"#________________#",
										"#________________#",
										"#________________#",
										"#________________#",
										"#________________#",
										"#________________#",
										"########___#######");

		List<String> bossRoomArray = Arrays.asList(
										"+++++++++++++++++++++++++",
										".........................",
										"....=....................",
										".........................",
										".........................",
										".........................",
										".........................",
										".........................",
										"+++++++++++++++++++++++++");

		// adding the limgrave map
		GameMap limGrave = new GameMap(groundFactory, limGraveArray);
		Location locationOfGrace = CategoriseMap.sortMap(limGrave);
		world.addGameMap(limGrave);

		// adding the stormveil map
		GameMap stormVeil = new GameMap(groundFactory,stormveilArray);
		world.addGameMap(stormVeil);
		CategoriseMap.sortMap(stormVeil);
		// adding the roundtable hold
		GameMap roundTable = new GameMap(groundFactory,roundTableArray);
		world.addGameMap(roundTable);
		roundTable.at(5,3).addActor(new FingerReaderEnia());
		//adding the bossroom
		GameMap bossRoom = new GameMap(groundFactory,bossRoomArray);
		world.addGameMap(bossRoom);

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
/**
 * Player could choose combat archetypes to set up correct hip point and weapon
 */
		HashMap<Character, CombatArcheTypes> selectArcheTypes= new HashMap<Character, CombatArcheTypes>();
		selectArcheTypes.put('a', new Bandit());
		selectArcheTypes.put('b', new Samurai());
		selectArcheTypes.put('c', new Wretch());
		selectArcheTypes.put('d', new Astrologer());

		Display display = new Display();
		display.println("Select Your Archetypes: ");
		for(Character i: selectArcheTypes.keySet()){
			display.println(i +". "+selectArcheTypes.get(i).getArcheTypeName());
		}
		// assume we always get good input
		char select =  display.readChar();




		//for the golden fog door
		Location limGraveDoor = limGrave.at(4,23);
		Location lim_storm = limGrave.at(30,3);
		Location storm_lim= stormVeil.at(39,20);
		lim_storm.setGround(new GoldenFogDoor(storm_lim,"Stormveil Castle"));
		storm_lim.setGround(new GoldenFogDoor(lim_storm,"Limgrave"));
		Location roundtableHoldDoor = roundTable.at(6,4);
		limGraveDoor.setGround(new GoldenFogDoor(roundtableHoldDoor,"RoundTable Hold"));
		roundtableHoldDoor.setGround((new GoldenFogDoor(limGraveDoor, "Limgrave")));
		Location bossRoomDoor= bossRoom.at(7,3);
		stormVeil.at(45,0).setGround(new GoldenFogDoor(bossRoomDoor,"Boss Room"));


		Player player = new Player("Tarnished", '@',1,selectArcheTypes.get(select));
		player.addItemToInventory(new RemembranceOfTheGrafted());
		limGrave.at(40, 12).addActor(new MerchantKale());
		limGrave.at(36,12).addActor(new Melenia());
		limGrave.at(33,15).addItem(new Apple(player));
		limGrave.at(55,20).addItem(new Apple(player));
		limGrave.at(37,8).addActor(new Dog());
		limGrave.at(20, 12).addItem(new GoldenRunes(player));
		limGrave.at(35,15).addActor(new GiantDog());
		// HINT: what does it mean to prefer composition to inheritance?6
		player.setRespawnPoint(locationOfGrace);
		player.addWeaponToInventory(new Grossmesser());
		world.addPlayer(player, locationOfGrace);
		//set up the reset ground site of lost grace

		world.run();
	}
}
