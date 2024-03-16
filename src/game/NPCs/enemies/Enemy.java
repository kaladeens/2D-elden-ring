package game.NPCs.enemies;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.managers.ResetManager;
import game.behaviours.*;
import game.characteristics.Resettable;
import game.gameactions.spawningrelated.DespawnAction;
import game.gamecapabilities.Type;
import game.gamecapabilities.Monetary;
import game.gamecapabilities.Status;
import game.gameactions.combatrelated.AttackAction;
import game.gameitems.Runes;
import game.managers.RunesManager;

import java.util.HashMap;
import java.util.Map;

/**
 * A enemy class for the enemy in the game
 * Created by:
 * @author Sam Abdi
 * Modify by: Issac van der Vliet
 */
public abstract class Enemy extends Actor implements Resettable {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private Type type;
    private final static int FOLLOW_HASH=2;
    private ResetManager resetManager = ResetManager.getInstance();
    private RunesManager runesManager = RunesManager.getInstance();

    /**
     * Constructor:
     * @param name
     * @param displayChar
     * @param hitPoints
     * @param type
     */
    public Enemy(String name, char displayChar, int hitPoints, Type type){
        //constructor
        super(name, displayChar, hitPoints);
        this.type=type;
        this.addCapability(type);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Monetary.DROPS_RUNES);
        this.behaviours.put(998, new WanderBehaviour());
        this.behaviours.put(997,new DespawnBehaviour());
        this.behaviours.put(1,new AttackBehaviour());
        resetManager.registerResettable(this);

    }

    /**
     *
     * @return the runesmanager
     */
    public RunesManager getRunesManager(){return runesManager;}

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * The lone wolf can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return the allowable actions on this enemy for otheractor
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !otherActor.hasCapability(this.getType()) && this.isConscious()){
            if (otherActor.getWeaponInventory().isEmpty()){
                actions.add(new AttackAction(this, direction));
            }
            for (Weapon weapon: otherActor.getWeaponInventory()){
                actions.add(new AttackAction(this,direction,weapon));
                if (weapon.getSkill(otherActor)!=null) {
                    actions.add(weapon.getSkill(otherActor));
                }
                if (weapon.getSkill(this,direction)!=new DoNothingAction()){
                    actions.add(weapon.getSkill(this,direction));
                }
            }
        }
        this.getBehaviours().put(FOLLOW_HASH, new FollowBehaviour(otherActor));
        return actions;
    }

    /**
     * Get enemy type
     * @return enemy type value
     */
    public Type getType() {
        return type;
    }

    /**
     *
     * @param actor
     * @param map
     */
    @Override
    public void reset(Actor actor, GameMap map){
        DespawnAction despawnAction = new DespawnAction();
        despawnAction.execute(this,map);
    }

    /**
     * Get the enemy behaviour
     * @return class enemy behaviour status
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * Get runes after killed enemy
     * @return int of runes
     */
    public abstract Runes getNewRunes();

    /**
     * create new behaviour for enemy
     * @param index behaviour hashmap key
     * @param behaviour behaviours class
     */

    public void addBehaviours(int index, Behaviour behaviour) {
        this.behaviours.put(index,behaviour);
    }

}
