package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Utils.FancyMessage;
import game.characteristics.Sleepable;
import game.combattypes.CombatArcheTypes;
//import game.gameactions.moneyrelated.PickUpRunesAction;
import game.gameactions.combatrelated.DeathAction;
import game.gamecapabilities.Monetary;
import game.gamecapabilities.Status;
import game.characteristics.Resettable;
import game.gamecapabilities.Type;
import game.gameitems.FlaskOfCrimsonTears;
import game.gameitems.Runes;
import game.gameitems.SpectralSteedWhistle;
import game.managers.RunesManager;
import game.managers.ResetManager;
import game.managers.SpawnManager;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Sam Abdi, Isaac van der Vliet
 *
 */
public class Player extends Actor implements Resettable, Sleepable {

	private final Menu menu = new Menu();
	private Runes runes = new Runes();
	private Runes oldRunes = null;
	private FlaskOfCrimsonTears flaskOfCrimsonTears;
	private ResetManager resetManager = ResetManager.getInstance();
	private Location respawnPoint;
	private SpawnManager spawnManager = SpawnManager.getInstance();
	private RunesManager runesManager = RunesManager.getInstance();
	private Location runesLocation;
	private CombatArcheTypes combatType;
	private Location previous;
	private int timesDied = 0;


	/**
	 * Constructor.
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, CombatArcheTypes combatType) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.UNKILLABLE);
		this.addCapability(Monetary.RECIEVES_RUNES);
		this.addCapability(Type.GOOD);
		this.runes.setRuneBalance(0,0);
		this.addItemToInventory(runes);
		this.flaskOfCrimsonTears = new FlaskOfCrimsonTears(this);
		this.addItemToInventory(flaskOfCrimsonTears);
		resetManager.registerResettable(this);
		runesManager.setPlayer(this);
		spawnManager.registerSleepbale(this);
		this.combatType=combatType;
		combatType.run(this);
	}

	/**
	 * Player operation
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return correct action
	 */

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		if(map.locationOf(this)!=this.previous) {
			this.runesLocation = this.previous;
			this.previous = map.locationOf(this);
		}
		if (!isConscious()){
			resetManager.run(this,map);
			for (String line : FancyMessage.YOU_DIED.split("\n")) {
				new Display().println(line);
			}
			display.print("\nGame has been reset.");
			return new DeathAction();
		}
		// return/print the console menu
		display.print("Current HP:"+this.printHp()+"\n");
		display.print("Rune Balance: " +this.runes.getRuneBalance() + "\n");
		display.print("Flask of crimson tears: " + flaskOfCrimsonTears.getQuantity() + "\n");


		return menu.showMenu(this, actions, display);
	}

	/**
	 * Get player's runes
	 * @return Runes
	 */
	public Runes getRunes(){
		return this.runes;
	}

	/**
	 * Get player's runes balance
	 * @return int of runes balance
	 */
	public int getBalance(){return this.getRunes().getRuneBalance();}

	/**
	 * Set up respawn point
	 * @param respawnPoint location player want
	 */
	public void setRespawnPoint(Location respawnPoint) {
		this.respawnPoint = respawnPoint;
	}

	/**
	 * Reset actor action
	 * @param actor who need be reset
	 * @param map reset location
	 */
	@Override
	public void reset(Actor actor,GameMap map) {
		if (map.locationOf(this)==this.respawnPoint){
			map.removeActor(this);
			map.addActor(this, respawnPoint);
			this.heal(this.getMaxHp()-this.hitPoints);
		} else{
			map.removeActor(this);
			map.addActor(this, respawnPoint);
			this.heal(this.getMaxHp()-this.hitPoints);
			timesDied ++;
			if (timesDied > 1){
				runesManager.getLocationOfRunes().removeItem(oldRunes);
				timesDied = 1;
			}
			Runes runes=new Runes();
			runes.setRuneBalance(this.getBalance(),this.getBalance());
			runesLocation.addItem(runes);
			this.getRunes().setRuneBalance(0,0);
			runesManager.setRunesSpawn(this.runesLocation);
			oldRunes = runes;


		}

	}

	/**
	 * Default weapon
	 * @return punch weapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11, "punches", 90);
	}

	/**
	 * Sleep action
	 * @param actor who will sleep
	 * @param map location
	 */
	@Override
	public void sleep(Actor actor, GameMap map) {
		this.setRespawnPoint(map.locationOf(this));
	}
}
