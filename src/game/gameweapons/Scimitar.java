package game.gameweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.characteristics.Purchasable;
import game.characteristics.Sellable;
import game.gameactions.combatrelated.AreaAttack;
import game.gamecapabilities.AttackType;
import game.gamecapabilities.Monetary;
import game.gameactions.moneyrelated.PurchaseAction;
import game.gameactions.moneyrelated.SellAction;

/**
 * Scimitar as weapon that can be used to attack the enemy.
 * Created by:
 * @author Sam Abdi
 * Modified by: Issac van der Vliet
 *
 */
public class Scimitar extends WeaponItem implements Purchasable, Sellable {
    private Action sellAction;
    boolean near =false;

    /**
     * Constructor:
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "Strikes", 88);
        this.addCapability(AttackType.AREA);
        this.addCapability(Monetary.PURCHASABLE);
        this.addCapability(Monetary.SELLABLE);
        this.addCapability(AttackType.SPECIAL);
    }

    /**
     * Special Attack action
     * @param holder weapon holder
     * @return Area Attack
     */
    @Override
    public Action getSkill(Actor holder) {
        return new AreaAttack(this);
    }

    /**
     * Purchase this weapon with trader
     * @param trader
     * @return Purchase action
     */
    public Action getPurchaseAction(Actor trader){
        return new PurchaseAction(trader,this.getPurchasePrice(),this);
    }

    /**
     * Weapon purchase price
     * @return int 600
     */
    public int getPurchasePrice() {
        return 600;
    }

    /**
     * Weapon sell price
     * @return int 100
     */
    public int getSellPrice() {
        return 100;
    }
//TODO
    /**
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void doSelling(Location currentLocation, Actor actor ){
        if (this.hasCapability(Monetary.SELLABLE) && sellAction==null && !this.near ) {
            for (Exit exit : currentLocation.getExits()) {
                if (exit.getDestination().containsAnActor()) {
                    Actor new_actor = exit.getDestination().getActor();
                    if (new_actor.hasCapability(Monetary.BUYS_ITEMS)) {
                        Actor trader=new_actor;
                        this.sellAction=new SellAction(this.getSellPrice(), this);
                        this.addAction(sellAction);
                        this.near=true;
                    }
                }
            }
        }
        this.near=false;
        for (Exit exit : currentLocation.getExits()){
            if (exit.getDestination().containsAnActor()) {
                Actor new_actor = exit.getDestination().getActor();
                if (new_actor.hasCapability(Monetary.BUYS_ITEMS)) {
                    this.near = true;
                }
            }
        }

    }
//TODO lock in and make this method better
    @Override
    public void tick(Location currentLocation, Actor actor) {
        doSelling(currentLocation,actor);
        if (!this.near && this.sellAction!=null){
            this.removeAction(sellAction);
            sellAction=null;
        }
    }
}
