package game.gameactions.moneyrelated;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.managers.RunesManager;

/**
 * Sell weapon to seller
 * Created by:
 * @author Sam Abdi
 * Modify by Issac van der Vliet
 */
public class SellAction extends Action {



    private int price;
    private WeaponItem weapon;
    private Item item;
    private boolean itemSell= false;
    private RunesManager runesManager=RunesManager.getInstance();

    /**
     * Constructor
     * @param price weapon selling price
     * @param weapon weapon for sale
     */
    public SellAction(int price,WeaponItem weapon){
        this.price=price;
        this.weapon=weapon;
    }
    public SellAction(int price, Item item){
        this.price=price;
        this.item=item;
        this.itemSell=true;
    }

    /**
     * Run selling action
     * Will add correct runes to actor and remove the weapon from actor
     * Show the sold message
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return sold message
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        runesManager.addRunes(price);
        if (itemSell){
            actor.removeItemFromInventory(item);
            return actor + " sold the " +item +" for "+price +" Runes";
        }
        actor.removeWeaponFromInventory(weapon);

        return actor + " sold the " +weapon +" for "+price +" Runes";
    }

    /**
     * Show what price that user's sellable weapon
     * @param actor The actor performing the action.
     * @return price message
     */
    @Override
    public String menuDescription(Actor actor) {
        if (itemSell){
            return actor + " can sell " + item +" to the trader for " +price;

        }
        return actor + " can sell " + weapon +" to the trader for " +price;
    }
}
