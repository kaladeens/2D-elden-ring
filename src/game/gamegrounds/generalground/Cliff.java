package game.gamegrounds.generalground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.gamecapabilities.Status;

/**
 *  A class that represents the cliff.
 *  Created by:
 * @author Sam
 */

public class Cliff extends Ground {
    public Cliff() {
        super('+');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY) && actor.hasCapability(Status.UNKILLABLE);
    }


    //dies if player is on top of maybe could do with allowable
    public void standingOn(Location location){
        if(location.containsAnActor()){
            Actor actor = location.getActor();
            actor.hurt(Integer.MAX_VALUE);
        }
    }
    @Override
    public void tick(Location location) {
        standingOn(location);
    }
}
