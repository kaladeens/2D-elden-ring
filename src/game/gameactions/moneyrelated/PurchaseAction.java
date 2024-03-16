package game.gameactions.moneyrelated;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.managers.RunesManager;

/**
 * Player purchases from trader action
 * Created by:
 * @author Sam Abdi
 * Modify by: Issac van der Vliet
 */
public class PurchaseAction extends Action {

    private WeaponItem weapon;
    private int price;
    private Actor trader;
    private Item item;
    private boolean itemPurchase= false;
    private RunesManager runesManager=RunesManager.getInstance();

    /**
     * Constructors:
     * @param trader the actor who buy weapon
     * @param price the weapon price
     * @param weapon the weapon for purchase
     */
    public PurchaseAction(Actor trader , int price, WeaponItem weapon){
        this.weapon=weapon;
        this.price=price;
        this.trader=trader;

    }
    public PurchaseAction(Actor trader,int price, Item item){
        this.trader=trader;
        this.price=price;
        this.item=item;
        this.itemPurchase=true;
    }


    /**
     * Return message to display, when actor's runes lower than price, will return failure message
     * Otherwise, reduce runes, add weapon to actor and return success message
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message
     */
    public String execute(Actor actor, GameMap map){
        if (runesManager.getPlayerRunesBalance()< price){
            return actor +" is too broke to buy";
        }
        runesManager.minusRunes(price);
        if (itemPurchase){
            actor.addItemToInventory(item);
            return actor + " bought the "+item +" for "+price +" runes from" +trader;
        }
        actor.addWeaponToInventory(weapon);
        return actor + " bought the "+weapon +" for "+price +" runes from" +trader;
    }

    /**
     * Show which weapon that actor can buy from seller
     * @param actor The actor performing the action.
     * @return message
     */
    @Override
    public String menuDescription(Actor actor) {
        if (itemPurchase){
            return actor +" can buy " +item + " from "+trader +" for "+price + " runes";
        }
        return actor +" can buy " +weapon + " from "+trader +" for "+price + " runes";
    }
}
