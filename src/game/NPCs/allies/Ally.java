package game.NPCs.allies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.combattypes.CombatArcheTypes;
import game.gamecapabilities.Status;
import game.gamecapabilities.Type;

import java.util.HashMap;
import java.util.Map;

/**
 * Ally class from actor
 * Created by:
 * @author Isaac van der Vliet
 * Modify by: Sam Abdi
 */
public class Ally extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private Type type;

    /**
     * Constructors:
     * @param combatType Ally combat type
     */
    public Ally(CombatArcheTypes combatType){
        super("Ally",'A',1);
        combatType.run(this);
        this.behaviours.put(998, new WanderBehaviour());
        this.behaviours.put(1,new AttackBehaviour());
        type = Type.GOOD;
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(type);

    }
//TODO
    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()){
            Action action = behaviour.getAction(this, map);
            if(action != null){
                return action;
            }
        }
        return new DoNothingAction();
    }
    /**
     * Get  type
     * @return  type value
     */
    public Type getType() {
        return type;
    }
}
