package game.gamegrounds.specialground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.gameactions.spawningrelated.TeleportAction;
import game.gamecapabilities.Type;
import game.gamecapabilities.Status;

/**
 * A door for teleport from one place to another place
 * Created by:
 * @author Sam Abdi
 */
public class GoldenFogDoor extends Ground {
    private Location teleportsTo;
    private String goesTo;

    /**
     * Constructor:
     * @param teleportsTo  location what we want
     * @param goesTo name of location
     */
    public GoldenFogDoor(Location teleportsTo,String goesTo){
        super('D');
        this.teleportsTo=teleportsTo;
        this.goesTo=goesTo;
    }
//TODO
    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Action finished message
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        actions.add(new TeleportAction(teleportsTo,goesTo));
        return actions;
    }

    /**
     *
     * @param actor the Actor to check
     * @return
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY) && actor.hasCapability(Status.UNKILLABLE);
    }
}
