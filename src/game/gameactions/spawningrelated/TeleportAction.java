package game.gameactions.spawningrelated;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Teleport on actor to spec location
 * Created by:
 * @author Sam Abdi
 */
public class TeleportAction extends Action {
    private Location telportsTo;
    private String goesTo;

    /**
     * Constructors:
     * @param location where want to transport
     * @param goesTo location name
     */
    public TeleportAction(Location location,String goesTo){
        this.telportsTo=location;
        this.goesTo=goesTo;
    }

    /**
     * Teleport actor to selected fog door
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return actor teleported message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        telportsTo.map().addActor(actor,telportsTo);
        return actor + " teleports to " +goesTo +" map's fog door";
    }

    /**
     * Show message in menu
     * @param actor The actor performing the action.
     * @return Menu selection
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +" can teleport to the map " +goesTo;
    }
}
