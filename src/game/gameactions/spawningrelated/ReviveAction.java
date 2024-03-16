package game.gameactions.spawningrelated;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Revive from one actor
 * Created by:
 * @author Sam Abdi
 */
public class ReviveAction extends Action {
    private Actor revives;

    /**
     * Constructors
     * @param target who will be revived
     */
    public ReviveAction(Actor target){
        this.revives=target;
    }

    /**
     * Revive an actor from actor location
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return revive message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location location=map.locationOf(actor);
        map.removeActor(actor);
        map.addActor(this.revives,location);
        return this.revives + " revives from "+ actor;
    }

    /**
     * Show message in menu
     * @param actor The actor performing the action.
     * @return Menu selection
     */
    @Override
    public String menuDescription(Actor actor){
        return this.revives + " revives from "+ actor;
    }
}
