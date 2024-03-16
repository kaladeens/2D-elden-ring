package game.gameweapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.characteristics.Purchasable;
import game.characteristics.Sellable;
import game.gameactions.combatrelated.UnsheathAttack;
import game.gamecapabilities.AttackType;
import game.gamecapabilities.Monetary;
import game.gameactions.moneyrelated.PurchaseAction;
import game.gameactions.moneyrelated.SellAction;

/**
 * Uchigatana as weapon that can be used to attack the enemy.
 * Created by:
 * @author Sam Abdi
 * Modified by:
 *
 */
public class Uchigatana extends WeaponItem implements Purchasable, Sellable {
    private Action sellAction;
    boolean near =false;

    /**
     * Constructor:
     */
    public Uchigatana(){
        super("Uchigatana",')',115,"slashes",80);
        this.addCapability(Monetary.PURCHASABLE);
        this.addCapability(Monetary.SELLABLE);
        this.addCapability(AttackType.UNSHEATHE);
        this.addCapability(AttackType.SPECIAL);
    }

    /**
     * Special Attack action
     * @param target target actor
     * @param direction to target
     * @return Unsheath Attack
     */
    @Override
    public Action getSkill(Actor target,String direction){
        return new UnsheathAttack(target,direction,this,60);
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
     * @return int 5000
     */
    public int getPurchasePrice(){
        return 5000;
    }

    /**
     * Weapon sell price
     * @return int 500
     */
    public int getSellPrice(){
        return 500;
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

    @Override
    public void tick(Location currentLocation, Actor actor) {
        doSelling(currentLocation,actor);
        if (!this.near && this.sellAction!=null){
            this.removeAction(sellAction);
            sellAction=null;
        }
    }
}
