package game.gameactions.moneyrelated;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Actor exchange one item to another item
 * Created by:
 * @author Sam Abdi
 */

public class ExchangeAction extends Action {
    private Item exchangeWith;
    private WeaponItem exchangeFor;
    private Item exchangeForItem;
    private boolean item =false;

    /**
     * Constructors:
     * @param exchangeWith Item that actor have
     * @param exchangeFor Weapon that actor want
     */
    public  ExchangeAction(Item exchangeWith, WeaponItem exchangeFor){
        this.exchangeWith=exchangeWith;
        this.exchangeFor=exchangeFor;
    }

    /**
     * Constructors:
     * @param exchangeWith Item that actor have
     * @param exchangeForItem Item that actor want
     */

    public  ExchangeAction(Item exchangeWith, Item exchangeForItem){
        this.exchangeWith=exchangeWith;
        this.exchangeForItem=exchangeForItem;
        item=true;
    }

    /**
     * Return message to display, for exchange message.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getItemInventory().contains(exchangeFor)||actor.getWeaponInventory().contains(exchangeFor)){
            return "already have this item dummy";
        }
        actor.removeItemFromInventory(exchangeWith);
        if (item){
            actor.addItemToInventory(exchangeForItem);
            return actor + " exchanged the "+exchangeWith+" for the "+exchangeForItem;
        }
        actor.addWeaponToInventory(exchangeFor);
        return actor + " exchanged the "+exchangeWith+" for the "+exchangeFor;
    }

    /**
     * Show message in menu selection
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        if (item){
            return actor + " can exchange "+exchangeWith+" for the "+exchangeForItem;
        }
        return actor + " can exchange "+exchangeWith+" for the "+exchangeFor;
    }
}
