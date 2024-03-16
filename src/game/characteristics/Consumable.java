package game.characteristics;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * A consumable interface
 * Created by:
 * @author Sam Abdi
 */
public interface Consumable {
    public void consume(Actor actor);
    public Item getItem();
}
