package game.characteristics;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
/**
 * A sleepable interface
 * Created by:
 * @author Sam Abdi
 */
public interface Sleepable {
    void sleep(Actor actor, GameMap map);
}
