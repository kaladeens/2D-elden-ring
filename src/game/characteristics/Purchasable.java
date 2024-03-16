package game.characteristics;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Player;
/**
 * A Purchasable interface
 * Created by:
 * @author Sam Abdi
 */
public interface Purchasable {
    public Action getPurchaseAction(Actor trader);
    public int getPurchasePrice();
}
