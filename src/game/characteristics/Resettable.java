package game.characteristics;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A resettable interface
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public interface Resettable {
    void reset(Actor actor, GameMap map);
}
