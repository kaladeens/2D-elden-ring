package game.gameactions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.characteristics.Consumable;

/**
 * Use item action class
 * Created by:
 * @author Issac van der Vliet
 */
public class ConsumeAction extends Action {
    private Actor actor;

    private Consumable item;

    /**
     * Constructors:
     * @param actor who use item
     * @param item which will be used
     */
    public ConsumeAction(Actor actor, Consumable item){
        this.actor = actor;
        this.item = item;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map){
        item.consume(actor);
        if (!actor.getItemInventory().contains(item)){
            map.locationOf(actor).removeItem(item.getItem());
        }
        String result = actor + " consumes " + item + " and restores to full health.";
        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " consumes " + item;
    }
}
