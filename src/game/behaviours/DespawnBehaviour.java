package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils.RandomNumberGenerator;
import game.gameactions.spawningrelated.DespawnAction;

/**
 * Despawn Behaviour for actor
 * Created by:
 * @author Sam Abdi
 */
public class DespawnBehaviour implements Behaviour {
    /**
     * After check the random number
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return A despawn action
     */
    public Action getAction(Actor actor, GameMap map) {
        if (RandomNumberGenerator.getBool(10)) {
            return new DespawnAction();
        }
        return null;
    }
}


